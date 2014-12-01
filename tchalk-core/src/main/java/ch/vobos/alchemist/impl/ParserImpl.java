package ch.vobos.alchemist.impl;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.alchemist.Converter;
import ch.vobos.alchemist.Parser;

public class ParserImpl<T> implements Parser<T> {

    protected final Converter<String, T> converter;

    public ParserImpl(Converter<String, T> converter) {
	super();
	this.converter = converter;
    }

    @Override
    public @NonNull T convert(@NonNull String in) {
	return converter.convert(in);
    }

}
