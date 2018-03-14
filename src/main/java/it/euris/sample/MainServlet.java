package it.euris.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = -602355360452682855L;

	// @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");
		out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.3.81:1521:xe", "stage", "stage");

		} catch (SQLException e) {

			out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			out.println("You made it, take control your database now!");
		} else {
			out.println("Failed to make connection!");
		}
	}
}
