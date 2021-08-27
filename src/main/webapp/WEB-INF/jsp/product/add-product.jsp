<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<c:if test="${addCProductSuccess}">
    <div>Successfully added Product with Id: ${savedProduct.id}</div>
</c:if>

<c:url var="add_name_url" value="/product/addProduct"/>
<form:form action="${add_customer_url}" method="post" modelAttribute="Product">
    <form:label path="name">Product Name: </form:label> <form:input type="text" path="name"/>
    <form:label path="price">Product Price: </form:label> <form:input path="price"/>
    <form:label path="amount">Product Amount: </form:label> <form:input path="amount"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>