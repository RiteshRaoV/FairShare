package splitwise.project.splitwise.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private String expenseName;
    private String expenseType;
    private String currency;
    private double amount;
    private LocalDate expenseDate;

    @JsonIgnore // Add this annotation to ignore JSON serialization of expensePayer
    @ManyToOne
    @JoinColumn(name = "expense_payer_id")
    private User expensePayer;

    @JsonIgnore // Add this annotation to ignore JSON serialization of expensePayedTo
    @ManyToMany
    @JoinTable(name = "expense_payed_to", joinColumns = @JoinColumn(name = "expense_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> expensePayedTo;

    @JsonIgnore // Add this annotation to ignore JSON serialization of group
    @ManyToOne(optional = true) // Allow null value for group
    private Group group;
}