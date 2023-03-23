package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import DAO.DAO_JPA_GROUPE;
import data.Groupe;
import fichiers.DAO_FICHIERS_GROUPE;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GroupeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAO_JPA_GROUPE dao_groupe = new DAO_JPA_GROUPE();
	private DAO_FICHIERS_GROUPE filesGroupe = new DAO_FICHIERS_GROUPE();

//	private HttpServletResponse setHeader(HttpServletResponse response) {
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//		response.addHeader("Access-Control-Allow-Headers",
//				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//		response.addHeader("Access-Control-Max-Age", "1728000");
//
//		response.setContentType("application/json; charset=UTF-8");
//
//		return response;
//	}
	 public void doOptions(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        response.setHeader("Access-Control-Max-Age", "86400");
	        response.setStatus(HttpServletResponse.SC_OK);
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response = setHeader(response);
		this.doOptions(request, response);
		PrintWriter out = response.getWriter();

		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			// /Groupe
			List<Groupe> groupes = this.dao_groupe.findAll();

			String groupesJson = "[";
			for (Groupe g : groupes) {
				List<Document> listeDocuments = this.filesGroupe.findGroupeFichiers(g.getIdGroupe());

				String lienImage = "";
				if (!listeDocuments.isEmpty()) {
					lienImage = listeDocuments.get(0).getString("nomFichier");
				}

				try {
					JSONObject gJson = new JSONObject(g.toString());
					gJson.put("image", lienImage);

					groupesJson += gJson.toString() + ",";

				} catch (JSONException e) {
					e.printStackTrace();

					String jsonError = "{\"res\": false, \"msg\": \"error json object\" }";
					out.write(jsonError);
					out.close();
					return;
				}
			}

			if (groupesJson != "[")
				groupesJson = groupesJson.substring(0, groupesJson.length() - 1);
			groupesJson += "]";

			out.write(groupesJson);
			out.close();
			return;
		}

		// /Groupe/:id
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idString = pathParts[1];

			Long id = (long) -1;
			try {
				id = Long.parseLong(idString);
			} catch (NumberFormatException e) {
				String jsonError = "{\"res\": false, \"msg\": \"id fourni incorrect\" }";
				out.write(jsonError);
				out.close();
				return;
			}

			Groupe groupe = this.dao_groupe.find(id);

			if (groupe == null) {
				out.write("{}");
			} else {
				List<Document> listeDocuments = this.filesGroupe.findGroupeFichiers(groupe.getIdGroupe());

				String lienImage = "";
				if (!listeDocuments.isEmpty()) {
					lienImage = listeDocuments.get(0).getString("nomFichier");
				}

				try {
					JSONObject groupeJson = new JSONObject(groupe.toString());
					groupeJson.put("image", lienImage);

					out.write(groupeJson.toString());

				} catch (JSONException e) {
					e.printStackTrace();

					String jsonError = "{\"res\": false, \"msg\": \"error json object\" }";
					out.write(jsonError);
				}

				out.close();
				return;

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		this.doOptions(request, response);
		PrintWriter out = response.getWriter();

//		Groupe groupe = new Gson().fromJson(request.getReader(), Groupe.class);

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String requestBody = sb.toString();

		String nomGroupe = "";
		String description = "";
		String lienImage = "";

		try {
			JSONObject body = new JSONObject(requestBody);
			nomGroupe = body.getString("nomGroupe");
			description = body.getString("description");
			lienImage = body.getString("image");

		} catch (JSONException e) {
			e.printStackTrace();

			String jsonSuccess = "{\"res\": false, \"msg\": \"error parse body at json\" }";
			out.write(jsonSuccess);
			out.close();
			return;
		}

		if (nomGroupe == null || nomGroupe == "null" || nomGroupe.isBlank() || description == null
				|| description == "null" || description.isBlank()) {

			String jsonError = "{\"res\": false, \"msg\": \"Information(s) groupe invalide(s)\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		this.dao_groupe.create(nomGroupe, description);

		Long idGroupe = (long) -1;

		// get the new id insert to find file in mongo
		List<Groupe> listeGroupes = this.dao_groupe.findAll();
		for (Groupe g : listeGroupes) {
			if (g.getNomGroupe().equalsIgnoreCase(nomGroupe)) {
				idGroupe = g.getIdGroupe();
			}
		}

		if (idGroupe != -1) {
			filesGroupe.insertOneGroupeFichier(idGroupe, lienImage, "image", nomGroupe, new Date());
		}

		String jsonSuccess = "{\"res\": true, \"msg\": \"Groupe ajouté\" }";
		out.write(jsonSuccess);
		out.close();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doOptions(request, response);

		PrintWriter out = response.getWriter();

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String requestBody = sb.toString();

		Long idGroupe = (long) -1;
		String nomGroupe = "";
		String description = "";
		String lienImage = "";

		try {
			JSONObject body = new JSONObject(requestBody);
			idGroupe = body.getLong("idGroupe");
			nomGroupe = body.getString("nomGroupe");
			description = body.getString("description");
			lienImage = body.getString("image");

		} catch (JSONException e) {
			e.printStackTrace();

			String jsonSuccess = "{\"res\": false, \"msg\": \"error parse body at json\" }";
			out.write(jsonSuccess);
			out.close();
			return;
		}

		if (nomGroupe == null || nomGroupe == "null" || nomGroupe.isBlank() || description == null
				|| description == "null" || description.isBlank()) {
			String jsonError = "{\"res\": false, \"msg\": \"Information(s) groupe invalide(s)\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		this.dao_groupe.update(idGroupe, nomGroupe, description);

		this.filesGroupe.deleteAllGroupeFichier(idGroupe);
		this.filesGroupe.insertOneGroupeFichier(idGroupe, lienImage, "image", nomGroupe, new Date());

		String jsonSuccess = "{\"res\": true, \"msg\": \"Groupe modifié\" }";
		out.write(jsonSuccess);
		out.close();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doOptions(request, response);
		PrintWriter out = response.getWriter();

		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			String jsonError = "{\"res\": false, \"msg\": \"Aucun id fourni\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		// /Groupe/:id
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idString = pathParts[1];

			Long id = (long) 0;
			try {
				id = Long.parseLong(idString);
			} catch (NumberFormatException e) {
				String jsonError = "{\"res\": false, \"msg\": \"id fourni incorrect\" }";
				out.write(jsonError);
				out.close();
				return;
			}

			this.dao_groupe.delete(id);
			this.filesGroupe.deleteAllGroupeFichier(id);

			String jsonSuccess = "{\"res\": \"true\", \"msg\": \"Groupe supprimé\" }";
			out.write(jsonSuccess);
			out.close();
		}
	}

}
