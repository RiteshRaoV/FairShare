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
  console.log("Active")
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
  const reimbursement = link.closest(".reimbursement");
  reimbursement.style.display = "none";
  alert("Marked as paid");
}

function deleteExpense(expenseId){
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