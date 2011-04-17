package com.github.greyteardrop.messagebackup;

import org.eclipse.jetty.server.Server;

public class Launcher {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.start();
		server.join();
	}
}
