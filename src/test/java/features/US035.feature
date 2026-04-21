Feature: US035 - Kullanıcı giriş işlemi

  Background:
    Given Kullanici InstuLearn anasayfasina gider

  @US035TC01
  Scenario: Kayitli kullanici gecerli bilgilerle basariyla giris yapar
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    Then Kullanici basariyla giris yapmis olmali

  @US035TC02Negative
  Scenario: Kayitsiz kullanici gecersiz bilgilerle giris yapamaz
    When Kullanici Login linkine tiklar
    And Kullanici email alanina "invalid@test.com" girer
    And Kullanici password alanina "WrongPass123!" girer
    And Kullanici Login butonuna tiklar
    Then Kullanici giris yapamamis olmali
