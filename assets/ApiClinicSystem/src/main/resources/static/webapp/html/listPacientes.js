document.getElementById('list-patients-button').addEventListener('click', function() {
    var token = localStorage.getItem('token');

    fetch('http://localhost:8080/pacientes', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        }
    })
    .then(response => response.json())
    .then(data => {
        var tableBody = document.querySelector('#patients-table tbody');
        tableBody.innerHTML = ''; // Clear existing data

        data.content.forEach(patient => {
            var row = document.createElement('tr');

            var nameCell = document.createElement('td');
            nameCell.textContent = patient.nome;
            row.appendChild(nameCell);

            var emailCell = document.createElement('td');
            emailCell.textContent = patient.email;
            row.appendChild(emailCell);

            var cpfCell = document.createElement('td');
            cpfCell.textContent = patient.cpf;
            row.appendChild(cpfCell);

            tableBody.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Error fetching patients:', error);
    });
});
