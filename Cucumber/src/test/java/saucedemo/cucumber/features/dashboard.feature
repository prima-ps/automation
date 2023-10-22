Feature: User di halaman dashboard
  Scenario: user menambahkan item
    Given user di halaman dashboard
    When user klik barang
    And keranjang bertambah
    Then user tetap halaman dashboard


  Scenario: user menghapus item
    Given user di halaman dashboard
    When user klik barang
    And user klik barang lain
    And user remove barang sebelumnya
    And keranjang berkurang
    Then user tetap halaman dashboard

  Scenario: user checkout barang
    Given user di halaman dashboard
    When user klik barang
    And user klik keranjang
    Then user di halaman checkout

  Scenario: user logout
    Given user di halaman dashboard
    When user klik tiga titik
    And user klik logout
    Then user kembali ke halaman login

