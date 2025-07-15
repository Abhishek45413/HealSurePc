package com.java.jsf.Provider.Controller;

import java.sql.SQLException;

import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.daoImpl.ProviderDaoImpl;
import com.java.jsf.Provider.daoImpl.ProviderOtpDaoImpl;
import com.java.jsf.Provider.model.ProviderOtp;

public class ProviderOtpController {
	private String providerId;
    private String otpCode;
    private String message;
    private ProviderOtp latestOtp;

    private ProviderOtpDao providerOtpDao = new ProviderOtpDaoImpl();

    private ProviderOtp otp = new ProviderOtp();

    // ✅ Method to Insert and Send OTP
//    public String sendOtp() {
//        try {
//            String generatedOtp = new ProviderDaoImpl().generateProviderId();
//            otp.setProviderId(providerId);
//            otp.setOtpCode(generatedOtp);
//
//            message = providerOtpDao.insertOtp(otp);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            message = "Error sending OTP: " + e.getMessage();
//        }
//        return "OtpSent";  // Navigation outcome (page name without extension)
//    }

    // ✅ Method to Verify OTP
    public String verifyOtp() {
        try {
            message = providerOtpDao.verifyOtp(providerId, otpCode);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message = "Error verifying OTP: " + e.getMessage();
        }
        return "OtpVerified";  // Navigation outcome
    }

    // ✅ Method to Get Latest OTP for Provider
    public String getLatestOtp() {
        try {
            latestOtp = providerOtpDao.getLatestOtp(providerId);
            if (latestOtp != null) {
                message = "Latest OTP: " + latestOtp.getOtpCode();
            } else {
                message = "No OTP found.";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message = "Error fetching OTP: " + e.getMessage();
        }
        return "LatestOtp";  // Navigation outcome
    }

    // ✅ Method to Mark OTP as Verified Manually
    public String markOtpAsVerified(int otpId) {
        try {
            message = providerOtpDao.markOtpAsVerified(otpId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message = "Error marking OTP: " + e.getMessage();
        }
        return "OtpMarked";  // Navigation outcome
    }

    // ===== Getters and Setters =====

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getMessage() {
        return message;
    }

    public ProviderOtp getOtp() {
        return otp;
    }

    public void setOtp(ProviderOtp otp) {
        this.otp = otp;
    }

    public ProviderOtp getLatestOtpDetails() {
        return latestOtp;
    }
}
