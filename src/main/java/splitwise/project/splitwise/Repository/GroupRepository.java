package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import splitwise.project.splitwise.Model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByGroupMembersUserId(Long userId);

}
