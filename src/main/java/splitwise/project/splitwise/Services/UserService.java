package splitwise.project.splitwise.Services;

import java.util.List;

import splitwise.project.splitwise.Model.User;

public interface UserService {

    List<User> getAllUser();

    User addUser(User user);

    void removeUser(long userId);

    User updateUser(long userId, User user);

    User getUser(long userId);

}
