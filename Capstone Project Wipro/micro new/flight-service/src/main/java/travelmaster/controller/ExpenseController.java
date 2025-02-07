package travelmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelmaster.dto.ExpenseDTO;
import travelmaster.model.Expense;
import travelmaster.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Get expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseDTO expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    // Create a new expense
    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
        return ResponseEntity.ok(createdExpense);
    }

    // Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDetails) {
        ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return ResponseEntity.ok(updatedExpense);
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
