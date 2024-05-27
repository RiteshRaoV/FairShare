package splitwise.project.splitwise.Services;

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

    public Map<String, Double> calculateBalances(Long groupId) {
        List<Expense> expenses = expenseRepository.findAll(); // Get all expenses
        Map<String, Double> balances = new HashMap<>();

        // Decimal format to limit to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");

        for (Expense expense : expenses) {
            if (expense.getGroup().getGroupId().equals(groupId)) {
                User payer = expense.getExpensePayer();
                List<User> participants = expense.getExpensePayedTo();
                double splitAmount = expense.getAmount() / participants.size();

                // Update payer balance
                balances.put(payer.getFirstName(),
                        Double.parseDouble(
                                df.format(balances.getOrDefault(payer.getFirstName(), 0.0) + expense.getAmount())));

                // Update participants balance
                for (User participant : participants) {
                    balances.put(participant.getFirstName(),
                            Double.parseDouble(
                                    df.format(balances.getOrDefault(participant.getFirstName(), 0.0) - splitAmount)));
                }
            }
        }
        return balances;
    }

    @Override
    public List<Map<String, Object>> settleDebts(Long groupId) {
        Map<String, Double> balances = calculateBalances(groupId);
        List<Map<String, Object>> transactions = new ArrayList<>();

        // Separate creditors and debtors
        List<Map.Entry<String, Double>> creditors = new ArrayList<>();
        List<Map.Entry<String, Double>> debtors = new ArrayList<>();

        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            if (entry.getValue() > 0) {
                creditors.add(entry);
            } else if (entry.getValue() < 0) {
                debtors.add(entry);
            }
        }

        creditors.sort(Map.Entry.comparingByValue());
        debtors.sort(Map.Entry.comparingByValue());

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            Map.Entry<String, Double> creditor = creditors.remove(creditors.size() - 1);
            Map.Entry<String, Double> debtor = debtors.remove(debtors.size() - 1);

            double settledAmount = Math.min(creditor.getValue(), -debtor.getValue());

            Map<String, Object> transaction = new HashMap<>();
            transaction.put("from", debtor.getKey());
            transaction.put("to", creditor.getKey());
            transaction.put("amount", settledAmount);

            transactions.add(transaction);

            creditor.setValue(creditor.getValue() - settledAmount);
            debtor.setValue(debtor.getValue() + settledAmount);

            if (creditor.getValue() > 0) {
                creditors.add(creditor);
            }
            if (debtor.getValue() < 0) {
                debtors.add(debtor);
            }
        }
        List<Expense> expenses = expenseRepository.findByGroup_GroupId(groupId);
        Group group = groupRepository.findByGroupId(groupId);
        group.setEndDate(LocalDate.now());
        group.setTotalExpense(expenses.stream().mapToDouble(Expense::getAmount).sum());
        groupRepository.save(group);
        return transactions;
    }

}
