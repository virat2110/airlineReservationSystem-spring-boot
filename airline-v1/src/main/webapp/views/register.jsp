<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka&family=Lato&display=swap" rel="stylesheet">
</head>
<body>



	<%@include file="nava.jsp" %>
	<div class="cont">

    <form action="RegisterData" method="post">
     
        
         
        <div class="box">
        <h3>Sign up</h3>
        <h4>${error}</h4>
        <input type="text" name="t1"  class="email" placeholder ="username" required/>
        <input type="text" name="t2"  class="email" placeholder ="name" required/>
        <input type="text" name="t3"  class="email"  placeholder ="email" required/>
        <input type="text" name="t4"  class="email"  placeholder ="gender" required/>
        <input type="number" name="t5"  class="email"  placeholder ="age" required/>
        <input type="number" name="t6"  class="email"  placeholder ="mobile No." required/>
         <input type="text" name="t7"  class="email"  placeholder ="address" required/>
         <input type="password" name="t8"  class="email"  placeholder ="password" required/>
         <input type="password" name="t9"  class="email"  placeholder ="re-type password" required/>
         <input type="number" name="t10"  class="email"  placeholder ="encryption-key(donot forget)" required/>
       <input type = "submit" value="sign up" id="btn2"/>
        
        </div>
          
        </form>
        
        
        <div class="image">
                <img src="/views/2.png" alt="no image" height = "300"/>
            </div>
            </div>
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
.cont{
display: flex;
margin-left:250px;
}
.image {
    margin-top: 150px;
    margin-left: 180px;
    width : 100px;
    height: 100px;
   
}
p{
  font-size:12px;
  text-decoration: none;
  color:#ffffff;
}
h1{
  font-size:1.5em;
  color:#525252;
}
.box{
  background:white;
  width:350px;
  border-radius:6px;
  margin-top: 10px;
  margin-left: 50px;
  padding:10px 0px 70px 0px;
  border: #3A5743 4px solid; 
}
.email{
  background:#ecf0f1;
  border: #ccc 1px solid;
  border-bottom: #ccc 2px solid;
  padding: 8px;
  width:250px;
  color:#AAAAAA;
  margin-top:8px;
  font-size:1em;
  border-radius:4px;
}
.password{
  border-radius:4px;
  background:#ecf0f1;
  border: #ccc 1px solid;
  padding: 8px;
  width:250px;
  font-size:1em;
}
.btn{
  background:#2ecc71;
  width:125px;
  padding-top:5px;
  padding-bottom:5px;
  color:white;
  border-radius:4px;
  border: #27ae60 1px solid;
  
  margin-top:20px;
  margin-bottom:20px;
  float:left;
  margin-left:80px;
  font-weight:800;
  font-size:0.8em;
}
.btn:hover{
  background:#2CC06B; 
}
#btn2{
  float:left;
  background:#3498db;
  width:125px;  padding-top:5px;
  padding-bottom:5px;
  color:white;
  border-radius:4px;
  border: #2980b9 1px solid;  
  margin-top:20px;
  margin-bottom:20px;
  margin-left:100px;
  font-weight:800;
  font-size:0.8em;
}
#btn2:hover{ 
background:blue; 
cursor:pointer;
}
</style>
</html>