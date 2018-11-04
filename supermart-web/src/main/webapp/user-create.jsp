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

    <title>Create New User</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
    <div class="p-2"></div>
    <form method="post" action="register">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required />
            <label for="surname">Surname</label>
            <input type="text" id="surname" name="surname" required />
        </div>
        <div class="form-group">
            <label for="adress">Address</label>
            <textarea id="adress" name="adress" rows="3" required ></textarea>
        </div>
        <div class="form-group">
            <label for="role">Role</label>
            <select id="role" name="role">
                <option value="CASHIER">CASHIER</option>
                <!--<option value="ADMIN">CASHIER</option>-->
            </select>
        </div>
        <div class="form-group">
            <label for="birthday">Birthday</label>
            <input type="date" id="birthday" name="birthday" required />
            <label for="employment_date">Birthday</label>
            <input type="date" id="employment_date" name="employment_date" required />
        </div>
        <div class="form-group">
            <label for="salary">Username</label>
            <input type="number" id="salary" name="salary" min="1" step="0.5" required />
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>