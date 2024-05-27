package splitwise.project.splitwise.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import splitwise.project.splitwise.DTO.ExpenseDTO;
import splitwise.project.splitwise.Model.Expense;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.ExpenseRepository;
import splitwise.project.splitwise.Repository.GroupRepository;
import splitwise.project.splitwise.Repository.UserRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

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
        return expenseRepository.findByGroup_GroupId(groupId);
    }

    @Override
    public void addExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setExpenseName(expenseDTO.getExpenseName());
        expense.setExpenseType(expenseDTO.getExpenseType());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());

        User expensePayer = userRepository.findByUserId(expenseDTO.getExpensePayerId());
        expense.setExpensePayer(expensePayer);

        List<User> expensePayedTo = new ArrayList<>();
        for (Long userId : expenseDTO.getExpensePayedToIds()) {
            User user = userRepository.findByUserId(userId);
            expensePayedTo.add(user);
        }
        expense.setExpensePayedTo(expensePayedTo);

        Group group = groupRepository.findByGroupId(expenseDTO.getGroupId());
        expense.setCurrency(group.getCurrency());
        group.getExpenses().add(expense);
        expense.setGroup(group);
        expenseRepository.save(expense);
        groupRepository.save(group);
    }

    @Override
    public double getTotalGroupExpense(long groupId){
        List<Expense> expenses = expenseRepository.findByGroup_GroupId(groupId);
        double totalGroupExpense=0;
        for(Expense expense:expenses){
            totalGroupExpense+=expense.getAmount();
        }
        return totalGroupExpense;
    }

}
