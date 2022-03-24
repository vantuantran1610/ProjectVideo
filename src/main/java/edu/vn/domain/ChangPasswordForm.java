package edu.vn.domain;

public class ChangPasswordForm {
	private String username;
	private String password;
	private String confirmPassword;
	private String currentPassowrd;

	public ChangPasswordForm() {
	}

	public ChangPasswordForm(String username, String password, String confirmPassword, String currentPassowrd) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.currentPassowrd = currentPassowrd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassowrd() {
		return currentPassowrd;
	}

	public void setCurrentPassowrd(String currentPassowrd) {
		this.currentPassowrd = currentPassowrd;
	}

}
