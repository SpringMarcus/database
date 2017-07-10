<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/17/16
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Marcus is Awesome</title>

    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
    <h2>Registration Form</h2>

    <form:form method="POST" modelAttribute="employee">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="name">Name: </label> </td>
                <!-- below calls Employee.getName() -->
                <td><form:input path="name" id="name"/></td>
                <!-- below calls Employee.getName() -->
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="joiningDate">Joining Date (MM/DD/YYYY): </label> </td>
                <td><form:input path="joiningDate" id="joiningDate"/></td>
                <td><form:errors path="joiningDate" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="salary">Salary: </label> </td>
                <td><form:input path="salary" id="salary"/></td>
                <td><form:errors path="salary" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="ssn">SSN: </label> </td>
                <td><form:input path="ssn" id="ssn"/></td>
                <td><form:errors path="ssn" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="birthDate">Birth Date: </label> </td>
                <td><form:input path="birthDate" id="birthDate"/></td>
                <td><form:errors path="birthDate" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="text">Phone (000)000-0000: </label> </td>
                <td><form:input path="text" id="ssn"/></td>
                <td><form:errors path="text" cssClass="error"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <!-- when test="true" show Update, otherwise show Register -->
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/employees' />">List of All Employees</a>
</body>
</html>
