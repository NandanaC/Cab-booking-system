import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		ResultSet rs;
		//Allocating Driver
		String cityname = "HITEC City";
			String vehiclenum = null;
			try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/cabbooking","root","fountainheadd");   
					Statement stmt=con.createStatement(); 
					String que = "select vehicleno from driver where rating = ( select max(rating) from driver where presentloc='"+ cityname +"' and availability='Yes') and presentloc='"+ cityname +"' and availability='Yes';";
					rs = stmt.executeQuery(que); 
					rs.next();  
						vehiclenum = rs.getString("vehicleno");
					con.close();
				}
				catch(Exception e) {
						System.out.println(e);
				}
		System.out.println(vehiclenum);

	}

}
