package splitwise.project.splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> getUser(User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

}
