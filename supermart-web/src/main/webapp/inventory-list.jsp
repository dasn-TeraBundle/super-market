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
    <div class="p-2"></div>
    <h3 class="text-center">List of All Products</h3>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Supplier</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ products }" var="p">
                <tr><td>${p.name}</td>
                    <td>${p.category}</td>
                    <td>${p.supplier}</td>
                    <td>${p.quantity}</td>
                    <td>${p.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
