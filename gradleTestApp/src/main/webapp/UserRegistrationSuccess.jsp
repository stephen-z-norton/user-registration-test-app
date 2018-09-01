<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/menu/NavBar.jsp">
        <jsp:param name="selectedLink" value="Completion"/>
</jsp:include>

<% 
JSONObject obj=new JSONObject(); 
obj=(JSONObject) request.getSession().getAttribute("responseData");

%>
<meta charset="ISO-8859-1">
<title>Confirmation page</title>
</head>
<body>
<div class="row">
  <div class="col-sm-1"></div>
  <div class="col-lg-5">
  <table class="table table-striped table-bordered">
  			<tr class="table table-primary">
  				<th> User Registration Successful</th>
  			</tr>
  			<tr class="table table-default">
  				<td><%=(String)obj.get("firstName") + " " + (String)obj.get("lastName") %></td>
  			</tr>
  	</table>
  </div>
</div>
</body>
</html>