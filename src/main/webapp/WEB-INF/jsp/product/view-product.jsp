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
        <th>Name</th>
        <th>Surname</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${book.name}</td>
            <td>${book.surname}</td>
            <td>${book.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>