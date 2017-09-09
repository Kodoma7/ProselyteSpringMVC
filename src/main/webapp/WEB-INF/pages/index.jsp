<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home Page</title>
  <style>
    body {
      /* Цвет фона и путь к файлу */
      background-image: url("http://cdn.fishki.net/upload/post/201411/26/1334081/57542_trava_priroda_doroga_leto_1920x1200_wwwgdefonru.jpg");
      background-color: #c7b39b;
      color: #fff; /* Цвет текста */
    }
  </style>
</head>

<body>

<h3><a href="/developer">Add Developer</a></h3>
<h3><a href="/contact">Add Contact</a></h3>
<h3><a href="/show">Show</a></h3>

<form method="get" action="/hello">
  <table>
    <tr>
      <td>Имя</td>
      <td><input type="text" name="name" value="Kira"></td>
    </tr>
    <tr>
      <td align="right" colspan="2"><input type="submit" value="Войти"></td>
    </tr>
  </table>
</form>

<form method="post" action="/validate">
  <table>
    <tr>
      <td>Имя</td>
      <td><input type="text" name="firstName"></td>
    </tr>
    <tr>
      <td>Пароль</td>
      <td><input type="text" name="password"></td>
    </tr>
    <tr>
      <td align="right" colspan="2"><input type="submit" value="Войти"></td>
    </tr>
  </table>
</form>

<p th:text>Hello ${firstName}</p>

</body>

</html>
