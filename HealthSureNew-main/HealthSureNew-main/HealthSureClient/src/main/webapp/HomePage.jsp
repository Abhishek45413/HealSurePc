<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>

<!DOCTYPE html>
<f:view>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #ffecd2, #fcb69f);
        margin: 0;
        padding: 0;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    header {
        background-color: #ff7e5f;
        padding: 20px 40px;
        color: white;
        text-align: center;
        font-size: 24px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    nav {
        background-color: #fff;
        padding: 10px 40px;
        display: flex;
        justify-content: center;
        gap: 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    nav a, nav .link-button {
        text-decoration: none;
        color: #333;
        font-weight: bold;
        padding: 8px 15px;
        border-radius: 8px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    nav a:hover, nav .link-button:hover {
        background-color: #ff7e5f;
        color: white;
    }

    .content {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        padding: 50px;
    }

    .content h2 {
        font-size: 32px;
        color: #444;
        margin-bottom: 20px;
    }

    .content p {
        font-size: 18px;
        color: #666;
        max-width: 600px;
    }

    footer {
        background-color: #fff;
        padding: 15px 0;
        text-align: center;
        font-size: 14px;
        color: #777;
        box-shadow: 0 -2px 5px rgba(0,0,0,0.1);
    }
</style>
</head>
<body>

<header>
    Welcome to HealthSure Portal
</header>

<nav>
    <h:form>
        <h:commandLink value="Home" action="HomePage"/>
        <h:commandLink value="SignUp" action="SignUp"/>
        <h:commandLink value="Login" action="Login"/>
        <h:commandLink value="Contact Us" action="Contact"/>
        <h:commandLink value="Logout" action="Logout"/>
    </h:form>
</nav>

<div class="content">
    <div>
        <h:outputText value="Your Health, Our Priority" style="font-size:32px; color:#444; margin-bottom:20px; display:block;" />
        <h:outputText value="Thank you for logging in. Explore the portal to manage your profile, access healthcare services, 
            and stay updated with the latest health information." 
            style="font-size:18px; color:#666; max-width:600px; display:block;" />
    </div>
</div>

<footer>
    <h:outputText value="© 2025 HealthSure. All rights reserved." />
</footer>

</body>
</html>
</f:view>
