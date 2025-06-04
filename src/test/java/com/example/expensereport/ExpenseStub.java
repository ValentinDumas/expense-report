package com.example.expensereport;

import org.jetbrains.annotations.NotNull;

public class ExpenseStub {
    static @NotNull Expense createExpense(ExpenseType expenseType, int expenseAmount) {
        return new Expense(
                expenseType,
                expenseAmount
        );
    }
}
