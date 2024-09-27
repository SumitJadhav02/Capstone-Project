package travelmaster.pojo;


import java.time.LocalDateTime;



public class Expense {

	private Long expenseId;

	private Double amount;

	private String category;

	private String description;

	private LocalDateTime date;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	// Default constructor
	public Expense() {
	}

}
