<html>
<head>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  <title>Test Application</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
     <c:if test = "${param.selectedLink == 'Home'}">
    	<li class="nav-item active">
      	 <a class="nav-link" href="index.jsp">Home</a>
    	</li>
    </c:if>
    <c:if test = "${param.selectedLink != 'Home'}">
    	<li class="nav-item">
      	 <a class="nav-link" href="index.jsp">Home</a>
    	</li>
    </c:if>
  
  
    <li class="nav-item">
     
    </li>
    <c:if test = "${param.selectedLink == 'UserRegistration'}">
    	<li class="nav-item active">
      	<a class="nav-link" href="UserRegistrationForm.jsp">User Registration</a>
    	</li>
    </c:if>
    <c:if test = "${param.selectedLink != 'UserRegistration'}">
    	<li class="nav-item">
      	<a class="nav-link" href="UserRegistrationForm.jsp">User Registration</a>
    	</li>
    </c:if>
    
    <c:if test = "${param.selectedLink == 'RegisteredUserReport'}">
    	<li class="nav-item active">
      	<a class="nav-link" href="RegisteredUserReport.jsp">Registered User Report</a>
    	</li>
    </c:if>
    <c:if test = "${param.selectedLink != 'RegisteredUserReport'}">
    	<li class="nav-item">
      	<a class="nav-link" href="RegisteredUserReport.jsp">Registered User Report</a>
    	</li>
    </c:if>
  </ul>
</nav>
 
</body>
</html>