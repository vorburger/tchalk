package ch.vobos.alchemist;

import java.util.Optional;

public interface ConversionService {

    @SuppressWarnings("null")
    default <InT, OutT> OutT convert(InT in, Class<OutT> outType) throws IllegalStateException {
	Optional<OutT> r = this.convertOptional(in, outType);
	if (!r.isPresent())
	    // Do NOT Stringify in for the message here.. we don't know if we can for sure, and are already in an error situation, enough:
	    throw new IllegalStateException("ConversionService cannot convert in Object (a " + in.getClass() + ") to requested type: "
		    + outType.toString());
	return r.get();
    };

    <InT, OutT> Optional<OutT> convertOptional(InT in, Class<OutT> outType);

}
