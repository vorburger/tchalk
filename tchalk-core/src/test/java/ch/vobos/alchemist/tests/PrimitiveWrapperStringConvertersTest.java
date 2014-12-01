package ch.vobos.alchemist.tests;

import org.junit.Test;

import ch.vobos.alchemist.impl.ConversionServiceImpl;
import ch.vobos.alchemist.impl.PrimitiveWrapperStringConverters;

@SuppressWarnings("null")
public class PrimitiveWrapperStringConvertersTest {

    @Test
    public void stringToInteger() {
	ConversionServiceImpl cs = new ConversionServiceImpl();
	new PrimitiveWrapperStringConverters().registerMeOn(cs);
	cs.convert("123", Integer.class);
    }

}
