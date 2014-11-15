package ch.vobos.alchemist;

import java.util.Optional;

public interface ConversionService {

    @SuppressWarnings("null")
    default <T> T convert(Object in, Class<T> outType) {
	Optional<T> r = this.convertOptional(in, outType);
	if (!r.isPresent())
	    // Do NOT Stringify in for the message here.. we don't know if we can for sure, and are already in an error situation, enough:
	    throw new IllegalStateException("ConversionService cannot convert Object in to requested type: " + outType.toString());
	return r.get();
    };

    <T> Optional<T> convertOptional(Object in, Class<T> outType);

}
