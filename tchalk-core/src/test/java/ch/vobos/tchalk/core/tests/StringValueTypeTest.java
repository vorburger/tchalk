package ch.vobos.tchalk.core.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ch.vobos.tchalk.core.domain.simpletypes.StringValueType;

@SuppressWarnings("serial")
public class StringValueTypeTest {

	@Test
	public void testEquals1() {
		StringValueType svt1 = new StringValueType("hello") {
		};
		StringValueType svt2 = new StringValueType("hello") {
		};
		// They are NOT the same type (but two DIFFERENT anonymous inner types)
		assertThat(svt1, not(equalTo(svt2)));
	}

	@Test
	public void testEquals2() {
		StringValueType svt1 = new StringValueTypeExtension("hello");
		StringValueType svt2 = new StringValueTypeExtension("hello");
		assertThat(svt1, equalTo(svt2));
	}

	private final class StringValueTypeExtension extends StringValueType {
		private StringValueTypeExtension(String string) {
			super(string);
		}
	}


}
