Feature: Test Application scenarios

@TestQA
  Scenario: TC01validtion of  error of mandatory field
    Given I launch browser and open application
    And Verify title of application
    When I click on "Contact" link
    #And I click on "Submit" link
    #And user validates errors  for below
      #| FieldName | ErrorMessage         |
      #| Forename  | Forename is required |
      #| Email     | Email is required    |
      #| Message   | Message is required  |
    #And I fill the mandatory field in form
      #| Forename  | Email        | Message      |
      #| Bajarangi | qa@gmail.com | Hello World! |
    #Then user validates all errors are gone

  @TestQA
  Scenario: TC02 case for fill contact details with valid data
    Given I launch browser and open application
    And Verify title of application
    When I click on "Contact" link
    And I fill the mandatory field in form
      | Forename  | Email        | Message |
      | Bajarangi | qa@gmail.com | message |
    And I click on "Submit" link
    Then I validates successful submission message for "Bajarangi"

  @TestQA
  Scenario: TC3validtion of  error of mandatory field
    Given I launch browser and open application
    And Verify title of application
    When I click on "Contact" link
    And I fill the mandatory field in form
      | Forename  | Email | Message |
      | Bajarangi | @@@   | message |
    Then I validates error message for invalid for emailid
      | ErrorMessage               |
      | Please enter a valid email |

  @TestQA
  Scenario: TC3validtion of  error of mandatory field
    Given I launch browser and open application
    And Verify title of application
    When I click on "Shop" link
    And I  Added below item  in my cart
      | ItemName     | Count |
      | Funny Cow    |     2 |
      | Fluffy Bunny |     1 |
    And I  clicks on cart menu
    Then I validates added item in cart
      | ItemName     | Count |
      | Funny Cow    |     2 |
      | Fluffy Bunny |     1 |
