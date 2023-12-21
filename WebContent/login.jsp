<%--
  Created by IntelliJ IDEA.
  User: MacWolfstarr
  Date: 5/14/2021
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
  
      Object errorMessage = request.getAttribute("errorMessage");
 
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>HUTCH SMS SERVICE LOGIN</title>
    <link href="https://fonts.googleapis.com/css?family=Nunito:300,400,400i,600,700,800,900" rel="stylesheet">
    <link href="dist-assets/css/themes/lite-purple.min.css" rel="stylesheet">
 
</head>
<div class="auth-layout-wrap" style="background-image: url(dist-assets/images/login-page.jpg)">
    <div class="auth-content">
        <div class="row">
            <div class=col-lg-3>

            </div>
            <div class="col-lg-6">
        <div class="card o-hidden">
            <div class="row">
                <div class="col-md-12">
                    <div class="p-4">
                        <div class="auth-logo text-center mb-4"><img src="dist-assets/images/logo.png" style="width: 100%; height: 100%;" alt=""></div>
                        <h1 class="mb-3 text-18" style="text-align: center;">HUTCH SMS SERIVICE</h1>
                      <%if(errorMessage != null){ %>
						<div id="errDisplay" style="text-align: center; color:red">
							<p>Invalid Username or Password</p>
						</div>
						<%} %>
                        <form action="<%=request.getContextPath()%>/UserLogin" method="post">
                            <div class="form-group">
                                <label for="email">Username</label>
                                <input class="form-control form-control-rounded" id="username" name="username" type="text">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input class="form-control form-control-rounded" id="password" name="password" type="password">
                            </div>
                            <button class="btn btn-rounded btn-primary btn-block mt-2">Login</button>
                        </form>
                        <div class="mt-3 text-center"><a class="text-muted" href="forgot.html">
                                <u>Forgot Password?</u></a></div>
                    </div>
                </div>
              
            </div>
        </div>
    </div>
    <div class=col-lg-3>

    </div>
       </div>
    </div>
</div>