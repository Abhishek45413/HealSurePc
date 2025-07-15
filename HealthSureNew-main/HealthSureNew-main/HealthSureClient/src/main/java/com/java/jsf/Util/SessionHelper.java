package com.java.jsf.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class SessionHelper {
	

	    public static SessionFactory getSessionFactory() {
	        return new AnnotationConfiguration().configure().buildSessionFactory();
	    }

		public static Session openSession() {
			// TODO Auto-generated method stub
			return null;
		}

}
