package com.itacademy.jd2.pk.hop.service.jndi;

import java.io.Serializable;

public class SmtpProperties implements Serializable {

	private String from;
	private String host;
	private String port;
	private String user;
	private String password;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SmtpProperties [from=" + from + ", host=" + host + ", port=" + port + ", user=" + user + ", password="
				+ password + "]";
	}

}
