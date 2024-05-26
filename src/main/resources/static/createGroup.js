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
    document.getElementById("participantSearch").value = "";
    document.getElementById("searchResults").style.display = "none";

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
  } else {
    alert("user already added");
  }
}

function removeParticipant(userId, button) {
  participants = participants.filter((id) => id !== userId);
  button.parentElement.remove();
}

function createGroup() {
  let groupName = document.getElementById("groupName").value;
  let currencySymbol = document.getElementById("currencySymbol").value;
  let type = document.getElementById("groupType").value;

  let groupDTO = {
    groupName: groupName,
    currency: currencySymbol,
    groupType: type,
    participants: participants, // Assuming participants is defined elsewhere
  };

  if (groupDTO.groupName != "") {
    fetch("http://localhost:1111/group/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        // Add any additional headers if needed
      },
      body: JSON.stringify(groupDTO),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        // Handle success response from server
        console.log("Group created successfully:", data);
        alert("Group created successfully");
        window.location.href = "/group" // Redirect to the group's details page
      })
      .catch((error) => {
        // Handle error
        console.error("There was a problem creating the group:", error);
        alert("There was a problem creating the group.");
      });
  } else {
    alert("enter a group name");
  }
}


function removeParticipant(button) {
  const participantDiv = button.parentElement;
  participantDiv.remove();
}

function clearParticipants() {
  participants = [];
  document.getElementById("participantsList").innerHTML = "";
  document.getElementById("participantSearch").value = "";
  document.getElementById("groupName").value = "";
  const participantsList = document.getElementById("participantsList");
  while (participantsList.firstChild) {
    participantsList.removeChild(participantsList.firstChild);
  }
  document.getElementById("participantSearch").value = "";
  document.getElementById("searchResults").style.display = "none";
  document.getElementById("groupName").value = "";
}
