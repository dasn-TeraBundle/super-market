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

    <title>Inventory</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">
    <div class="row" style="padding-top: 5%">
        <div class="col-6 offset-3">
            <form method="post" action="login">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" name="username" required/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password" required/>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
