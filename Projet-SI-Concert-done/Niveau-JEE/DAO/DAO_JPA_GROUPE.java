package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import data.Groupe;

public class DAO_JPA_GROUPE {

	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction trans;

	public DAO_JPA_GROUPE() {
		this.emf = Persistence.createEntityManagerFactory("gestionManager");
		this.em = emf.createEntityManager();
		this.trans = null;
	}

	public Groupe create(String nomGroupe, String Description) {
		Groupe groupe = new Groupe();
		groupe.setDescription(Description);
		groupe.setNomGroupe(nomGroupe);
		try {
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.persist(groupe);
			System.out.println(groupe.getDescription());
			this.trans.commit();
			return groupe;
		} catch (Exception e) {
			// if (trans != null) trans.rollback();
			System.out.println(e);
		}
		return null;
	}

	public void update(Long idGroupe, String nomGroupe, String Description) {
		try {
			Groupe groupe = this.find(idGroupe);
			groupe.setNomGroupe(nomGroupe);
			groupe.setDescription(Description);
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.persist(groupe);
			this.trans.commit();
		} catch (Exception e) {
			// if (trans != null) trans.rollback();
			System.err.println(e);
		}
	}

	public Groupe find(Long i) {
		return this.em.find(Groupe.class, i);
	}

	public List<Groupe> findAll() {
		TypedQuery<Groupe> requete = this.em.createQuery("SELECT g FROM Groupe g", Groupe.class);
		return (List<Groupe>) requete.getResultList();
	}

	public void delete(Long idGroupe) {
		Groupe groupe = this.find(idGroupe);

		if (groupe == null)
			return;

		try {
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.remove(groupe);
			this.trans.commit();
		} catch (Exception e) {
			if (this.trans != null)
				this.trans.rollback();

			System.err.println(e);
		}
	}
}
