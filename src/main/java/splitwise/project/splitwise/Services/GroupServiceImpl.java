package splitwise.project.splitwise.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import splitwise.project.splitwise.DTO.GroupDTO;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.GroupRepository;
import splitwise.project.splitwise.Repository.UserRepository;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllGroupMembers(long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return group.getGroupMembers();
    }

    @Override
    public Group getGroup(long groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroupOfUser(String email) {
        User user = userRepository.findByEmail(email);
        return groupRepository.findByGroupMembersUserId(user.getUserId());
    }

    @Override
    public long getGroupSize(long groupId) {
        return groupRepository.findById(groupId).get().getGroupMembers().size();
    }

    @Override
    public Group addUserToGroup(long groupId, User user) {
        Group group = groupRepository.findById(groupId).get();
        List<User> groupUsers = group.getGroupMembers();
        groupUsers.add(user);
        group.setGroupMembers(groupUsers);
        groupRepository.save(group);
        return group;
    }

    @Override
    public Group addUsersToGroup(long groupId, List<Long> userIds) {
        Group group = groupRepository.findById(groupId).get();
        List<User> groupUsers = group.getGroupMembers();
        List<User> users=userRepository.findAllByUserIds(userIds);
        groupUsers.addAll(users);
        group.setGroupMembers(groupUsers);
        groupRepository.save(group);
        return group;
    }

    @Override
    public void removeUsersFromGroup(long groupId, List<User> user) {
        Group group = groupRepository.findById(groupId).get();
        List<User> groupUsers = group.getGroupMembers();
        groupUsers.removeAll(user);
        group.setGroupMembers(groupUsers);
        groupRepository.save(group);
    }

    @Override
    public void removeUserFromGroup(long groupId, User user) {
        Group group = groupRepository.findById(groupId).get();
        List<User> groupUsers = group.getGroupMembers();
        groupUsers.remove(user);
        group.setGroupMembers(groupUsers);
        groupRepository.save(group);
    }

    @Override
    public Group createGroup(GroupDTO groupDTO) {
        List<Long> userIds=groupDTO.getParticipants();

        Group newGroup=new Group();
        newGroup.setGroupName(groupDTO.getGroupName());
        newGroup.setGroupMembers(userRepository.findAllByUserIds(userIds));
        newGroup.setCurrency(groupDTO.getCurrency());
        newGroup.setGroupType(groupDTO.getGroupType());
        newGroup.setStartDate(LocalDate.now());
        newGroup.setTotalExpense(0.0);
        return groupRepository.save(newGroup);

    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

}
