<%@page import="com.virat.demo.validation.UserAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AVA ARS</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka&family=Lato&display=swap" rel="stylesheet">
</head>
 <%
if(UserAdmin.admin !=1) {
	
	response.sendRedirect(request.getContextPath() + "/login");
}
%>

<body>
            <div class="navbar">
             <div class="header">
            
            <div class="title">
            <a href="#">AVA-ARS ADMIN PAGE</a>
           </div>
                <ul>
                     <li><a href="#">Add Flight</a></li>
                      <li><a href="#" >Flight Data</a></li>
                    <li><a href="login" >Logout</a></li>
                    <li><a href="register"></a></li>
                    
                    
                </ul>
            </div>
            </div>
           
       
</body>


<style>
.navbar ul{
    display: flex;
    margin-right: 70px;
    cursor: pointer;
}
.navbar li:hover{
    color: black;
}
.navbar li {
    padding: 10px 50px 10px 50px;
    list-style: none;
    color: white;
}
a {
    text-decoration: none;
    color:white;
}
a:hover{
color: black;
}
.header {
    display: flex;
    justify-content: space-between;
    font-size: 20px;
    color: #ffff;
}
.title {
    padding: 30px;
}
</style>



</html>