package com.java.jsf.Provider.Controller;

import java.util.List;

import com.java.jsf.Provider.dao.DoctorsDao;
import com.java.jsf.Provider.daoImpl.DoctorsDaoImpl;
import com.java.jsf.Provider.model.Doctors;

public class DoctorsController {
	 private Doctors doctor = new Doctors();
	    private List<Doctors> doctorList;

	    private DoctorsDao doctorDao = new DoctorsDaoImpl();

	    // Add/register new doctor
	    public String addDoctor() throws Exception {
	        doctorDao.addDoctors(doctor);
	        doctor = new Doctors(); // reset form
	        return "doctor_success"; // define in faces-config.xml
	    }

	    // Get all doctors
	    public List<Doctors> getDoctorList() throws Exception {
	        if (doctorList == null) {
	            doctorList = doctorDao.getAllDoctors();
	        }
	        return doctorList;
	    }

	 // Get doctors by provider
	    public List<Doctors> getDoctorsByProvider(String providerId) throws Exception {
	        return doctorDao.searchByProviderId(providerId);
	    }

	 // Update doctor details
	    public String updateDoctor() throws Exception {
	        doctorDao.updateDoctors(doctor);
	        return "doctor_updated"; // define navigation
	    }

	    // Delete doctor by ID
	    public String deleteDoctor(int doctorId) throws Exception {
	        doctorDao.deleteDoctors(doctorId);
	        return "doctor_deleted"; // redirect or reload
	    }

		public Doctors getDoctor() {
			return doctor;
		}

		public void setDoctor(Doctors doctor) {
			this.doctor = doctor;
		}


}
