package jp.co.internous.mysession.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable {
	private static final long serialVersionUID = 695383970234960954L;
	
	private String userName;
	private String password;
	
	public String getUserName () {
		return userName;
	}
	public void setUserName (String userName ) {
		this.userName = userName;
	}
		
	public String getPassword () {
		return password;
	}
	public void setPassword (String password) {
		this.password= password;
	}

}
