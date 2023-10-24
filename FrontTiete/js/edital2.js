const formulario = document.querySelector("form");
const Iinstituicao = document.querySelector("#instituicao");
const Iorganizacao = document.querySelector("#organização");
const IpolicialCadastrou = document.querySelector("#policialCadastrou");
const Icargoo = document.querySelector("#cargoo");
const Idata = document.querySelector("#data");
const IdiaNome = document.querySelector("#diaNome");
const Ihora = document.querySelector("#hora");
const Iestudo = document.querySelector("#estudo");
const listaEdital = document.querySelector(".lista-edital");


// Função para listar edital sem autenticação
async function listarEdital() {
    const url = "http://localhost:8080/api/edital/list";
    
    try {
        const response = await fetch(url, {
            method: "GET", // Altere o método para GET
            headers: {
                "Authorization": `Bearer ${token}` // Adicione o token JWT no cabeçalho de autenticação
            }
        });
        
        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }
        
        const data = await response.json();
        exibirEdital(data);
    } catch (error) {
        console.error(error);
    }
}


// Exemplo de uso:
const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    listarEdital(token); // Chame a função de listagem com o token
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "login.html";
}


function exibirEdital(editais) {
    listaEdital.innerHTML = "";

    if (editais.length === 0) {
        // Não há edital para exibir
    } else {
        const table = document.createElement("table");
        table.classList.add("contato-table"); // Alterei a classe
        table.classList.add("move-right");

        const tableHeader = table.createTHead();
        const headerRow = tableHeader.insertRow();
        const headers = ["Selecionar", "Instituição", "Organização", "Policial Cadastro", "Cargo", "Data", "Dia Nome", "Hora", "Estudo"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        let corDeFundo = true;

        editais.forEach(function (edital) {
            if (edital.instituicao && edital.organizacao && edital.policialCadastrou && edital.data && edital.diaNome && edital.hora && edital.estudo) {
                const row = table.insertRow();

                const removeButtonCell = row.insertCell();
                const removeButton = document.createElement("button");
                removeButton.textContent = "X";
                removeButton.style.backgroundColor = "red"; // Define a cor de fundo vermelha
                removeButton.style.color = "black"; // Define a cor do texto como branco para melhor legibilidade
                removeButton.addEventListener("click", function () {
                    const editalId = edital.id; // Obtenha o ID do usuário
                    deletarEdital(editalId, token); // Chame a função para deletar o usuário com o token e o ID
                });
                removeButtonCell.appendChild(removeButton);

                const editalData = [edital.instituicao, edital.organizacao, edital.policialCadastrou, edital.cargoo, edital.data, edital.diaNome, edital.hora, edital.estudo];

                editalData.forEach(function (value, index) {
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

        listaEdital.appendChild(table);
    }
}

async function deletarEdital(editalId, token) {
    const url = `http://localhost:8080/api/edital/delete/${editalId}`;

    try {
        const response = await fetch(url, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}` // Adicione o token JWT no cabeçalho de autenticação
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        // Usuário excluído com sucesso, recarregue a página
        location.reload();

    } catch (error) {
        console.error(error);
    }
}
