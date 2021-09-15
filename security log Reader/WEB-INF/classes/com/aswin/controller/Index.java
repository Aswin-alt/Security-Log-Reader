package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aswin.dao.DAO;
import com.aswin.model.User;

/**
 * Servlet implementation class Index
 */
//@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String evtid = request.getParameter("evtid");
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			User u = dao.getData(evtid);
			out.println("Event RecordID : " + u.getEventRecordID());
			out.println("<br>Keywords : Audit Success<br>");
			out.println("Task Category : User Account Management<br><br>");
			out.println("Security :<br>");
			out.println("Account Name : " + u.getAccount_Name() + "<br>");
			out.println("Account Domain : " + u.getAccount_Domain() + "<br>");
			out.println("Logon ID : " + u.getLogon_ID() + "<br>");
			out.println("<br>User :<br>");
			out.println("Security ID : " + u.getSecurity_ID1() + "<br>");
			out.println("Account Name : " + u.getAccount_Name1() + "<br>");
			out.println("Account Domain : " + u.getAccount_Domain1() + "<br>");
			out.println("<br>Process Information :<br>");
			out.println("Process ID : " + u.getProcess_ID2() + "<br>");
			out.println("Process Name : " + u.getProcess_Name2() + "<br>");
			out.println("<form class=\"container w-25 text-center\" style=\"margin-top: 100px;\" action=\"index.jsp\" method=\"get\"><button type=\"submit\">Back</button></form>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}