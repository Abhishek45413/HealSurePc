package com.java.jsf.Provider.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.jsf.Provider.dao.ProviderDao;
import com.java.jsf.Provider.model.Provider;
import com.java.jsf.Util.EncryptPassword;
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

	        // Encrypt password
	        if (provider.getPassword() != null && !provider.getPassword().isEmpty()) {
	            String encryptedPassword = EncryptPassword.getCode(provider.getPassword());
	            provider.setPassword(encryptedPassword);
	        }

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
	@Override
	public Provider searchByProviderId(String providerId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
		session = sf.openSession();

		 try {
			return (Provider) session.get(Provider.class, providerId);
		} finally {
			session.close();
		}
	}


	@Override
	public void updateProvider(Provider provider) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
		Transaction trans = null;
		session = sf.openSession();

		try {
			trans = session.beginTransaction();
			session.update(provider);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}
		finally {
			session.close();
			}
	}

	@Override
	public void deleteProvider(String providerId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
		Transaction trans = null;
		session = sf.openSession();

		try {
			trans = session.beginTransaction();
			Provider provider = (Provider) session.get(Provider.class, providerId);
			if (provider != null) {
				session.delete(provider);
			}
			trans.commit();
		} catch (Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	
	public String generateProviderId() {
	    Session session = null;
	    try {
	        sf = SessionHelper.getSessionFactory();
	        session = sf.openSession();

	        Query query = session.getNamedQuery("ProviderId"); // This should be defined in your Provider.hbm.xml
	        String latestId = (String) query.uniqueResult();

	        if (latestId == null) {
	            return "P001";
	        } else {
	            int num = Integer.parseInt(latestId.substring(1));
	            return "P" + String.format("%03d", num + 1);
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
}
