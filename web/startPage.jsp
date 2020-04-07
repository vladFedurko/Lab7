<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мега-чат!</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
    <%
        if (request.getSession().getAttribute("error") != null) {
            out.println("<p><span style=\"color: red; \">" + request.getSession().getAttribute("error") + "</span></p>");
        }
    %>
    <div style="width: 400px; padding: 20px; margin: 10% auto auto; border: 1px black solid">
        <form action='/chat' class='needs-validation' method='post' novalidate>
            <div class="form-group">
                <label for='name' >Введите имя:</label>
                <input type='text' class="form-control" autofocus name='name' id='name' value='' required>
                <div class="invalid-feedback">Пожалуйста, заполните это поле.</div>
                <input type='checkbox' name="answering" id="answering" value=''>Автоответчик
            </div>
            <input type='submit' align="center" class='btn btn-primary' value='Войти в чат'>
        </form>
        <script type="text/javascript">
            (function() {
                'use strict';
                window.addEventListener('load', function() {
                    var forms = document.getElementsByClassName('needs-validation');
                    var validation = Array.prototype.filter.call(forms, function(form) {
                        form.addEventListener('submit', function(event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </div>
</body>
</html>
