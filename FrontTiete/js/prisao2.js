const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Icpf = document.querySelector("#cpf");
const Iservicos = document.querySelector("#servicos");
const Ivalor = document.querySelector("#valor");
const Icrimes = document.querySelector("#crimes");
const Ipolicial = document.querySelector("#policial");
const listaPrisoes = document.querySelector(".lista-prisao"); // Alterei o nome da variável

// Função para listar prisões autenticada
async function listarPrisoes(token) {
    const url = "http://localhost:8080/api/prisao/list";

    try {
        const response = await fetch(url, {
            headers: {
                "Authorization": `Bearer ${token}` // Adicione o token JWT no cabeçalho de autenticação
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        const data = await response.json();
        exibirPrisao(data);
    } catch (error) {
        console.error(error);
    }
}

// Suponha que você já tenha obtido o token após o login

// Exemplo de uso:
const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    listarPrisoes(token); // Chame a função de listagem com o token
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    alert("Usuário não autenticado. Faça o login primeiro.");
}

function exibirPrisao(prisoes) {
    listaPrisoes.innerHTML = "";

    if (prisoes.length === 0) {
        // Não há prisões para exibir
    } else {
        const table = document.createElement("table");
        table.classList.add("contato-table"); // Alterei a classe
        table.classList.add("move-right");

        const tableHeader = table.createTHead();
        const headerRow = tableHeader.insertRow();
        const headers = ["Nome", "Cpf", "Serviços", "Valor", "Crimes", "Policiais"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        let corDeFundo = true;

        prisoes.forEach(function (prisao) {
            if (prisao.nome && prisao.cpf && prisao.servicos && prisao.valor && prisao.crimes && prisao.policial) {
                const row = table.insertRow();

                const prisaoData = [prisao.nome, prisao.cpf, prisao.servicos, prisao.valor, prisao.crimes, prisao.policial];

                prisaoData.forEach(function (value, index) {
                    const cell = row.insertCell();
                    cell.textContent = value;
                });

                if (corDeFundo) {
                    row.style.backgroundColor = "transparent";
                    row.style.fontSize = "20px";
                } else {
                    row.style.backgroundColor = "transparent";
                    row.style.fontSize = "20px";
                }
                corDeFundo = !corDeFundo;
            }
        });

        listaPrisoes.appendChild(table);
    }
}
