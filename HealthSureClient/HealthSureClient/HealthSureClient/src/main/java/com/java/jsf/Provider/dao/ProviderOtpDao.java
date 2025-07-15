package com.java.jsf.Provider.dao;

import java.sql.SQLException;

import com.java.jsf.Provider.model.ProviderOtp;


public interface ProviderOtpDao {

	 // ✅ Generate and insert OTP by email directly
    String generateOtp(String providerEmail) throws ClassNotFoundException, SQLException;

    // ✅ Insert a manually created OTP record
   // String insertOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException;

    // ✅ Verify if OTP is correct for a given provider
    String verifyOtp(String providerEmail, String otpCode) throws ClassNotFoundException, SQLException;

    // ✅ Get the most recent OTP for a provider (for verification or resend)
    ProviderOtp getLatestOtp(String providerEmail) throws ClassNotFoundException, SQLException;

    // ✅ Mark an OTP record as verified
    String markOtpAsVerified(int otpId) throws ClassNotFoundException, SQLException;

    // ✅ Update an existing OTP record (e.g., after verification)
    String updateOtp(ProviderOtp otp) throws ClassNotFoundException, SQLException;
}
