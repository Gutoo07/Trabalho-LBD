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
        <c:forEach var="consulta" items="${lista_consulta}">
            <tr>
            	<td>${consulta.getClienteRg().getNome()}</td>
            	<td>${consulta.getMedicoRg().getNome()}</td>
            	<td>${consulta.getMedicoRg().getEspecialidade().getNome()}</td>
            	<td>${consulta.getDia()}</td>
            	<td>${consulta.getHora()}</td>
        	<tr>
    	</c:forEach>
            
    </table>
    </form>
    </div>
</body>
</html>