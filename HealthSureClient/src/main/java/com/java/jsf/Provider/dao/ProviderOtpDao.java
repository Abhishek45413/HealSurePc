package com.java.jsf.Provider.dao;

import java.sql.SQLException;

import com.java.jsf.Provider.model.ProviderOtp;


public interface ProviderOtpDao {

	 // ✅ Insert a new OTP record
    public String insertOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException;

    // ✅ Verify if OTP is correct for a given provider
    public String verifyOtp(String providerId, String otpCode) throws ClassNotFoundException, SQLException;

    // ✅  Get the latest OTP for a provider (for resend or display)
    public ProviderOtp getLatestOtpByProviderId(String providerId) throws ClassNotFoundException, SQLException;

    // ✅  Mark OTP as verified
    public String markOtpAsVerified(int otpId) throws ClassNotFoundException, SQLException;

}
