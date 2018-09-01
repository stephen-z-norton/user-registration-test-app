<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.json.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>User Registration Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var hasErrors = false;
	$("#errorDiv").hide();
	
	$("#submitBtn").click(function() {
		
		addErrorIfValueIsBlank("firstName", "First Name");
		addErrorIfValueIsBlank("lastName", "Last Name");
		addErrorIfValueIsBlank("address1", "Address 1");
		addErrorIfValueIsBlank("address2", "Address 2");
		addErrorIfValueIsBlank("city", "City");
		addErrorIfValueIsBlank("state", "State");
		addErrorIfValueIsBlank("zipCode", "Zip Code");
		addErrorIfValueIsBlank("country", "Country");
		
		if(!hasErrors) {
			userRegistrationForm.submit();
		}
		else {
			$("#errorDiv").show();
		}
	});
	
	$(".errorDiv").click(function() {
		$(".errorDiv").hide();
	});
	function addErrorIfValueIsBlank(elementName, displayName) {	
		if(!$.trim($("#" + elementName).val())) {
			hasErrors = true;
			var html = $("#errorDiv").html();
			$("#errorDiv").html(html + "<strong> Error:</strong> " + displayName + " is required!<br/>");
		}
	}
});
</script>

</head>
<body>
<% 
//Get any errors from server side validation
JSONObject obj=new JSONObject(); 
obj=(JSONObject) request.getSession().getAttribute("responseData");

String errors = null;
if(obj != null && !obj.isEmpty()) {
	
	try{
		errors = (String) obj.get("errors");
	} catch (JSONException jse) {
		;//ignore
	}
}
request.setAttribute("errors", errors);
%>
<jsp:include page="/menu/NavBar.jsp">
        <jsp:param name="selectedLink" value="UserRegistration"/>
</jsp:include>
<div class="row">
  <div class="col-sm-1"></div>
  <div class="col-lg-5">
  		<table class="table table-striped table-bordered">
  			<tr class="table table-primary">
  				<th> New User Registration</th>
  			</tr>
  		</table>
  		<form name="userRegistrationForm" id="form" method="post" action="app/UserRegistrationController.registerNewUser">
  		<div class="errorDiv alert alert-danger error" id="errorDiv">
  		</div>
		 <c:if test = "${not empty errors}">
			 <div class="errorDiv alert alert-danger error" id="errorDiv2">
				${errors}
  			</div>
		 </c:if>
		<div class="form-group">
			<label class="label label-primary">First Name:</label>
			<input class="form-control" type="text" name="firstName" id="firstName" placeholder="Enter first name" maxlength="50" value="${firstName}"/><br/>
		</div>
		<div class="form-group">
			<label>Last Name:</label>
			<input class="form-control" type="text" name="lastName" id="lastName" placeholder="Enter last name" maxlength="50" value="${lastName}"><br/>
		</div>
		<div class="form-group">
			<label>Address 1:</label>
			<input class="form-control" type="text" name="address1" id="address1" placeholder="Enter address 1" maxlength="100" value="${address1}"><br/>
		</div>
		<div class="form-group">
			<label>Address 2:</label>
			<input class="form-control" type="text" name="address2" id="address2" placeholder="Enter address 2" maxlength="100" value="${address2}"><br/>
		</div>
		<div class="form-group">
			<label>City:</label>
			<input class="form-control" type="text" name="city" id="city" placeholder="Enter city" maxlength="50" value="${city}"><br/>
		</div>
		<div class="form-group">
			<label>State:</label>
			<input class="form-control" type="text" name="state" id="state" placeholder="Enter state" maxlength="2" value="${state}"><br/>
		</div>
		<div class="form-group">
			<label>Zip Code:</label>
			<input class="form-control" type="text" name="zipCode" id="zipCode" placeholder="Enter zip code" maxlength="10" value="${zipCode}"><br/>
		</div>
		<div class="form-group">
			<label>Country:</label>
			<input class="form-control" type="text" name="country" id="country" placeholder="Enter country" maxlength="5" value ="${country}"><br/>
		</div>

		<button type="button" id="submitBtn" class="btn btn-primary">Submit</button>
		</form>
  
 
  </div>
</div>

</body>
</html>