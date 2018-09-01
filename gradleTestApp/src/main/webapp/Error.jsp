<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/menu/NavBar.jsp">
        <jsp:param name="selectedLink" value=""/>
</jsp:include>

<meta charset="ISO-8859-1">
<title>Error page</title>
</head>
<body>
<div class="row">
  <div class="col-sm-1"></div>
  <div class="col-lg-5">
  <table class="table table-striped table-bordered">
  			<tr class="table table-primary">
  				<th> Error</th>
  			</tr>
  			<tr class="table table-default">
  				
  			</tr>
  	</table>
  	<div class="alert alert-danger error" id="errorDiv">
  			An Unexpected error has occurred. 
  			Please contact an administrator.
  	</div>
  </div>
</div>
</body>
</html>