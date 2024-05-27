package splitwise.project.splitwise.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import splitwise.project.splitwise.DTO.GroupDTO;
import splitwise.project.splitwise.Model.Expense;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.ExpenseRepository;
import splitwise.project.splitwise.Repository.GroupRepository;
import splitwise.project.splitwise.Repository.UserRepository;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
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
    @Transactional
    public void deleteGroup(Long groupId) {

        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();

            // Remove group association from expenses
            List<Expense> groupExpenses = group.getExpenses();
            if (groupExpenses != null && !groupExpenses.isEmpty()) {
                for (Expense expense : groupExpenses) {
                    expense.setGroup(null);
                    expenseRepository.save(expense);
                }
            }

            // Remove group association from users
            List<User> groupMembers = group.getGroupMembers();
            if (groupMembers != null && !groupMembers.isEmpty()) {
                for (User user : groupMembers) {
                    user.getGroups().remove(group);
                    userRepository.save(user);
                }
            }

            // Finally, delete the group
            groupRepository.delete(group);
        } else {
            throw new IllegalArgumentException("Group not found with id: " + groupId);
        }
    }   
}
