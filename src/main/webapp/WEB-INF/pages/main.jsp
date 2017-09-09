<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Кодома
  Date: 04.09.2017
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<p>Hello, ${user.name}!</p>
<p>Your ID is ${user.id}</p>

<h1>Список контактов</h1>

<form method="post" action="/edit">
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Group Name</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td><c:out value="${contact.fname}"/></td>
                <td><c:out value="${contact.lname}"/></td>
                <td><c:out value="${contact.address}"/></td>
                <td><c:out value="${contact.phoneNumber}"/></td>
                <td><c:out value="${contact.groupName}"/></td>
                <td>
                    <p><select name="Take">
                        <option value="Edit"> Редактировать контакт</option>
                        <option value="Remove"> Удалить контакт</option>
                    </select></p>
                </td>
                <td><button type="submit" name="Ok" value="${contact.id}">Ok</button></td>
            </tr>
        </c:forEach>
    </table>
</form>

<form action="/add" method="post">
    <input type="submit" value="Add"
           name="Add" id="frm1_submit" />
</form>

</body>
</html>
