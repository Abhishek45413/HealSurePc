package com.java.jsf.Provider.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.model.Provider;
import com.java.jsf.Provider.model.ProviderOtp;
import com.java.jsf.Util.MailSend;
import com.java.jsf.Util.SessionHelper;

public class ProviderOtpDaoImpl implements ProviderOtpDao{
	Session session;
	SessionHelper sf;
	  // Timestamp
    Timestamp now = new Timestamp(System.currentTimeMillis());
    Timestamp expiry = new Timestamp(now.getTime() + 2 * 60 * 1000); // 2 minutes

//	@Override
//	public String insertOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException {
//		 session = SessionHelper.getSessionFactory().openSession();
//	        Transaction tx = session.beginTransaction();
//
//	        // Set OTP expiry 10 minutes from now
//	        otp.setExpiresAt(LocalDateTime.now().plusMinutes(10));
//            otp.setVerified(false);
//            System.out.println("____________"+otp.getOtpCode());
//	        session.save(otp);
//	        tx.commit();
//
//	        // Send OTP Email (optional)
//	        String subject = "Your OTP for HealthSure Registration";
//	        String body = "Your OTP is: " + otp.getOtpCode() + ". It is valid for 10 minutes.";
//	        MailSend.sendInfo(otp.getProviderId(), subject, body);
//
//	        return "OTP inserted and email sent successfully.";
//	}

	@Override
	public String verifyOtp(String providerId, String otpCode) throws ClassNotFoundException, SQLException {
		session = SessionHelper.getSessionFactory().openSession();

        String hql = "FROM ProviderOtp WHERE providerId = :providerId AND otpCode = :otpCode AND isVerified = false";
        Query query = session.createQuery(hql);
        query.setParameter("providerId", providerId);
        query.setParameter("otpCode", otpCode);

        ProviderOtp otp = (ProviderOtp) query.uniqueResult();

        if (otp != null) {
            // Check expiry
            if (otp.getExpiresAt().equals(expiry)) {
                session.close();
                return "OTP expired. Please request a new one.";
            }

            Transaction tx = session.beginTransaction();
            otp.setVerified(true);
            session.update(otp);
            tx.commit();
            session.close();

            return "OTP verified successfully.";
        } else {
            session.close();
            return "Invalid OTP or already verified.";
        }
	}

	@Override
	public ProviderOtp getLatestOtp(String providerId) throws ClassNotFoundException, SQLException {
		 session = SessionHelper.getSessionFactory().openSession();

	        String hql = "FROM ProviderOtp WHERE providerId = :providerId ORDER BY createdAt DESC";
	        Query query = session.createQuery(hql);
	        query.setParameter("providerId", providerId);
	        query.setMaxResults(1);

	        ProviderOtp latestOtp = (ProviderOtp) query.uniqueResult();
	        session.close();

	        return latestOtp;
	}

	@Override
	public String markOtpAsVerified(int otpId) throws ClassNotFoundException, SQLException {
		session = SessionHelper.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "UPDATE ProviderOtp SET isVerified = true WHERE otpId = :otpId";
        int updated = session.createQuery(hql).setParameter("otpId", otpId).executeUpdate();

        tx.commit();
        session.close();

        if (updated > 0) {
            return "OTP verified.";
        } else {
            return "OTP not found or already verified.";
	}
	
	}

	@Override
	public String generateOtp(String providerId) throws ClassNotFoundException, SQLException {

		    // Generate a 6-digit OTP
		    int code = new Random().nextInt(900000) + 100000;
		    String otpCode = String.valueOf(code);

		    // Hibernate session and transaction
		    Session session = SessionHelper.getSessionFactory().openSession();
		    Transaction tx = null;

		    try {
		        tx = session.beginTransaction();

			  

			    // Create OTP entity
			    ProviderOtp otp = new ProviderOtp();
			    Provider p = new Provider();
			    otp.setProviderId(p.getProviderId());
			    System.out.println("provider is is .........");
			    System.out.println(p.getProviderId());
			    //System.out.println();
			    System.out.println("otp generated is.............");
			    otp.setOtpCode(otpCode);
			    System.out.println(otpCode);
			    otp.setCreatedAt(now);
			    otp.setExpiresAt(expiry);
			    otp.setVerified(false);

		        session.save(otp);
		        tx.commit();
		        

			    //  Send OTP via email
			    String subject = "Your OTP Code";
			    String body = "Hi, your OTP code is " + otpCode + ". It is valid for 2 minutes.";
			    MailSend.sendInfo(providerId, subject, body);
			    
		    } catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }

		    
		    return otpCode;
		}

	@Override
	public String updateOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException {
		Session session = SessionHelper.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			tx = session.beginTransaction();
			session.save(otp);
			tx.commit();
			return "OTP save successfully.";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "OTP update failed."; 
		}
	}
}
	