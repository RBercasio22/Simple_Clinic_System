package net.codejava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.IOException;
import java.util.Date;


public class ClinicSystem {
	private static final String[] args = null;
	public static String jdbc = "jdbc:mysql://localhost:3306/hospitaldb";
	public static String username = "root", password = "Sample";
	public static String Name, Disease, Date_of_Admission, Date_of_Discharge;	
	public static String developer = "Developed by RBercasio";
	public static int Age, Client_ID=0;		
	static Thread thread = new Thread();
	static Scanner scan = new Scanner(System.in);


	/***
	+---------------------------------IMPORTANT NOTE FOR USERS OF THIS CODE---------------------------------+
	+-------------------------------------------------------------------------------------------------------+
	*1. You need to supply the JDBC Driver to the classpath of your program compiled						*  	
	*2. Create a specific Folder of your program including the JDBC Connector								*
	*3. You can run your JAVA Program files using cmd in your desktop.										*
	*4. Then Input the Important cmd Command in your console terminal 										*
	*java -cp mysql-connector-java-8.0.29.jar;. (Title of your Java Program Files).java"					*
	*5. Only optional if your JAVA Program converted to the Java Runnable JAR Application					*	
	+-------------------------------------------------------------------------------------------------------+
	*						Computer Engineering Project Java Object Oriented Programming					*
	+-------------------------------------------------------------------------------------------------------+
	 */
	public static void DateTime(){
		Date thisdate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		System.out.println(dateformat.format(thisdate));

	}
	public static void Initialize(){
			System.out.print("Initialize");
		for(int i =0; i <3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		ClearScreen();	
		System.out.print("100% Successfully Completed!");
		try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		ClearScreen();			
	}		
	public static void Patient(){	
			
		Scanner scan = new Scanner(System.in);		
		try {					
			Connection conn = DriverManager.getConnection(jdbc, username, password);					
			Statement state = conn.createStatement();
			
		    System.out.print("Name of the Patient\t: ");
		    Name = scan.nextLine().toLowerCase();	   
		 				    
			System.out.print("Date of Admission\t: ");
			Date_of_Admission = scan.nextLine();
					
			System.out.print("Disease\t\t\t: ");
			Disease = scan.nextLine().toLowerCase();
					
			System.out.print("Date of Discharge\t: ");
			Date_of_Discharge = scan.nextLine();
							
			System.out.print("Age\t\t\t: ");
			Age = scan.nextInt();
					
			Client_ID = Client_ID++;		
			state.executeUpdate("INSERT INTO client VALUE('"+Client_ID+"','"+Name+"','"+Date_of_Admission+"','"+Disease+"','"+Date_of_Discharge+"','"+Age+"')");	
			System.out.println("---------------------------------------------------------------------------------------------------------------");			
			System.out.println("Patient added Successfully!");
			System.out.println("---------------------------------------------------------------------------------------------------------------");	
			conn.close();
			thread.sleep(4000);
			ClearScreen();	
			Display();
			
		}
		catch (Exception ex) {
			System.out.println("Oopss! receiving error in execution error type ==> "+"\n"+ex);		
			try {
				thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			ClearScreen();		
			main(args);
		}				
	}
	public static void Display() {		
		Initialize();
		DateTime();
		
		int counter1 = 1;
		try {		
				
			Connection conn = DriverManager.getConnection(jdbc, username, password);			
			String sql = "SELECT * FROM client";
			Statement state = conn.createStatement();	
			ResultSet rs = state.executeQuery(sql);
			System.out.println("---------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%1s%10s%72s\n","|","Computer Engineering Java OOP Activity","|");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			System.out.printf("%1s%65s%45s\n","|","DISPLAY ALL PATIENT","|");
			System.out.println("------+------------------------+-----------------------------+------------------------+-----------------+------");	
			System.out.printf("%-6s%-25s%8s%28s%27s%12s%3s\n", "|ID","|NAME","|ADMISSION","|DISEASE","|DISCHARGE","|AGE","|");
			System.out.println("------+------------------------+-----------------------------+------------------------+-----------------+------");					
			while(rs.next()) {		
				counter1++;				
				System.out.printf("%1s%-6d%-25s%8s%25s%30s%10d%4s\n" ,"|",rs.getInt("Client_ID"),rs.getString("Name"),rs.getString("Date_of_Admission"),rs.getString("Disease"),rs.getString("Date_of_Discharge"),rs.getInt("Age"),"|");
				System.out.println("---------------------------------------------------------------------------------------------------------------");
				thread.sleep(400);					
			}			
			System.out.printf("%1s%91s%1s\n", "|TOTAL PATIENT : "+(counter1+=-1),"Developed by RBercasio ","|");
			System.out.println("---------------------------------------------------------------------------------------------------------------");	
			
		conn.close();
		MenuDp();	
		
		}catch(Exception ex) {
			System.out.println(ex);
			ClearScreen();
			main(args);		
		}		
					
	}
	public static void Displaypdp() {
		Initialize();
		DateTime();
			
		int counter2 = 1;	
		try {			
			Connection conn = DriverManager.getConnection(jdbc, username, password);	
			
			String sql = "SELECT * FROM client";
			Statement state = conn.createStatement();	
			ResultSet rs = state.executeQuery(sql);
			System.out.println("---------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%1s%10s%72s\n","|","Computer Engineering Java OOP Activity","|");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			System.out.printf("%1s%65s%45s\n","|","DISPLAY PEDIATRIC PATIENT","|");
			System.out.println("------+------------------------+-----------------------------+------------------------+-----------------+------");	
			System.out.printf("%-6s%-25s%8s%28s%27s%12s%3s\n", "|ID","|NAME","|ADMISSION","|DISEASE","|DISCHARGE","|AGE","|");
			System.out.println("------+------------------------+-----------------------------+------------------------+-----------------+------");	
			while(rs.next()) {
				if(rs.getInt("Age")< 12) {
					counter2++;				
					System.out.printf("%1s%-6d%-25s%8s%25s%30s%10d%4s\n" ,"|",rs.getInt("Client_ID"),rs.getString("Name"),rs.getString("Date_of_Admission"),rs.getString("Disease"),rs.getString("Date_of_Discharge"),rs.getInt("Age"),"|");
					System.out.println("---------------------------------------------------------------------------------------------------------------");
					thread.sleep(400);								
				}			
			}
			System.out.printf("%1s%82s%1s\n", "|TOTAL PEDIATRIC PATIENT : "+(counter2+=-1),"Developed by RBercasio ","|");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
				
		conn.close();
		MenuPdp();		
		}catch(Exception ex) {
			System.out.println(ex);
			ClearScreen();
			main(args);		
		}	
					
	}
	public static void ClearScreen(){
		try
		{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E)
			{
				System.out.println(E);	
			
			}
		}
	public static void MenuPdp(){
		System.out.println("+-----+-------------------------------+");
		System.out.printf("%-6s%-30s%3s\n","|  ID","|Menu","|");
		System.out.println("+-----+-------------------------------+");
		System.out.printf("%-6s%-30s%3s\n","|  1","|Back to Main Menu","|");
		System.out.printf("%-6s%-30s%3s\n","|  2","|Reload Records","|");
		System.out.printf("%-6s%-30s%3s\n","|  3","|Exit","|");
		System.out.println("+-----+-------------------------------+");
		System.out.print("Input ID : ");
		String in = scan.next();
		if(in.equalsIgnoreCase("1")) {
			ClearScreen();
			main(args);
		}else if(in.equalsIgnoreCase("2")){
			ClearScreen();
			Displaypdp();
		}
		else if(in.equalsIgnoreCase("3")) {
			System.exit(0);
		}else {
			System.out.println("Ooopss! Invalid Input, Please try again!");	
			try {
				thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ClearScreen();
			MenuPdp();					
		}				
	}	
	public static void MenuDp(){
		System.out.println("+-----+-------------------------------+");
		System.out.printf("%-6s%-30s%3s\n","|  ID","|Menu","|");
		System.out.println("+-----+-------------------------------+");
		System.out.printf("%-6s%-30s%3s\n","|  1","|Back to Main Menu","|");
		System.out.printf("%-6s%-30s%3s\n","|  2","|Update Patient Data","|");
		System.out.printf("%-6s%-30s%3s\n","|  3","|Delete Patient Data","|");
		System.out.printf("%-6s%-30s%3s\n","|  4","|Reload Records","|");
		System.out.printf("%-6s%-30s%3s\n","|  5","|Exit","|");
		System.out.println("+-----+-------------------------------+");
		System.out.print("Input ID : ");
		
		String in = scan.next();
		try{
				
		if(in.equalsIgnoreCase("1")) {
			ClearScreen();
			main(args);
		}else if(in.equalsIgnoreCase("2")) {		
			ClearScreen();
			System.out.print("Incomplete program!");
			thread.sleep(2400);
			ClearScreen();
			Display();
		}else if(in.equalsIgnoreCase("3")) {
			ClearScreen();
			System.out.print("Incomplete program!");
			thread.sleep(2400);
			ClearScreen();
			Display();
		}else if(in.equalsIgnoreCase("4")){
			ClearScreen();
			Display();
		}
		else if(in.equalsIgnoreCase("5")) {
			System.exit(0);
		}else {
			System.out.println("Ooopss! Invalid Input, Please try again!");
			thread.sleep(500);	
			ClearScreen();	
			MenuDp();				
		}		
		}catch(Exception ex){
			System.out.println("Error"+ ex);
		}
	}	
	public static void main(String[] args){
			ClearScreen();	
			DateTime();

			String choice;
			Scanner in = new Scanner(System.in);	
			System.out.println("+-----+-------------------------------+");
			System.out.printf("%-6s%-30s%3s\n","|  ID","|Menu","|");
			System.out.println("+-----+-------------------------------+");
			System.out.printf("%-6s%-30s%3s\n","|  1","|Insert new Patient","|");
			System.out.printf("%-6s%-30s%3s\n","|  2","|Display all Patient","|");
			System.out.printf("%-6s%-30s%3s\n","|  3","|Display Pediatric Patient","|");
			System.out.printf("%-6s%-30s%3s\n","|  4","|Exit","|");
			System.out.println("+-----+-------------------------------+");
				
			System.out.print("Input ID : ");
				
			choice = in.next();		
			if(choice.equalsIgnoreCase("1")) {
				ClearScreen();
				Patient(); 	
			}
			else if(choice.equalsIgnoreCase("2")) {
				ClearScreen();
				Display();		
			}
			else if(choice.equalsIgnoreCase("3")) {
				ClearScreen();
				Displaypdp();			
			}
			else if(choice.equalsIgnoreCase("4")) {
				System.exit(0);
			}
			else if(choice.equalsIgnoreCase("5")) {
				ClearScreen();
				main(args);
			}
			else main(args);						
	
		
	}
		
}
