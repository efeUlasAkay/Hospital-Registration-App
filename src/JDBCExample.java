
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

	public static void main(String[] argv) {
		Statement stmt;

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/sample_db");
			stmt = connection.createStatement();
			String sql = "Select * FROM Doctor";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
		         String id  = rs.getString("id");
		         
		         String name = rs.getString("name");
		         String teamID = rs.getString("teamId");

		         System.out.print("ID: " + id);
		         System.out.print(", Name: " + name);
		         System.out.println(", TeamID: " + teamID);
		      }
			rs.close();
			stmt.close();
		

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
