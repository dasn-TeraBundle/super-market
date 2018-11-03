<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Nirupam
  Date: 01-11-2018
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <title>401-Unauthorized</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div align="container">
    <div style="padding-top: 3%">
        <h3 style="color:red">ERROR !</h3>
        You don't have access to this page.<br/>
        Go to home page.
    </div>
</div>
</body>
</html>