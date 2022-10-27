<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AVA-ARS | ADMIN</title>
</head>
<body>
<%@include file="navadmin.jsp" %>
<h1 class="error">You are not authenticated, Please contact your admin.</h1>

</body>
<style> 
body{
  font-family: 'Open Sans', sans-serif;
  background: rgb(19,91,176);
  background: linear-gradient(90deg, rgba(19,91,176,1) 0%, rgba(33,141,175,1) 55%, rgba(0,212,255,1) 100%);
  margin: 0 auto 0 auto;  
  width:100%; 
  text-align:center;
  margin: 0px 0px 20px 0px; 
}
.error {
text-align: center;
margin-top: 50px;
color: #830A29;
}
</style>
</html>