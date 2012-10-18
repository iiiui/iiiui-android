package iiiui.android.model;

import org.json.JSONObject;

public class UserEntity {

	private boolean success;
	private UserErrors errors;
	private User user;
	private Object token;
	
	public Object getToken() {
		return token;
	}
	public void setToken(Object token) {
		this.token = token;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserErrors getErrors() {
		return errors;
	}
	public void setErrors(UserErrors errors) {
		this.errors = errors;
	}
	
}
