package ch.vobos.alchemist.tests;

import java.util.Date;

import org.junit.Test;

import ch.vobos.alchemist.impl.ConversionServiceImpl;

@SuppressWarnings("null")
public class ConverterRegistryTest {

    @Test(expected = IllegalStateException.class)
    public void doubleRegistration() {
	ConversionServiceImpl s = new ConversionServiceImpl();
	s.registerParser(in -> new Date(), Date.class);
	s.registerParser(in -> new Date(), Date.class);
    }

}
