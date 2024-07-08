document.getElementById('search-doctor-button').addEventListener('click', function() {
    var token = localStorage.getItem('Bearer Token');
    var doctorId = document.getElementById('doctor-id').value;

    if (!token) {
        console.error('Token not found');
        return;
    }

    if (!doctorId) {
        console.error('Doctor ID is required');
        document.getElementById('error-message').innerText = 'Doctor ID is required.';
        return;
    }

    fetch(`http://localhost:8080/medicos/${doctorId}`, {
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
        var tableBody = document.querySelector('#doctor-table tbody');
        tableBody.innerHTML = ''; // Clear existing data

        if (data) {
           var row = document.createElement('tr');

           var nameCell = document.createElement('td');
           nameCell.textContent = data.name;
           row.appendChild(nameCell);

           var emailCell = document.createElement('td');
           emailCell.textContent = data.email;
           row.appendChild(emailCell);

           var crmCell = document.createElement('td');
           crmCell.textContent = data.crm;
           row.appendChild(crmCell);

           var specialtyCell = document.createElement('td');
           specialtyCell.textContent = data.specialty;
           row.appendChild(specialtyCell);

           tableBody.appendChild(row);
        } else {
            console.error('Patient not found');
            document.getElementById('error-message').innerText = 'Doctor not found.';
        }
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Error fetching doctor data. Please try again.';
        console.error('Error fetching patient:', error);
    });
});
