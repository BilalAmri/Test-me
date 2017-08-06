
import java.io.IOException;
import java.net.InetAddress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



import java.sql.ResultSet;

public class Test {

	Connection connection = null;
	ResultSet rst=null;
	Statement st=null;
	
	
	public void OpenConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection("jdbc:sqlserver://10.57.2.250:1433;user=SA;password=Messenger1!;databaseName=DMG_MORI_Messenger_V2;");
		System.out.println("Connected to DataBase !!!");
		
	}
	
	
	public void ResultSet() throws SQLException
	{
		
		 st = connection.createStatement();
		 rst = st.executeQuery("select Time,Status from mdetail where (select max(Id) from mdetail)=Id");
		
		
	}
	
	
	public String toString()
	{  
		String s="";
		try {
			while(rst.next())
			{
				 s+="*** "+ rst.getString("Time") + "   " + rst.getString("Status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		Test test=new Test();
			
		test.OpenConnection();
		test.ResultSet();
		System.out.println(test.toString());
		
			
			Thread.sleep(10000);
		
	}

}
