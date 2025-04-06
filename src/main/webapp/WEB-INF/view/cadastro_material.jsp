<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Materiais</title>
	<link rel="stylesheet" href="/resources/css/styles.css">
	<link rel="stylesheet" href="/resources/css/menu.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
    <div class="body_div">
		<jsp:include page="menu_medico.jsp"></jsp:include>
        <form action="/salvarMaterial" method="POST">
            <h2>Cadastrar Material</h2>
			<label>Id:</label> <input class="id" type="text" name="id"><br>
            <label>Nome:</label> <input class="nome" type="text" name="nome"><br>
            <label>Preço:</label><input class="valor" type="number" name="valor" step="0.01" min="0" placeholder="0.00"><br>
            <button type="submit">Salvar</button>
        </form>
        <form>
            <table border="1">
                <h2>Materiais Cadastrados</h2>
				<c:if test="${!erro.isEmpty() == true}">
					<h2 style="color: red;">${erro}</h2>
				</c:if>
                <tr><th>Id</th><th>Nome</th><th>Preço</th><th>Editar</th><th>Excluir</th></tr>
				<c:forEach var="lista" items="${lista_material}">
				    <tr>
						<td>${lista.getId()}</td>
						<td>${lista.getNome()}</td>
						<td>${lista.getValor()}</td>
						<td>
							<a onclick="editLabels(${lista.getId()},'${lista.getNome()}',${lista.getValor()})" style="color: inherit; text-decoration: none;cursor: pointer;">
								<i class="fa-solid fa-pencil"></i>
							</a>
						</td>
							<td>
								<a onclick="enviarDados(${lista.getId()},'${lista.getNome()}',${lista.getValor()})" style="color: inherit; text-decoration: none;cursor: pointer;">
									<i class="fa-solid fa-trash"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
			</table>
        </form>
    </div>
	<script>
			function editLabels(id,nome,valor){
				console.log("teste")
				console.log(valor)
				console.log("teste")
				document.getElementsByClassName("id")[0].value = id
				document.getElementsByClassName("nome")[0].value = nome
				document.getElementsByClassName("valor")[0].value = valor
				window.scrollTo(0, 0);
			}
			
			function enviarDados(id,nome,valor) {
				console.log("teste")
				fetch('/deleteMaterial', {
				  method: 'POST',
				  headers: {
				    'Content-Type': 'application/json'
				  },
				  body: JSON.stringify({
				    id: id,
				    nome: nome,
					valor: valor
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
