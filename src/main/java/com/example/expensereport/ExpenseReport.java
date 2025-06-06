package com.example.expensereport;

import java.util.Date;
import java.util.List;

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            if (isMeal(expense)) {
                mealExpenses += expense.amount;
            }

            String expenseName = getLabel(expense);

            String mealOverExpensesMarker =
                    isOverLimit(expense)
                            ? "X"
                            : " ";

            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

    private boolean isOverLimit(Expense expense) {
        return expense.type == ExpenseType.DINNER && expense.amount > 5000
                || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000;
    }

    private String getLabel(Expense expense) {
        String expenseName = "";
        switch (expense.type) {
            case DINNER:
                expenseName = "Dinner";
                break;
            case BREAKFAST:
                expenseName = "Breakfast";
                break;
            case CAR_RENTAL:
                expenseName = "Car Rental";
                break;
        }
        return expenseName;
    }

    private boolean isMeal(Expense expense) {
        return expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST;
    }
}
