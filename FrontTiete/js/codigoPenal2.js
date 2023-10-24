const formulario = document.querySelector("form");
const Iartigo = document.querySelector("#artigo");
const Icrime = document.querySelector("#crime");
const Iservicos = document.querySelector("#servicos");
const Ivalor = document.querySelector("#valor");
const listaContatos = document.querySelector(".lista-codigo");


// Função para listar usuários autenticada
async function exibirCodigoPenal(token) {
    const url = "http://localhost:8080/api/dados/list";

    try {
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        const data = await response.json();
        exibirDados(data);
    } catch (error) {
        console.error(error);
    }
}


// Exemplo de uso:
const token = sessionStorage.getItem("token");
if (token) {
    exibirCodigoPenal(token);
} else {
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "login.html";
}


function exibirDados(dados) {
    listaContatos.innerHTML = "";

    if (dados.length === 0) {
        // Não há dados para exibir, então limpe os totais
        totalValor = 0;
        totalServico = 0;
    } else {
        contatos = dados; // Atualize a variável contatos
        const table = document.createElement("table");
        table.classList.add("contato-table");
        table.classList.add("move-right"); // Adicione essa classe para mover a tabela para a direita


        const tableHeader = table.createTHead();
        const headerRow = tableHeader.insertRow();
        const headers = ["Selecionar", "Artigo", "Crime", "Serviços", "Valor"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        dados.forEach(function (contato) {
            if (contato.artigo && contato.crime && contato.servicos && contato.valor) {
                const row = table.insertRow();

                const removeButtonCell = row.insertCell();
                const removeButton = document.createElement("button");
                removeButton.textContent = "X";
                removeButton.style.backgroundColor = "red"; // Define a cor de fundo vermelha
                removeButton.style.color = "black"; // Define a cor do texto como branco para melhor legibilidade
                removeButton.addEventListener("click", function () {
                    const contatoId = contato.id; // Obtenha o ID do usuário
                    deletarCodigo(contatoId, token); // Chame a função para deletar o usuário com o token e o ID
                });

                removeButtonCell.appendChild(removeButton);

                const contatoData = [contato.artigo, contato.crime, contato.servicos, contato.valor];

                contatoData.forEach(function (value, index) {
                    const cell = row.insertCell();
                    cell.textContent = value;
                });
            }
        });

        listaContatos.appendChild(table);
    }
}

async function deletarCodigo(contatoId, token) {
    const url = `http://localhost:8080/api/dados/delete/${contatoId}`; // Alterei de usuarioId para contatoId

    try {
        const response = await fetch(url, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        location.reload();
        // Dados excluídos com sucesso, você pode atualizar a lista ou realizar outra ação aqui
    } catch (error) {
        console.error(error);
    }
}
