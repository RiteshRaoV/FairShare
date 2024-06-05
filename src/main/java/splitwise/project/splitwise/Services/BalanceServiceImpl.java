package splitwise.project.splitwise.Services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import splitwise.project.splitwise.Model.Expense;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.ExpenseRepository;
import splitwise.project.splitwise.Repository.GroupRepository;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Map<User, Double> calculateBalances(Long groupId) {
        List<Expense> expenses = expenseRepository.findAll(); // Get all expenses
        Map<User, Double> balances = new HashMap<>();

        // Decimal format to limit to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");

        for (Expense expense : expenses) {
            if (expense.getGroup().getGroupId().equals(groupId)) {
                User payer = expense.getExpensePayer();
                List<User> participants = expense.getExpensePayedTo();
                double splitAmount = expense.getAmount() / participants.size();

                // Update payer balance
                balances.put(payer,
                        Double.parseDouble(
                                df.format(balances.getOrDefault(payer, 0.0) + expense.getAmount())));

                // Update participants balance
                for (User participant : participants) {
                    balances.put(participant,
                            Double.parseDouble(
                                    df.format(balances.getOrDefault(participant, 0.0) - splitAmount)));
                }
            }
        }
        return balances;
    }

    @Override
    public List<Map<String, Object>> settleDebts(Long groupId) {
        Map<User, Double> balances = calculateBalances(groupId);
        List<Map<String, Object>> transactions = new ArrayList<>();
    
        // Separate creditors and debtors
        List<Map.Entry<User, Double>> creditors = new ArrayList<>();
        List<Map.Entry<User, Double>> debtors = new ArrayList<>();
    
        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            if (entry.getValue() > 0) {
                creditors.add(entry);
            } else if (entry.getValue() < 0) {
                debtors.add(entry);
            }
        }
    
        creditors.sort(Map.Entry.comparingByValue());
        debtors.sort(Map.Entry.comparingByValue());
    
        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            Map.Entry<User, Double> creditor = creditors.remove(creditors.size() - 1);
            Map.Entry<User, Double> debtor = debtors.remove(debtors.size() - 1);
    
            BigDecimal creditorValue = BigDecimal.valueOf(creditor.getValue()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal debtorValue = BigDecimal.valueOf(debtor.getValue()).setScale(2, RoundingMode.HALF_UP);
    
            BigDecimal settledAmount = creditorValue.min(debtorValue.negate());
    
            Map<String, Object> transaction = new HashMap<>();
            transaction.put("from", debtor.getKey());
            transaction.put("to", creditor.getKey());
            transaction.put("amount", settledAmount.setScale(2, RoundingMode.HALF_UP).doubleValue());
    
            transactions.add(transaction);
    
            creditorValue = creditorValue.subtract(settledAmount).setScale(2, RoundingMode.HALF_UP);
            debtorValue = debtorValue.add(settledAmount).setScale(2, RoundingMode.HALF_UP);
    
            if (creditorValue.compareTo(BigDecimal.ZERO) > 0) {
                creditor.setValue(creditorValue.doubleValue());
                creditors.add(creditor);
            }
            if (debtorValue.compareTo(BigDecimal.ZERO) < 0) {
                debtor.setValue(debtorValue.doubleValue());
                debtors.add(debtor);
            }
        }
    
        List<Expense> expenses = expenseRepository.findByGroup_GroupId(groupId);
        Group group = groupRepository.findByGroupId(groupId);
        group.setEndDate(LocalDate.now());
        group.setTotalExpense(expenses.stream()
            .mapToDouble(Expense::getAmount)
            .map(amount -> BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).doubleValue())
            .sum());
        groupRepository.save(group);
    
        return transactions;
    }
    

}
