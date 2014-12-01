package ch.vobos.alchemist;

import java.util.Optional;

@SuppressWarnings("null")
public interface ConversionService {

    default <InT, OutT> OutT convert(InT in, Class<OutT> outType) throws IllegalStateException {
	return convert(in, outType, Optional.empty());
    };

    default <InT, OutT, ContextT> OutT convert(InT in, Class<OutT> outType, Optional<ContextT> context) throws IllegalStateException {
	Optional<OutT> r = convertToOptional(in, outType, context);
	if (!r.isPresent())
	    // Do NOT Stringify in for the message here.. we don't know if we can for sure, and are already in an error situation, enough:
	    throw new IllegalStateException("ConversionService cannot convert in Object (a " + in.getClass() + ") to requested type: "
		    + outType.toString());
	return r.get();
    }

    default <InT, OutT> Optional<OutT> convertToOptional(InT in, Class<OutT> outType) {
	return convertToOptional(in, outType, Optional.empty());
    }

    <InT, OutT, ContextT> Optional<OutT> convertToOptional(InT in, Class<OutT> outType, Optional<ContextT> context);

}
