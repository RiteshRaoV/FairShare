package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByGroupMembersUserId(Long userId);

    @Query(value = "select * from user_group where group_id=:groupId",nativeQuery = true)
    Group findByGroupId(Long groupId);


}
