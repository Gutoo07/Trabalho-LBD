<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Especialidade</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
	<link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
    <div class="body_div">
		<jsp:include page="menu_medico.jsp"></jsp:include>
        <form action="/salvarEspecialidade" method="POST">
            <h2>Cadastrar Especialidade</h2>
			<label>Id da Especialidade:</label> <input class="id" type="number" name="id"><br>
            <label>Nome da Especialidade:</label> <input class="nome" type="text" name="nome"><br>
            <button type="submit">Salvar</button>
        </form>
		<form>
			
		    <table border="1">
			
				<h2>Especialidades Cadastradas</h2>
				<c:if test="${!erro.isEmpty() == true}">
					<h2 style="color: red;">${erro}</h2>
				</c:if>
				<tr><th>Id</th><th>Nome</th><th>Editar</th><th>Excluir</th></tr>
				<c:forEach var="lista" items="${lista_especialidade}">
		        <tr>
					<td>${lista.getId()}</td>
					<td>${lista.getNome()}</td>
					<td>
						<a onclick="editLabels(${lista.getId()},'${lista.getNome()}')" style="color: inherit; text-decoration: none;cursor: pointer;">
							<i class="fa-solid fa-pencil"></i>
						</a>
					</td>
						<td>
							<a onclick="enviarDados(${lista.getId()},'${lista.getNome()}')" style="color: inherit; text-decoration: none;cursor: pointer;">
								<i class="fa-solid fa-trash"></i>
							</a>
						</td>
					</tr>
		   		</c:forEach>
			 </table>
		</form>
    </div>
	<script>
		function editLabels(id,nome){
			document.getElementsByClassName("id")[0].value = id
			document.getElementsByClassName("nome")[0].value = nome
			window.scrollTo(0, 0);
		}
		
		function enviarDados(id,nome) {
			console.log("teste")
			fetch('/deleteEspecialidade', {
			  method: 'POST',
			  headers: {
			    'Content-Type': 'application/json'
			  },
			  body: JSON.stringify({
			    id: id,
			    nome: nome
			  })
			})
			.then(response => {
				
			  return response.text(); // pega HTML como string
			}).then(html => {
				document.open();
				document.write(html);
				document.close();      
			})

	
		  }
	</script>
</body>
</html>
