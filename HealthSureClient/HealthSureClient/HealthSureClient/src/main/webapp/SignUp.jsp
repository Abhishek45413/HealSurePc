<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Provider SignUp</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="bg-gray-100 flex justify-center items-center min-h-screen">
    <h:form prependId="false" styleClass="w-full max-w-5xl mx-auto p-6 bg-white shadow-lg rounded-lg">

        <h2 class="text-center text-2xl font-semibold text-gray-800 mb-6">Provider SignUp</h2>

        <div class="grid grid-cols-2 gap-6">
            <!-- Provider Name -->
            <div>
                <label class="block text-gray-700 font-medium mb-1">
                    <span class="text-red-600">*</span> Provider Name:
                </label>
               <h:inputText id="providerName" value="#{providerController.provider.providerName}" required="true"
                   requiredMessage="Provider name is required"
                   styleClass="w-full border border-gray-300 rounded-md p-2">
                   
               </h:inputText>
               <h:message for="providerName" style="color: red;" />
            </div>

            <!-- Hospital Name -->
            <div>
                <label class="block text-gray-700 font-medium mb-1">
                    <span class="text-red-600">*</span> Hospital Name:
                </label>
                <h:inputText id="hospitalName" value="#{providerController.provider.hospitalName}" required="true"
                    requiredMessage="Hospital name is required"
                    styleClass="w-full border border-gray-300 rounded-md p-2">
                </h:inputText>
                <h:message for="hospitalName" style="color: red;" />
            </div>

            <!-- Phone Number -->
            <div>
                <label class="block text-gray-700 font-medium mb-1">
                    <span class="text-red-600">*</span> Phone Number:
                </label>
                <h:inputText id="telephone" value="#{providerController.provider.telephone}" maxlength="10" required="true"
                    requiredMessage="Phone number is required"
                    styleClass="w-full border border-gray-300 rounded-md p-2">
                   
                </h:inputText>
                <h:message for="telephone" style="color: red;" />
            </div>

            <!-- Email -->
            <div>
                <label class="block text-gray-700 font-medium mb-1">
                    <span class="text-red-600">*</span> Email:
                </label>
                <h:inputText id="email" value="#{providerController.provider.email}" required="true"
                    requiredMessage="Email is required"
                    styleClass="w-full border border-gray-300 rounded-md p-2">
                  
                </h:inputText>
                <h:message for="email" style="color: red;" />
            </div>

            <!-- Address -->
            <div class="col-span-2">
                <label class="block text-gray-700 font-medium mb-1">
                    <span class="text-red-600">*</span> Address:
                </label>
                <h:inputTextarea id="address" value="#{providerController.provider.address}" rows="3" required="true"
                    requiredMessage="Address is required"
                    styleClass="w-full border border-gray-300 rounded-md p-2" />
                <h:message for="address" style="color: red;" />
            </div>

            <!-- City, State, Zipcode in one row -->
            <div class="grid grid-cols-3 gap-6 col-span-2">
                <!-- City -->
                <div>
                    <label class="block text-gray-700 font-medium mb-1">
                        <span class="text-red-600">*</span> City:
                    </label>
                    <h:inputText id="city" value="#{providerController.provider.city}" required="true"
                        requiredMessage="City is required"
                        styleClass="w-full border border-gray-300 rounded-md p-2" />
                    <h:message for="city" style="color: red;" />
                </div>

                <!-- State -->
                <div>
                    <label class="block text-gray-700 font-medium mb-1">
                        <span class="text-red-600">*</span> State:
                    </label>
                    <h:inputText id="state" value="#{providerController.provider.state}" required="true"
                        requiredMessage="State is required"
                        styleClass="w-full border border-gray-300 rounded-md p-2" />
                    <h:message for="state" style="color: red;" />
                </div>

                <!-- Zipcode -->
                <div>
                    <label class="block text-gray-700 font-medium mb-1">
                        <span class="text-red-600">*</span> Zipcode:
                    </label>
                    <h:inputText id="zipcode" value="#{providerController.provider.zipcode}" required="true"
                        requiredMessage="Zipcode is required"
                        styleClass="w-full border border-gray-300 rounded-md p-2">
                      
                    </h:inputText>
                    <h:message for="zipcode" style="color: red;" />
                </div>
            </div>
        </div>

        <!-- Submit Button -->
        <div class="text-center mt-6">
            <h:commandButton value="Sign Up" action="#{providerController.register}"
                styleClass="bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-6 rounded shadow-md" />
        </div>
    </h:form>
</body>
</html>
</f:view>