package com.java.jsf.Provider.model;

import java.sql.Timestamp;

public class Accounts {
	    private int accountId;
	    private String providerId;
	    private String bankName;
	    private String ifscCode;
	    private String accountNumber;
	    private Timestamp createdAt;

		public int getAccountId() {
			return accountId;
		}
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		public String getProviderId() {
			return providerId;
		}
		public void setProviderId(String providerId) {
			this.providerId = providerId;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getIfscCode() {
			return ifscCode;
		}
		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Accounts(int accountId, String providerId, String bankName, String ifscCode, String accountNumber,
				Timestamp createdAt) {
			super();
			this.accountId = accountId;
			this.providerId = providerId;
			this.bankName = bankName;
			this.ifscCode = ifscCode;
			this.accountNumber = accountNumber;
			this.createdAt = createdAt;
		}

		public Accounts() {
			super();
			// TODO Auto-generated constructor stub
		}
}