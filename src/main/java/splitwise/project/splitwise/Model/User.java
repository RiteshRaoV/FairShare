package splitwise.project.splitwise.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String role;
	private String resetToken;
	private LocalDateTime passwordResetTokenDateTime;
	private LocalDateTime verificationTokenDateTime;
	private boolean verificationStatus;


    @JsonIgnore // Add this annotation to ignore JSON serialization of groups
    @ManyToMany(mappedBy = "groupMembers")
    private List<Group> groups;

    @JsonIgnore // Add this annotation to ignore JSON serialization of expenses
    @OneToMany(mappedBy = "expensePayer")
    private List<Expense> expenses;
}