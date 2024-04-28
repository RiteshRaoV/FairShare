package splitwise.project.splitwise.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import splitwise.project.splitwise.Model.Expense;
import splitwise.project.splitwise.Repository.ExpenseRepository;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void removeExpense(long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public Expense updateExpense(long expenseId, Expense expense) {
        Expense updatedExpense = expense;
        updatedExpense.setExpenseId(expenseId);
        return expenseRepository.save(updatedExpense);
    }

    @Override
    public Expense getExpense(long expenseId) {
        return expenseRepository.findById(expenseId).get();
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getAllExpenseOfUser(long userId) {
        return expenseRepository.findByExpensePayerUserId(userId);
    }

    @Override
    public List<Expense> getAllExpenseOfGroup(long groupId) {
        return expenseRepository.findByExpenseGroupsId(groupId);
    }

}
