package com.Learner.demo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


@WebServlet("/fetch")
public class FetchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/Html");
		
		SessionFactory fact = HibernateUtil.getSessionFactory();
		
		Session session = fact.openSession();
		
		//To begin the transaction we need to derive the table name 
		
		List<ClassDetails> list = session.createQuery("from ClassDetails").list(); //Here inside the query we give class name not table name
		
		for(ClassDetails ep:list) {
			out.println("<br><br>---------------------------------------------------------------------------------"+"<br>");
			
			out.println("<b>Class ID: </b>"+ep.getID()+"<br>");
			out.println("<b>Grade : </b>"+ep.getStd()+"<br>");
			out.println("<b>Student ID: </b>"+ep.getS_ID() +"<br>");
			out.println("<b>Teacher ID : </b>"+ep.getT_ID()+"<br>");
			
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
}
