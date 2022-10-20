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
<body>
    <div class="container">
        <div class="header">
            <div class="title">AVA ARS</div>
            <div class="navbar">
                <ul>
                    <li><a href="https://github.com/virat2110" target="_blank" rel="noopener noreferrer">Git hub</a></li>
                    <li><a href="https://www.linkedin.com/in/virat-anand-842498169" target="_blank" rel="noopener noreferrer">LinkedIn</a></li>
                    
                    <a href="login"><li class="login">Login</li></a>
                </ul>
            </div>
        </div>
        <div class="content">
            <div class="textarea">
                Our application intends to help people to find the cheapest<br />
                 flight,with many more functionalities like search for flight, <br />
                discounts on booking, easy cancellation and many more under one hood. <br />
                <a href="register"><button class="button-68">Join us</button></a>
            </div>
            <div class="image">
                <img src="/views/2.png" alt="no image" height = "300"/>
            </div>
            </div>
        </div>
    </div>
</body>


<style>
body {
    background: rgb(19,91,176);
	background: linear-gradient(90deg, rgba(19,91,176,1) 0%, rgba(33,141,175,1) 55%, rgba(0,212,255,1) 100%);
    font-family: 'Fredoka', sans-serif;
    font-family: 'Lato', sans-serif;
    padding: 0;
    
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
}
.login {
	background-color: rgb(171, 75, 157);
    border-radius: 10px;
    
}
.content {
    display: flex;
}
.textarea{
    width: 50%;
    padding: 60px;
    margin-top: 80px;
    font-size: 20px;
    align-items: left;
    justify-content: center;
    display: flex;
    flex-direction: column;
    color: white;
}
.image {
    margin-top: 150px;
    margin-left: 40px;
    width : 100px;
    height: 100px;
   
}
.button-68 {
  margin-top: 50px;
  appearance: none;
  backface-visibility: hidden;
  background-color:white;
  border-radius: 10px;
  border-style: none;
  box-shadow: rgba(39, 174, 96, .15) 0 4px 9px;
  box-sizing: border-box;
  color:black;
  cursor: pointer;
  display: inline-block;
  font-family: Inter,-apple-system,system-ui,"Segoe UI",Helvetica,Arial,sans-serif;
  font-size: 16px;
  font-weight: 600;
  letter-spacing: normal;
  line-height: 1.5;
  outline: none;
  overflow: hidden;
  padding: 13px 20px;
  position: relative;
  text-align: center;
  text-decoration: none;
  transform: translate3d(0, 0, 0);
  transition: all .3s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: top;
  white-space: nowrap;
}

.button-68:hover {
  background-color:cyan;
  opacity: 1;
  transform: translateY(0);
  transition-duration: .35s;
}

.button-68:active {
  transform: translateY(2px);
  transition-duration: .35s;
}

.button-68:hover {
  box-shadow: rgba(39, 174, 96, .2) 0 6px 12px;
}
</style>
</html>