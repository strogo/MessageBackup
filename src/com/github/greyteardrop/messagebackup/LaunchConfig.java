package com.github.greyteardrop.messagebackup;

import com.beust.jcommander.Parameter;

public class LaunchConfig {
	@Parameter(names = { "-p", "--port" }, description = "Local port number")
	private int port = 8080;

	@Parameter(names = { "-f", "--file" }, description = "Database file for message storage")
	private String dbName = "messagebackup";

	@Parameter(names = "--app-id", description = "vkontakte application id")
	private String appId = "2220649";

	public int getPort() {
		return port;
	}

	public String getDbName() {
		return dbName;
	}

	public String getAppId() {
		return appId;
	}
}
