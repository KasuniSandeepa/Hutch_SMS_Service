<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.hutch.service.servlet.IncomingCalls"%>
<%@page import="com.hutch.service.sms.util.PropertyReader"%>
<!DOCTYPE html>
<html>
<%
    Object CountPhoneNumber01 = request.getAttribute("value");
%>
<%
    Object CountPhoneNumber02 = request.getAttribute("value1");


%>

<%
    Object CountTotalEngagement = request.getAttribute("value2");


%>
<%
    Object CountSentMessages = request.getAttribute("value3");


%>
<%
    Object CountDeliveredMessages = request.getAttribute("value4");


%>

<head>


    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>HUTCH SMS DASHBOARD</title>
    <link href="https://fonts.googleapis.com/css?family=Nunito:300,400,400i,600,700,800,900" rel="stylesheet" />
    <link href="dist-assets/css/themes/lite-purple.min.css" rel="stylesheet" />
    <link href="dist-assets/css/plugins/perfect-scrollbar.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="dist-assets/css/plugins/ladda-themeless.min.css" />
</head>

<body class="text-left" >

<div class="app-admin-wrap layout-sidebar-large">
    <div class="main-header">
        <div class="logo">
            <img src="dist-assets/images/logo.png" style="width: 100%; height: 100%;     margin-left: 5px;" alt="">
        </div>
        <div class="menu-toggle">
            <div></div>
            <div></div>
            <div></div>
        </div>
        <div class="d-flex align-items-center">
            <!-- Mega menu -->

        </div>
        <div style="margin: auto"></div>
        <div class="header-part-right" >
            <!-- Full screen toggle -->
            <i class="i-Full-Screen header-icon d-none d-sm-inline-block" data-fullscreen></i>
            <!-- Grid menu Dropdown -->

            <!-- Notificaiton -->
           
            <!-- Notificaiton End -->
            <!-- User avatar dropdown -->
            <div class="dropdown">
                <div class="user col align-self-end">
                    <img src="dist-assets/images/faces/1.jpg" id="userDropdown" alt="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <div class="dropdown-header">
                            <i class="i-Lock-User mr-1"></i> Admin
                        </div>
                      
                        <a class="dropdown-item" href="UI.jsp">Sign out</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="side-content-wrap">
        <div class="sidebar-left open rtl-ps-none" data-perfect-scrollbar="" data-suppress-scroll-x="true">
            <ul class="navigation-left">
                <li class="nav-item" data-item="dashboard"><a class="nav-item-hold" href="#"><i class="nav-icon i-Bar-Chart"></i><span class="nav-text">Dashboard</span></a>
                    <div class="triangle"></div>
                </li>

            </ul>
        </div>

        <div class="sidebar-overlay"></div>
    </div>
    <!-- =============== Left side End ================-->
    <div class="main-content-wrap sidenav-open d-flex flex-column">
        <!-- ============ Body content start ============= -->
        <div class="main-content">
            <div class="breadcrumb">
                <h1 class="mr-2">HUTCH SMS SERVICE</h1>
                <ul>
                    <li><a href="">Dashboard</a></li>

                </ul>
            </div>
            <div class="separator-breadcrumb border-top"></div>
            <div class="row">
                <div class="col-md-1 col-lg-1 col-sm-1">
                </div>
                <div class="col-lg-6 col-md-12">

                    <!-- CARD ICON-->
                    <div class="row">
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Telephone-2"></i>
                                    <p class="text-muted mt-2 mb-2">Phone Number 01</p>
                                    <p class="text-primary text-24 line-height-1 m-0" id="PhoneNumber01"><%=CountPhoneNumber01%></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Telephone-2"></i>
                                    <p class="text-muted mt-2 mb-2">Phone Number 02</p>
                                    <p class="text-primary text-24 line-height-1 m-0" id="PhoneNumber02"><%=CountPhoneNumber02%></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Receipt-4"></i>
                                    <p class="text-muted mt-2 mb-2">Total Engagement</p>
                                    <p class="text-primary text-24 line-height-1 m-0" id="TotalMissedCalls"><%=CountTotalEngagement%></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Mail-2"></i>
                                    <p class="text-muted mt-2 mb-2">Sent</p>
                                    <p class="text-primary text-24 line-height-1 m-0" id="SentMessages"><%=CountSentMessages %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Mail-Read"></i>
                                    <p class="text-muted mt-2 mb-2">Delivered</p>
                                    <p class="text-primary text-24 line-height-1 m-0" id="DeliveredMessages"><%=CountDeliveredMessages %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="card card-icon mb-4">
                                <div class="card-body text-center"><i class="i-Download1"></i>
                                    <p>
                                        <button class="btn btn-danger ladda-button example-button m-1" data-style="expand-right">
                                            <span class="ladda-label">Download Report</span>
                                        </button>
                                    </p>
                                </div>

                            </div>
                        </div>
                
                    </div>

                </div>


                <!-- <div class="col-lg-6 col-md-12">
                    <div class="card mb-4">
                        <div class="card-body p-0">
                            <h5 class="card-title m-0 p-3">Sales</h5>
                            <div id="simplePie" style="height: 300px"></div>
                        </div>
                    </div>
                </div> -->

                <div class="col-md-6 col-lg-4 col-sm-6">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="card-title">Analytics</div>
                            <div id="simplePie"></div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- end of row-->
            <!-- end of main-content -->
        </div><!-- Footer Start -->
        <div class="flex-grow-1"></div>

        <!-- fotter end -->
    </div>
</div><!-- ============ Search UI Start ============= -->

<!-- ============ Search UI End ============= -->



<script src="dist-assets/js/plugins/jquery-3.3.1.min.js"></script>
<script src="dist-assets/js/plugins/bootstrap.bundle.min.js"></script>
<script src="dist-assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="dist-assets/js/scripts/script.min.js"></script>
<script src="dist-assets/js/scripts/sidebar.large.script.min.js"></script>
<script src="dist-assets/js/plugins/echarts.min.js"></script>
<script src="dist-assets/js/scripts/echart.options.min.js"></script>
<script src="dist-assets/js/plugins/datatables.min.js"></script>
<script src="dist-assets/js/scripts/dashboard.v2.script.min.js"></script>
<script src="dist-assets/js/scripts/customizer.script.min.js"></script>


<script src="dist-assets/js/plugins/apexcharts.min.js"></script>
<script src="dist-assets/js/plugins/apexcharts.dataseries.js"></script>
<script src="dist-assets/js/scripts/apexPieDonutChart.script.min.js"></script>


<script src="dist-assets/js/plugins/spin.min.js"></script>
<script src="dist-assets/js/plugins/ladda.min.js"></script>
<script src="dist-assets/js/scripts/ladda.script.min.js"></script>


</body>

</html>