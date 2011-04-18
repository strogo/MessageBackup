package com.github.greyteardrop.messagebackup;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Launcher {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler context = new ServletContextHandler();
		context.addServlet(AuthRequestServlet.class, "/");
		context.addServlet(TokenSaverServlet.class, "/return");
		server.setHandler(context);

		server.start();
		server.join();
	}
}
