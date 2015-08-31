package com.hand.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hand.filter.MyInterceptor;

public class HibernateUtil {

	public static Session getSession(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession(new MyInterceptor());
		return session;
	}
}
