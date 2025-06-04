package com.example.expensereport;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
        Expense dinner = new Expense();
        dinner.type = ExpenseType.DINNER;
        dinner.amount = 6000; // Over limit (>5000)

        Expense breakfast = new Expense();
        breakfast.type = ExpenseType.BREAKFAST;
        breakfast.amount = 800; // Under limit (<1000)

        Expense carRental = new Expense();
        carRental.type = ExpenseType.CAR_RENTAL;
        carRental.amount = 15000; // No limit for car rental

        return List.of(dinner, breakfast, carRental);
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

    @Test
    public void testPrintReportTwice() {
        // Arrange - Create test expenses
        List<Expense> expenses = createTestExpenses();
        ExpenseReport expenseReport = new ExpenseReport();

        // Act - Capture System.out
        String output = captureSystemOut(() -> {
                    expenseReport.printReport(expenses);
                    expenseReport.printReport(expenses);
                }
        );

        // Assert - Approval test verifies the complete output
        Approvals.verify(output, ScruberUtils.scrubDate());
    }

    // EDGE CASES

    @Test
    public void testPrintReportWithBoundaryValues() {
        List<Expense> expenses = createBoundaryTestExpenses();
        ExpenseReport expenseReport = new ExpenseReport();

        String output = captureSystemOut(() -> expenseReport.printReport(expenses));

        Approvals.verify(output, ScruberUtils.scrubDate());
    }

    @Test
    public void testPrintReportWithEmptyList() {
        List<Expense> expenses = new ArrayList<>();
        ExpenseReport expenseReport = new ExpenseReport();

        String output = captureSystemOut(() -> expenseReport.printReport(expenses));

        Approvals.verify(output, ScruberUtils.scrubDate());
    }

    private List<Expense> createBoundaryTestExpenses() {
        // Test exactly at the limits
        Expense dinnerAtLimit = new Expense();
        dinnerAtLimit.type = ExpenseType.DINNER;
        dinnerAtLimit.amount = 5000; // Exactly at limit

        Expense dinnerOverLimit = new Expense();
        dinnerOverLimit.type = ExpenseType.DINNER;
        dinnerOverLimit.amount = 5001; // Just over limit

        Expense breakfastAtLimit = new Expense();
        breakfastAtLimit.type = ExpenseType.BREAKFAST;
        breakfastAtLimit.amount = 1000; // Exactly at limit

        Expense breakfastOverLimit = new Expense();
        breakfastOverLimit.type = ExpenseType.BREAKFAST;
        breakfastOverLimit.amount = 1001; // Just over limit

        return List.of(dinnerAtLimit, breakfastAtLimit, dinnerOverLimit, breakfastOverLimit);
    }

}
