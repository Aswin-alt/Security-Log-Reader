package com.aswin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aswin.model.User;
import com.aswin.model.User1;
public class DAO {
	Connection con=null;
	
	Statement st = null;
	public DAO() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event","root","aswin123");
		
	}
	
	
	public User getData(String evtid) throws SQLException{
		String view_query = "Select * from eventlog where EventRecordID=\"" + evtid + "\"";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(view_query);
		rs.next();
		String EventRecordID = rs.getString("EventRecordID");
		String Account_Name = rs.getString("Account_Name");
		String Account_Domain = rs.getString("Account_Domain");
		String Logon_ID = rs.getString("Logon_ID");
		String Security_ID1 = rs.getString("Security_ID1");
		String Account_Name1 = rs.getString("Account_Name1");
		String Account_Domain1 = rs.getString("Account_Domain1");
		String Process_ID2 = rs.getString("Process_ID2");
		String Process_Name2 = rs.getString("Process_Name2");

		User u = new User(EventRecordID, Account_Name, Account_Domain, Logon_ID, Security_ID1, Account_Name1, Account_Domain1, Process_ID2, Process_Name2);
		
		rs.close();
		con.close();
		
		return u;
	}
	
	public String get_time() throws SQLException{
		String view_query = "Select * from eventlog ORDER BY EventRecordID DESC";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(view_query);
		rs.next();
		String EventRecordID = rs.getString("EventRecordID");
		String CreationTime = rs.getString("CreationTime");

		
		rs.close();
		con.close();
		
		return CreationTime;
	}
	
	public void insertData(User1 user) throws SQLException {
		String INSERT_DATA = "insert into eventlog(Source, CreationTime, EventRecordID, EventID, Account_Name, Account_Domain, Logon_ID, Security_ID1, Account_Name1, Account_Domain1, Process_ID2, Process_Name2) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pr = con.prepareStatement(INSERT_DATA);
		pr.setString(1, user.getSource());
		pr.setString(2, user.getCreationTime());
		pr.setString(3, user.getEventRecordID());
		pr.setString(4, user.getEventID());
		pr.setString(5, user.getAccount_Name());
		pr.setString(6, user.getAccount_Domain());
		pr.setString(7, user.getLogon_ID());
		pr.setString(8, user.getSecurity_ID1());
		pr.setString(9, user.getAccount_Name1());
		pr.setString(10, user.getAccount_Domain1());
		pr.setString(11, user.getProcess_ID2());
		pr.setString(12, user.getProcess_Name2());
		
		pr.executeUpdate();
		
		pr.close();
		con.close();
	}
}

