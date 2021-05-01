Feature: Login Page

  Scenario: Verify Login Page
    When Login Page is displayed

  Scenario: Verify Skip test
    When Login Page is displayed
    And User provides username admin123
    And User provides password qwerty11
    And User clicks on login button
    Then Test case is skipped