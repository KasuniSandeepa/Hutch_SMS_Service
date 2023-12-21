package com.hutch.service.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutch.service.sms.DatabaseConnection;

import java.sql.DriverManager;
 import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
/**
 * Servlet implementation class IncomingCalls
 */
@WebServlet("/IncomingCalls")
public class IncomingCalls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomingCalls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DatabaseHandler db= new DatabaseHandler();
		
		Connection con = null;
		try
		{
	//	con = DatabaseConnection.getInstance().getConnection();
		//Statement st=con.createStatement();
		
//		String strQuery = "SELECT COUNT(*) FROM incoming_call WHERE Serv='94788100000'";
		ResultSet CountPhoneNumber01 = db.query("SELECT COUNT(*) FROM incoming_call WHERE Serv='94788100000'");
		ResultSet CountPhoneNumber02 = db.query("SELECT COUNT(*) as Count FROM incoming_call WHERE Serv='94788100100'");
		if (CountPhoneNumber02.next()) {
			int count1=CountPhoneNumber02.getInt("Count");
			request.setAttribute("value1", count1);
		}
		ResultSet CountTotalEngagement = db.query("SELECT COUNT(*) as Count FROM incoming_call");
		if (CountTotalEngagement.next()) {
			int count1=CountTotalEngagement.getInt("Count");
			request.setAttribute("value2", count1);
		}
		
		ResultSet CountSentMessages = db.query("SELECT COUNT(*) as Count FROM sent_messages");
		if (CountSentMessages.next()) {
			int count1=CountSentMessages.getInt("Count");
			request.setAttribute("value3", count1);
		}
		
		ResultSet CountDeleveredMessages = db.query("SELECT COUNT(*) as Count FROM sms_api_response WHERE response LIKE '%success%' ");
		if (CountDeleveredMessages.next()) {
			int count1=CountDeleveredMessages.getInt("Count");
			request.setAttribute("value4", count1);
		}
//		ResultSet rs = st.executeQuery(strQuery);
		String Countrow="";
		while(CountPhoneNumber01.next()){
		Countrow = CountPhoneNumber01.getString(1);
		System.out.println("Total Row :" +Countrow);
		request.setAttribute("value", Countrow);
	    request.getRequestDispatcher("/UI.jsp").forward(request, response);
	    
	 
		}
		}
		catch (Exception e){
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
