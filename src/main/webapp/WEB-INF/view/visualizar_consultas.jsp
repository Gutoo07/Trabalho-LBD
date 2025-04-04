<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Consultas</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
	<link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
    <div class="body_div">

	<jsp:include page="menu_medico.jsp"></jsp:include>
    <form>
    <h2>Consultas</h2>
    <table border="1">
        <!--TITULO TABELA-->
        <tr>
            <th>Paciente</th>
            <th>Médico</th>
            <th>Especialidade</th>
            <th>Data</th>
            <th>Hora</th>
        </tr>
        <!--FOREACH-->
        <tr>
            <td>Teste</td>
            <td>Dr. Fulano</td>
            <td>Cardiologia</td>
            <td>02/04/2025</td>
            <td>10:00</td></tr>
    </table>
    </form>
    </div>
</body>
</html>