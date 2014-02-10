import java.util.*;
import java.sql.*;

/*
 *This program performs operations on a calendar created in the CreateCalendar.Java. Run it first to create the calendar
 *It can add, delete, and check calendar entries 
 */
public class CalendarMain {

	public CalendarMain(){
	}
	
	//Creates a new Calendar event
	public void createEvent(){
		
		try{
			String description, date, startTime, endTime;
			
			Scanner myScanner = new Scanner(System.in);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "GUEST_USERNAME_HERE", "INSERT_PASSWORD_HERE");
			//Statement st0 = connection.createStatement();  <--For use in different formatting
			
			System.out.println("Please enter the description of the event (Example, Dinner with the wife): ");
			description = myScanner.nextLine();
			
			System.out.println("Please enter the date of the event (Example, 2013-10-22 <--YYYY/MM/DD): ");
			date = myScanner.nextLine();

			System.out.println("Please enter the start time of the event (Example, 1330 <--Military Hours): ");
			startTime = myScanner.nextLine();
			
			System.out.println("Please enter the end time of the event (Example, 1415 <--Military Hours):  ");
			endTime = myScanner.nextLine();
			
			//Adds the event into the calendar via the input they provided
			PreparedStatement stat1 = connection.prepareStatement("INSERT INTO Calendar(" + "description," + "date," + "startingTime," + "endingTime)" + "VALUES(?,?,?,?)"); 
			stat1.setString(1, description);
			stat1.setString(2, date);
			stat1.setString(3, startTime);
			stat1.setString(4, endTime);
			stat1.execute();
			
			System.out.println("The event has been added to the calendar");
			
			connection.close();
			myScanner.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	//Cancels an existing calendar event
	public void cancelEvent(){
		
		try{
			String description, date, startTime, endTime;
			
			Scanner myScanner = new Scanner(System.in);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "root", "1Patrick");
			//Statement st0 = connection.createStatement();   <--For use in different formatting
			
			System.out.println("Please enter the description of the event you want to cancel (Example, Dinner with the wife): ");
			description = myScanner.nextLine();
			
			System.out.println("Please enter the date of the event you want to cancel (Example, 2013-10-22 <--YYYY/MM/DD): ");
			date = myScanner.nextLine();

			System.out.println("Please enter the start time of the event you want to cancel (Example, 1330 <--Military Hours): ");
			startTime = myScanner.nextLine();
			
			System.out.println("Please enter the end time of the event you want to cancel (Example, 1415 <--Military Hours):  ");
			endTime = myScanner.nextLine();
			
			//Deletes the event into the calendar via the input they provided
			PreparedStatement stat1 = connection.prepareStatement("DELETE FROM Calendar WHERE description = '"+ description+"' AND date = '"+ date+"' AND startingTime = '"+startTime+"' AND endingTime = '"+endTime+"'"); 
			stat1.execute();
			
			System.out.println("The event has been deleted from the calendar");
			
			connection.close();
			myScanner.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	//Checks a specific day for events
	public void checkCalendar(){
		
		
		try{
			String date;
			String query;
			
			Scanner myScanner = new Scanner(System.in);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "root", "1Patrick");
			//Statement st0 = connection.createStatement();   <--For use in different formatting
		
			System.out.println("What day would you like to check? (Example, 2013-10-22 <--YYYY/MM/DD):");
			date = myScanner.nextLine();
			
			query = "SELECT * FROM Calendar WHERE date = '" + date + "'";
					
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			ResultSetMetaData rsmd = res.getMetaData();
	
			int columnsNumber = rsmd.getColumnCount();
			
			// Iterate through the data in the result set and display it. 
			while (res.next()) {     
				System.out.println("Description:  Date:  Begin:End");
				for(int i = 1 ; i <= columnsNumber; i++){
				      System.out.print(res.getString(i) + " "); //Print one element of a row
				}
				  System.out.println("");//Move to the next line to print the next row.           
				}
			connection.close();
			myScanner.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Main class
	public static void main(String[] args) {
		CalendarMain cal = new CalendarMain();
		Scanner in = new Scanner(System.in);
		int answer; 
		System.out.println("This is your calendar");
		System.out.println("What would you like to do? Please choose from one of the following options (via number)");
		System.out.println("1: Add new calendar event\n2: Cancel/ delete calendar event\n3: Check a specific day for events\n4: Nothing at all");
		answer = in.nextInt();
		
		if (answer == 1){
			cal.createEvent();
		}
		else if (answer == 2){
			cal.cancelEvent();
		}
		else if (answer == 3){
			cal.checkCalendar();
		}
		else{
			System.out.println("You have yourself a great day!");
		}
		
		in.close();
	}
}
