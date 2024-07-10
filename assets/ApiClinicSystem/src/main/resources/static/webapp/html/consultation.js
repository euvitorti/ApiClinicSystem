// Função para recuperar o token do armazenamento local
function getToken() {
    return localStorage.getItem('Bearer Token');
}

// Função para registrar um novo paciente
function registerConsultation(event) {
    event.preventDefault();

    // Captura os valores dos campos de entrada
    var idDoctor = document.querySelector('#idDoctor').value;
    var idPaciente = document.querySelector('#idPaciente').value;
    var data = document.querySelector('#data').value;
    var hora = document.querySelector('#hora').value;
    var especialidade = document.querySelector('#especialidade').value;

    // Combinar a data e a hora - formato ISO 8601
    const dataHoraInput = `${data}T${hora}:00.000Z`;

    // Cria um objeto com os dados do paciente
    var consultationData = {
        idDoctor: idDoctor,
        idPaciente: idPaciente,
        data: dataHoraInput,
        especialidade: especialidade
    };

    // Envia uma requisição POST para registrar o paciente
    fetch('http://localhost:8080/consultas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        },
        body: JSON.stringify(consultationData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Registration failed.');
        }
        return response.json();
    })
    .then(data => {
        alert('Consultation registered successfully!');
        window.location.href = 'menu.html'; // Redireciona após o registro bem-sucedido
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Registration failed. Please check your inputs.';
        console.error('Registration error:', error);
    });
}

// Adiciona um event listener para o evento 'submit' do formulário de registro
document.querySelector('#register-form').addEventListener('submit', registerConsultation);
