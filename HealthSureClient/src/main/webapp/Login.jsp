<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!DOCTYPE html>
<f:view>
<html>
<head>
<meta charset="UTF-8">
<title>Provider Login</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f2f5;
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 100vh;
        margin: 0;
        padding-top: 50px;
    }

    .login-container {
        background-color: #fff;
        padding: 30px 40px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        width: 350px;
    }

    .login-title {
        text-align: center;
        font-size: 22px;
        margin-bottom: 10px;
        color: #333;
    }

    .greeting-message {
        text-align: center;
        font-size: 14px;
        color: #777;
        margin-bottom: 20px;
    }

    .form-label {
        font-weight: 500;
        margin-bottom: 5px;
        color: #555;
    }

    .form-input {
        width: 100%;
        padding: 8px 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        margin-bottom: 15px;
        font-size: 14px;
    }

    .login-button {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        border: none;
        border-radius: 8px;
        color: white;
        font-size: 15px;
        cursor: pointer;
        box-shadow: 0 3px 8px rgba(0,0,0,0.2);
    }

    .login-button:hover {
        background-color: #45a049;
    }

    .forgot-link {
        display: block;
        text-align: center;
        margin-top: 10px;
        font-size: 13px;
    }

    .error-message {
        color: red;
        font-size: 12px;
        margin-bottom: 10px;
        text-align: center;
    }

    .signup-link, .ignore-link {
        margin-top: 15px;
        font-size: 13px;
        text-align: center;
    }

    .ignore-button {
        padding: 8px 20px;
        background-color: #ccc;
        border: none;
        border-radius: 8px;
        font-size: 14px;
        cursor: pointer;
    }

    .ignore-button:hover {
        background-color: #bbb;
    }

</style>
</head>
<body>
    <h:form prependId="false">
        <div class="login-container">
            <h2 class="login-title">Provider Login</h2>

            <div class="greeting-message">
                Welcome back! Please enter your credentials to continue.
            </div>

            <h:messages globalOnly="true" styleClass="error-message" />

            <h:panelGrid columns="1" cellpadding="5" style="width:100%;">

                <h:outputLabel value="Email:" styleClass="form-label" />
                <h:inputText value="#{providerController.provider.email}" required="true" styleClass="form-input" />

                <h:outputLabel value="Password:" styleClass="form-label" />
                <h:inputSecret value="#{providerController.provider.password}" required="true" styleClass="form-input" />

                <h:outputLink value="forgotPassword.jsf" styleClass="forgot-link">
                    Forgot Password?
                </h:outputLink>
            </h:panelGrid>

            <h:commandButton value="Login" action="#{providerController.login}" styleClass="login-button" />
        </div>

        <div class="signup-link">
            Don't have an account? <h:outputLink value="SignUp.jsf">Sign Up</h:outputLink>
        </div>

    </h:form>
</body>
</html>
</f:view>
