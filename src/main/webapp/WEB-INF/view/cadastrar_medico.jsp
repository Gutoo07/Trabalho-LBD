<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
	<title>Cadastro de Médicos</title>
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
   		<form action="/crudMedicoPost" method="post">
   			<h1>Cadastrar Médicos</h1>
			<select class="especialidade_options" name="especialidade">
			<c:forEach var="especialidade" items="${listaEspecialidade}">
						<option value="${especialidade.nome}">${especialidade.nome}</option>
			</c:forEach>
        </select><br>
                <label>RG:</label> <input type="text" name="rg" value='<c:out value="${medico.rg }" />'><br>
                <label>Nome:</label> <input type="text" name="nome" value='<c:out value="${medico.nome }" />'><br>
                <label>Senha:</label> <input type="text" name="senha" value='<c:out value="${medico.senha }" />'><br>
                <label>Telefone:</label> <input type="text" name="telefone" value='<c:out value="${medico.telefone }" />'><br>
                <label>Valor da Consulta:</label> <input type="number" min="0" name="valorConsulta" value='<c:out value="${medico.valor_consulta }" />'><br>
                <select name="periodo">
					<option value="Manhã">Manhã</option>
					<option value="Tarde">Tarde</option>
					<option value="Noite">Noite</option>
		        </select><br>  
		        <table>
		        	<tr>
       			        <td><input type="submit" name="botao" value="Inserir" class="btn btn-dark"></td>
						<td><input type="submit" name="botao" value="Atualizar" class="btn btn-dark"></td>
						<td><input type="submit" name="botao" value="Excluir" class="btn btn-dark"></td>
						<td><input type="submit" name="botao" value="Listar" class="btn btn-dark"></td>   
		        	</tr>
		        </table> 
   		</form>
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
		<div class="conteiner" align="center">
		<c:if test="${not empty medicos }">
			<table class="table table-dark table-striped-columns">
				<thead>
					<tr>
						<th>RG</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Periodo</th>
						<th>Valor da Consulta</th>
						<th>Especialidade</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="m" items="${medicos }">
						<tr>
							<td>${m.rg }</td>
							<td>${m.nome }</td>
							<td>${m.telefone }</td>
							<td>${m.periodo }</td>
							<td>${m.valor_consulta }</td>
							<td>${m.getEspecialidade().getNome() }</td>
							<td><a href="${pageContext.request.contextPath }/crudMedicoGet?acao=editar&rg=${m.rg}">Editar</a></td>
							<td><a href="${pageContext.request.contextPath }/crudMedicoGet?acao=excluir&rg=${m.rg}">Excluir</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		</div>
	</div>
	
</body>
</html>