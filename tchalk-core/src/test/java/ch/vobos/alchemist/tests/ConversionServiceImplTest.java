package ch.vobos.alchemist.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.jdt.annotation.Nullable;
import org.junit.Test;

import ch.vobos.alchemist.ConversionService;
import ch.vobos.alchemist.ConversionServiceImpl;

@SuppressWarnings("null")
public class ConversionServiceImplTest {

    @Test
    public void identicalType() {
	ConversionService cs = new ConversionServiceImpl();
	String s1 = "hello, world";
	String s2 = cs.convert(s1, String.class);
	assertTrue(s1 == s2);
	// cs.register(converter, inType, outType)
    }

    @Test
    public void toStringConversion() {
	ConversionService cs = new ConversionServiceImpl();
	String n = cs.convert(123, String.class);
	assertEquals("123", n);
    }

    @Test
    public void toStringNull() {
	ConversionService cs = new ConversionServiceImpl();
	Optional<String> o = cs.convertOptional(new Object() {
	    @Override
	    public @Nullable String toString() {
		return null;
	    }
	}, String.class);
	assertFalse(o.isPresent());
    }

}
