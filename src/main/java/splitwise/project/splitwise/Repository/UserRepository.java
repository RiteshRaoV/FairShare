package splitwise.project.splitwise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
