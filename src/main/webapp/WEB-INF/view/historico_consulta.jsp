<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Histórico de Consultas</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
	<link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
    <div class="body_div">
   	<jsp:include page="menu_cliente.jsp"></jsp:include>
    <form>
        <h2>Histórico de Consultas</h2>
        <table border="1">
            <tr><th>Data</th><th>Especialidade</th><th>Médico</th></tr>
            <tr><td>2025-03-01</td><td>Ortopedia</td><td>Dr. Silva</td></tr>
        </table>
    </form>
    </div>
</body>
</html>