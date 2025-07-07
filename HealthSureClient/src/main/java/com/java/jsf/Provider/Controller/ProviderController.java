package com.java.jsf.Provider.Controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.java.jsf.Provider.dao.ProviderDao;
import com.java.jsf.Provider.daoImpl.ProviderDaoImpl;
import com.java.jsf.Provider.model.LoginStatus;
import com.java.jsf.Provider.model.Provider;
import com.java.jsf.Util.EncryptPassword;

public class ProviderController {

    private Provider provider = new Provider();
    private String confirmPassword;

    private ProviderDao providerDao = new ProviderDaoImpl();

    // ✅ Register a new provider with password confirmation
    public String register() throws Exception {
        System.out.println("Registering provider...");

        if (provider == null) {
            System.out.println("Provider object is null!");
            return null;
        }

        // ✅ Password match check
        if (provider.getPassword() == null || confirmPassword == null ||
            !provider.getPassword().equals(confirmPassword)) {

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match.", null));
            System.out.println("Passwords do not match.");
            return null;  // Stop registration
        }

        System.out.println("Passwords match. Proceeding...");

        provider.setStatus(LoginStatus.PENDING);

        if (providerDao == null) {
            System.out.println("ProviderDao is null!");
            return null;
        }

        providerDao.addProvider(provider);
        System.out.println("Provider added successfully.");
        return "Success";
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

    // ✅ Get provider by ID
    public Provider getProviderById(String providerId) throws Exception {
        System.out.println("Fetching provider by ID: " + providerId);

        if (providerDao == null) {
            System.out.println("ProviderDao is null!");
            return null;
        }

        Provider result = providerDao.searchByProviderId(providerId);
        System.out.println("Fetched provider: " + result);
        return result;
    }

    // ✅ Update status
    public String updateStatus(String providerId, LoginStatus status) throws Exception {
        System.out.println("Updating status for provider ID: " + providerId + " to " + status);

        if (providerDao == null) {
            System.out.println("ProviderDao is null!");
            return "error";
        }

        Provider p = providerDao.searchByProviderId(providerId);

        if (p != null) {
            p.setStatus(status);
            providerDao.updateProvider(p);
            System.out.println("Provider status updated.");
        } else {
            System.out.println("Provider not found.");
        }

        return "dashboard";
    }

    // ✅ Delete provider
    public String delete(String providerId) throws Exception {
        System.out.println("Deleting provider with ID: " + providerId);

        if (providerDao == null) {
            System.out.println("ProviderDao is null!");
            return "error";
        }

        providerDao.deleteProvider(providerId);
        System.out.println("Provider deleted.");
        return "provider_list";
    }

    // ✅ Getters and Setters
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
