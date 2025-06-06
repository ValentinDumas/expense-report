package com.example.expensereport;

enum ExpenseType {
    DINNER,
    BREAKFAST,
    CAR_RENTAL;

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
