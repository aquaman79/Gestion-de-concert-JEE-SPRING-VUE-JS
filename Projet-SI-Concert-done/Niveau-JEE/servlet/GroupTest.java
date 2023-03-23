package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import DAO.DAO_JPA_GROUPE;

/**
 * Servlet implementation class GroupTest
 */
public class GroupTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DAO_JPA_GROUPE groupe; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupTest() {
        super();
        // TODO Auto-generated constructor stub
        groupe = new DAO_JPA_GROUPE();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StringBuilder sb = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        try {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line).append('\n');
	            }
	        } finally {
	            reader.close();
	        }
	        JSONObject json = new JSONObject(sb.toString());
	        String login = json.getString("nom_groupe");
	        String password = json.getString("description");
	        groupe.create(login, password);
				response.setContentType("application/json");
				 json = new JSONObject();
		        json.put("nom_groupe", login);
		        json.put("description", password);

		        response.setContentType("application/json");
		        PrintWriter out = response.getWriter();
		        out.print(json.toString());
		        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
