package ch.vobos.tchalk.core.domain.simpletypes;



/**
 * UserEmail | TwitterHandle | PhoneNumber...
 */
@SuppressWarnings("serial")
public abstract class UserPublicID extends StringValueType {

	protected UserPublicID(String string) {
		super(string);
	}

	public static class EmailUserID extends UserPublicID {
		public EmailUserID(String emailAddress) {
			super(emailAddress);
		}
		@Override
		void accept(UserPublicIDVisitor visitor) {
			visitor.visit(this);
		}
	}

	public static class TwitterUserID extends UserPublicID {
		public TwitterUserID(String twitterHandle) {
			super(twitterHandle);
		}
		@Override
		void accept(UserPublicIDVisitor visitor) {
			visitor.visit(this);
		}
	}

	public static class PhoneNumberUserID extends UserPublicID {
		public PhoneNumberUserID(String phoneNumber) {
			super(phoneNumber);
		}
		@Override
		void accept(UserPublicIDVisitor visitor) {
			visitor.visit(this);
		}
	}

	abstract void accept(UserPublicIDVisitor visitor);
	
	public static interface UserPublicIDVisitor {
		void visit(EmailUserID emailUserID);
		void visit(TwitterUserID twitterUserID);
		void visit(PhoneNumberUserID phoneNumberUserID);
	}
}
