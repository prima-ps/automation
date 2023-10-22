Feature: User login website

  Scenario: user tidak berhasil login
    Given user di halaman login
    When input username
    And input invalid password
    And klik login button
    Then user get error message

  Scenario: user berhasil login
    Given user di halaman login
    When input username
    And input password
    And klik login button
    Then user masuk ke dashboard

  Scenario: user login dengan user diblock
    Given user di halaman login
    When input username diblock
    And input password
    And klik login button
    Then user dapat error