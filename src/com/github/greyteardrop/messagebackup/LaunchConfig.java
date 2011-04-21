package com.github.greyteardrop.messagebackup;

import com.beust.jcommander.Parameter;

public class LaunchConfig {
	@Parameter(names = { "-p", "--port" }, description = "Local port number")
	private int port = 8080;

	@Parameter(names = { "-f", "--file" }, description = "Database file for message storage")
	private String dbName = "messagebackup";

	public int getPort() {
		return port;
	}

	public String getDbName() {
		return dbName;
	}
}
