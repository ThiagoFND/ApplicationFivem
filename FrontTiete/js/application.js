const formulario = document.querySelector("form");
const Iartigo = document.querySelector("#artigo");
const Icrime = document.querySelector("#crime");
const Iservicos = document.querySelector("#servicos");
const Ivalor = document.querySelector("#valor");
const listaContatos = document.querySelector(".lista-contatos");

// Variáveis para rastrear os totais de Valor e Serviço
let totalValor = 0;
let totalServico = 0;
let contatos = []; // Adicione uma variável para manter os contatos

function validarCamposPreenchidos() {
    if (!Iartigo.value || !Icrime.value || !Iservicos.value || !Ivalor.value) {
        alert("Por favor, preencha todos os campos antes de enviar o formulário.");
        return false;
    }
    return true;
}

const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validarCamposPreenchidos()) {
            cadastrar(token);
            alert('Cadastrado com sucesso!');
            location.reload();
        }
    });
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    console.log("Usuário não autenticado. Faça o login primeiro.");
}


// cadastra os dados
function cadastrar() {
    fetch("http://localhost:8080/api/dados/create", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Adicione o token JWT no cabeçalho
        },
        method: "POST",
        body: JSON.stringify({
            artigo: Iartigo.value,
            crime: Icrime.value,
            servicos: Iservicos.value,
            valor: Ivalor.value
        })
    })
    .then(function (res) {
        if (!res.ok) {
            throw new Error('Erro na requisição. Status: ' + res.status + ' ' + res.statusText);
        }
        return res.json();
    })
    .then(function (data) {
        console.log(data);
        // Após cadastrar, limpe os campos
        Iartigo.value = "";
        Icrime.value = "";
        Iservicos.value = "";
        Ivalor.value = "";
        listarDados();
    })
    .catch(function (error) {
        console.error(error);
    });
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

                const checkboxCell = row.insertCell();
                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkboxCell.appendChild(checkbox);

                const contatoData = [contato.artigo, contato.crime, contato.servicos, contato.valor];

                contatoData.forEach(function (value, index) {
                    const cell = row.insertCell();
                    cell.textContent = value;
                });
            }
        });

        listaContatos.appendChild(table);

        // Atualize os totais ao exibir os dados
        atualizarTotais();
    }
}

function atualizarTotais() {
    totalValor = 0;
    totalServico = 0;
    const artigosSelecionados = [];
    const crimesSelecionados = [];

    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function (checkbox, index) {
        if (checkbox.checked) {
            totalValor += parseFloat(contatos[index].valor);
            totalServico += parseFloat(contatos[index].servicos);
            artigosSelecionados.push(contatos[index].artigo);
            crimesSelecionados.push(contatos[index].crime);
        }
    });

    document.getElementById("total-valor").textContent = "Valor total: " + totalValor;
    document.getElementById("total-servico").textContent = "Serviço total: " + totalServico;

    // Exibir os nomes dos artigos selecionados
    document.getElementById("artigos-selecionados").textContent = "Artigos selecionados: " + artigosSelecionados.join(", ");

    // Exibir os nomes dos crimes selecionados
    document.getElementById("crimes-selecionados").textContent = "Crimes selecionados: " + crimesSelecionados.join(", ");
}


async function listarDados() {
    const url = "http://localhost:8080/api/dados/list";

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        const data = await response.json();
        exibirDados(data);
    } catch (error) {
        console.error(error);
    }
}

// ... (código anterior)

document.addEventListener('DOMContentLoaded', function() {
    listarDados();

    // Adicione um evento de clique para atualizar os totais quando os checkboxes são clicados
    document.addEventListener('change', function(event) {
        if (event.target && event.target.type === 'checkbox') {
            atualizarTotais();
        }
    });
});
