<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Successful</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #74ebd5, #9face6);
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        color: #333;
    }

    .success-container {
        background-color: #fff;
        padding: 40px 60px;
        border-radius: 15px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        text-align: center;
        max-width: 400px;
    }

    .success-container h1 {
        font-size: 28px;
        color: #4CAF50;
        margin-bottom: 20px;
    }

    .success-container p {
        font-size: 16px;
        margin-bottom: 30px;
    }

    .success-container a {
        text-decoration: none;
        background-color: #4CAF50;
        color: #fff;
        padding: 10px 20px;
        border-radius: 8px;
        font-weight: bold;
        transition: background-color 0.3s ease;
    }

    .success-container a:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

<div class="success-container">
    <h1>Login Successful!</h1>
    <p>Welcome back! You have successfully logged in.</p>
    <a href="HomePage.jsf">Go to Home</a>
</div>

</body>
</html>
