<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Customer Create Center</title>
    <style>
        td, th {
            font-family: arial, sans-serif;
        }
    </style>
    <script type="text/javascript">
        function validate() {
            if (document.forms["customer"]["userName"].value == "") {
                alert("Please enter user name");
                document.forms["customer"]["userName"].focus();
                return false;
            }
            if (document.forms["customer"]["firstName"].value == "") {
                alert("Please enter first name");
                document.forms["customer"]["firstName"].focus();
                return false;
            }
            if (document.forms["customer"]["lastName"].value == "") {
                alert("Please enter last name");
                document.forms["customer"]["lastName"].focus();
                return false;
            }
            if (document.forms["customer"]["phone"].value == "")  {
                alert("Please enter user phone");
                document.forms["customer"]["phone"].focus();
                return false;
            }
            if (document.forms["customer"]["password"].value == "")  {
                alert("Please enter user password");
                document.forms["customer"]["password"].focus();
                return false;
            }
        }
    </script>
</head>
<body style="background-color: #FFFFE0;">
<div style="margin-top:50px; margin-left:250px; height:50px;"><h2>Customer <c:out
        value="${customer.id != null ? 'Update' : 'Registration' }"/></h2></div>


<form:form method="POST" modelAttribute="customerDTO" action="/home" name="customer">
    <table style="vertical-align: center; margin-left:20%;">

        <tr>
            <td><form:hidden path="id"/></td>
        </tr>
        <tr>
            <td>User Name :</td>
            <td><form:input path="userName" id="userName"/></td>
        </tr>
        <tr>
            <td>First Name :</td>
            <td><form:input path="firstName" id="firstname"/></td>
        </tr>
        <tr>
            <td>Last Name :</td>
            <td><form:input path="lastName" id="lastname"/></td>
        </tr>
        <tr>
            <td>Phone :</td>
            <td><form:input path="phone" id="phone"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<c:out value="${customer.id != null ? 'Update' : 'Register' }" />"
                                   onclick="return validate();">&nbsp;&nbsp; <a href="/list">Customer List</a>&nbsp;
                <c:if test="${customer.id ne null}"><b>|</b>&nbsp;<a href="/registration">adding new Customer</a></c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
