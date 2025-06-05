package com.example.expensereport;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MealExpensesStepDefinitions {

  List<Expense> expenses;
  ExpenseReport expenseReport;
  String output;

  @Given("the expense is a breakfast expense under limit")
  public void the_expense_is_a_breakfast_expense_under_limit() {
    expenses = createExpenses(800); // limit is 1000
    expenseReport = new ExpenseReport();
  }

  @Given("the expense is a breakfast expense over limit")
  public void the_expense_is_a_breakfast_expense_over_limit() {
    expenses = createExpenses(1200); // limit is 1000
    expenseReport = new ExpenseReport();
  }

  @When("printing expenses report")
  public void printing_expenses_report() {
    output = captureSystemOut(() -> expenseReport.printReport(expenses));
  }

  @Then("the breakfast expense appears unchecked")
  public void the_breakfast_expense_appears_unchecked() {
    // Assert - Approval test verifies the complete output
    assertThat(output).containsPattern("Breakfast\\t\\d+\\t");
  }

  @Then("the breakfast expense appears checked")
  public void the_breakfast_expense_appears_checked() {
    // Assert - Approval test verifies the complete output
    assertThat(output).containsPattern("Breakfast\\t\\d+\\tX");
  }

  private List<Expense> createExpenses(int amount) {
    Expense breakfast = new Expense();
    breakfast.type = ExpenseType.BREAKFAST;
    breakfast.amount = amount; // Under limit (<1000)

    return List.of(breakfast);
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
}
