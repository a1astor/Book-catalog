Feature:
  Verify GRUD operations

  Scenario: Verify book
    Given check if there is book with isbn = "0" and name = "getter"
    And perfome GET for book id "0"
    Then should return book with name "getter"

  Scenario: verify post operation
    Given Create new book entity with name "Thief of the ring"
    And save book in db
    Then check if book in base and name is "Thief of the ring"

  Scenario: verify delete operation
    Given Add book to data base and stored it isbn
    And delete book with stored isbn
    Then check that book is not in data base

  Scenario: verify put operarion
    Given Check if book  is in data base with id "2"
    And update book name to "Guns Sellor"
    Then check if book in  data base have name "Guns Sellor"