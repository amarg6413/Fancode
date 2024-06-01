Feature: Fancode Assignment

  @Fancode
  Scenario Outline: All the users of City <City> should have more than <Percentage>% of their todos task completed.
    Given User has the todo tasks
    And User belongs to the city "<City>"
    Then User Completed task percentage should be greater than <Percentage>% for "<City>" city

    Examples:
    |City|Percentage|
    |FanCode|50|
    |FanCode|60|
    |FanCode|70|