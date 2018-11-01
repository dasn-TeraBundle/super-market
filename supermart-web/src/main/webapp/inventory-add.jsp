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
    <form method="post" action="inventory-add">
        <div class="form-group">
            <label for="p_name">Name</label>
            <input type="text" id="p_name" name="p_name" />
        </div>
        <div class="form-group">
            <label for="p_category">Category</label>
            <select id="p_category" name="p_category">
                <c:forEach items="${categories}" var="category">
                    <option value="${ category.id }">${ category.name }</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="p_supplier">Supplier</label>
            <select id="p_supplier" name="p_supplier">
                <c:forEach items="${suppliers}" var="supplier">
                    <option value="${ supplier.id }">${ supplier.name }</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="p_quantity">Quantity</label>
            <input type="number" min="0" step="1" id="p_quantity" name="p_quantity" />
        </div>
        <div class="form-group">
            <label for="p_price">Price</label>
            <input type="number" min="0" step="0.01" id="p_price" name="p_price" />
        </div>
        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
</div>
</body>
</html>
