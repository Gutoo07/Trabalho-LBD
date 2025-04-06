<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
	<link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>

    <form action="/checkLogin" method="POST">
        <h2>Login</h2>

		
		<c:if test="${value == 0}">
		    <h2 style="color: red;">Usuário ou Senha incorretos, Tente novamente!</h2>
		</c:if>
		<c:if test="${value == 1}">
		    <script>window.location.href = "/consultas"</script>
		</c:if>
		<c:if test="${value == 2}">
		    <script>window.location.href = "/visualizar"</script>
		</c:if>
		
        <label>Usuário:</label> <input type="text" name="rg"><br>
        <label>Senha:</label> <input type="password" name="password"><br>
        <button type="submit">Entrar</button>
    </form>

</body>
</html>