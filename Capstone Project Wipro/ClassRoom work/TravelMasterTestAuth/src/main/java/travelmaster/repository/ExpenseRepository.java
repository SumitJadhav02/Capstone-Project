package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
