Feature:
  Verify get operations
  Scenario: Verify book
    Given Perfome GET operation for "/book"
    And perfome GET for book id "0"
    Then should return book with name "a"