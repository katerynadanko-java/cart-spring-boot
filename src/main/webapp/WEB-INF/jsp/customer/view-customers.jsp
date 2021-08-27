<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View Customers</title>
<%--    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">--%>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Password</th>
        <th>Carts</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${username}</td>
            <td>${First Name}</td>
            <td>${Last Name}</td>
            <td>${Phone}</td>
            <td>${Password}</td>
            <td>${Carts}</td>

            <td>
                <a href="/edit?id=${customerDTO.id}">Edit</a>
            </td>
            <td>
                <form action="/delete?id=${customerDTO.id}" method="post">
                    <input type="submit" value="Delete" style="background:none;border:0px;cursor: pointer;"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%--</body>--%>
<%--</html>--%>
