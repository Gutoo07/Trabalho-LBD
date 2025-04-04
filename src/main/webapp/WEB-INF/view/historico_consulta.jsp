<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <form action="/visualizar">
        <h2>Histórico de Consultas</h2>
        <table border="1">
            <tr><th>Data</th><th>Especialidade</th><th>Médico</th></tr>
			<c:forEach var="consulta" items="${lista_consulta}">
	            <tr><td>${consulta.getDia()}</td><td>${consulta.getMedicoRg().getEspecialidade().getNome()}</td><td>${consulta.getMedicoRg().getNome()}</td></tr>
			</c:forEach>
        </table>
    </form>
    </div>
</body>
</html>