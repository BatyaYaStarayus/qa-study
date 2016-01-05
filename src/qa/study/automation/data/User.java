package qa.study.automation.data;

public class User {
	private String loginname;
	private String password;
	
	private User(String login, String password){
		this.loginname = login;
		this.password = password;
	}
	
	//-----------------
	public static User createUser(String login, String password){
		return new User(login, password);
	}
	
	public String getLoginName() {
		return loginname;
	}
	
	public String getPassword() {
		return password;
	}
}
