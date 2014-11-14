package ch.vobos.tchalk.core.domain.simpletypes;

/**
 * JSON Web Token.
 */
@SuppressWarnings("serial")
public class Token extends StringValueType {

	public Token(String jsonWebToken) {
		super(jsonWebToken);
	}
	
}
