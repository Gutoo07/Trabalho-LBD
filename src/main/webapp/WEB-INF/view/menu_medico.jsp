<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
    <div class="navbar">
        <div class="menu">
            <a href="/visualizar">Inicio</a>
            <a href="/clientes">Clientes</a>
            <!-- <a href="cadastro_paciente.html">Clientes</a> -->
            <a href="/medicos">Médicos</a>
            <!-- <a href="cadastro_medico.html">Médicos</a>  -->
            <a href="/especialidades">Especialidades</a>
            <a href="/material">Materiais</a>
        </div>
        <div>
			<a style="cursor: pointer" onclick="logout()">Sair</a>
        </div>
    </div>

	<script>
	function logout() {
	    document.cookie = "user=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	    document.cookie = "tipo=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
		window.location.href = "/";
	}
	</script>
</body>
</html>

