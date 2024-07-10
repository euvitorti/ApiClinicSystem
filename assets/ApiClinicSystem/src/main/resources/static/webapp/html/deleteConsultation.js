document.getElementById('delete-consultation-button').addEventListener('click', function() {
    var token = localStorage.getItem('Bearer Token');
    var consultationId = document.getElementById('consultation-id').value;
    var cancellationReason = document.getElementById('cancellationReason-id').value;

    if (!token) {
        console.error('Token not found');
        document.getElementById('error-message').innerText = 'Token not found.';
        return;
    }

    if (!consultationId) {
        document.getElementById('error-message').innerText = 'Consultation ID is required.';
        return;
    }

    var deleteData = {
           idConsulta: consultationId,
           cancellationReason: cancellationReason,
        };

    fetch(`http://localhost:8080/consultas`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token.trim()

        },
        body: JSON.stringify(deleteData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.text(); // DELETE geralmente não retorna JSON
    })
    .then(data => {
        alert('Consultation deleted successfully!');
        document.getElementById('error-message').innerText = '';
    })
    .catch(error => {
        document.getElementById('error-message').innerText = 'Error deleting consultation. Please try again.';
        console.error('Error deleting consultation:', error);
    });
});
