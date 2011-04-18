package com.github.greyteardrop.messagebackup;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenSaverServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accessToken = req.getParameter("access_token");
		if (accessToken == null) {
			PrintWriter writer = resp.getWriter();
			writer.write("<!DOCTYPE HTML>\n<html>\n<head>\n\t<meta charset=\"UTF-8\">\n\t<title></title>\n</head>\n<body>\n\t<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js\"></script>\n\t<script>\n\t\t$(document).ready(function() {\n\t\t\t$.ajax('http://127.0.0.1:8080/return?' + window.location.hash.substr(1));\n\t\t})\n\t</script>\n</body>\n</html>");
		} else {
			log(accessToken);
		}
	}
}
