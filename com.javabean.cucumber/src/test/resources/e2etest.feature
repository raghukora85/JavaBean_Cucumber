@smoke @e2e
Feature: e2e test


  Scenario: Verify vehicle make and color
    Given I Scanned Details from example csv file
    When  I Navigated to "https://www.gov.uk/get-vehicle-information-from-dvla" page
    And   I enter car registration number
    Then  I should see related model and color
