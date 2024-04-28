package splitwise.project.splitwise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import splitwise.project.splitwise.Model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
