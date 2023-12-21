package com.hutch.service.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutch.service.sms.Login;


/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */   public static final Logger logger = Logger.getLogger(UserLogin.class.getName());
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String username = request.getParameter("username");
	        String password = request.getParameter("password");
            System.out.println("this is"+username);
	        Login l = new Login();

	        //if login success redirect to UIDashboard ,if login unsuccess display same page
	        if(l.userLogin(username,password)) {
	           // RequestDispatcher dis = request.getRequestDispatcher("UI.jsp");
	            response.sendRedirect("IncomingCalls");  
	           // dis.forward(request, response);
	        }
	        else{
	            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
	            request.setAttribute("errorMessage", "Invalid user or password");
             
	            dis.forward(request,response);
	           
	            
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
