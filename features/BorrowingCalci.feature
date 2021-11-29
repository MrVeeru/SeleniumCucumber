Feature: Borrowing Calculator

  Background:
    Given Browser is launched
    And Loan calculator page is opened

  Scenario: Verify the borrow estimate by providing the requested info
    When The User provides the requested info
    Then Clicks on borrow estimation

  Scenario: Verify clicking on start over button clears the form
    When The User provides the requested info
    Then Clicks on borrow estimation
    And Clicks the Start over button

  Scenario: Verify message for not providing enough details on borrow estimation
    When User enters incomplete details
    Then Clicks on borrow estimation
    And Gets an error message
