package com.java.jsf.Provider.Controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.java.jsf.Provider.dao.ProviderDao;
import com.java.jsf.Provider.dao.ProviderOtpDao;
import com.java.jsf.Provider.daoImpl.ProviderDaoImpl;
import com.java.jsf.Provider.daoImpl.ProviderOtpDaoImpl;
import com.java.jsf.Provider.model.LoginStatus;
import com.java.jsf.Provider.model.Provider;
import com.java.jsf.Provider.model.ProviderOtp;
import com.java.jsf.Util.EncryptPassword;



public class ProviderController implements Serializable{

	 Timestamp now = new Timestamp(System.currentTimeMillis());
	    Timestamp expiry = new Timestamp(now.getTime() + 2 * 60 * 1000); // 2 minutes
	
    private static final long serialVersionUID = 1L;
    
	private Provider provider = new Provider();
	private String otpCode;
	
	private ProviderDao providerDao;
    private ProviderOtpDao providerOtpDao;
    
    public ProviderController() {
        // Manually initialize DAO implementations
        this.providerDao = new ProviderDaoImpl();
        this.providerOtpDao = new ProviderOtpDaoImpl();
    }


    // ✅ Register a new provider with password confirmation
    public String register() throws Exception {
    	System.out.println("controller callesd______________________________________");
        System.out.println("Registering provider...");
        boolean a=false;

        if (provider == null) {
            System.out.println("Provider object is null!");
            return null;
        } 

        if (providerDao == null || providerOtpDao == null) {
            System.out.println("DAO is not initialized!");
            return null;
        }
        
     // ✅ Validate Provider Name: Only letters and spaces
        if (!provider.getProviderName().matches("^[A-Za-z\\s]+$")) {
        	System.out.println(1);
            FacesContext.getCurrentInstance().addMessage("providerName",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must contain only alphabets.", null));
            a=true;
        }

        // ✅ Validate Hospital Name: Optional but must be non-numeric if provided
        String hospital = provider.getHospitalName();
        if (hospital != null && !hospital.trim().isEmpty() && !hospital.matches("^[A-Za-z\\s]+$")) {
        	System.out.println(12);
        	FacesContext.getCurrentInstance().addMessage("hospitalName",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hospital name must contain only alphabets.", null));
        	a=true;
        }

        // ✅ Email Format Validation
        if (!provider.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}$")) {
        	System.out.println(13);
        	FacesContext.getCurrentInstance().addMessage("email",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email format.", null));
            a=true;
        }

        // ✅ Email Uniqueness Check
        if (providerDao.emailExists(provider.getEmail())) {
        	System.out.println(14);
        	FacesContext.getCurrentInstance().addMessage("email",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email already exists.", null));
        	a=true;
        }

        // ✅ Phone Number Format Validation
        if (!provider.getTelephone().matches("^[0-9]{10}$")) {
        	System.out.println(15);
        	FacesContext.getCurrentInstance().addMessage("telephone",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number must be exactly 10 digits.", null));
        	a=true;
        }

        // ✅ Phone Number Uniqueness Check
        if (providerDao.phoneExists(provider.getTelephone())) {
        	System.out.println(16);
        	FacesContext.getCurrentInstance().addMessage("telephone",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number already exists.",null));
        	a=true;
        }

        // ✅ Zipcode Format Validation
        if (!provider.getZipcode().matches("^[0-9]{6}$")) {
        	System.out.println(17);
        	FacesContext.getCurrentInstance().addMessage("zipcode",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zipcode must be exactly 6 digits.", null));
        	a=true;
        }

        // ✅ Zipcode Uniqueness Check
        if (providerDao.zipcodeExists(provider.getZipcode())) {
        	System.out.println(18);
        	FacesContext.getCurrentInstance().addMessage("zipcode",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zipcode already exists.", null));
        	a=true;
        }
        if(a) {
        	return null;
        }
        provider.setStatus(LoginStatus.PENDING);

        // Save provider to database
        providerDao.addProvider(provider);
        System.out.println("Provider added successfully.");

        // Generate OTP
        String otp = providerOtpDao.generateOtp(provider.getEmail());

        // Simulate sending OTP (replace with actual email/SMS logic)
        System.out.println("OTP sent to: " + provider.getEmail() + " | OTP: " + otp);

        // Store email in session for OTP verification
        FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().put("providerEmail", provider.getEmail());

        // Redirect to OTP verification page
        return "VerifyOtp.jsf?faces-redirect=true";
    }
    
 // ✅ Submit OTP
    public String verifyOtp(String email, String inputOtp) throws Exception {
        ProviderOtp latestOtp = providerOtpDao.getLatestOtp(email);
        System.out.println(email+inputOtp);

        if (latestOtp == null || !latestOtp.getOtpCode().equals(inputOtp)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid OTP.", null));
            return null;
        }

        if (latestOtp.getExpiresAt().equals(expiry)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "OTP has expired.", null));
            return null;
        }

        latestOtp.setVerified(true);
        providerOtpDao.updateOtp(latestOtp);

        System.out.println("OTP verified. Provider approved.");
        return "otpSuccess.jsf?faces-redirect=true";
    }

    // ✅ Resend OTP
    public String resendOtp() throws Exception {
        String email = (String) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("providerEmail");

        String newOtp = providerOtpDao.generateOtp(email);
        System.out.println("New OTP sent to: " + email + " | OTP: " + newOtp);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "A new OTP has been sent.", null));
        return null;
    }


    // ✅ Login existing provider
    public String login() throws Exception {
        System.out.println("Login method triggered");

        if (provider == null) {
            System.out.println("Provider object is null");
            return null;
        }

        System.out.println("Email entered: " + provider.getEmail());
        System.out.println("Password entered (plain): " + provider.getPassword());

        if (provider.getEmail() == null || provider.getPassword() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email and password are required.", null));
            return null;
        }

        String encryptedPassword = EncryptPassword.getCode(provider.getPassword());
        System.out.println("Encrypted password: " + encryptedPassword);

        Provider dbProvider = providerDao.login(provider.getEmail(), encryptedPassword);

        if (dbProvider != null) {
            if (dbProvider.getStatus() == LoginStatus.PENDING) {
                System.out.println("Login successful.");
                return "loginSuccess?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Your account is not approved yet.", null));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email or password.", null));
            return null;
        }
    }

  
    // ✅ Getters and Setters
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }


	public ProviderOtpDao getProviderOtpDao() {
		return providerOtpDao;
	}

	public void setProviderOtpDao(ProviderOtpDao providerOtpDao) {
		this.providerOtpDao = providerOtpDao;
	}
	
	public String getOtpCode() {
		return otpCode;
	}


	 public void setOtpCode(String otpCode) {
		 this.otpCode = otpCode;
	 }
}
