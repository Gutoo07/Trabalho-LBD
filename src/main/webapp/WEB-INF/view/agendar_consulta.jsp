<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Consulta</title>
    <link rel="stylesheet" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
    <div class="body_div">
        <div class="navbar">
            <div class="menu">
                <a href="index.html">Home</a>
                <a href="consultas.html">Consultas</a>
                <a href="historico.html">Histórico</a>
            </div>
            <div>
                <a href="logout.html">Sair</a>
            </div>
        </div>
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