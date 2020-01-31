<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мега-чат!</title>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
</head>
<body>
    <%
        if (request.getSession().getAttribute("error") != null) {
            out.println("<p><span style=\"color: red; \">" + request.getSession().getAttribute("error") + "</span></p>");
        }
    %>
    <form action='/chat' method='post'>
        Введите имя:
        <input type='text' autofocus name='name' value=''><input type='submit' value='Войти в чат'>
    </form>
</body>
</html>
