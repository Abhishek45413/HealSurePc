<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <title>Set New Password</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function checkStrength(password) {
            let strengthBar = document.getElementById("strengthBar");
            let strengthText = document.getElementById("strengthText");
            let errorMsg = document.getElementById("passwordError");
            let strength = 0;

            const regex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}$/;

            if (password.length >= 8) strength++;
            if (/[0-9]/.test(password)) strength++;
            if (/[!@#$%^&*]/.test(password)) strength++;
            if (/[A-Z]/.test(password)) strength++;

            strengthBar.value = strength;
            const levels = ["Very Weak", "Weak", "Moderate", "Strong", "Very Strong"];
            const colors = ["#dc2626", "#f97316", "#facc15", "#4ade80", "#22c55e"];
            strengthText.innerText = "Strength: " + levels[strength];
            strengthText.style.color = colors[strength];

            // Show error if password doesn't match regex
            if (!regex.test(password)) {
                errorMsg.innerText = "Password must be at least 8 characters and include a number, a special character, and an uppercase letter.";
            } else {
                errorMsg.innerText = "";
            }

            checkConfirmMatch();
        }

        function checkConfirmMatch() {
            const password = document.getElementById("newPassword").value;
            const confirm = document.getElementById("confirmPassword").value;
            const confirmError = document.getElementById("confirmError");

            if (confirm && password !== confirm) {
                confirmError.innerText = "Passwords do not match.";
            } else {
                confirmError.innerText = "";
            }
        }

        function toggleVisibility(id) {
            const input = document.getElementById(id);
            input.type = input.type === "password" ? "text" : "password";
        }

        function showInstructions() {
            alert("Password must be at least 8 characters long and include:\n- One number\n- One special character (!@#$%^&*)\n- One uppercase letter");
        }
    </script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <f:view>
        <h:form styleClass="bg-white p-8 rounded shadow-md w-full max-w-md">
            <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Set Your New Password</h2>

            <!-- Email Field (if needed) -->
            <div class="mb-4">
                <h:outputLabel for="email" value="Email" styleClass="block text-gray-700 font-medium mb-2" />
                <h:inputText id="email" value="#{providerController.provider.email}"
                             styleClass="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>

            <!-- New Password Field -->
            <div class="mb-4">
                <label for="newPassword" class="block text-gray-700 font-medium mb-2">New Password</label>
                <h:inputSecret id="newPassword" value="#{providerController.provider.newPassword}"
                               styleClass="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                               onkeyup="checkStrength(this.value)" />
                <button type="button" onclick="toggleVisibility('newPassword')" ></button>
                <p id="passwordError" class="text-red-500 text-sm mt-1"></p>

                <!-- Strength Meter -->
                <div class="mt-2">
                    <progress id="strengthBar" value="0" max="4" class="w-full h-2"></progress>
                    <p id="strengthText" class="text-sm mt-1 font-medium">Strength: </p>
                </div>
            </div>

            <!-- Password Requirement Info -->
            <div class="mb-4 text-left">
                <a href="javascript:void(0)" onclick="showInstructions()" class="text-blue-600 text-sm underline hover:text-blue-800">
                    Password Requirements
                </a>
            </div>

            <!-- Confirm Password Field -->
            <div class="mb-6">
                <label for="confirmPassword" class="block text-gray-700 font-medium mb-2">Confirm Password</label>
                <h:inputSecret id="confirmPassword" value="#{providerController.provider.confirmPassword}"
                               styleClass="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                               onkeyup="checkConfirmMatch()" />
                <button type="button" onclick="toggleVisibility('confirmPassword')" ></button>
                <p id="confirmError" class="text-red-500 text-sm mt-1"></p>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <h:commandButton 
                value="Submit" 
                action="Login.jsp?faces-redirect=true"
                styleClass="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700" />
            </div>

            <!-- Global Error Messages -->
            <h:messages globalOnly="true" styleClass="text-red-500 text-sm mt-4 text-center" />
        </h:form>
    </f:view>
</body>
</html>
