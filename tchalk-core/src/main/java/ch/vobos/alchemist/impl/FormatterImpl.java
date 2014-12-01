package ch.vobos.alchemist.impl;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.alchemist.Converter;
import ch.vobos.alchemist.Formatter;

public class FormatterImpl<T> implements Formatter<T> {

    protected final Converter<T, String> converter;

    public FormatterImpl(Converter<T, String> converter) {
	super();
	this.converter = converter;
    }

    @Override
    public @NonNull String convert(@NonNull T in) {
	return converter.convert(in);
    }

}
