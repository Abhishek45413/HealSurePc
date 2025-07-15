package com.java.jsf.Provider.dao;

import java.util.List;

import com.java.jsf.Provider.model.Doctors;

public interface DoctorsDao {
    void addDoctors(Doctors doctors) throws Exception;
    Doctors searchByDoctorsId(int doctorId) throws Exception;
    List<Doctors> getAllDoctors() throws Exception;
    List<Doctors> searchByProviderId(String providerId) throws Exception;
    void updateDoctors(Doctors doctor) throws Exception;
    void deleteDoctors(int doctorId) throws Exception;

}
