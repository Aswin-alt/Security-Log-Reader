package com.aswin.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.lang.StringBuilder;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aswin.dao.DAO;
import com.aswin.model.User1;

/**
 * Servlet implementation class EventNative1
 */
//@WebServlet("/eventNative1")
@SuppressWarnings("serial")
public class EventNative1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StringBuilder content = new StringBuilder();
	public static String latest_time = "2021-09-08T12:02:25.5944995Z";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventNative1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void logDetail(String logdata) throws NullPointerException{
		System.out.println("Hi : 11");
		content.append(logdata);
		content.append(System.lineSeparator());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          ByteArrayInputStream input = new ByteArrayInputStream(logdata.getBytes("UTF-8"));

          Document doc = db.parse(input);

          // optional, but recommended
          doc.getDocumentElement().normalize();
			
			//Root node
          //System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          
          // get <staff>
		  NodeList list = doc.getElementsByTagName("Event");
			
          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);
			System.out.println("\nCurrent Element :" + node.getNodeName());
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
					
				  NodeList provider = element.getElementsByTagName("Provider");
                  // get Provider's attribute
                  //String name = provider.getAttribute("Name");
				  String Source = provider.item(0).getAttributes().getNamedItem("Name").getTextContent();

                  // get text
                  String EventID = element.getElementsByTagName("EventID").item(0).getTextContent();
                  String Task = element.getElementsByTagName("Task").item(0).getTextContent();
                  String EventRecordID = element.getElementsByTagName("EventRecordID").item(0).getTextContent();
					
					NodeList time = element.getElementsByTagName("TimeCreated");
					String CreationTime = time.item(0).getAttributes().getNamedItem("SystemTime").getTextContent();

                  //NodeList salaryNodeList = element.getElementsByTagName("salary");
                  //String salary = salaryNodeList.item(0).getTextContent();

                  // get salary's attribute
                  //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                  //System.out.println("Current Element :" + node.getNodeName());
                  System.out.println("Event Name : " + Source);
				  System.out.println("Event Time of creation : " + CreationTime);
                  System.out.println("EventID : " + EventID);
                  System.out.println("Task ID : " + Task);
                  System.out.println("EventRecordID : " + EventRecordID);
                
					NodeList data1 = element.getElementsByTagName("Data");
					
                  // get Data's attribute
				  /*for(int i = 0;i < data1.getLength();i++){
                  name1 = data1.item(i).getTextContent();
				  data = data1.item(i).getAttributes().getNamedItem("Name").getTextContent();
				
        
                  System.out.println("User Name : " + name1);
				  System.out.println("Data Name : " + data);
				  }*/
				  //data = data1.item(0).getAttributes().getNamedItem("Name").getTextContent();
                  
				  System.out.println("\nDetails : ");
				  String Account_Name = data1.item(4).getTextContent();
				  String Account_Domain = data1.item(5).getTextContent();
				  String Logon_ID = data1.item(6).getTextContent();
				  
				  String Account_Name1 = data1.item(0).getTextContent();
				  String Account_Domain1 = data1.item(1).getTextContent();
				  String Security_ID1 = Account_Domain1 + "\\" + Account_Name1;
				  
				  String Process_ID2 = data1.item(7).getTextContent();
				  String Process_Name2 = data1.item(8).getTextContent();
				  
				  System.out.println("Account_Name : " + Account_Name);
				  System.out.println("Account_Domain : " + Account_Domain);
                  System.out.println("Logon_ID : " + Logon_ID);
                  System.out.println("Account_Name1 : " + Account_Name1);
                  System.out.println("Account_Domain1 : " + Account_Domain1);
                  System.out.println("Security_ID1 : " + Security_ID1);
                  System.out.println("Process_ID2 : " + Process_ID2);
                  System.out.println("Process_Name2 : " + Process_Name2);
                  latest_time = CreationTime;
				  
				  try{
					  System.out.println("Event");
					  User1 u = new User1(Source, CreationTime, EventRecordID, EventID, Account_Name, Account_Domain, Logon_ID, Security_ID1, Account_Name1, Account_Domain1, Process_ID2, Process_Name2);
					  DAO dao = new DAO();
					  dao.insertData(u);
					  System.out.println("Event1");
				  }
				  catch(SQLIntegrityConstraintViolationException e) {
						// TODO Auto-generated catch block
						continue;
						} 
				  catch(ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
					catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("Proccessing request!");
		out.println("Done...Thanks!");
		out.println("<form class=\"container w-25 text-center\" style=\"margin-top: 100px;\" action=\"index.jsp\" method=\"get\"><button type=\"submit\">Back</button></form>");
		EventNative1 ob = new EventNative1();
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			latest_time = dao.get_time();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hi1 : " + latest_time);
		String nativeQuery = "Event/System[(EventID = 4798) and TimeCreated[@SystemTime >= '" + latest_time + "']]";
		ob.log(nativeQuery); // call a native method
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	// declare native method
		public native void log(String nativeQuery);
			
		// load DLL that contains static method
		static {
			System.loadLibrary("Native_Eve1");
		}
		 public void destroy() {
				// TODO Auto-generated method stub
			}
}
