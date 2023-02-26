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

@WebServlet("/saveClassDetails")
public class Class_Insertion extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String tid = req.getParameter("t_ID");
		String sid = req.getParameter("s_ID");
		String std = req.getParameter("std");
		String time = req.getParameter("time");
		String aid = req.getParameter("a_ID");
		
		ClassDetails cd = new ClassDetails(new BigDecimal(tid),new BigDecimal(sid),std,time,new BigDecimal(aid));
		
		SessionFactory fact = HibernateUtil.getSessionFactory();
		
		Session session = fact.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.save(cd);
		
		tx.commit();
		session.close();
		
		out.println("Class Data Inserted Successfully");	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
	
	
	
}
