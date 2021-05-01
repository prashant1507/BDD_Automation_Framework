Feature: Home Page
  @Test1
  Scenario: Verify Fail test - HomeFeature
    When Login Page is displayed
    And User provides username Admin
    And User provides password admin123
    Then User clicks on login button
    And User is at homepage