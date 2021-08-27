<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<c:if test="${addCustomerSuccess}">
    <div>Successfully added Customer with Id: ${savedCustomer.id}</div>
</c:if>

<c:url var="add_username_url" value="/customer/addCustomer"/>
<form:form action="${add_customer_url}" method="post" modelAttribute="customer">
    <form:label path="username">Customer Username: </form:label> <form:input type="text" path="name"/>
    <form:label path="firstName">Customer First Name: </form:label> <form:input path="firstName"/>
    <form:label path="lastName">Customer Last Name: </form:label> <form:input path="lastName"/>
    <form:label path="phone">Customer Phone: </form:label> <form:input path="phone"/>
    <form:label path="password">Customer Password: </form:label> <form:input path="password"/>
    <form:label path="carts">Customer Carts: </form:label> <form:input path="carts"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>


