package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import travelmaster.model.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
