// Função para recuperar o token do armazenamento local
function getToken() {
    return localStorage.getItem('Bearer Token');
}

// Função para registrar um novo paciente
function registerDoctor(event) {
    event.preventDefault();

    // Captura os valores dos campos de entrada
    var id = document.querySelector('#id').value;
    var name = document.querySelector('#name').value;
    var email = document.querySelector('#email').value;
    var phone = document.querySelector('#phone').value;
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
        id: id,
        name: name,
        phone: phone,
        dataAddress: {
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

    // Envia uma requisição PUT para atualizar o paciente
    fetch('http://localhost:8080/medicos', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        },
        body: JSON.stringify(patientData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Update failed.');
        }
        return response.json();
    })
    .then(data => {
        alert('Doctor updated successfully!');
        window.location.href = 'menu.html'; // Redireciona após a atualização bem-sucedida
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Update failed. Please check your inputs.';
        console.error('Update error:', error);
    });
}

// Adiciona um event listener para o evento 'submit' do formulário de registro
document.querySelector('#register-form').addEventListener('submit', registerDoctor);
