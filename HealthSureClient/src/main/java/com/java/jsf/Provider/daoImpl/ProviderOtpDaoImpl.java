package com.java.jsf.Provider.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.model.ProviderOtp;
import com.java.jsf.Util.SessionHelper;

public class ProviderOtpDaoImpl implements ProviderOtpDao{
	SessionFactory sf;
	Session session;

	@Override
	public void saveOtp(ProviderOtp otp) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.save(otp);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}finally {
			session.close();
		}

	}
	@Override
	public ProviderOtp getLatestOtpByProviderId(String providerId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();

		try {
            Query query = session.createQuery(
                "FROM ProviderOtp WHERE providerId = :providerId ORDER BY generatedTime DESC");
            query.setParameter("providerId", providerId);
            query.setMaxResults(1);
            return (ProviderOtp) query.uniqueResult();
        } finally {
            session.close();
        }
	}
	@Override
	public List<ProviderOtp> getAllOtpsByProviderId(String providerId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();

		 try {
	            Query query = session.createQuery(
	                "FROM ProviderOtp WHERE providerId = :providerId ORDER BY generatedTime DESC");
	            query.setParameter("providerId", providerId);
	            query.setMaxResults(1);
	            return query.list();
	        } finally {
	            session.close();
	        }
	}
	@Override
	public void deleteOtp(int otpId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			ProviderOtp otp = (ProviderOtp) session.get(ProviderOtp.class,otpId);
			if (otp != null) {
				session.delete(otp);
			}
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	@Override
	public boolean validateOtp(String providerId, String otpCode) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();

		return false;
	}

}