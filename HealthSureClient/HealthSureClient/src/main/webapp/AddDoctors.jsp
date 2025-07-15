<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!DOCTYPE html>
<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Doctor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .form-container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            width: 500px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #444;
        }

        input[type="text"], input[type="email"], select, textarea {
            width: 100%;
            padding: 8px 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        .button-group {
            text-align: center;
            margin-top: 20px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
    <div class="form-container">
        <h2>Add Doctor</h2>
        <h:form>
            <div class="form-group">
                <label for="doctorId">Doctor ID</label>
                <h:inputText id="doctorId" value="#{doctors.doctorId}" required="true" />
            </div>

            <div class="form-group">
                <label for="providerId">Provider ID</label>
                <h:inputText id="providerId" value="#{doctors.provider.providerId}" />
            </div>

            <div class="form-group">
                <label for="doctorName">Doctor Name</label>
                <h:inputText id="doctorName" value="#{doctors.doctorName}" required="true" />
            </div>

            <div class="form-group">
                <label for="qualification">Qualification</label>
                <h:inputText id="qualification" value="#{doctors.qualification}" />
            </div>

            <div class="form-group">
                <label for="specialization">Specialization</label>
                <h:inputText id="specialization" value="#{doctors.specialization}" />
            </div>

            <div class="form-group">
                <label for="licenseNo">License No</label>
                <h:inputText id="licenseNo" value="#{doctors.licenseNo}" required="true" />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <h:inputText id="email" value="#{doctors.email}" required="true" />
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <h:inputText id="phoneNumber" value="#{doctors.phoneNumber}" />
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <h:inputTextarea id="address" value="#{doctors.address}" required="true" rows="3"/>
            </div>

            <div class="form-group">
                <label for="gender">Gender</label>
                <h:selectOneMenu id="gender" value="#{doctors.gender}" required="true">
                    <f:selectItem itemLabel="Select Gender" itemValue="" />
                    <f:selectItem itemLabel="Male" itemValue="MALE" />
                    <f:selectItem itemLabel="Female" itemValue="FEMALE" />
                    <f:selectItem itemLabel="Other" itemValue="OTHER" />
                </h:selectOneMenu>
            </div>

            <div class="form-group">
                <label for="type">Doctor Type</label>
                <h:selectOneMenu id="type" value="#{doctors.type}">
                    <f:selectItem itemLabel="Standard" itemValue="STANDARD" />
                    <f:selectItem itemLabel="Adhoc" itemValue="ADHOC" />
                </h:selectOneMenu>
            </div>

            <div class="button-group">
                <h:commandButton value="Add Doctor" action="#{doctors.addDoctors}" styleClass="submit-button"/>
            </div>
        </h:form>
    </div>
</body>
</html>
</f:view>
