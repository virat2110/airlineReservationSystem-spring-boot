<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AVA ARS</title>
    

   
</head>


<body>
            <div class="navbar">
             <div class="header">
            
            <div class="title">
            <a href="/">AVA ARS</a>
           </div>
                <ul>
                     <li><a href="https://github.com/virat2110" target="_blank" rel="noopener noreferrer">Git hub</a></li>
                      <li><a href="https://www.linkedin.com/in/virat-anand-842498169" target="_blank" rel="noopener noreferrer">LinkedIn</a></li>
                    <li><a href="login" >Login</a></li>
                    <li><a href="register">Sign up</a></li>
                    
                    
                </ul>
            </div>
            </div>
           
       
</body>


<style>
.navbar{
	background: rgb(21,110,124);
background: linear-gradient(90deg, rgba(21,110,124,1) 13%, rgba(16,131,149,1) 51%, rgba(84,190,207,1) 89%);
}
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