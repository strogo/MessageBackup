package com.github.greyteardrop.messagebackup;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Config {
	@Id
	private int id;
	private String token;
	private Date tokenExpirationTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Date tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
}
