package iiiui.android.model;

import java.lang.reflect.Array;

import org.json.JSONObject;

public class UserErrors extends JSONObject{

	private Array email;

	public Array getEmail() {
		return email;
	}

	public void setEmail(Array email) {
		this.email = email;
	}
	
}
