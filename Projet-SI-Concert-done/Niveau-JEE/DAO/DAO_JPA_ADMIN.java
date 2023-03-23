package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import data.Admin;
import data.Concert;

public class DAO_JPA_ADMIN {

	
	EntityManagerFactory emf ;
	EntityManager em; 
	EntityTransaction trans ;
	
	public DAO_JPA_ADMIN(){
		this.emf= Persistence.createEntityManagerFactory("gestionManager");
		this.em = emf.createEntityManager();
		this.trans = null;
	}

	public void create(String login, String password,String nom ) {
		Admin admin = new Admin();
		admin.setLogin(login);
		admin.setMotdepasse(password);
		admin.setNom(nom);
		 try {
			 this.trans =this.em.getTransaction();
			 this.trans.begin();
			 this.em.persist(admin);
			 this.trans.commit();
		 }catch(Exception e) {
			 if (trans != null) trans.rollback();
		 }
	}
	
	public Admin find (int id ) {
		Admin admin = null;
		try {
			admin  = this.em.find(Admin.class, id);
		} catch (Exception e) {
			System.err.println(e);
		}
		return admin ;
	}
}
