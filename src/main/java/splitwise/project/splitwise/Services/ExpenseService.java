package splitwise.project.splitwise.Services;

import java.util.List;

import splitwise.project.splitwise.DTO.ExpenseDTO;
import splitwise.project.splitwise.Model.Expense;

public interface ExpenseService {


    void removeExpense(long expenseId);

    Expense updateExpense(long expenseId, Expense expense);

    Expense getExpense(long expenseId);

    List<Expense> getAllExpenses();

    List<Expense> getAllExpenseOfUser(long userId);

    List<Expense> getAllExpenseOfGroup(long groupId);

    void addExpense(ExpenseDTO expenseDTO);

    double getTotalGroupExpense(long groupId);
}
