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

import DAO.DAO_JPA_ADMIN;
import data.Admin;

/**v 
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO_JPA_ADMIN admin;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
         this.admin = new DAO_JPA_ADMIN();
    }
    public void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setStatus(HttpServletResponse.SC_OK);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doOptions(request, response);		
//		String login = request.getParameter("username");
//		String password = request.getParameter("password");
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
	        String login = json.getString("username");
	        String password = json.getString("password");
	        Admin adm = this.admin.find(1);
		 if(adm.getLogin().equals(login)&&adm.getMotdepasse().equals(password)) {
				response.setContentType("application/json");
				 json = new JSONObject();
		        json.put("token", true);

		        response.setContentType("application/json");
		        PrintWriter out = response.getWriter();
		        out.print(json.toString());
		        out.flush();
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doOptions(request, response);		
		doGet(request, response);
	}

}
