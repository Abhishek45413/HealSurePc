package com.java.jsf.Provider.Controller;

import java.io.Serializable;
import java.sql.Timestamp;

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

public class ProviderController implements Serializable {

    private static final long serialVersionUID = 1L;

    Timestamp now = new Timestamp(System.currentTimeMillis());
    Timestamp expiry = new Timestamp(now.getTime() + 2 * 60 * 1000); // 2 minutes

    private Provider provider = new Provider();
    private String providerId;
	private String otpCode;

    private ProviderDao providerDao;
    private ProviderOtpDao providerOtpDao;

    public ProviderController() {
        this.providerDao = new ProviderDaoImpl();
        this.providerOtpDao = new ProviderOtpDaoImpl();
    }

    // ✅ Register a new provider with password confirmation
    public String register() throws Exception {
        System.out.println("controller called______________________________________");
        System.out.println("Registering provider...");
        boolean a = false;

        if (provider == null) {
            System.out.println("Provider object is null!");
            return null;
        }

        if (providerDao == null || providerOtpDao == null) {
            System.out.println("DAO is not initialized!");
            return null;
        }

        // ✅ Validations
        if (!provider.getProviderName().matches("^[A-Za-z\\s]+$")) {
            FacesContext.getCurrentInstance().addMessage("providerName",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must contain only alphabets.", null));
            a = true;
        }

        String hospital = provider.getHospitalName();
        if (hospital != null && !hospital.trim().isEmpty() && !hospital.matches("^[A-Za-z\\s]+$")) {
            FacesContext.getCurrentInstance().addMessage("hospitalName",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hospital name must contain only alphabets.", null));
            a = true;
        }

        if (!provider.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}$")) {
            FacesContext.getCurrentInstance().addMessage("email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email format.", null));
            a = true;
        }

        if (providerDao.emailExists(provider.getEmail())) {
            FacesContext.getCurrentInstance().addMessage("email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email already exists.", null));
            a = true;
        }

        if (!provider.getTelephone().matches("^[0-9]{10}$")) {
            FacesContext.getCurrentInstance().addMessage("telephone",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number must be exactly 10 digits.", null));
            a = true;
        }

        if (providerDao.phoneExists(provider.getTelephone())) {
            FacesContext.getCurrentInstance().addMessage("telephone",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number already exists.", null));
            a = true;
        }

        if (!provider.getZipcode().matches("^[0-9]{6}$")) {
            FacesContext.getCurrentInstance().addMessage("zipcode",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zipcode must be exactly 6 digits.", null));
            a = true;
        }

        if (providerDao.zipcodeExists(provider.getZipcode())) {
            FacesContext.getCurrentInstance().addMessage("zipcode",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zipcode already exists.", null));
            a = true;
        }

        if (a) return null;

        provider.setStatus(LoginStatus.PENDING);

        providerDao.addProvider(provider);
        System.out.println("Provider added successfully.");

        String otp = providerOtpDao.generateOtp(provider.getProviderId(), provider.getEmail());
        System.out.println("OTP sent to: " + provider.getEmail() + " | OTP: " + otp);

        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("providerEmail", provider.getEmail());

        return "VerifyOtp.jsf?faces-redirect=true";
    }

    // ✅ Submit OTP
    public String verifyOtp() {
        try {
            System.out.println("Verifying OTP...");
            System.out.println("Provider ID: " + providerId);
            System.out.println("Entered OTP: " + otpCode);

            String message = providerOtpDao.verifyOtp(providerId, otpCode);

            if ("OTP verified successfully.".equals(message)) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, message));
                return "otpSuccess.jsf?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error verifying OTP.", null));
            return null;
        }
    }



    // ✅ Resend OTP
    public String resendOtp() throws Exception {
        String email = (String) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("providerEmail");

        String newOtp = providerOtpDao.generateOtp(provider.getProviderId(), provider.getEmail());
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

    // ✅ Update password method (NEW)
    public String updatePassword() {
        System.out.println("Password update triggered");

        // Validate inputs
        if (provider.getEmail() == null || provider.getEmail().trim().isEmpty()
                || provider.getNewPassword() == null || provider.getNewPassword().trim().isEmpty()
                || provider.getConfirmPassword() == null || provider.getConfirmPassword().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "All fields are required.", null));
            return null;
        }

        // Check if passwords match
        if (!provider.getNewPassword().equals(provider.getConfirmPassword())) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match.", null));
            return null;
        }

        // Encrypt new password
        String encryptedPassword = EncryptPassword.getCode(provider.getNewPassword());

        // Update password in database
        boolean updated = providerDao.updatePasswordByEmail(
            provider.getEmail().trim(),
            encryptedPassword
        );

        if (updated) {
            System.out.println("Password updated successfully for: " + provider.getEmail());

            // ✅ Clear provider fields for safety
            provider.setNewPassword(null);
            provider.setConfirmPassword(null);
            provider.setPassword(null);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Password updated successfully. Please login.", null));

            // ✅ Redirect to login page
            return "login?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "No provider found with this email.", null));
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
    
    public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
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

    public void setProviderDaoImpl(ProviderDao providerDaoImpl) {
        this.providerDao = providerDaoImpl;
    }

    public void setProviderOtpDaoImpl(ProviderOtpDao providerOtpDaoImpl) {
        this.providerOtpDao = providerOtpDaoImpl;
    }
}
