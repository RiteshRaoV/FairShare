package splitwise.project.splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import splitwise.project.splitwise.DTO.ExpenseDTO;
import splitwise.project.splitwise.Services.ExpenseService;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseDTO expenseDTO){
        expenseService.addExpense(expenseDTO);
        return ResponseEntity.ok("Expense added successfully");
    }
}
