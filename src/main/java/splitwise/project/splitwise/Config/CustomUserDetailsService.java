package splitwise.project.splitwise.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		if (user.getResetToken() == null && !user.isVerificationStatus()) {
			throw new UsernameNotFoundException("User account not verified yet");
		} else {
			return new CustomUser(user);
		}

	}
}
