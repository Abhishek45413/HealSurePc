package com.java.jsf.Provider.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.jsf.Provider.dao.DoctorsDao;
import com.java.jsf.Provider.model.Doctors;
import com.java.jsf.Util.SessionHelper;

public class DoctorsDaoImpl implements DoctorsDao{
	SessionFactory sf;
	Session session;

	@Override
	public void addDoctors(Doctors doctors) throws Exception {
		Session session = (Session) SessionHelper.getSessionFactory();
        session.beginTransaction();
        session.save(doctors);
        session.getTransaction().commit();
        session.close();
		}

	@Override
	public Doctors searchByDoctorsId(int doctorId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
        Query query = session.getNamedQuery("Doctor.findById");
        query.setParameter("doctorId", doctorId);
        Doctors doctors = (Doctors) query.uniqueResult();
        session.close();
        return doctors;
	}

	@Override
	public List<Doctors> getAllDoctors() throws Exception {
		 SessionFactory sf = SessionHelper.getSessionFactory();
	        Query query = session.getNamedQuery("Doctor.findAll");
	        List<Doctors> doctors = query.list();
	        session.close();
	        return doctors;
	}

	@Override
	public List<Doctors> searchByProviderId(String providerId) throws Exception {
		SessionFactory sf = SessionHelper.getSessionFactory();
        Query query = session.getNamedQuery("Doctor.findByProviderId");
        query.setParameter("providerId", providerId);
        List<Doctors> doctors = query.list();
        session.close();
        return doctors;
	}

	@Override
	public void updateDoctors(Doctors doctor) throws Exception {
		 SessionFactory sf = SessionHelper.getSessionFactory();
	        session.beginTransaction();
	        session.update(doctor);
	        session.getTransaction().commit();
	        session.close();

	}

	@Override
	public void deleteDoctors(int doctorId) throws Exception {
		 SessionFactory sf = SessionHelper.getSessionFactory();
	        Doctors doctor = (Doctors) session.get(Doctors.class, doctorId);
	        session.beginTransaction();
	        session.delete(doctor);
	        session.getTransaction().commit();
	        session.close();

	}

}
