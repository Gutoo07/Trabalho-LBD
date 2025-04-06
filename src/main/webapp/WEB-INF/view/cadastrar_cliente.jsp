<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Cadastro de Clientes</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

    <div class="body_div">
    <jsp:include page="menu_medico.jsp"></jsp:include>
    <form action="/crudClientePost" method="post">
        <h2>Cadastrar Paciente</h2>
        <label>RG:</label> <input type="text" name="rg" value='<c:out value="${cliente.rg }" />'><br>
        <label>Nome:</label> <input type="text" name="nome" value='<c:out value="${cliente.nome }" />'><br>        
        <label>Telefone:</label> <input type="text" name="telefone" value='<c:out value="${cliente.telefone }" />'><br>
        <label>Data de Nascimento:</label> <input type="date" name="dataNascimento" value='<c:out value="${cliente.dt_nasc }" />'><br>
        <label>Senha:</label> <input type="text" name="senha" value='<c:out value="${cliente.senha }" />'><br>
        
        <table>
        	<tr>
     			<td><input type="submit" name="botao" value="Inserir" class="btn btn-dark"></td>
				<td><input type="submit" name="botao" value="Atualizar" class="btn btn-dark"></td>
				<td><input type="submit" name="botao" value="Excluir" class="btn btn-dark"></td>
				<td><input type="submit" name="botao" value="Listar" class="btn btn-dark"></td>   
        	</tr>
        </table>
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
		<c:if test="${not empty clientes }">
			<table class="table table-dark table-striped-columns">
				<thead>
					<tr>
						<th>RG</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Data Nasc</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${clientes }">
						<tr>
							<td>${c.rg }</td>
							<td>${c.nome }</td>
							<td>${c.telefone }</td>
							<td>${c.dt_nascStr }</td>
							<td><a href="${pageContext.request.contextPath }/crudClienteGet?acao=editar&rg=${c.rg}">Editar</a></td>
							<td><a href="${pageContext.request.contextPath }/crudClienteGet?acao=excluir&rg=${c.rg}">Excluir</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
    </div>

</body>
</html>