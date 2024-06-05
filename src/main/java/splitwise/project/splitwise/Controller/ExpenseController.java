package splitwise.project.splitwise.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import splitwise.project.splitwise.DTO.ExpenseDTO;
import splitwise.project.splitwise.Model.Expense;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Services.BalanceService;
import splitwise.project.splitwise.Services.ExpenseService;
import splitwise.project.splitwise.Services.GroupService;
import splitwise.project.splitwise.Services.UserService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-expense/{groupId}")
    public String addExpense(@ModelAttribute ExpenseDTO expenseDTO,@PathVariable long groupId){
        expenseDTO.setGroupId(groupId);
        expenseService.addExpense(expenseDTO);
        return "redirect:/expenses/group/" + groupId;
    }

    @PostMapping("/add-reimbersement/{groupId}")
    public String addReimbersment(@RequestBody ExpenseDTO expenseDTO,@PathVariable long groupId){
        expenseDTO.setGroupId(groupId);
        expenseService.addExpense(expenseDTO);
        return "redirect:/expenses/group/" + groupId;
    }

    @GetMapping("/group/{groupId}")
    public String expenses(@PathVariable Long groupId,Model model,Principal principal){
            List<Expense> expenses = expenseService.getAllExpenseOfGroup(groupId);
            List<User> groupMembers = groupService.getAllGroupMembers(groupId);
            double totalGroupSpending = expenseService.getTotalGroupExpense(groupId);
            Map<User, Double> balances = balanceService.calculateBalances(groupId);
            List<Map<String, Object>> reimbursements = balanceService.settleDebts(groupId);
            List<User> users = userService.getAllUser();
            List<User> existingUsers = groupService.getAllGroupMembers(groupId);
            users.removeAll(existingUsers);
            String currency = groupService.getGroup(groupId).getCurrency();
            Group group = groupService.getGroup(groupId);
            model.addAttribute("groupMembers", groupMembers);
            model.addAttribute("expenseDTO", new ExpenseDTO());
            model.addAttribute("expenses", expenses);
            model.addAttribute("currency", currency);
            model.addAttribute("totalGroupSpending", totalGroupSpending);
            model.addAttribute("balances", balances);
            model.addAttribute("reimbursements", reimbursements);
            model.addAttribute("users", users);
            model.addAttribute("group", group);
            return "Home/expense";
    }

    @DeleteMapping("/delete-expense/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long expenseId){
        expenseService.removeExpense(expenseId);
        return ResponseEntity.ok("deleted successfully");
    }

}
