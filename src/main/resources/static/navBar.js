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

  function logout() {
      // Handle redirection here
      window.location.href = '/logout';
  }

  