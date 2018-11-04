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

    <title>Create Invoice</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
    <div class="p-2"></div>

    <form method="post" action="invoice-create">
        <div class="form-group">
            <label for="pr_1">Name</label>
            <select id="pr_1" name="pr_1">
                <option value="">Select</option>
                <c:forEach items="${products}" var="p">
                    <option value="${p.id}">${p.name}</option>
                </c:forEach>
            </select>
            <label for="quant_1">Quantity</label>
            <input type="number" min="1" id="quant_1" name="quant_1" />
        </div>
        <div class="form-group">
            <label for="pr_2">Name</label>
            <select id="pr_2" name="pr_2">
                <option value="">Select</option>
                <c:forEach items="${products}" var="p">
                    <option value="${p.id}">${p.name}</option>
                </c:forEach>
            </select>
            <label for="quant_2">Quantity</label>
            <input type="number" min="1" id="quant_2" name="quant_2" />
        </div>
        <button type="submit" class="btn btn-primary">Generate Bill</button>
    </form>
</div>
</body>
</html>