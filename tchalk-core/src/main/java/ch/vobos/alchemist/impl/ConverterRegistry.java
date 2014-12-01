package ch.vobos.alchemist.impl;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.alchemist.Converter;

@SuppressWarnings("null")
public interface ConverterRegistry {

    <InT, OutT> void register(Converter<InT, OutT> converter, Class<InT> inType, Class<OutT> outType) throws IllegalStateException;

    default <OutT> void registerParser(Converter<String, @NonNull OutT> converter, Class<@NonNull OutT> outType) {
	register(converter, String.class, outType);
    }

    default <InT> void registerFormatter(Converter<@NonNull InT, String> converter, Class<@NonNull InT> inType) {
	register(converter, inType, String.class);
    }

}
