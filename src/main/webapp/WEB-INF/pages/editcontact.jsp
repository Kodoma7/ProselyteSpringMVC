<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Кодома
  Date: 05.09.2017
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    Edit contact
</head>
<body>
<h2>Ввод данных</h2>
<form:form method="post" commandName="contact" action="/editContact">
    <table>
        <tr>
            <form:hidden path="id" id="${contact.id}"/>
        </tr>
        <tr>
            <td><form:label path="fname">Имя</form:label></td>
            <td><form:input path="fname" /></td>
        </tr>
        <tr>
            <td><form:label path="lname">Фамилия</form:label></td>
            <td><form:input path="lname" /></td>
        </tr>
        <tr>
            <td><form:label path="address">Адрес</form:label></td>
            <td><form:input path="address" /></td>
        </tr>
        <tr>
            <td><form:label path="phoneNumber">Номер телефона</form:label></td>
            <td><form:input path="phoneNumber" /></td>
        </tr>
        <tr>
            <td><form:label path="groupName">Название группы</form:label></td>
            <td><form:input path="groupName" /></td>
        </tr>
        <tr>
            <td><button type="submit" name="Ok">Ok</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
