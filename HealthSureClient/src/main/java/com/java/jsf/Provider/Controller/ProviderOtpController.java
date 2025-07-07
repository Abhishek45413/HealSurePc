package com.java.jsf.Provider.Controller;

import java.util.Date;
import java.util.List;

import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.daoImpl.ProviderOtpDaoImpl;
import com.java.jsf.Provider.model.ProviderOtp;
import com.java.jsf.Provider.model.ProviderType;

public class ProviderOtpController {
	  private ProviderOtp providerOtp = new ProviderOtp();
	    private List<ProviderOtp> providerOtpList;

	    private ProviderOtpDao providerOtpDao = new ProviderOtpDaoImpl();

	 // Generate and store a new OTP for a provider
	    public String generateOtp(String providerId,ProviderType providerType, String otpCode, Date expiryTime) throws Exception {
	        providerOtp.setProviderId(providerId);
	        providerOtp.setProviderType(providerType);
	        providerOtp.setOtpCode(otpCode);
	        providerOtp.setExpiresAt(expiryTime);
	        providerOtpDao.saveOtp(providerOtp);
	        providerOtp = new ProviderOtp(); // reset after saving
	        return "otp_generated";  // must match a navigation case
	    }

	 // Get all OTPs
	    public List<ProviderOtp> getOtpList() throws Exception {
	        if (providerOtpList == null) {
	            providerOtpList = providerOtpDao.getAllOtpsByProviderId(null);
	        }
	        return providerOtpList;
	    }

	    // Verify OTP for a provider
	    public boolean verifyOtp(String providerId, String inputOtp) throws Exception {
	        ProviderOtp storedOtp = providerOtpDao.getLatestOtpByProviderId(providerId);
	        if (storedOtp != null && storedOtp.getOtpCode().equals(inputOtp)
	                && storedOtp.getExpiresAt().after(new Date(System.currentTimeMillis()))) {
	            return true;
	        }
	        return false;
	    }

	    public ProviderOtp getOtp() {
	        return providerOtp;
	    }

	    public void setOtp(ProviderOtp otp) {
	        this.providerOtp = otp;
	    }

}
