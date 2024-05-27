    let expenseContainer = document.querySelector(".mf1");
    let balanceContainer = document.querySelector(".mf2");
    let addExpenseContainer = document.querySelector(".mf3")
    let statsContainer = document.querySelector(".mf4")
    let balanceBtn = document.querySelector(".bln-btn");
    let expenseBtn = document.querySelector(".exp-btn");
    let addExpenseBtn = document.querySelector(".add-exp-btn");
    let statsBtn = document.querySelector(".sts-btn");

    balanceBtn.addEventListener('click',()=>{
        expenseContainer.style.display='none'
        balanceContainer.style.display='block'
        addExpenseContainer.style.display="none"
        statsContainer.style.display="none"
        balanceBtn.classList.add('active')
        expenseBtn.classList.remove('active')
        statsBtn.classList.remove('active')

    })
    expenseBtn.addEventListener('click',()=>{
        expenseContainer.style.display='block'
        balanceContainer.style.display='none'
        statsContainer.style.display="none"
        expenseBtn.classList.add('active')
        balanceBtn.classList.remove('active')
        statsBtn.classList.remove('active')
        addExpenseContainer.style.display="none"

    })
    addExpenseBtn.addEventListener('click',()=>{
        expenseContainer.style.display='none'
        balanceContainer.style.display='none'
        addExpenseContainer.style.display="block"
        statsContainer.style.display="none"
        expenseBtn.classList.add('active')
        balanceBtn.classList.remove('active')

    })

    statsBtn.addEventListener('click',()=>{
        expenseContainer.style.display='none'
        balanceContainer.style.display='none'
        addExpenseContainer.style.display="none"
        statsContainer.style.display="block"
        statsBtn.classList.add('active')
        expenseBtn.classList.remove('active')

    })

    function filterExpenses() {
        var input, filter, expensesList, expenseItems, expenseName, i, txtValue;
        input = document.getElementById('searchInput');
        filter = input.value.toLowerCase();
        expensesList = document.getElementById("expensesList");
        expenseItems = expensesList.getElementsByClassName('mf-expense-item');
        
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