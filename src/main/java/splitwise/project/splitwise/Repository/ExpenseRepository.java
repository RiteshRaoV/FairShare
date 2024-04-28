package splitwise.project.splitwise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import splitwise.project.splitwise.Model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Existing method:
    List<Expense> findByExpensePayerUserId(Long userId);

    // New method to find expenses by group:
    List<Expense> findByGroup_GroupId(Long groupId);
}
