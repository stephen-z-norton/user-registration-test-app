<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test Application</title>
</head>
<body>
		<jsp:include page="/menu/NavBar.jsp">
		        <jsp:param name="selectedLink" value="Home"/>
		</jsp:include>
		
		<div class="row">
		  <div class="col-sm-1"></div>
		  <div class="col-lg-5">
		  		<table class="table table-striped table-bordered">
		  			<tr class="table table-primary">
		  				<th> Home</th>
		  			</tr>
		  		</table>
		  		<p>
		  		This is a small test application written in java.<br/>
		  		The back end logic is hand written. <br/>
		  		The front end uses jquery and bootstrap <br/>
		
		  		</p>
		  </div>
		</div>

</body>
</html>