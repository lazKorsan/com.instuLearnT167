@wip
Feature: US40 - Student Dashboard Test

  Background: User is logged in as a student
    Given the user is on the login page
    When the user enters their student information and logs in
    Then the user should see the home page


  Scenario: Checking the dashboard link in the navbar
    When The user hovers the mouse over the profile icon in the upper right corner
    And In the pop-up that opens, click the dashboard link
    Then Confirms that you were redirected to the dashboard page


  Scenario: Checking the links in the dashboard sidebar
    Given the user is on the dashboard page
    Then The user confirms that all links on the dashboard are visible and active

  Scenario: View all event link visibility and activity test
    Given the user is on the dashboard page
    Then The user verifies that the "view all events" link is visible and active

  Scenario: Checking the visibility and activity of links in the account body section
    Given the user is on the dashboard page
    Then The visibility and activity of the Account Balance link are verified in the dashboard body section
    And In the dashboard body section, the links for Purchased Courses, Meetings, Support Messages, and Comments are visible and confirmed to be active

  Scenario: Visibility and accessibility test of the Announcement table and monthly learning table in the dashboard body section
    Given the user is on the dashboard page
    Then The advertisements and their details should be visible on the Noticeboard.
    And the monthly learning table should be visible to the user
