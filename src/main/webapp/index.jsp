<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>index</title>
</head>
<body>
<%
    String redirectURL = request.getContextPath() + "/login.jsf";
    response.sendRedirect(redirectURL);
%>



</html>
