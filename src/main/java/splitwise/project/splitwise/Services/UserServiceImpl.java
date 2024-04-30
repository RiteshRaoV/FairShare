package splitwise.project.splitwise.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(long userId, User user) {
        User updatedUser = user;
        updatedUser.setUserId(userId);
        return userRepository.save(updatedUser);
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsersById(List<Long> userIds) {
        return userRepository.findAllByUserIds(userIds);
    }

}
