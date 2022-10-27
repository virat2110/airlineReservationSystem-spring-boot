<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AVA-ARS|Login</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka&family=Lato&display=swap" rel="stylesheet">
   
</head>
<body>
  <%@include file="navadmin.jsp" %>
		
		<div class="cont">

        <div class="box">
        <div class="column">
        <h3>Total Flight:100	</h3>
        <h3>Flight cancelled:100	</h3>
        <h3>source covering:100	</h3>
        <h3>Dest. covering: 100	</h3>
        
        </div>
        
        <div class="column">
        <h3>Total user:100		</h3>
        <h3>Ticket cancelled:100	</h3>
         <h3>Ticket booked:100		</h3>
      <h3>Running late:100	</h3>
        </div>
       
        
          
          
        </div>
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
margin-left:100px;
}
.cont::before{
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(#6f0681,#ce17dc);
    clip-path: circle(20% at right 92%);
    z-index: -99999;
}

.cont::after{
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(#6f0681,#ce17dc);
    clip-path: circle(15% at 5% 5%);
    z-index: -99999;
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
  width:500px;
  border-radius:6px;
  margin-top: 170px;
  margin-left: 10px;
  padding:20px 10px 10px 10px;
  border: #3A5743 4px solid; 
  display : flex;
  float : left;
}
.column {
  flex: 50%;
  text-align: left;
}
.email{
  background:#ecf0f1;
  border: #ccc 1px solid;
  border-bottom: #ccc 2px solid;
  padding: 8px;
  width:250px;
  color:#AAAAAA;
  margin-top:10px;
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
background:green; 
cursor:pointer;
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
  margin-left:10px;
  font-weight:800;
  font-size:0.8em;
}
#btn2:hover{ 
background:green; 
cursor:pointer;
}
</style>
</html>