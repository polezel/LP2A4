<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Atualizar Pessoa</title>
    <link rel="stylesheet" href="resources/bootstrap.min.css">
    <!-- Adicione outros estilos conforme necessário -->
</head>
<body class="bg-light">

    <jsp:include page="header.jsp" />

    <main role="main" class="container">
        <div class="d-flex align-items-center p-3 my-3 bg-purple rounded shadow-sm">
            <div class="col-sm-6 d-flex align-items-center justify-content-start">
                <h4>Atualizar Pessoa</h4>
            </div>
        </div>

        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <form action="UpdatePessoa" method="post">
                <input type="hidden" name="id" value="${pessoa.id}">
                <div class="form-group">
                    <label for="novoNome">Novo Nome:</label>
                    <input type="text" class="form-control" id="novoNome" name="novoNome" value="${pessoa.nome}">
                </div>
                <button type="submit" class="btn btn-primary">Atualizar</button>
            </form>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>
