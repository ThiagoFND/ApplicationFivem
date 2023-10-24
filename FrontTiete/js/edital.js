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

function validarCamposPreenchidos() {
    if (!Iinstituicao.value || !Iorganizacao.value || !IpolicialCadastrou.value || !Icargoo.value || !Idata.value || !IdiaNome.value || !Ihora.value || !Iestudo.value) {
        alert("Por favor, preencha todos os campos antes de enviar o formulário.");
        return false;
    }
    return true;
}

// Cadastra o edital
function cadastrar(token) {
    const dados = {
        instituicao: Iinstituicao.value,
        organizacao: Iorganizacao.value,
        policialCadastrou: IpolicialCadastrou.value,
        cargoo: Icargoo.value,
        data: Idata.value,
        diaNome: IdiaNome.value,
        hora: Ihora.value,
        estudo: Iestudo.value
    };

    fetch("http://localhost:8080/api/edital/create", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Adicione o token JWT no cabeçalho
        },
        method: "POST",
        body: JSON.stringify(dados)
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
        Iinstituicao.value = "";
        Iorganizacao.value = "";
        IpolicialCadastrou.value = "";
        Icargoo.value = "";
        Idata.value = "";
        IdiaNome.value = "";
        Ihora.value = "";
        Iestudo.value = "";
        listarEdital(token); // Atualize a lista de edital após o cadastro
    })
    .catch(function (error) {
        console.error(error);
    });
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
        const headers = ["Instituição", "Organização", "Policial Cadastro", "Cargo", "Data", "Dia Nome", "Hora", "Estudo"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        let corDeFundo = true;

        editais.forEach(function (edital) {
            if (edital.instituicao && edital.organizacao && edital.policialCadastrou && edital.data && edital.diaNome && edital.hora && edital.estudo) {
                const row = table.insertRow();

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

// Função para listar edital sem autenticação
async function listarEdital() {
    const url = "http://localhost:8080/api/edital/list";
    
    try {
        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }
        
        const data = await response.json();
        exibirEdital(data);
    } catch (error) {
        console.error(error);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    listarEdital();
});

document.addEventListener('DOMContentLoaded', function () {
    const token = sessionStorage.getItem("token");

    // Verifique se o token não está definido ou é nulo
    if (!token) {
        // Redirecione o usuário para a página de login
        window.location.href = "login.html";
        return; // Saia da função para evitar a execução do restante do código
    }

    // O código a seguir será executado apenas se o usuário tiver um token
    const formulario = document.getElementById("cadastroForm");

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        if (validarCamposPreenchidos()) {
            cadastrar(token);
            alert('Cadastro efetuado com sucesso');
        } else {
            alert('Usuário não autenticado, faça login primeiro');
            window.location.href = "login.html"; // Redirecione o usuário para a página de login
        }
    });

    // Restante do seu código...
});



Idata.addEventListener("input", function () {
    let inputValue = Idata.value;

    // Remove todos os caracteres não numéricos usando expressões regulares
    inputValue = inputValue.replace(/\D/g, "");

    // Verifique se a data tem mais de 8 dígitos (8 é o comprimento máximo que você deseja)
    if (inputValue.length > 8) {
        // Se tiver mais de 8 dígitos, corte o valor para 8 dígitos
        inputValue = inputValue.slice(0, 8);
    }

    // Separe os números em dia, mês e ano
    const day = inputValue.slice(0, 2);
    const month = inputValue.slice(2, 4);
    const year = inputValue.slice(4);

    // Formate a data no campo
    Idata.value = `${day}/${month}/${year}`;
});
