const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Icpf = document.querySelector("#cpf");
const Iservicos = document.querySelector("#servicos");
const Ivalor = document.querySelector("#valor");
const Icrimes = document.querySelector("#crimes");
const Ipolicial = document.querySelector("#policial");
const listaContatos = document.querySelector(".lista-prisao");

function validarCamposPreenchidos() {
    if (!Inome.value || !Icpf.value || !Iservicos.value || !Ivalor.value || !Icrimes.value || !Ipolicial.value) {
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
        }
    });
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    console.log("Usuário não autenticado. Faça o login primeiro.");
}

// cadastra os dados
function cadastrar() {
    fetch("http://localhost:8080/api/prisao/create", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Adicione o token JWT no cabeçalho
        },
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            cpf: Icpf.value,
            servicos: Iservicos.value,
            valor: Ivalor.value,
            crimes: Icrimes.value,
            policial: Ipolicial.value
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
        Inome.value = "";
        Icpf.value = "";
        Iservicos.value = "";
        Ivalor.value = "";
        Icrimes.value = "";
        Ipolicial.value = "";
    })
    .catch(function (error) {
        console.error(error);
    });
}