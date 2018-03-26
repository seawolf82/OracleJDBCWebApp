package it.euris.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DataSourceServlet
 */
public class DataSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		writer.println();
	       try {
	    	   InitialContext ctx = new InitialContext();
	    	   DataSource ds = (DataSource)ctx.lookup("java:OracleDS");
	            Connection conn = ds.getConnection(); 
	            Statement statement = conn.createStatement();
	            String sql = "select nome,cognome from tab_persona";
	            ResultSet rs = statement.executeQuery(sql);
	             
	            int count = 1;
				writer.println();
	            while (rs.next()) {
					writer.println(String.format("User #%d: %-15s %s", count++,
	                        rs.getString("nome"), rs.getString("cognome")));
							writer.println();
	            }
	        } catch (NamingException ex) {
	            System.err.println(ex);
	        } catch (SQLException ex) {
	            System.err.println(ex);
	        }
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
