package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelmaster.client.UserClient;
import travelmaster.model.Expense;
import travelmaster.pojo.User;
import travelmaster.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserClient userClient; 

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    // Create a new expense
    public Expense createExpense(Expense expense) {
        if (expense.getUser() == null || expense.getUser().getUserId() == null) {
            throw new RuntimeException("User is required");
        }

        User user = userClient.getUserById(expense.getUser().getUserId());
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    // Update an existing expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setAmount(expenseDetails.getAmount());
        existingExpense.setCategory(expenseDetails.getCategory());
        existingExpense.setDescription(expenseDetails.getDescription());
        existingExpense.setDate(expenseDetails.getDate());
        return expenseRepository.save(existingExpense);
    }

    // Delete an expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    
    
    

	/*
	 * // Get user by ID public User getUserById(Long userId) { return
	 * userClient.getUserById(userId); }
	 */
}
