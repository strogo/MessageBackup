package com.github.greyteardrop.messagebackup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenSaverServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(TokenSaverServlet.class);
	private Config configObject;
	private EntityManager entityManager;

	@Override
	public void init() throws ServletException {
		super.init();
		configObject = (Config) getServletContext().getAttribute("config");
		entityManager = (EntityManager) getServletContext().getAttribute("entityManager");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accessToken = req.getParameter("access_token");
		String expiresIn = req.getParameter("expires_in");
		String userId = req.getParameter("user_id");
		if (accessToken == null) {
			PrintWriter writer = resp.getWriter();
			writer.write("<!DOCTYPE HTML>\n<html>\n<head>\n\t<meta charset=\"UTF-8\">\n\t<title></title>\n</head>\n<body>\n\t<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js\"></script>\n\t<script>\n\t\t$(document).ready(function() {\n\t\t\t$.ajax('http://127.0.0.1:8080/return?' + window.location.hash.substr(1));\n\t\t})\n\t</script>\n</body>\n</html>");
		} else {
			Date expirationTime = new Date(System.currentTimeMillis() + Integer.valueOf(expiresIn) * 1000);
			configObject.setToken(accessToken);
			configObject.setTokenExpirationTime(expirationTime);
			entityManager.getTransaction().begin();
			entityManager.merge(configObject);
			entityManager.getTransaction().commit();
		}
	}
}
