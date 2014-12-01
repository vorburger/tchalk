package ch.vobos.alchemist.impl;

import ch.vobos.alchemist.Parser;

@SuppressWarnings("null")
// TODO raise Eclipse bug.. @SuppressWarnings("null") should, ideally, not be needed above
public class PrimitiveWrapperStringConverters {

    // Use simple protected methods here,
    // and not protected initialized fields here or even worse private static final;
    // that way subclasses COULD customize them.

    protected Parser<Integer> integerParser() {
	return s -> {
	    return new Integer(s);
	};
    };

    protected Parser<Double> doubleParser() {
	return Double::new;
    };

    // TODO Later make this un-necessary and use an annotation on Parser above
    public void registerMeOn(ConverterRegistry registry) {
	registry.registerParser(integerParser(), Integer.class);
	registry.registerParser(doubleParser(), Double.class);
    }
}
