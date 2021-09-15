package com.aswin.model;

public class User {
	private String EventRecordID;
	private String Account_Name;
	private String Account_Domain;
	private String Logon_ID;
	private String Security_ID1;
	private String Account_Name1;
	private String Account_Domain1;
	private String Process_ID2;
	private String Process_Name2;
	
	public User(String EventRecordID, String Account_Name, String Account_Domain, String Logon_ID, String Security_ID1, String Account_Name1, String Account_Domain1, String Process_ID2, String Process_Name2)
	{
		
		this.EventRecordID = EventRecordID;
		this.Account_Name = Account_Name;
		this.Account_Domain = Account_Domain;
		this.Logon_ID = Logon_ID;
		this.Security_ID1 = Security_ID1;
		this.Account_Name1 = Account_Name1;
		this.Account_Domain1 = Account_Domain1;
		this.Process_ID2 = Process_ID2;
		this.Process_Name2 = Process_Name2;
	}
	
	
	public String getEventRecordID()
	{
		return EventRecordID;
	}
	public String getAccount_Name()
	{
		return Account_Name;
	}
	public String getAccount_Domain()
	{
		return Account_Domain;
	}
	public String getLogon_ID()
	{
		return Logon_ID;
	}
	public String getSecurity_ID1()
	{
		return Security_ID1;
	}
	public String getAccount_Name1()
	{
		return Account_Name1;
	}
	public String getAccount_Domain1()
	{
		return Account_Domain1;
	}
	public String getProcess_ID2()
	{
		return Process_ID2;
	}
	public String getProcess_Name2()
	{
		return Process_Name2;
	}
}

