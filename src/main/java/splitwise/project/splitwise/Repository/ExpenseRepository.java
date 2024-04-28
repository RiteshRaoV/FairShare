package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpensePayerUserId(Long userId);

    List<Expense> findByExpenseGroupsId(Long groupId);

}
