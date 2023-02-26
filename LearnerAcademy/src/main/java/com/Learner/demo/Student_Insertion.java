package com.Learner.demo;
import java.io.IOException;
import java.math.BigDecimal;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebServlet("/save")
public class Student_Insertion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		
		String sname = req.getParameter("sname");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String doj = req.getParameter("doj");
		String clas = req.getParameter("class");
		String fees = req.getParameter("fees");
		String address = req.getParameter("address");
		
		StudentDetails sd = new StudentDetails(sname, fname, lname, gender, dob, doj, address, clas, new BigDecimal(fees));
		
		SessionFactory fact =  HibernateUtil.getSessionFactory();
		
		Session session = fact.openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(sd);
		
		tx.commit();
		session.close();
		out.println("Student Data Inserted Successfully");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
	
	
	
	
}
