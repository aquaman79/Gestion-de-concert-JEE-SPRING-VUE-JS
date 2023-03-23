package fichiers;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DAO_FICHIERS_CONCERT {

	private final String URI = "mongodb://localhost:27017";

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public DAO_FICHIERS_CONCERT() {
		this.mongoClient = MongoClients.create(URI);
		this.database = this.mongoClient.getDatabase("sysinfo");
		this.collection = this.database.getCollection("fichiersConcert");
	}

	/**
	 * Récupère tous les fichiers qui sont liés à l'id du concert.
	 * 
	 * @param idConcert l'id du concert pour lequel on souhaite récupérer les
	 *                  fichiers.
	 * @return List<Documents> Une liste contenant toutes les informations de chaque
	 *         documents.
	 */
	public List<Document> findConcertFichiers(int idConcert) {
		List<Document> documents = this.collection.find(eq("idConcert", idConcert)).into(new ArrayList<Document>());
		return documents;
	}

	/**
	 * Récupère un fichier lié à l'id du concert et qui à ce nom.
	 * 
	 * @param idConcert  l'id du concert pour lequel on souhaite récupérer le
	 *                   fichier.
	 * @param nomFichier nom du fichier que l'on souhaite récupérer.
	 * @return Document Le document trouver, null si aucun
	 */
	public Document findOneConcertFichier(int idConcert, String nomFichier) {
		return this.collection.find(and(eq("idConcert", idConcert), eq("nomFichier", nomFichier))).first();
	}

	/**
	 * Stock les informations d'un nouveau fichiers.
	 * 
	 * @param idConcert   l'id du concert auquel est lié le fichier.
	 * @param nomFichier  nom du fichier
	 * @param typeFichier type du fichier
	 * @param auteur      auteur
	 */
	public void insertOneConcertFichier(int idConcert, String nomFichier, String typeFichier, String auteur,
			Date date) {

		if (this.findOneConcertFichier(idConcert, nomFichier) != null) {
			System.err.println("Un fichier existe déjà sous ce nom pour ce concert.");
			return;
		}

		Document doc = new Document();

		doc.append("idConcert", idConcert);
		doc.append("nomFichier", nomFichier);
		doc.append("typeFichier", typeFichier);
		doc.append("auteur", auteur);
		doc.append("date", date);

		try {
			this.collection.insertOne(doc);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Met à jour les informations d'un fichier.
	 * 
	 * @param idConcert   l'id du concert auquel est lié le fichier.
	 * @param nomFichier  nom du fichier qui est à modifier
	 * @param typeFichier nouveau type du fichier
	 * @param auteur      nouvel auteur
	 */
	public void updateOneConcertFichier(int idConcert, String nomFichier, String typeFichier, String auteur,
			Date date) {
		Document previousDoc = this.findOneConcertFichier(idConcert, nomFichier);

		if (previousDoc == null) {
			System.err.println("Aucun fichier avec ce nom pour ce concert");
			return;
		}

		Document doc = new Document();

		doc.append("typeFichier", typeFichier);
		doc.append("auteur", auteur);
		doc.append("date", date);

		try {
			this.collection.updateOne(previousDoc, new Document("$set", doc));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Supprimer les informations d'un fichiers
	 * 
	 * @param idConcert  l'id du concert auquel est lié le fichier.
	 * @param nomFichier nom du fichier
	 */
	public void deleteOneConcertFichier(int idConcert, String nomFichier) {
		this.collection.deleteOne(and(eq("idConcert", idConcert), eq("nomFichier", nomFichier)));
	}

	/**
	 * Supprimer les informations de tous les fichiers lié au concert.
	 * 
	 * @param idConcert l'id du concert auquel sont liés les fichiers.
	 */
	public void deleteAllConcertFichier(int idConcert) {
		this.collection.deleteMany(eq("idConcert", idConcert));
	}
}
