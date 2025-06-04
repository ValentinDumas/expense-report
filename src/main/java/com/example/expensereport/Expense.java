package com.example.expensereport;

record Expense(ExpenseType type, int amount) {
    String getLabel() {
        return switch (this.type()) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
            case FIBER -> "Fibber";
        };
    }

    boolean isOverLimit() {
        return type() == ExpenseType.DINNER && amount() > 5000
                || type() == ExpenseType.BREAKFAST && amount() > 1000;
    }

    boolean isMeal() {
        return type() == ExpenseType.DINNER || type() == ExpenseType.BREAKFAST;
    }
}
