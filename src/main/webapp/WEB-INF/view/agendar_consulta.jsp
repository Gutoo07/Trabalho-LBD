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
    <form>
		<h1></h1>
        <h2>Agendar Consulta</h2>
        <!--<label>Paciente:</label> <input type="text" name="paciente"><br>
        <label>Médico:</label> <input type="text" name="medico"><br>
        <label>Especialidade:</label> -->

        <!-- FOREACH-->
		
		
		
        <select class="especialidade_options" name="especialidade">
			<c:forEach var="nome" items="${listaEspecialidade}">
						<option value="${nome}">${nome}</option>
			</c:forEach>
        </select><br>
        <label>Data:</label> <input type="date" name="data"><br>
        <label>Hora:</label> <input type="time" name="hora"><br>
        <button type="submit">Salvar</button>
    </form>
</div>
</div>
    
</body>
</html>