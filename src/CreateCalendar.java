import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateCalendar{

	public static void main(String[]args) {

		Connection con = null;
		String url = "localhost";
		String driverName = "mariadb-java-client-1.1.3.jar";
		
		try{
			
			Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "GUEST_USERNAME_HERE", "INSERT_PASSWORD_HERE");
			Statement st = connection.createStatement();
			
			//Create Columns
			st.execute("CREATE TABLE Calendar(description VARCHAR(100), date VARCHAR(30), startingTime VARCHAR (30), endingTime VARCHAR (30))");

			//Create Rows
			st.execute("INSERT INTO Calendar VALUES('Pick up Chicken', '2013-10-01', '1500', '1630')");
			st.execute("INSERT INTO Calendar VALUES('Dinner with the wife', '2013-08-25', '1800', '1900')");
			st.execute("INSERT INTO Calendar VALUES('Repair Computer', '2013-11-11', '0900', '1030')");
			st.execute("INSERT INTO Calendar VALUES('Buy new phone', '2013-12-21', '1100', '1630')");
			st.execute("INSERT INTO Calendar VALUES('Clean kitchen', '2013-12-31', '1900', '2330')");
			
			System.out.println("Calendar table success");
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
}