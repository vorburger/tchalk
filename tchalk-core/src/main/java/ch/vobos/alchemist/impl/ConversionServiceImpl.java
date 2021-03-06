package ch.vobos.alchemist.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import ch.vobos.alchemist.ConversionService;
import ch.vobos.alchemist.Converter;
import ch.vobos.alchemist.ConverterWithContext;

public class ConversionServiceImpl implements ConversionService, ConverterRegistry {

    // This is *NOT* multi thread safe!
    // TODO Is HashMap a good choice for this? copy on write thingie?
    protected final Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converters = new HashMap<Class<?>, Map<Class<?>, Converter<?, ?>>>();

    @Override
    public <InT, OutT> void register(Converter<InT, OutT> converter, Class<InT> inType, Class<OutT> outType) throws IllegalStateException {
	Map<Class<?>, Converter<?, ?>> innerMap = converters.computeIfAbsent(inType, x -> {
	    return new HashMap<Class<?>, Converter<?, ?>>();
	});
	if (innerMap.containsKey(outType))
	    throw new IllegalStateException("Converter from " + inType.getClass() + " to " + outType.getClass() + " already registered");
	innerMap.put(outType, converter);
    }

    @Override
    @SuppressWarnings({ "null", "unused" })
    // TODO "unused" is wrong - Eclipse bug??
    public <InT, OutT, ContextT> Optional<OutT> convertToOptional(InT in, Class<OutT> outType, Optional<ContextT> context) {
	if (outType.equals(in.getClass()))
	    return optionalOf(in);

	Map<Class<?>, Converter<?, ?>> innerMap = converters.get(in.getClass());
	if (innerMap != null) {
	    @SuppressWarnings("unchecked")
	    Converter<InT, OutT> converter = (Converter<InT, OutT>) innerMap.get(outType);
	    OutT out;
	    if (converter instanceof ConverterWithContext<?, ?, ?>) {
		@SuppressWarnings("unchecked")
		ConverterWithContext<InT, OutT, ContextT> converterWithContext = (ConverterWithContext<InT, OutT, ContextT>) converter;
		out = converterWithContext.convert(in, context);
	    } else {
		out = converter.convert(in);
	    }
	    return optionalOf(out);
	}

	// This needs to happen last, AFTER all other Converter have been tried.
	if (String.class.equals(outType))
	    return optionalOfNullable(in.toString());

	// It MAY be tempting to add something here which,
	// without processing annotations (which I do want to add)
	// auto scans classes for a constructor or static method with matching signature..
	// .. but let's not do that - automagic often turns out to be more trouble than worth;
	// think about a java.lang.Integer and it's getInteger(), which doesn't do what you expect!

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
