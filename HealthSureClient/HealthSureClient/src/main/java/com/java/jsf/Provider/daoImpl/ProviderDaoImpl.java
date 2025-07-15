package com.java.jsf.Provider.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.jsf.Provider.dao.ProviderDao;
import com.java.jsf.Provider.model.Provider;
 import com.java.jsf.Util.SessionHelper;

public class ProviderDaoImpl implements ProviderDao{

	SessionFactory sf;
	Session session;

	@Override
	public void addProvider(Provider provider) throws Exception {
	    sf = SessionHelper.getSessionFactory();
	    session = sf.openSession();
	    Transaction trans = null;

	    try {
	        trans = session.beginTransaction();

	        // Generate and set unique provider ID
	        String newId = generateProviderId();
	        provider.setProviderId(newId);

	        session.save(provider);
	        trans.commit();
	        System.out.println("Provider saved with ID: " + newId);
	    } catch (Exception e) {
	        if (trans != null) {
	            trans.rollback();
	        }
	        throw e;
	    } finally {
	        session.close();
	    }
	}
	
	public String generateProviderId() {
	    Session session = null;
	    try {
	        sf = SessionHelper.getSessionFactory();
	        session = sf.openSession();

	        Query query = session.getNamedQuery("ProviderId"); 
	        String latestId = (String) query.uniqueResult();

	        if (latestId == null) {
	            return "PROV001";
	        } else {
	            int num = Integer.parseInt(latestId.substring(4));
	            return "PROV" + String.format("%03d", num + 1);
	        }
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
	@Override
	public Provider login(String email, String encryptedPassword) throws Exception {
	    SessionFactory sf = SessionHelper.getSessionFactory();
	    Session session = sf.openSession();
	    Provider provider = null;

	    try {
	        Query query = session.createQuery(
	            "FROM Provider WHERE email = :email AND password = :password");
	        query.setParameter("email", email);
	        query.setParameter("password", encryptedPassword);

	        provider = (Provider) query.uniqueResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        session.close();
	    }

	    return provider;
	}

	@Override
	public boolean emailExists(String email) throws Exception {
		Session session = SessionHelper.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("SELECT COUNT(p) FROM Provider p WHERE p.email = :email");
			query.setParameter("email", email);
			Long count = (Long)query.uniqueResult();
			return count > 0;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean phoneExists(String phone) throws Exception {
		 Session session = SessionHelper.getSessionFactory().openSession();
	     
	        try {
	            Query query = session.createQuery(
	                "SELECT COUNT(p) FROM Provider p WHERE p.telephone = :phone");
	            query.setParameter("phone", phone);
	            Long count = (Long) query.uniqueResult();
	            return count > 0;
	        } finally {
	            session.close();
	        }

	}

	@Override
	public boolean zipcodeExists(String zipcode) throws Exception {
	    Session session = SessionHelper.getSessionFactory().openSession();

        try {
            Query query = session.createQuery(
                "SELECT COUNT(p) FROM Provider p WHERE p.zipcode = :zipcode");
            query.setParameter("zipcode", zipcode);
            Long count = (Long) query.uniqueResult();
            return count > 0;
        } finally {
            session.close();
        }
    }
}
