package ch.vobos.tchalk.core.domain.simpletypes;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("serial")
public abstract class StringValueType implements java.io.Serializable, Comparable<String>, CharSequence {

	private String string;

	protected StringValueType(String string) {
//		if (string == null)
//			throw new IllegalArgumentException("string == null");
		this.string = string;
	}
	
	@Override
	public int length() {
		return string.length();
	}

	@Override
	public char charAt(int index) {
		return string.charAt(index);
	}

	@Override
	@SuppressWarnings("null")
	public CharSequence subSequence(int start, int end) {
		return string.subSequence(start, end);
	}

	@Override
	public int compareTo(String o) {
		return string.compareTo(o);
	}

	@Override
	public int hashCode() {
		return string.hashCode();
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((string == null) ? 0 : string.hashCode());
//		return result;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		return obj != null && getClass() == obj.getClass() && string.equals(obj.toString());
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		StringValueType other = (StringValueType) obj;
//		if (string == null) {
//			if (other.string != null)
//				return false;
//		} else if (!string.equals(other.string))
//			return false;
//		return true;
	}

	@Override
	@SuppressWarnings("null")
	public String toString() {
		return string.toString();
	}	
	
}
