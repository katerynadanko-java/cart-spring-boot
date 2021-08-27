<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>EMS - Customer List</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 60%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body style="background-color: #FFFFE0;">

<div style="margin-top:50px; margin-left:200px; height:50px;"><h2>Customer List</h2></div>
<table style="margin-top: 0px;margin-left: 100px; ">
    <tr>
        <th>User Name</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Password</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${customerList}" var="customerDTO">
        <tr>
            <td>${customerDTO.userName}</td>
            <td>${customerDTO.firstName}</td>
            <td>${customerDTO.lastName}</td>
            <td>${customerDTO.phone}</td>
            <td>${customerDTO.password}</td>
            <td>
                <a href="/customerEdit?id=${customerDTO.id}">Edit</a>
            </td>
            <td>
                <form action="/customerDelete?id=${customerDTO.id}" method="post">
                    <input type="submit" value="Delete" style="background:none;border:0px;cursor: pointer;"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
