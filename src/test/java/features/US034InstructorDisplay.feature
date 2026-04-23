Feature: US_034 - As a registered user, you can access instructor information and manage appointments via the 'Instructors' link in the Navbar.

  Background: User is logged in as a student
    Given the user is on the login page
    When the user enters their student information and logs in
    Then the user should see the home page


  Scenario: Verify navigation to instructors page from navbar
    When user click on "Instructors" link in the navbar
    Then user should be redirected to the instructors page

  Scenario: Verification of visibility and functionality for the search textbox and search button on the Instructors page
    Given user is on the instructors page
    Then check that the search box is visible
    And check that the search button is visible and active

  Scenario: category selection feature functionality check
    Given user is on the instructors page
    And confirms that the category section is visible
    When select a "Web Design" category from the category section
    Then confirms that the results related to category selection are listed

  Scenario: Verification of visibility of relevant fields on the instructor card
    Given user is on the instructors page
    Then the instructor's picture is checked to ensure it is visible
    And the trainer's competence is checked
    And the instructor's price information is checked to ensure it is displayed
    And check that the reserve a meeting button is visible

  @wip
  Scenario: Schedule a meeting with the instructor
    Given user is on the instructors page
    When the user clicks the reserve a meeting button
    And the user selects day number 22
    And the user chooses any time
    And select "Online" as the meeting type
    And the user clicks the reserve a meeting button for create meeting
    Then verify that the item "Meeting Reservation" is added to the cart