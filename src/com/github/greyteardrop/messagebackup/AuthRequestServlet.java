package com.github.greyteardrop.messagebackup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthRequestServlet extends HttpServlet {
	private static final String AUTH_URL = "http://api.vkontakte.ru/oauth/authorize?client_id=%s&scope=%s&redirect_uri=%s&response_type=token";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String authUrl = String.format(
			AUTH_URL,
			2220649,
			"messages",
			"http://127.0.0.1:8080/return"
		);

		resp.sendRedirect(authUrl);
	}
}
