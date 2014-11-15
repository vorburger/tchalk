package ch.vobos.alchemist;

import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class ConversionServiceImpl implements ConversionService, ConverterRegistry {

    @Override
    public <InT, OutT> void register(Converter<InT, OutT> converter, Class<InT> inType, Class<OutT> outType) {

    }

    @Override
    public <T> Optional<T> convertOptional(Object in, Class<@NonNull T> outType) {
	if (outType.equals(in.getClass()))
	    return optionalOf(in);
	if (String.class.equals(outType))
	    return optionalOfNullable(in.toString());
	return emptyOptional();
    }

    // TODO move these helpers somewhere else..

    @SuppressWarnings("unchecked")
    protected <T> Optional<T> optionalOf(Object in) {
	return (@NonNull Optional<T>) Optional.of(in);
    }

    @SuppressWarnings("unchecked")
    protected <T> Optional<T> optionalOfNullable(@Nullable Object in) {
	return (@NonNull Optional<T>) Optional.ofNullable(in);
    }

    @SuppressWarnings("null")
    protected <T> Optional<T> emptyOptional() {
	return Optional.empty();
    }

}
