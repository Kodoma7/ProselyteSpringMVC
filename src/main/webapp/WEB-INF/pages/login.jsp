<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<body>
<h2>Авторизация</h2>
<form:form method="post" commandName="user" action="/checkUser">
    <table>
        <tr>
            <form:label path="name">Имя</form:label>
            <form:input path="name" />
        </tr>
        <tr>
            <form:label path="password">Пароль</form:label>
            <form:input path="password" />
        </tr>
        <tr>
            <td align="right" colspan="2"><input type="submit" value="Ok"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
