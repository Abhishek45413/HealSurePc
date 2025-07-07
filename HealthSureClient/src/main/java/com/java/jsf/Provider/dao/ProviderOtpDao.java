package com.java.jsf.Provider.dao;

import java.util.List;

import com.java.jsf.Provider.model.ProviderOtp;

public interface ProviderOtpDao {
	void saveOtp(ProviderOtp otp) throws Exception;
    ProviderOtp getLatestOtpByProviderId(String providerId) throws Exception;
    List<ProviderOtp> getAllOtpsByProviderId(String providerId) throws Exception;
    void deleteOtp(int otpId) throws Exception;
    boolean validateOtp(String providerId, String otpCode) throws Exception;

}
