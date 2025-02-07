package travelmaster.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import travelmaster.pojo.Expense;
import travelmaster.pojo.Hotel;

@FeignClient(name = "expense-service", url = "${hotel-service:http://localhost:8004}")

public interface ExpenseClient {

	    @GetMapping("/api/expense")     
	    List<Hotel> getAllExpenses();
	
	
    @GetMapping("/api/expense/{id}")
    Hotel getHotelById(@PathVariable("id") Long id);
    
    
    @GetMapping("/api/expense/user/{userId}") 
    List<Expense> getExpensesByUserId(@PathVariable("userId") Long userId);
    
    
    

    
    
}
