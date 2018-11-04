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

    <title>Inventory Update - ${product.id}</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
    <div class="row p-2">
        <div class="offset-6">
            <a href="inventory-list">View Inventory List</a>
        </div>
    </div>
    <form method="post" action="inventory-update">
        <input type="hidden" name="p_id" value="${product.id}">
        <div class="form-group">
            <label for="p_name">Name</label>
            <input type="text" id="p_name" name="p_name" value="${product.name}" />
        </div>
        <div class="form-group">
            <label for="p_category">Category</label>
            <select id="p_category" name="p_category">
                <c:forEach items="${categories}" var="category">
                    <c:choose>
                        <c:when test="${product.category == category.id}">
                            <option value="${ category.id }" selected>${ category.name }</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${ category.id }">${ category.name }</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="p_supplier">Supplier</label>
            <select id="p_supplier" name="p_supplier">
                <c:forEach items="${suppliers}" var="supplier">
                    <c:choose>
                        <c:when test="${product.supplier == supplier.id}">
                            <option value="${ supplier.id }" selected>${ supplier.name }</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${ supplier.id }">${ supplier.name }</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="p_quantity">Quantity</label>
            <input type="number" min="0" step="1" id="p_quantity" name="p_quantity" value="${product.quantity}" />
        </div>
        <div class="form-group">
            <label for="p_price">Price</label>
            <input type="number" min="0" step="0.01" id="p_price" name="p_price" value="${product.price}" />
        </div>
        <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
</div>
</body>
</html>