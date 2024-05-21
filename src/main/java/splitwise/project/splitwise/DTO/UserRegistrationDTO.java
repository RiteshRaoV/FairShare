package splitwise.project.splitwise.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
}
