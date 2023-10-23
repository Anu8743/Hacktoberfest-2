import java.security.DrbgParameters.NextBytes;
import java.sql.*;
import java.util.*;

public class StudentManagementSystem {

	public static void main(String[] args) {
		
		int choice =0;
		while(choice!=1234) {
			System.out.println("Welcome to STUDENT MANAGEMENT SYSTEM");
			System.out.println("Please Authenticate System:");
			
			System.out.println("Enter Choice :\n 1: Login \n 2: Signup \n 3. EXIT \n");
			int menu=0;
			Scanner sc=new Scanner(System.in);
			menu=sc.nextInt();
			switch(menu) {
			case 1:
				boolean result=login();
				if(result)
					System.out.println("Login Success!");
				else
					System.out.println("Wrong Creditials or not record found.");
				break;
			case 2:
				signup();
				break;
			case 3:
				choice=1234;
				break;
			
			}
		}
	}
		
	private static boolean login() {
		boolean loginSuccess = false;
		String id="",password="";
		String id1 = "",pass="";
		Scanner sc=new Scanner (System.in);
		System.out.println("Enter ID:");
		id=sc.nextLine();
		System.out.println("Enter Password:");
		password=sc.nextLine();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem", "root", "Incapp@12");
			Statement st = c.createStatement();
			ResultSet rs=st.executeQuery("select * from login");
			
			while (rs.next()) {
				id1=rs.getString(1);
				pass=rs.getNString(2);
			}
			if(id.equals(id1) && password.equals(pass))
				loginSuccess=true;
			else
				loginSuccess=false;
		
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return loginSuccess;
	}
	
	private static void signup() {
		int loop=0;
		int code=0;
		String id="",password="",name="";
		while(loop!=8008) {
			Scanner sc=new Scanner(System.in);
			System.out.println("Signup: Two-step verification: \n");
			code=sc.nextInt();
			if(code==8008) {
				Scanner data=new Scanner(System.in);
				System.out.println("Enter ID:");
				id=data.nextLine();
				System.out.println("Enter Password:");
				password=data.nextLine();
				System.out.println("Enter Name:");
				name=data.nextLine();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem", "root", "Incapp@12");
					String query="insert into signup (id,password,name)"+ "values (?,?,?)";
					String query1="insert into login (id,password)"+"values (?,?)";
					PreparedStatement ps = con.prepareStatement(query);
					PreparedStatement ps1 = con.prepareStatement(query1);
					ps.setString(1, id);
					ps.setString(2, password);
					ps.setString(3, name);
					ps1.setString(1, id);
					ps1.setString(2, password);
					int i=ps.executeUpdate();
					ps1.executeUpdate();
					 if(i>0) {
						 System.out.println("\n\nData inserted..\n\n");
					 }else {
						 System.out.println("\n\nData not inserted...");
					 }
				}catch(Exception e) {
					System.out.print(e);
				}
			}else {
				System.out.println("\nWrong pin_code.");
				return;
			}
		}
		
	}

}
