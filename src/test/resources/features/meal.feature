Feature: Meal expense feature

  Scenario: Breakfast is a meal expense under limit
    Given the expense is a breakfast expense under limit
    When printing expenses report
    Then the breakfast expense appears unchecked

  Scenario: Breakfast is a meal expense over limit
    Given the expense is a breakfast expense over limit
    When printing expenses report
    Then the breakfast expense appears checked
