package bean;

public class UserParams {
	String username, password;
	
	String getUsername () {
		return this.username;
	}
	String getPassword () {
		return this.password;
	}
	
	void setUsername (String username) {
		this.username = username;
	}
	void setPassword (String password) {
		this.password = password;
	}
}
