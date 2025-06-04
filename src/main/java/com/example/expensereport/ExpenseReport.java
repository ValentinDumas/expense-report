package com.example.expensereport;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, FIBER, CAR_RENTAL
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealExpenses += expense.amount();
            }

            String expenseName = expense.getLabel();

            String mealOverExpensesMarker = expense.isOverLimit() ? "X" : " ";

            System.out.println(expenseName + "\t" + expense.amount() + "\t" + mealOverExpensesMarker);

            total += expense.amount();
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }
}
