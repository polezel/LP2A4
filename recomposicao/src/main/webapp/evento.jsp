<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Atividades</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        form {
            margin: 0;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .edit-icon, .delete-icon {
            cursor: pointer;
        }

        button {
            border: none;
            margin: 0;
            padding: 0;
            width: auto;
            overflow: visible;
            background: transparent;
            color: inherit;
            font: inherit;
            line-height: normal;
            cursor: pointer;
            outline: none;
        }

        .container {
            display: flex;
            justify-content: space-between;
        }

        .box {
            display: flex;
            align-items: center;
            flex-direction: column;

            border: 1px solid #ccc;
            padding: 10px;
        }

        input{
            margin-top: 10px;
            border-radius: 10px;
            padding: 10px;
            border: black 2px solid;
        }

        .add{
            border-radius: 10px;
            padding: 10px;
            background-color: #ccc;
            box-shadow: #ccc inset;
            border: black 2px solid;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="box" style="width: 65%">
        <h2>Lista de Atividades</h2>
        <table>
            <thead>
            <tr>
                <th>Nome</th>
                <th>Data</th>
                <th>Hora</th>
                <th>Descrição</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="atividade" items="${atividades}">
                <tr>
                    <td>${atividade.nome}</td>
                    <td>${atividade.data}</td>
                    <td>${atividade.hora}</td>
                    <td>${atividade.descricao}</td>
                    <td>
                        <form action="/edit" method="put">
                            <input type="text" id="id" name="id" style="display: none" value="${atividade.id}">
                            <button type="submit" class="edit-icon">✎</button>
                        </form>
                    </td>
                    <td>
                        <form action="/delete" method="delete">
                            <input type="text" id="id" name="id" style="display: none" value="${atividade.id}">
                            <button type="submit" class="delete-icon">❌</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="box" style="width: 30%">
        <h2>Adicionar Atividade</h2>
        <form action="/atividade" method="post">
            <input type="text" id="id" name="id" style="display: none" value="${id}">
            <label for="nome">Nome</label>
            <br>
            <input type="text" id="nome" name="nome" required value="${atividade.nome}">
            <br>
            <label for="data">Data</label>
            <br>
            <input type="text" id="data" name="data" required value="${atividade.data}">
            <br>
            <label for="hora">Hora</label>
            <br>
            <input type="text" id="hora" name="hora" required value="${atividade.hora}">
            <br>
            <label for="descricao">Descrição</label>
            <br>
            <textarea id="descricao" name="descricao" required>${atividade.descricao}</textarea>
            <br>
            <button class="add" type="submit">Adicionar</button>
        </form>
    </div>
</div>

<script>
    function excluirAtividade(id) {

        alert("Excluir atividade com ID: " + id);
    }
</script>
</body>
</html>
