package splitwise.project.splitwise.Services;

import java.util.List;

import splitwise.project.splitwise.DTO.GroupDTO;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;

public interface GroupService {
    List<User> getAllGroupMembers(long groupId);

    Group getGroup(long groupId);

    List<Group> getAllGroups();

    List<Group> getAllGroupOfUser(long userId);

    long getGroupSize(long groupId);

    Group addUserToGroup(long groupId, User user);

    Group addUsersToGroup(long groupId, List<Long> userId);

    void removeUsersFromGroup(long groupId, List<User> user);

    void removeUserFromGroup(long groupId, User user);

    Group createGroup(GroupDTO groupDTO);

}
