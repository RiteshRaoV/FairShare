package splitwise.project.splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import splitwise.project.splitwise.DTO.UserRegistrationDTO;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserRegistrationDTO user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        }
    }

}
