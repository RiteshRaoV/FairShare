package splitwise.project.splitwise.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.GroupRepository;

public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

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
    public List<Group> getAllGroupOfUser(long userId) {
        return groupRepository.findByGroupMembersUserId(userId);
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
    public Group addUsersToGroup(long groupId, List<User> user) {
        Group group = groupRepository.findById(groupId).get();
        List<User> groupUsers = group.getGroupMembers();
        groupUsers.addAll(user);
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

}
