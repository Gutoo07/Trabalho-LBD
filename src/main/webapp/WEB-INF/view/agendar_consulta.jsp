<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Consulta</title>
    <link rel="stylesheet" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
    <div class="body_div">
		<jsp:include page="menu_cliente.jsp"></jsp:include>	
    <form action="/agendarConsulta" method="post">
		<h1></h1>
        <h2>Agendar Consulta</h2>
        <!--<label>Paciente:</label> <input type="text" name="paciente"><br>
        <label>Médico:</label> <input type="text" name="medico"><br>
        <label>Especialidade:</label> -->

        <!-- FOREACH-->
		
		
		
        <select class="especialidade_options" name="especialidade">
			<c:forEach var="especialidade" items="${listaEspecialidade}">
						<option value="${especialidade.nome}">${especialidade.nome}</option>
			</c:forEach>
        </select><br>
        <label>Data:</label> <input type="date" name="dia"><br>
        <label>Hora:</label> <input type="time" name="hora"><br>
        <label>Particular?</label>
        <br />
        	<select name="particularBoolean">
        		<option value="0">Não</option>
        		<option value="1">Sim</option>
        	</select>
        <label>Código de Autorização para Consulta Particular</label> <input type="text" name="codigo_autorizacao" placeholder="Código de Autorização">

        <button type="submit">Salvar</button>
    </form>
    <br />
    <!--  Saidas e Mensagens de Sucesso -->
	<div class="conteiner" align="center">
		<c:if test="${not empty saida }">
			<h2 style="color: green;"><c:out value="${saida }" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty erro }">
			<h2 style="color: red;"><c:out value="${erro }" /></h2>
		</c:if>
	</div>
</div>



</body>
</html>