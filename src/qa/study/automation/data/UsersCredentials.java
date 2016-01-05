package qa.study.automation.data;

public class UsersCredentials {
	public static User getTestUser() {
		return User.createUser("some@mail.com", "qwerty");
	}
}
