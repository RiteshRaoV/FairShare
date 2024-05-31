package splitwise.project.splitwise.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupDTO {
    private long groupId;
    private String groupName;
    private List<Long> participants;
}
