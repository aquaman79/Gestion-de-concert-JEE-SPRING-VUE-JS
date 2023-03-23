package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import data.Artiste;
import data.Groupe;

public class DAO_JPA_ARTISTE {

	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction trans;

	public DAO_JPA_ARTISTE() {
		this.emf = Persistence.createEntityManagerFactory("gestionManager");
		this.em = emf.createEntityManager();
		this.trans = null;
	}

	public void create(Long idGroupe, String prenom, String nom) {

		Groupe groupe = this.em.find(Groupe.class, idGroupe);

		if (groupe == null) {
			System.err.println("Aucune groupe avec l'id " + idGroupe);
			return;
		}
		Artiste artiste = new Artiste();
		artiste.setIdGroupe(groupe);
		artiste.setNomArtiste(nom);
		artiste.setPrenomArtiste(prenom);
		try {
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.persist(artiste);
			this.trans.commit();
		} catch (Exception e) {
			if (this.trans != null)
				this.trans.rollback();
			System.err.println(e);
		}
	}

	public Artiste find(int i) {
		return this.em.find(Artiste.class, i);
	}

	public List<Artiste> findAll() {
		TypedQuery<Artiste> requete = this.em.createQuery("SELECT a FROM Artiste a", Artiste.class);
		return (List<Artiste>) requete.getResultList();
	}

	public List<Artiste> findAll(Long idGroupe) {
		TypedQuery<Artiste> requete = this.em.createQuery("SELECT a FROM Artiste a WHERE a.id_groupe = " + idGroupe,
				Artiste.class);
		return (List<Artiste>) requete.getResultList();
	}

	public void update(int idArtiste, Long idGroupe, String prenom, String nom) {
		Artiste artiste = this.em.find(Artiste.class, idArtiste);

		if (artiste == null) {
			System.err.println("Aucune artiste avec l'id " + idArtiste);
			return;
		}

		Groupe groupe = this.em.find(Groupe.class, idGroupe);
		if (groupe == null) {
			System.err.println("Aucun groupe avec l'id " + idGroupe);
			return;
		}

		artiste.setNomArtiste(nom);
		artiste.setPrenomArtiste(prenom);
		artiste.setIdGroupe(groupe);

		try {
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.persist(artiste);
			this.trans.commit();
		} catch (Exception e) {
			if (this.trans != null)
				this.trans.rollback();

			System.err.println(e);
		}
	}

	public void delete(int idArtiste) {
		Artiste artiste = this.em.find(Artiste.class, idArtiste);

		if (artiste == null)
			return;

		try {
			this.trans = this.em.getTransaction();
			this.trans.begin();
			this.em.remove(artiste);
			this.trans.commit();
		} catch (Exception e) {
			if (this.trans != null)
				this.trans.rollback();

			System.err.println(e);
		}
	}

}