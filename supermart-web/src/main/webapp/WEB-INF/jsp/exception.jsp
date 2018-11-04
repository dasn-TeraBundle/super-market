<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"  session="false"%>
<!DOCTYPE html >
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Exception</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="container">
        <div style="padding-top: 3%">
           <h3 style="color:red">Exception</h3>
           <p>
               Type : <%= exception.getClass() %><br />
               Message : <%= exception.getMessage() %><br />
           </p>
           <p>Click the back button to continue.</p>
        </div>
    </div>
</body>
</html>