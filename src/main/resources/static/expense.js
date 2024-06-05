let expenseContainer = document.querySelector(".mf1");
let balanceContainer = document.querySelector(".mf5");
let groupContainer = document.querySelector(".mf6");
let addExpenseContainer = document.querySelector(".mf3");
let statsContainer = document.querySelector(".mf4");
let balanceBtn = document.querySelector(".bln-btn");
let expenseBtn = document.querySelector(".exp-btn");
let addExpenseBtn = document.querySelector(".add-exp-btn");
let statsBtn = document.querySelector(".sts-btn");
let settingsBtn = document.querySelector(".set-btn");

balanceBtn.addEventListener("click", () => {
  expenseContainer.style.display = "none";
  balanceContainer.style.display = "block";
  addExpenseContainer.style.display = "none";
  groupContainer.style.display = "none";
  statsContainer.style.display = "none";
  console.log("Active");
  balanceBtn.classList.add("active");
  expenseBtn.classList.remove("active");
  statsBtn.classList.remove("active");
  settingsBtn.classList.remove("active");
});
expenseBtn.addEventListener("click", () => {
  expenseContainer.style.display = "block";
  balanceContainer.style.display = "none";
  groupContainer.style.display = "none";
  statsContainer.style.display = "none";
  addExpenseContainer.style.display = "none";
  expenseBtn.classList.add("active");
  balanceBtn.classList.remove("active");
  statsBtn.classList.remove("active");
  balanceBtn.classList.remove("active");
  settingsBtn.classList.remove("active");
});
addExpenseBtn.addEventListener("click", () => {
  expenseContainer.style.display = "none";
  balanceContainer.style.display = "none";
  groupContainer.style.display = "none";
  addExpenseContainer.style.display = "block";
  statsContainer.style.display = "none";
  expenseBtn.classList.add("active");
  balanceBtn.classList.remove("active");
  balanceBtn.classList.remove("active");
});

statsBtn.addEventListener("click", () => {
  expenseContainer.style.display = "none";
  balanceContainer.style.display = "none";
  addExpenseContainer.style.display = "none";
  groupContainer.style.display = "none";
  statsContainer.style.display = "block";
  statsBtn.classList.add("active");
  expenseBtn.classList.remove("active");
  balanceBtn.classList.remove("active");
  settingsBtn.classList.remove("active");
});

settingsBtn.addEventListener("click", () => {
  expenseContainer.style.display = "none";
  balanceContainer.style.display = "none";
  addExpenseContainer.style.display = "none";
  statsContainer.style.display = "none";
  groupContainer.style.display = "block";
  settingsBtn.classList.add("active");
  statsBtn.classList.remove("active");
  expenseBtn.classList.remove("active");
  balanceBtn.classList.remove("active");
});

function filterExpenses() {
  var input, filter, expensesList, expenseItems, expenseName, i, txtValue;
  input = document.getElementById("searchInput");
  filter = input.value.toLowerCase();
  expensesList = document.getElementById("expensesList");
  expenseItems = expensesList.getElementsByClassName("mf-expense-item");

  for (i = 0; i < expenseItems.length; i++) {
    expenseName = expenseItems[i].getElementsByClassName("mf-expense-name")[0];
    txtValue = expenseName.textContent || expenseName.innerText;
    if (txtValue.toLowerCase().indexOf(filter) > -1) {
      expenseItems[i].style.display = "";
    } else {
      expenseItems[i].style.display = "none";
    }
  }
}

function markAsPaid(event) {
  event.preventDefault();

  const link = event.target;
  const expenseDTO = {
    expenseName: "Reimbursement", // or set dynamically if needed
    expenseType: "Reimbursement", // or set dynamically if needed
    currency: link.getAttribute("data-currency"),
    amount: parseFloat(link.getAttribute("data-amount")),
    expenseDate: new Date().toISOString().split("T")[0], // Current date
    expensePayerId: parseFloat(link.getAttribute("data-expense-payer-id")),
    expensePayedToIds: [
      parseFloat(link.getAttribute("data-expense-payed-to-ids")),
    ], // Constructing an array with a single element
    groupId: parseFloat(link.getAttribute("data-group-id")),
  };
  fetch("/expenses/add-reimbersement/" + expenseDTO.groupId, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(expenseDTO),
  })
    .then((response) => {
      if (response.ok) {
        // Request was successful (status code 2xx)
        alert("Expense marked as paid successfully");
        window.location.href =
          "http://localhost:1111/expenses/group/" + expenseDTO.groupId; // Redirect to the group's details page
      } else {
        // Request failed (status code not 2xx)
        alert("Failed to mark expense as paid");
      }
    })
    .catch((error) => {
      // Handle network error
      console.error("Error:", error);
      alert("An error occurred while marking the expense as paid");
    });
}

function deleteExpense(expenseId) {
  // Confirm before deleting
  if (confirm("Are you sure you want to delete this expense?")) {
    fetch(`http://localhost:1111/expenses/delete-expense/${expenseId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        alert("expense deleted successfully");
        // Reload the page after deletion
        location.reload();
      })
      .catch((error) => {
        console.error("There was a problem deleting the expense:", error);
      });
  }
}

