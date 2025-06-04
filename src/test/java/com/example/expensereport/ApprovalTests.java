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
        Expense dinner = ExpenseStub.createExpense(ExpenseType.DINNER, 6000);
        Expense breakfast = ExpenseStub.createExpense(ExpenseType.BREAKFAST, 800);
        Expense carRental = ExpenseStub.createExpense(ExpenseType.CAR_RENTAL, 15000);
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
        Expense dinnerAtLimit = new Expense(ExpenseType.DINNER, 5000);
        Expense dinnerOverLimit = new Expense(ExpenseType.DINNER, 5001);
        Expense breakfastAtLimit = new Expense(ExpenseType.BREAKFAST, 1000);
        Expense breakfastOverLimit = new Expense(ExpenseType.BREAKFAST, 1001);
        return List.of(dinnerAtLimit, breakfastAtLimit, dinnerOverLimit, breakfastOverLimit);
    }

}
