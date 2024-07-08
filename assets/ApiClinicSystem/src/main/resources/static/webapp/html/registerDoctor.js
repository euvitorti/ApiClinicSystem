// Função para recuperar o token do armazenamento local
function getToken() {
    return localStorage.getItem('Bearer Token');
}

// Função para registrar um novo paciente
function registerDoctor(event) {
    event.preventDefault();

    // Captura os valores dos campos de entrada
    var name = document.querySelector('#name').value;
    var email = document.querySelector('#email').value;
    var phone = document.querySelector('#phone').value;
    var crm = document.querySelector('#crm').value;
    var specialty = document.querySelector('#specialty').value;
    var cpf = document.querySelector('#cpf').value;
    var street = document.querySelector('#street').value;
    var neighborhood = document.querySelector('#neighborhood').value;
    var cep = document.querySelector('#cep').value;
    var city = document.querySelector('#city').value;
    var uf = document.querySelector('#uf').value;
    var number = document.querySelector('#number').value;
    var complement = document.querySelector('#complement').value;

    // Cria um objeto com os dados do paciente
    var patientData = {
        name: name,
        email: email,
        phone: phone,
        crm: crm,
        specialty: specialty,
        cpf: cpf,
        address: {
            street: street,
            neighborhood: neighborhood,
            cep: cep,
            city: city,
            uf: uf,
            number: number,
            complement: complement
        }
    };

    // Exibe o token para verificar se está sendo capturado corretamente
    console.log("Token:", getToken());
    var token = getToken();

    // Envia uma requisição POST para registrar o paciente
    fetch('http://localhost:8080/medicos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        },
        body: JSON.stringify(patientData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Registration failed.');
        }
        return response.json();
    })
    .then(data => {
        alert('Doctor registered successfully!');
        window.location.href = 'menu.html'; // Redireciona após o registro bem-sucedido
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Registration failed. Please check your inputs.';
        console.error('Registration error:', error);
    });
}

// Adiciona um event listener para o evento 'submit' do formulário de registro
document.querySelector('#register-form').addEventListener('submit', registerDoctor);
