package com.example.expensereport;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.printReport(List.of());
    }
}
