document.getElementById('search-patient-button').addEventListener('click', function() {
    var token = localStorage.getItem('Bearer Token');
    var patientId = document.getElementById('patient-id').value;

    if (!token) {
        console.error('Token not found');
        return;
    }

    if (!patientId) {
        console.error('Patient ID is required');
        document.getElementById('error-message').innerText = 'Patient ID is required.';
        return;
    }

    fetch(`http://localhost:8080/pacientes/${patientId}`, {
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

        if (data) {
            var row = document.createElement('tr');

            var nameCell = document.createElement('td');
            nameCell.textContent = data.nome;
            row.appendChild(nameCell);

            var emailCell = document.createElement('td');
            emailCell.textContent = data.email;
            row.appendChild(emailCell);

            var cpfCell = document.createElement('td');
            cpfCell.textContent = data.cpf;
            row.appendChild(cpfCell);

            tableBody.appendChild(row);
        } else {
            console.error('Patient not found');
            document.getElementById('error-message').innerText = 'Patient not found.';
        }
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Error fetching patient data. Please try again.';
        console.error('Error fetching patient:', error);
    });
});
