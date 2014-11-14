package ch.vobos.tchalk.core.domain.simpletypes;

/**
 * Anything, like site URL if public or UUID if private.
 */
@SuppressWarnings("serial")
public class Channel extends StringValueType {

	public Channel(String channel) {
		super(channel);
	}

}
