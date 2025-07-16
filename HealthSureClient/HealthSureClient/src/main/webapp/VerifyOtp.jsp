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
            position: relative;
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
            color: #333333;
            position: relative;
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

        .resend-link {
            display: block;
            text-align: right;
            margin-top: 5px;
            font-size: 13px;
            color: #007bb5;
            text-decoration: underline;
            cursor: pointer;
        }

        .error-message {
            color: red;
            margin-top: 5px;
            font-size: 13px;
        }

        .info-message {
            color: orange;
            margin-top: 5px;
            font-size: 13px;
        }

        .success-message {
            color: green;
            margin-top: 5px;
            font-size: 13px;
        }

        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>

<body>
    <h:form>
        <div class="form-container">
            <div class="form-title">Verify OTP</div>

            <!-- ✅ Provider ID input -->
            <div class="form-group">
                <h:outputLabel for="providerId" value="Provider ID:" styleClass="form-label" />
                <h:inputText id="providerId" value="#{providerController.providerId}" styleClass="form-input" required="true" />
                <h:message for="providerId" styleClass="error-message" />
            </div>

            <!-- ✅ OTP input -->
            <div class="form-group">
                <h:outputLabel for="otp" value="OTP Code:" styleClass="form-label" />
                <h:inputText id="otp" value="#{providerController.otpCode}" styleClass="form-input" required="true" />
                <h:message for="otp" styleClass="error-message" />

                <!-- ✅ Messages -->
                <h:messages globalOnly="true" layout="list" styleClass="error-message" />

                <!-- ✅ Resend OTP -->
                <h:commandLink action="#{providerController.resendOtp}" value="Resend OTP?" styleClass="resend-link" />
            </div>

            <!-- ✅ Verify Button -->
            <div class="form-group">
                <h:commandButton 
                    value="Verify OTP" 
                    action="#{providerController.verifyOtp}" 
                    styleClass="submit-button" />
            </div>

        </div>
    </h:form>
</body>
</html>
</f:view>
