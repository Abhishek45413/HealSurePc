package com.java.jsf.Provider.dao;

import com.java.jsf.Provider.model.Provider;

public interface ProviderDao {
	void addProvider(Provider provider) throws Exception;
    Provider login(String email, String encryptedPassword) throws Exception;
    boolean emailExists(String email) throws Exception;
    boolean phoneExists(String phone) throws Exception;
    boolean zipcodeExists(String zipcode) throws Exception;



}