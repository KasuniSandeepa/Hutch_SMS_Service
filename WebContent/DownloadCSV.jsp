<%@ page import="java.io.*,java.sql.*"%>
<%@page import="com.hutch.service.sms.util.PropertyReader"%>
<html>
<body>
<%
//File filename = new File("E:\\csv");
//String filename = "C:\\Users\\94766\\eclipse-workspace\\HUTCH.SMS.SERVICE\\IncomingCalls.csv";
File filePath = File.createTempFile("Incoming Calls Report", ".csv", new File("C:\\Users\\94766\\eclipse-workspace\\HUTCH.SMS.SERVICE"));
Connection conn = null;
String url = "jdbc:mysql://localhost:3306/";
String dbName = "hutch";
String driver = "com.mysql.jdbc.Driver";
String userName = "root";
String password = "root";
Statement stmt;
try
{
FileWriter fw = new FileWriter(filePath);
fw.append("Incoming Caller Id");
fw.append(',');
fw.append("IVR Number");
fw.append(',');
fw.append("Called Date & Time");
fw.append('\n');
Class.forName(driver).newInstance();
conn = DriverManager.getConnection(url+dbName,userName,password);
String query = "select * from incoming_call";
stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next())
{
fw.append(rs.getString(2));
fw.append(',');
fw.append(rs.getString(3));
fw.append(',');
fw.append(rs.getString(4));
fw.append('\n');
}
fw.flush();
fw.close();
conn.close();

} catch (Exception e) {
e.printStackTrace();
}
%>

</body>
</html>