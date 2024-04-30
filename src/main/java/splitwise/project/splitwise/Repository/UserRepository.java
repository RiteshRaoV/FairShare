package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where user_id in :ids",nativeQuery = true)
    List<User> findAllByUserIds(List<Long> ids);
}
