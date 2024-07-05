document.getElementById('list-patients-button').addEventListener('click', function() {
    var token = localStorage.getItem('Bearer Token');

    if (!token) {
        console.error('Token not found');
        return;
    }

    fetch('http://localhost:8080/pacientes', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        var tableBody = document.querySelector('#patients-table tbody');
        tableBody.innerHTML = ''; // Clear existing data

        if (data.content && data.content.length > 0) {
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
        } else {
            console.error('No patients found in response');
        }
    })
    .catch(error => {
        console.error('Error fetching patients:', error);
    });
});
