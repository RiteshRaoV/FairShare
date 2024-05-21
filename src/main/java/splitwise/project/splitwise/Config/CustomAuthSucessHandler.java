package splitwise.project.splitwise.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.UserRepository;

@Component
public class CustomAuthSucessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Map<String, String> redirectMap = new HashMap<>();

        if (!user.isVerificationStatus()) {
            redirectMap.put("redirectUrl", "/verifyAccount"); // Redirect to a page for verifying the account
        } else {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ROLE_ADMIN")) {
                redirectMap.put("redirectUrl", "/swagger-ui.html");
            } else {
                redirectMap.put("redirectUrl", "/balances/1");
            }
        }

        // Convert the map to JSON and write it to the response
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(redirectMap));
    }

}
