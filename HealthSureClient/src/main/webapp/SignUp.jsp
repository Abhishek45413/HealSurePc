<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Provider SignUp</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 120vh;
        }

        .form-container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);	
            width: 450px;
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
            color: #333333;
        }

        .form-label {
            font-weight: 500;
            color: #555555;
            padding-top: 8px;
        }

        .form-input {
            width: 100%;
            padding: 8px 10px;
            border-radius: 8px;
            border: 1px solid #cccccc;
            margin-top: 5px;
            margin-bottom: 10px;
            font-size: 14px;
        }

        .form-textarea {
            width: 100%;
            padding: 8px 10px;
            border-radius: 8px;
            border: 1px solid #cccccc;
            margin-top: 5px;
            margin-bottom: 10px;
            font-size: 14px;
            resize: none;
        }

        .form-buttons {
            text-align: center;
            margin-top: 20px;
        }

        .form-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            cursor: pointer;
            box-shadow: 0 3px 8px rgba(0,0,0,0.2);
        }

        .form-button:hover {
            background-color: #45a049;
        }

        .telephone-row {
            display: flex;
            align-items: center;
        }

        .telephone-row span {
            font-weight: bold;
            margin-right: 8px;
            color: #555555;
        }

        .telephone-row input {
            flex: 1;
            padding: 8px;
            border-radius: 8px;
            border: 1px solid #cccccc;
         }
            
        .invalid-password {
            border: 2px solid red !important;
            background-color: #fff5f5;
        }
        
    </style>
    
   <script>
   function toggleInstructions() {
       var instructions = document.getElementById('passwordInstructions');
       instructions.style.display = (instructions.style.display === 'none') ? 'block' : 'none';
   }
    // Show Password Instructions and hide the link
    function showInstructions() {
        document.getElementById('passwordInstructions').style.display = 'block';
        document.getElementById('showInstructionsLink').style.display = 'none';
    }

    // Validate Password Strength + Rules
    function validatePassword(inputField) {
        var password = inputField.value;
        var strengthBar = document.getElementById('strengthBar');
        var strengthText = document.getElementById('strengthText');
        var errorDiv = document.getElementById('passwordError');
        var isValid = true;
        var strength = 0;

        if (password.length >= 8) strength++; else isValid = false;
        if (/[A-Z]/.test(password)) strength++; else isValid = false;
        if (/[a-z]/.test(password)) strength++; else isValid = false;
        if (/[0-9]/.test(password)) strength++; else isValid = false;
        if (/[@$!%*?&#]/.test(password)) strength++; else isValid = false;
        if (/\s/.test(password)) isValid = false;

        // Strength Meter
        var width = strength * 20;
        strengthBar.style.width = width + '%';
        if (strength <= 2) {
            strengthBar.style.backgroundColor = 'red';
            strengthText.innerText = 'Weak';
        } else if (strength === 3) {
            strengthBar.style.backgroundColor = 'orange';
            strengthText.innerText = 'Medium';
        } else {
            strengthBar.style.backgroundColor = 'green';
            strengthText.innerText = 'Strong';
        }

        // Password Box Styling
        if (!isValid) {
            inputField.classList.add("invalid-password");
            errorDiv.style.display = 'block';
        } else {
            inputField.classList.remove("invalid-password");
            errorDiv.style.display = 'none';
        }

        // Also check confirm password in case it's already filled
        validateConfirmPassword();
    }

    // Validate Confirm Password Matching
    function validateConfirmPassword() {
        var password = document.getElementById('passwordField').value;
        var confirmInput = document.getElementById('confirmPasswordField');
        var confirmValue = confirmInput.value;
        var errorDiv = document.getElementById('confirmPasswordError');

        if (confirmValue === "") {
            confirmInput.classList.remove("invalid-password");
            errorDiv.style.display = 'none';
            return;
        }

        if (password === confirmValue) {
            confirmInput.classList.remove("invalid-password");
            errorDiv.style.display = 'none';
        } else {
            confirmInput.classList.add("invalid-password");
            errorDiv.style.display = 'block';
        }
    }
</script>
    
</head>

<body>
    <h:form>
    
    <h:messages globalOnly="true" style="color: red; margin-bottom: 10px;" />
    
        <div class="form-container">
            <h2 class="form-title">Provider SignUp</h2>

            <h:panelGrid columns="2" cellpadding="5">
                
                <h:outputLabel value="Provider Name:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.providerName}" required="true" styleClass="form-input"/>

                <h:outputLabel value="Hospital Name:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.hospitalName}" styleClass="form-input"/>

                <h:outputLabel value="Phone Number:" styleClass="form-label"/>
                <h:panelGroup layout="block" styleClass="telephone-row">
                    <h:outputText value="+91"/>
                    <h:inputText value="#{providerController.provider.telephone}" maxlength="10" required="true" styleClass="form-input"/>
                </h:panelGroup>

                <h:outputLabel value="Email:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.email}" required="true" styleClass="form-input"/>

              <h:outputLabel value="Password:" styleClass="form-label"/>
              <h:inputSecret id="passwordField"
                             value="#{providerController.provider.password}"
                             required="true"
                             styleClass="form-input"
                             onkeyup="validatePassword(this)" />
              
              <h:outputLabel value="" />
                 <div id="passwordError" style="display: none; font-size: 12px; color: red; margin-top: 3px;">
                      Password does not meet the requirements.
                  </div>
              
              <!-- Password Instructions Link -->
              <h:outputLink value="#" onclick="toggleInstructions(); return false;" style="font-size: 12px; color: #007BFF;">
                      Password Instructions
              </h:outputLink>

              <!-- Password Instructions Hidden Section -->
              <div id="passwordInstructions" style="display:none; font-size: 12px; color: #555; margin-top: 5px;">
                  Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number, and one special character.
              </div>

              <!-- Password Strength Meter -->
              <div style="margin-top: 8px;">
                <div style="height: 8px; width: 100%; background-color: #eee; border-radius: 4px;">
                    <div id="strengthBar" style="height: 100%; width: 0%; background-color: red; border-radius: 4px;"></div>
                </div>
                   <span id="strengthText" style="font-size: 12px; color: #555;"></span>
              </div>

              <!-- Confirm Password Field -->
              <h:outputLabel value="Confirm Password:" styleClass="form-label"/>
              <h:inputSecret id="confirmPasswordField"
                             value="#{providerController.confirmPassword}"
                             required="true"
                             styleClass="form-input"
                             onkeyup="validateConfirmPassword()" />
                             
                 <h:outputLabel value="" />
                 <div id="confirmPasswordError" style="display: none; font-size: 12px; color: red; margin-top: 3px;">
                       Passwords do not match.
                 </div>            

                <h:outputLabel value="Address:" styleClass="form-label"/>
                <h:inputTextarea value="#{providerController.provider.address}" rows="3" cols="30" styleClass="form-textarea"/>

                <h:outputLabel value="City:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.city}" styleClass="form-input"/>


                <h:outputLabel value="State:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.state}" styleClass="form-input"/>

                <h:outputLabel value="Zipcode:" styleClass="form-label"/>
                <h:inputText value="#{providerController.provider.zipcode}" styleClass="form-input"/>
            </h:panelGrid>

            <div class="form-buttons">
                <h:commandButton value="Sign Up" action="#{providerController.register}" styleClass="form-button"/>
            </div>
        </div>
    </h:form>
</body>
</html>
</f:view>
