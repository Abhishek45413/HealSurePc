package com.java.jsf.Provider.model;

import java.util.Date;

public class Provider {
	 private String providerId;
	    private String providerName;
	    private String hospitalName;
	    private String email;
	    private String telephone;
	    private String address;
	    private String city;
	    private String state;
	    private String zipcode;
	    private String password;
	    private LoginStatus status; // Should be "PENDING", "APPROVED", or "REJECTED"
	    private Date createdAt;


		public String getProviderId() {
			return providerId;
		}
		public void setProviderId(String providerId) {
			this.providerId = providerId;
		}
		public String getProviderName() {
			return providerName;
		}
		public void setProviderName(String providerName) {
			this.providerName = providerName;
		}
		public String getHospitalName() {
			return hospitalName;
		}
		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public LoginStatus getStatus() {
			return status;
		}
		public void setStatus(LoginStatus status) {
			this.status = status;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Provider(String providerId, String providerName, String hospitalName, String email,String telephone, String address,
				String city, String state, String zipcode, String password, LoginStatus status, Date createdAt) {
			super();
			this.providerId = providerId;
			this.providerName = providerName;
			this.hospitalName = hospitalName;
			this.email = email;
			this.telephone = telephone;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zipcode = zipcode;
			this.password = password;
			this.status = status;
			this.createdAt = createdAt;
		}

		public Provider() {
			super();
			// TODO Auto-generated constructor stub
		}
		

}
