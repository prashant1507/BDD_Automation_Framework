Feature: Home Page

  Scenario: Verify Login - HomeFeature
    Given Login Page is displayed
    When Login Page is displayed
    And User provides username Admin
    And User provides password admin123
    Then User clicks on login button
    And User is at homepage

  # With examples
  Scenario Outline: Invalid Login
    Given Login Page is displayed
    When User provides username <Username>
    And User provides password <Password>
    Then User clicks on login button
    And User is at homepage
    Examples:
      |Username   |Password |
      |Admin7777  |Admin7777|
      |Admin8888  |Admin8888|
      |Admin9999  |Admin9999|