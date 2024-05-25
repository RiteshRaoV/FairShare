let participants = [];

function searchParticipants() {
  let input = document.getElementById("participantSearch").value.toLowerCase();
  let searchResults = document.getElementById("searchResults"); // Changed the ID to avoid duplication
  searchResults.innerHTML = "";

  if (input.length > 0) {
    users
      .filter((user) => user.email.toLowerCase().includes(input))
      .forEach((user) => {
        let option = document.createElement("option");
        option.textContent = `${user.firstName}-${user.email}`;
        option.onclick = () => addParticipant(user);
        searchResults.appendChild(option);
      });
    searchResults.style.display = "block";
  } else {
    searchResults.style.display = "none";
  }
}

function addParticipant(user) {
  if (!participants.includes(user.userId)) {
    participants.push(user.userId);
    const participantsList = document.getElementById("participantsList");
    const participantDiv = document.createElement("div");
    participantDiv.className = "participant";

    const input = document.createElement("input");
    input.type = "text";
    input.placeholder = "Participant Name";
    input.value = `${user.firstName}`;

    const removeButton = document.createElement("button");
    removeButton.className = "remove-participant";
    removeButton.onclick = function () {
      removeParticipant(this);
    };

    const icon = document.createElement("i");
    icon.className = "fas fa-trash-alt";
    removeButton.appendChild(icon);

    participantDiv.appendChild(input);
    participantDiv.appendChild(removeButton);
    participantsList.appendChild(participantDiv);
  }
}

function removeParticipant(userId, button) {
  participants = participants.filter((id) => id !== userId);
  button.parentElement.remove();
}

function createGroup() {
  let groupName = document.getElementById("groupName").value;
  let currencySymbol = document.getElementById("currencySymbol").value;

  let groupDTO = {
      groupName: groupName,
      currency: currencySymbol,
      participants: participants, // Assuming participants is defined elsewhere
  };

  fetch('http://localhost:1111/group/create', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
          // Add any additional headers if needed
      },
      body: JSON.stringify(groupDTO),
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Network response was not ok');
      }
      return response.json();
  })
  .then(data => {
      // Handle success response from server
      console.log('Group created successfully:', data);
  })
  .catch(error => {
      // Handle error
      console.error('There was a problem creating the group:', error);
  });
  
}


function clearParticipants() {
  participants = [];
  document.getElementById("participantsList").innerHTML = "";
  document.getElementById("participantSearch").value="";
}

function removeParticipant(button) {
  const participantDiv = button.parentElement;
  participantDiv.remove();
}

function clearParticipants() {
  const participantsList = document.getElementById("participantsList");
  while (participantsList.firstChild) {
    participantsList.removeChild(participantsList.firstChild);
  }
  document.getElementById("participantSearch").value="";
  document.getElementById("searchResults").style.display="none";
}

document.addEventListener("DOMContentLoaded", function () {
  closeProfileModal();
});
function openProfileModal() {
  const modal = document.getElementById("profileModal");
  modal.style.display = "block";
}

function closeProfileModal() {
  const modal = document.getElementById("profileModal");
  modal.style.display = "none";
}

window.onclick = function (event) {
  const modal = document.getElementById("profileModal");
  if (event.target === modal) {
    modal.style.display = "none";
  }
};
