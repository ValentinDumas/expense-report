package com.example.expensereport;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ApprovalTests {

  @Test
  public void testPrintReportOutput() {
    // Arrange - Create test expenses
    List<Expense> expenses = createTestExpenses();
    ExpenseReport expenseReport = new ExpenseReport();

    // Act - Capture System.out
    String output = captureSystemOut(() -> expenseReport.printReport(expenses));

    // Assert - Approval test verifies the complete output
    Approvals.verify(output, ScruberUtils.scrubDate());
  }

  private List<Expense> createTestExpenses() {
    Expense dinnerUnderLimit = new Expense();
    dinnerUnderLimit.type = ExpenseType.DINNER;
    dinnerUnderLimit.amount = 4000; // Over limit (>5000)

    Expense breakfastUnderLimit = new Expense();
    breakfastUnderLimit.type = ExpenseType.BREAKFAST;
    breakfastUnderLimit.amount = 800; // Under limit (<1000)

    Expense carRentalNoLimit = new Expense();
    carRentalNoLimit.type = ExpenseType.CAR_RENTAL;
    carRentalNoLimit.amount = 15000; // No limit for car rental

    // Boundary test expenses
    /*
    Expense dinnerAtLimit = new Expense(ExpenseType.DINNER, 5000);
    Expense dinnerOverLimit = new Expense(ExpenseType.DINNER, 5001);
    Expense breakfastAtLimit = new Expense(ExpenseType.BREAKFAST, 1000);
    Expense breakfastOverLimit = new Expense(ExpenseType.BREAKFAST, 1001);
    return List.of(dinnerUnderLimit,breakfastUnderLimit,carRentalNoLimit, dinnerAtLimit, breakfastAtLimit, dinnerOverLimit, breakfastOverLimit);
     */
    return List.of(dinnerUnderLimit, breakfastUnderLimit, carRentalNoLimit);
  }

  private String captureSystemOut(Runnable action) {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      System.setOut(new PrintStream(outputStream));
      action.run();
      return outputStream.toString();
    } finally {
      System.setOut(originalOut);
    }
  }
}
