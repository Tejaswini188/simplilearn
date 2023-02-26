package com.Learner.demo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

	private static SessionFactory sessionFactory;
//	
//	static {
//		try {
//			
//			StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml").build();
//			
//			Metadata mtdata = new MetadataSources(reg).getMetadataBuilder().build();
//			
//			sessionFactory = mtdata.getSessionFactoryBuilder().build();
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}}
		
		public static SessionFactory getSessionFactory() {
//			static {
			try {
				
				StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml").build();
				System.out.print(reg);
				
				Metadata mtdata = new MetadataSources(reg).getMetadataBuilder().build();
				System.out.print(mtdata);
				sessionFactory = mtdata.getSessionFactoryBuilder().build();
				System.out.print(sessionFactory);
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//}
			return sessionFactory;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

