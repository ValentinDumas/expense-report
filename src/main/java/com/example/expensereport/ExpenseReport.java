package com.example.expensereport;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, FIBER, CAR_RENTAL
}

record Expense(ExpenseType type, int amount) {
    String getLabel() {
        String expenseName = switch (type()) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
            case FIBER -> "Fibber";
        };
        return expenseName;
    }

    boolean isOverLimit() {
        return type() == ExpenseType.DINNER && amount() > 5000
                || type() == ExpenseType.BREAKFAST && amount() > 1000;
    }

    boolean isMeal() {
        return type() == ExpenseType.DINNER || type() == ExpenseType.BREAKFAST;
    }
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
