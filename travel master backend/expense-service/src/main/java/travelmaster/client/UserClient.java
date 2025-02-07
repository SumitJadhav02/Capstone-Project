package travelmaster.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import travelmaster.pojo.User;
import travelmaster.model.Expense;

import java.util.List;

// fetch expenses associated with a specific user
@FeignClient(name = "user-service", url = "${user-service:http://localhost:8001}")
public interface UserClient {

    @GetMapping("/auth/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

	/*
	 * //fetch expenses associated with a specific user
	 * 
	 * @GetMapping("/expenses/user/{userId}") //
	 * http://localhost:8004/api/expenses/auth/2 List<Expense>
	 * getExpensesByUserId(@PathVariable("userId") Long userId);
	 */
}
