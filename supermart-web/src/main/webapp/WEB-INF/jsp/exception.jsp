<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"  session="false"%>
<!DOCTYPE html >
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Exception</title>
  <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
    <div align="center">
        <%@ include file="header.jsp" %>
        <div style="width:900px;background-color:cornsilk" align="left">
           <h3 style="color:red">Exception</h3>
           <p>
               Type : <%= exception.getClass() %><br />
               Message : <%= exception.getMessage() %><br />
           </p>
           <p>Click the back button to continue.</p>
        </div>
        <div style="width:900px" align="center">
              <%@ include file="footer.jsp" %>
        </div>
    </div>
</body>
</html>