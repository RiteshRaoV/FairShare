function addParticipant() {
    const participantsList = document.getElementById('participantsList');
    const participantDiv = document.createElement('div');
    participantDiv.className = 'participant';

    const input = document.createElement('input');
    input.type = 'text';
    input.placeholder = 'Participant Name';
    input.required = true;

    const removeButton = document.createElement('button');
    removeButton.className = 'remove-participant';
    removeButton.onclick = function() { removeParticipant(this) };

    const icon = document.createElement('i');
    icon.className = 'fas fa-trash-alt';
    removeButton.appendChild(icon);

    participantDiv.appendChild(input);
    participantDiv.appendChild(removeButton);
    participantsList.appendChild(participantDiv);
}

function removeParticipant(button) {
    const participantDiv = button.parentElement;
    participantDiv.remove();
}

function clearParticipants() {
    const participantsList = document.getElementById('participantsList');
    while (participantsList.firstChild) {
        participantsList.removeChild(participantsList.firstChild);
    }
}


document.addEventListener('DOMContentLoaded', function () {
    closeProfileModal();  
  })
  function openProfileModal() {
      const modal = document.getElementById('profileModal');
      modal.style.display = 'block';
  }

  function closeProfileModal() {
      const modal = document.getElementById('profileModal');
      modal.style.display = 'none';
  }

  window.onclick = function(event) {
      const modal = document.getElementById('profileModal');
      if (event.target === modal) {
          modal.style.display = 'none';
      }
  }