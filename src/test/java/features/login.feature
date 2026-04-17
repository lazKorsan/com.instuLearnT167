Feature: Login Functionality

  @JAVA-7
    # https://teamyuzelliuc.atlassian.net/browse/JAVA-7
  Scenario: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should see the home page
