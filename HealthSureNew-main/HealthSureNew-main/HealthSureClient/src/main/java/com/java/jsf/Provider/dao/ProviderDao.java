package com.java.jsf.Provider.dao;

import com.java.jsf.Provider.model.Provider;

public interface ProviderDao {
	void addProvider(Provider provider) throws Exception;
    Provider searchByProviderId(String providerId) throws Exception;
    void updateProvider(Provider provider) throws Exception;
    void deleteProvider(String providerId) throws Exception;
    Provider login(String email, String encryptedPassword) throws Exception;

}