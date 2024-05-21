package splitwise.project.splitwise.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.UserRepository;
import splitwise.project.splitwise.Services.UserService;



@Component
public class AdminUserInitializer implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!userService.existsByEmail("admin@fairsplit.com")) {
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setEmail("admin@fairsplit.com");
            adminUser.setPassword(encoder.encode("admin")); 
            adminUser.setRole("ROLE_ADMIN");
            adminUser.setVerificationStatus(true);
            userRepository.save(adminUser);
        }
    }
}