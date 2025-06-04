package com.example.expensereport;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, FIBER, CAR_RENTAL
}

class Expense {
    ExpenseType type;
    int amount;
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        System.out.println("Expenses " + new Date());

        int total = 0;
        int mealExpenses = 0;
        for (Expense expense : expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount;
            }

            String mealOverExpensesMarker = expense.type == ExpenseType.DINNER && expense.amount > 5000
                    || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";

            String leasureOverExpensesMarker = expense.type == ExpenseType.FIBER && expense.amount > 3000 ? "X" : " ";

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
                case FIBER:
                    expenseName = "Fiber";
                    break;
            }
            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker + "\t" + leasureOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }
}
