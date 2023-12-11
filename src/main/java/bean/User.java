package bean;

public class User {
	private String username, password;
	private boolean authed, isAdmin;
	
	User (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername () {
		return this.username;
	}
	String getPassword () {
		return this.password;
	}
	public boolean getAuthed () {
		return this.authed;
	}
	public boolean getAdmin () {
		return this.isAdmin;
	}
	
	void setUsername (String username) {
		this.username = username;
	}
	void setPassword (String password) {
		this.password = password;
	}
	void setAuthed (boolean auth) {
		this.authed = auth;
	}
	void setAdmin (boolean admin) {
		this.isAdmin = admin;
	}
	
}
