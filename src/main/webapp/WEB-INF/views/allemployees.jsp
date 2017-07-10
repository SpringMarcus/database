<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/17/16
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Marcus is the Best</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>
<body>
    <h2>List of Employees</h2>

    <table>
        <tr>
            <td>varStatus</td><td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td>first</td><td>middle</td><td>last</td><td>birth date</td><td>text</td><td>OneToMany</td><td>phone</td><td></td>
        </tr>
        <c:forEach items="${employees}" var="employee" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <!-- this calls the Employee.getName() method -->
                <td>${employee.name}</td>
                <td>${employee.joiningDate}</td>
                <td>${employee.salary}</td>
                <td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>

                <td>${employee.nameTwo.first}</td>
                <td>${employee.nameTwo.middle}</td>
                <td>${employee.nameTwo.last}</td>

                <td>${employee.birthDate}</td>
                <td>${employee.text}</td>

                <td></td>

                <!-- this calls the employee.getPhones().get(0).getNumber() -->
                <td>${employee.phones[0].number}</td>

                <td><a href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <a href="<c:url value='/register' />">Register New Employee</a>

    <br/>
    Go to <a href="<c:url value='/home' />">Home Page</a>
</body>
</html>
