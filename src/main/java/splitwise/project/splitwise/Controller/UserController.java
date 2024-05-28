package splitwise.project.splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import splitwise.project.splitwise.DTO.UserRegistrationDTO;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public String addUser(@ModelAttribute UserRegistrationDTO user,HttpSession session) {
        session.removeAttribute("msg");
        if (userService.existsByEmail(user.getEmail())) {
            session.setAttribute("msg", "User with this email already exists.");
            return "redirect:/user/sign-up";
        } else {
            User savedUser = userService.addUser(user);
            if (savedUser != null) {
                session.setAttribute("msg", "Registered successfully.");
                return "redirect:/user/sign-in";
            } else {
                session.setAttribute("msg", "Something went wrong on the server.");
                return "redirect:/user/sign-up";
            }
        }
    }

    @GetMapping("/sign-in")
    public String login(){
        return "Auth/loginPage";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "Auth/signUpPage";
    }

    @GetMapping("/home")
    public String homePage(){
        return "Home/homePage";
    }

}
