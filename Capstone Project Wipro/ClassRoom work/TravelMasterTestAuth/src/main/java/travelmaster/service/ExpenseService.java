package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelmaster.dto.ExpenseDTO;
import travelmaster.exception.ExpenseNotFoundException;
import travelmaster.model.Expense;
import travelmaster.repository.ExpenseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get expense by ID
    public ExpenseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with ID: " + id));
        return convertToDto(expense);
    }

    // Create a new expense
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = convertToEntity(expenseDTO);
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDto(savedExpense);
    }

    // Update an existing expense
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDetails) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setAmount(expenseDetails.getAmount());
            expense.setCategory(expenseDetails.getCategory());
            expense.setDescription(expenseDetails.getDescription());
            expense.setDate(expenseDetails.getDate());
            Expense updatedExpense = expenseRepository.save(expense);
            return convertToDto(updatedExpense);
        }).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with ID: " + id));
    }

    // Delete an expense
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with ID: " + id));
        expenseRepository.delete(expense);
    }

    // Convert Expense entity to DTO
    private ExpenseDTO convertToDto(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(expense.getExpenseId());
        dto.setUserId(expense.getUser().getUserId());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setDescription(expense.getDescription());
        dto.setDate(expense.getDate());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());
        return dto;
    }

    // Convert DTO to Expense entity
    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        // Assuming you set the User entity separately
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setDate(expenseDTO.getDate());
        return expense;
    }
}
