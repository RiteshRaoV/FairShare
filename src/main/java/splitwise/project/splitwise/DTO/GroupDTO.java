package splitwise.project.splitwise.DTO;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import splitwise.project.splitwise.Model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupDTO {
    private String groupName;
    private String currency;
    private String groupType;
    private List<Long> participants;
}
