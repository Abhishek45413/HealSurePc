package com.java.jsf.Provider.daoImpl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.model.ProviderOtp;
import com.java.jsf.Util.MailSend;
import com.java.jsf.Util.SessionHelper;

public class ProviderOtpDaoImpl implements ProviderOtpDao{
	Session session;
	SessionHelper sf;
	
	// Generate 6-digit OTP (100000 to 999999)
	public String generateOtp() {
        Random r = new Random(System.currentTimeMillis());
        int otp = 100000 + r.nextInt(900000);
        return String.valueOf(otp);
    }
	

	@Override
	public String insertOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException {
		 session = SessionHelper.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();

	        // Set OTP expiry 10 minutes from now
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MINUTE, 10);
	        otp.setExpiresAt(calendar.getTime());
	        otp.setVerified(false);

	        session.save(otp);
	        tx.commit();

	        // Send OTP Email (optional)
	        String subject = "Your OTP for HealthSure Registration";
	        String body = "Your OTP is: " + otp.getOtpCode() + ". It is valid for 10 minutes.";
	        MailSend.sendInfo(otp.getProviderId(), subject, body);

	        return "OTP inserted and email sent successfully.";
	}

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
            if (otp.getExpiresAt().before(new java.util.Date())) {
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
	public ProviderOtp getLatestOtpByProviderId(String providerId) throws ClassNotFoundException, SQLException {
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
            return "OTP marked as verified.";
        } else {
            return "OTP not found or already verified.";
	}
	
	}	
}
	