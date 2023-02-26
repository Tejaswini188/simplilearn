package com.Learner.demo;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebServlet("/saveSubject")
public class Subject_Insertion extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String std = req.getParameter("std");
		String subcode = req.getParameter("subcode");
		
		SubjectDetails sd = new SubjectDetails(name,std,new BigDecimal(subcode));
		
		SessionFactory fact = HibernateUtil.getSessionFactory();
		
		Session session = fact.openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(sd);
		tx.commit();
		session.close();

		out.println("Subject Data Inserted Successfully");
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
	
	
	
	
	
}
