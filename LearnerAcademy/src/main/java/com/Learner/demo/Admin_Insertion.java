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

@WebServlet("/saveAdminDetials")
public class Admin_Insertion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String mobno = req.getParameter("mobno");
		String address = req.getParameter("address");
		
		
		AdminDetails td = new AdminDetails(name,email,gender,dob,new BigDecimal(mobno),address);
		
		SessionFactory fact = HibernateUtil.getSessionFactory();
		
		Session session = fact.openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(td);
		
		tx.commit();
		
		session.close();
		
		out.println("Admin Data Inserted Successfully");
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
}
