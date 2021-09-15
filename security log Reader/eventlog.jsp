<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String driverName = "com.mysql.cj.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "event";
String userId = "root";
String password = "aswin123";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Log Data</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#DEB887">
<td><b>CreationTime</b></td>
<td><b>Source</b></td>
<td><b>EventID</b></td>
<td><b>EventRecordID</b></td>
</tr>
<%
try{ 
	int x = 0;
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="select * from eventlog ORDER BY EventRecordID DESC";

ResultSet rs = statement.executeQuery(sql);
while(x < 25){
	rs.next();
%>
<tr>

<td><%=rs.getString("CreationTime") %></td>
<td><%=rs.getString("Source") %></td>
<td><%=rs.getString("EventID") %></td>
<td><%=rs.getString("EventRecordID") %></td>
<td><form class="container w-25 text-center" style="margin-top: 100px;" action="index" method="get"><button type="submit" name="evtid" value=<%=rs.getString("EventRecordID") %>>Log Details</button></form></td>
</tr>

<% 
x = x + 1;
}
%>
<form class="container w-25 text-center" style="margin-top: 100px;" action="eventlog1.jsp" method="get"><button type="submit" name="evtid" value=<%=rs.getString("EventRecordID") %>>Next</button></form>
<form class="container w-25 text-center" style="margin-top: 100px;" action="index.jsp" method="get"><button type="submit">Back</button></form>
<%
} catch (Exception e) {
e.printStackTrace();
}
%>

</table>