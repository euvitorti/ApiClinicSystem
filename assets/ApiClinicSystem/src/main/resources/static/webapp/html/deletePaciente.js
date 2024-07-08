document.getElementById('delete-patient-button').addEventListener('click', function() {
    var token = localStorage.getItem('Bearer Token');
    var patientId = document.getElementById('patient-id').value;

    if (!token) {
        console.error('Token not found');
        document.getElementById('error-message').innerText = 'Token not found.';
        return;
    }

    if (!patientId) {
        console.error('Patient ID is required');
        document.getElementById('error-message').innerText = 'Patient ID is required.';
        return;
    }

    fetch(`http://localhost:8080/pacientes/${patientId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.text(); // DELETE geralmente não retorna JSON
    })
    .then(data => {
        alert('Patient deleted successfully!');
        document.getElementById('error-message').innerText = '';
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Error deleting patient. Please try again.';
        console.error('Error deleting patient:', error);
    });
});
