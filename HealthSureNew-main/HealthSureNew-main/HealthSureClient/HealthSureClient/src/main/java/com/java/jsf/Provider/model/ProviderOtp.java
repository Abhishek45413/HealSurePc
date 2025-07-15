package com.java.jsf.Provider.model;


import java.sql.Timestamp;

public class ProviderOtp {
	 private int otpId;
	    private String providerId;
	    private String otpCode;
	    private Timestamp createdAt;
	    private Timestamp expiresAt;
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
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp now) {
			this.createdAt = now;
		}
		public Timestamp getExpiresAt() {
			return expiresAt;
		}
		public void setExpiresAt(Timestamp localDateTime) {
			this.expiresAt = localDateTime;
		}
		public boolean isVerified() {
			return isVerified;
		}
		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}
		
		public ProviderOtp(int otpId, String providerId, String otpCode, Timestamp createdAt, Timestamp expiresAt,
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