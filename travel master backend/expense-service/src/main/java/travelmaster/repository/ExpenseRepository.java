package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelmaster.model.Expense;
import travelmaster.pojo.User;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId); 
   // List<Expense> findByUser(User user);
}
