package splitwise.project.splitwise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import splitwise.project.splitwise.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
