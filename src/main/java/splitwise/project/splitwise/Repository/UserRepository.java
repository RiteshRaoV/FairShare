package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    List<User> findAllByUserIds(@Param("ids") List<Long> ids);

    @Query(value = "select * from user where user_id=:userId", nativeQuery = true)
    User findByUserId(Long userId);

    User findByEmail(String email);
}
