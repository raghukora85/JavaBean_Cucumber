@smoke
Feature: Filter file types tests

  Scenario Outline: Display File Info present in directory

    Given I set the file path to "<filepath>"
    Then the file related data should be displayed

    Examples:
      | filepath             |
      | /src/main/resources/ |


  Scenario Outline: Display File Info filtered by file type given

    Given I set the file path to "<filepath>"
    When I get file data with Extension "<extension>"
    Then the file related data should be displayed with filter applied

    Examples:
      | filepath             | extension |
      | /src/main/resources/ | xls       |
      | /src/main/resources/ | csv       |

