package com.java.jsf.Provider.model;

import java.util.Date;

public class ProviderOtp {
	 private int otpId;
	    private String providerId;
	    private String otpCode;
	    private Date createdAt;
	    private Date expiresAt;
	    private boolean isVerified;
	    
		public int getOtpId() {
			return otpId;
		}
		public void setOtpId(int otpId) {
			this.otpId = otpId;
		}
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
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getExpiresAt() {
			return expiresAt;
		}
		public void setExpiresAt(Date expiresAt) {
			this.expiresAt = expiresAt;
		}
		public boolean isVerified() {
			return isVerified;
		}
		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}
		
		public ProviderOtp(int otpId, String providerId, String otpCode, Date createdAt, Date expiresAt,
				boolean isVerified) {
			super();
			this.otpId = otpId;
			this.providerId = providerId;
			this.otpCode = otpCode;
			this.createdAt = createdAt;
			this.expiresAt = expiresAt;
			this.isVerified = isVerified;
		}
		
		public ProviderOtp() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
		
}