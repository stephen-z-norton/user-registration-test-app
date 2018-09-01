<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    <%@ page import="com.test.model.UserRegistrationDataModel" %>
     <%@ page import="com.test.controller.UserRegistrationController" %>
    <%@ page import="java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Registered User Report</title>
</head>
<body>
<jsp:include page="/menu/NavBar.jsp">
        <jsp:param name="selectedLink" value="RegisteredUserReport"/>
</jsp:include>
<%
		UserRegistrationController controller = new UserRegistrationController();
		ArrayList<UserRegistrationDataModel> registrations = controller.getRegistrationList();
		request.setAttribute("registrations", registrations);
%>
<div class="row">
  <div class="col-sm-1"></div>
  <div class="col-lg-10">
			<table class="table table-striped table-bordered">
			  <tr class="table table-primary">
			  	<th colspan=9> Registered User Report</th>
			  </tr>
			  <tr  class = "table table-primary">
			  	<th>First Name</th>
			  	<th>Last Name</th>
			  	<th>Address 1</th>
			  	<th>Address 2</th>
			  	<th>City</th>
			  	<th>State</th>
			  	<th>Zip Code</th>
			  	<th>Country</th>
			  	<th>Registration Date</th>
			  </tr>
			  
			  <c:forEach items="${registrations}" var="registration">
				  <tr>
				   	     <td><c:out value="${registration.getFirstName()}" /></td>
				   	     <td><c:out value="${registration.getLastName()}" /></td>
				   	     <td><c:out value="${registration.getAddress1()}" /></td>
				   	     <td><c:out value="${registration.getAddress2()}" /></td>
				   	     <td><c:out value="${registration.getCity()}" /></td>
				   	     <td><c:out value="${registration.getState()}" /></td>
				   	     <td><c:out value="${registration.getZipCode()}" /></td>
				   	     <td><c:out value="${registration.getCountry()}" /></td>
				   	     <td><c:out value="${registration.getRegistrationDate()}" /></td>
				  </tr>
			  </c:forEach>
			
			</table>
  
  
  
  </div>
</div>


</body>
</html>