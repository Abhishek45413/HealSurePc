<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
    
<!DOCTYPE html>
<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Provider OTP Verification</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
            color: #333333;
        }

        .form-label {
            font-weight: 500;
            color: #555555;
            display: block;
            margin-top: 10px;
        }

        .form-input {
            width: 100%;
            padding: 8px 10px;
            border-radius: 8px;
            border: 1px solid #cccccc;
            margin-top: 5px;
            font-size: 14px;
        }

        .submit-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            cursor: pointer;
            box-shadow: 0 3px 8px rgba(0, 0, 0, 0.2);
            margin-top: 20px;
            width: 100%;
        }

        .submit-button:hover {
            background-color: #45a049;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .resend-button {
            background-color: #008CBA;
            margin-top: 10px;
        }

        .resend-button:hover {
            background-color: #007bb5;
        }
    </style>
</head>

<body>
    <h:form>
        <div class="form-container">
            <h2 class="form-title">Verify Otp </h2>

            <h:messages globalOnly="true" layout="table" />

            <div class="form-group">
                <h:outputLabel for="email" value="Email ID:" styleClass="form-label" />
                <h:inputText id="email" value="#{providerController.provider.email}" styleClass="form-input" required="true" />
            </div>

            <div class="form-group">
                <h:outputLabel for="otp" value="OTP Code:" styleClass="form-label" />
                <h:inputText id="otp" value="#{providerController.otpCode}" styleClass="form-input" required="true" />
            </div>

            <div class="form-group">
                <h:commandButton 
                    value="Submit OTP" 
                    action="#{providerController.verifyOtp(providerController.provider.email, providerOtp.otpCode)}" 
                    styleClass="submit-button" />
            </div>

            <div class="form-group">
                <h:commandButton 
                    value="Resend OTP" 
                    action="#{providerController.resendOtp}" 
                    immediate="true" 
                    styleClass="submit-button resend-button" />
            </div>
        </div>
    </h:form>
</body>
</html>
</f:view>
