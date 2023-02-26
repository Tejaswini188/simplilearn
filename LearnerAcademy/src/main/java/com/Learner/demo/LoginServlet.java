package com.Learner.demo;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		
		SessionFactory fact = HibernateUtil.getSessionFactory();
		
//		Session session = fact.openSession();
		
		if(username.equalsIgnoreCase("teju") && password.equals("1234")) {
			resp.sendRedirect("homepage.html");
		}
		else {
			out.println("Error in the credentials");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
	
	
	
	
	
	
	
	
}
