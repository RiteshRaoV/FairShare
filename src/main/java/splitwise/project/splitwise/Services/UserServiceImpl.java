package splitwise.project.splitwise.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import splitwise.project.splitwise.DTO.UserRegistrationDTO;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserRegistrationDTO user) {
        String password = passwordEncoder.encode(user.getPassword());
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(password);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
		newUser.setRole("USER");
        newUser.setVerificationStatus(false);
        newUser.setVerificationTokenDateTime(LocalDateTime.now());
        return userRepository.save(newUser);
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

    @Override
    public boolean existsByEmail(String email) {
        if(userRepository.findByEmail(email)!=null){
            return true;
        }
        return false;
    }

}
