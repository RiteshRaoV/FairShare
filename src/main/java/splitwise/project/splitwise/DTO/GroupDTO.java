package splitwise.project.splitwise.DTO;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupDTO {
    private String groupName;
    private String currency;
    private String groupType;
    private Long groupCreatorId;
    private List<Long> participants;
}
