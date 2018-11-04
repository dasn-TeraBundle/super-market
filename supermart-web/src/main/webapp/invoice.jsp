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

    <title>Invoice-${invoice.id}</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
    <div class="p-2"></div>
    <h3 class="text-center">Invoice Summary</h3>
    <div>
        Invoice Number : ${invoice.id} <br/>
        Bill Time : ${invoice.billingDate} <br />
        Total Amount : ${total}
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Cost</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${invoice.products}" var="p">
                <tr>
                    <td>${p.name}</td>
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
