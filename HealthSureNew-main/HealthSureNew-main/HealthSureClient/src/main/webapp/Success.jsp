<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<f:view>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>

<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .success-container {
        background-color: #ffffff;
        padding: 40px 50px;
        border-radius: 15px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 400px;
    }

    .success-icon {
        font-size: 50px;
        color: #4CAF50;
        margin-bottom: 15px;
    }

    .success-container h2 {
        color: #333333;
        font-size: 24px;
        margin-bottom: 10px;
    }

    .success-container p {
        color: #555555;
        font-size: 16px;
        margin-bottom: 25px;
    }

    .login-link {
    background-color: #4CAF50;
    color: white;
    padding: 10px 25px;
    border: none;
    border-radius: 8px;
    font-size: 15px;
    cursor: pointer;
    text-align: center;
    box-shadow: 0 3px 8px rgba(0, 0, 0, 0.2);
}

.login-link:hover {
    background-color: #45a049;
}

    .timer-text {
        margin-top: 10px;
        font-size: 13px;
        color: #999;
    }
</style>

<script>
    let seconds = 15;
    function countdown() {
        const timerElement = document.getElementById('timer');
        if (seconds > 0) {
            timerElement.innerHTML = seconds;
            seconds--;
            setTimeout(countdown, 1000);
        } else {
            window.location.href = 'Login.jsf';
        }
    }

    window.onload = countdown;
</script>

</head>
<body>
<h:form>
    <h:panelGroup layout="block" styleClass="success-container">
        <div class="success-icon">✅</div>
        <h2>Registration Successful!</h2>
        <p>Thank you for signing up. Your Provider account has been submitted for approval.</p>

        <h:commandButton value="Go to Login Page" action="Login.jsp" styleClass="login-link" />

        <p class="timer-text">
           Redirecting to login in <span id="timer">15</span> seconds...<br/>
            Or  click the button to go immediately.
        </p>
        
    </h:panelGroup>
    </h:form>
</body>
</html>
</f:view>
