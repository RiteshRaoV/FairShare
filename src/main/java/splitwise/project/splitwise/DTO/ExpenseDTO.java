package splitwise.project.splitwise.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDTO {
    private String expenseName;
    private String expenseType;
    private String currency;
    private double amount;
    private LocalDate expenseDate;
    private Long expensePayerId;
    private List<Long> expensePayedToIds;
    private Long groupId;

}

