package splitwise.project.splitwise.Services;

import java.util.List;

import splitwise.project.splitwise.Model.Expense;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    void removeExpense(long expenseId);

    Expense updateExpense(long expenseId, Expense expense);

    Expense getExpense(long expenseId);

    List<Expense> getAllExpenses();

    List<Expense> getAllExpenseOfUser(long userId);

    List<Expense> getAllExpenseOfGroup(long groupId);
}
