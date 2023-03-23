package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import DAO.DAO_JPA_ARTISTE;
import data.Artiste;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAO_JPA_ARTISTE dao_artiste = new DAO_JPA_ARTISTE();

	// TODO : get artistes by groupe

	private HttpServletResponse setHeader(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");

		response.setContentType("application/json; charset=UTF-8");

		return response;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response = setHeader(response);
		PrintWriter out = response.getWriter();

		String idGroupe_param = request.getParameter("idGroupe");
		if (idGroupe_param != null) {
			try {
				Long idGroupe = Long.parseLong(idGroupe_param);

				// /Artiste?idGroupe=x
				List<Artiste> artistes = this.dao_artiste.findAll(idGroupe);
				String artistesJson = "[";
				for (Artiste artiste : artistes) {
					artistesJson += artiste.toString() + ",";
				}

				if (artistesJson != "[")
					artistesJson = artistesJson.substring(0, artistesJson.length() - 1);
				artistesJson += "]";

				out.write(artistesJson);

			} catch (NumberFormatException e) {
				String jsonError = "{\"res\": false, \"msg\": \"idGroupe invalide\"}";
				out.write(jsonError);
			}

			out.close();
			return;

		}

		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			// /Artiste
			List<Artiste> artistes = this.dao_artiste.findAll();

			String artistesJson = "[";
			for (Artiste artiste : artistes) {
				artistesJson += artiste.toString() + ",";
			}

			if (artistesJson != "[")
				artistesJson = artistesJson.substring(0, artistesJson.length() - 1);
			artistesJson += "]";

			out.write(artistesJson);
			out.close();
			return;
		}

		// /Artiste/:id
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idString = pathParts[1];

			int id = -1;
			try {
				id = Integer.parseInt(idString);
			} catch (NumberFormatException e) {
				String jsonError = "{\"res\": false, \"msg\": \"id fourni incorrect\" }";
				out.write(jsonError);
				out.close();
				return;
			}

			Artiste artiste = this.dao_artiste.find(id);

			String artisteJson = artiste == null ? "{}" : artiste.toString();

			out.write(artisteJson);
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response = setHeader(response);
		PrintWriter out = response.getWriter();

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String requestBody = sb.toString();
		System.out.println(requestBody);

		Long idGroupe = (long) -1;
		String nomArtiste = "";
		String prenomArtiste = "";

		try {
			JSONObject body = new JSONObject(requestBody);
			idGroupe = body.getLong("idGroupe");
			nomArtiste = body.getString("nomArtiste");
			prenomArtiste = body.getString("prenomArtiste");

		} catch (JSONException e) {
			e.printStackTrace();

			String jsonSuccess = "{\"res\": false, \"msg\": \"error parse body at json\" }";
			out.write(jsonSuccess);
			out.close();
			return;
		}

		if (nomArtiste == null || nomArtiste == "null" || nomArtiste.isBlank() || prenomArtiste == null
				|| prenomArtiste == "null" || prenomArtiste.isBlank()) {
			String jsonError = "{\"res\": false, \"msg\": \"Information(s) artiste invalide(s)\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		this.dao_artiste.create(idGroupe, nomArtiste, prenomArtiste);

		String jsonSuccess = "{\"res\": true, \"msg\": \"Artiste ajouté\" }";
		out.write(jsonSuccess);
		out.close();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response = setHeader(response);

		PrintWriter out = response.getWriter();
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String requestBody = sb.toString();
		System.out.println(requestBody);

		Long idGroupe = (long) -1;
		int idArtiste = -1;
		String nomArtiste = "";
		String prenomArtiste = "";

		try {
			JSONObject body = new JSONObject(requestBody);
			idGroupe = body.getLong("idGroupe");
			idArtiste = body.getInt("idArtiste");
			nomArtiste = body.getString("nomArtiste");
			prenomArtiste = body.getString("prenomArtiste");

		} catch (JSONException e) {
			e.printStackTrace();

			String jsonSuccess = "{\"res\": false, \"msg\": \"error parse body at json\" }";
			out.write(jsonSuccess);
			out.close();
			return;
		}

		if (nomArtiste == null || nomArtiste == "null" || nomArtiste.isBlank() || prenomArtiste == null
				|| prenomArtiste == "null" || prenomArtiste.isBlank()) {
			String jsonError = "{\"res\": false, \"msg\": \"Information(s) artiste invalide(s)\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		this.dao_artiste.update(idArtiste, idGroupe, prenomArtiste, nomArtiste);

		String jsonSuccess = "{\"res\": true, \"msg\": \"Artiste modifié\" }";
		out.write(jsonSuccess);
		out.close();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response = setHeader(response);
		PrintWriter out = response.getWriter();

		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			String jsonError = "{\"res\": false, \"msg\": \"Aucun id fourni\" }";
			out.write(jsonError);
			out.close();
			return;
		}

		// /Artiste/:id
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idString = pathParts[1];

			int id = 0;
			try {
				id = Integer.parseInt(idString);
			} catch (NumberFormatException e) {
				String jsonError = "{\"res\": false, \"msg\": \"id fourni incorrect\" }";
				out.write(jsonError);
				out.close();
				return;
			}

			this.dao_artiste.delete(id);

			String jsonSuccess = "{\"res\": \"true\", \"msg\": \"Artiste supprimé\" }";
			out.write(jsonSuccess);
			out.close();
		}
	}

}
