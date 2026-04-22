Feature: US035 - Kullanici giris ve urun sayfasi islemleri

  Background:
    Given Kullanici InstuLearn anasayfasina gider

  @loginProccess @smoke @US035TC01
  Scenario: Kayitli kullanici gecerli bilgilerle basariyla giris yapar
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    Then Kullanici basariyla giris yapmis olmali

  @loginProccess @negative @US035TC02
  Scenario: Kayitsiz kullanici gecersiz bilgilerle giris yapamaz
    When Kullanici Login linkine tiklar
    And Kullanici email alanina "invalid@test.com" girer
    And Kullanici password alanina "WrongPass123!" girer
    And Kullanici Login butonuna tiklar
    Then Kullanici giris yapamamis olmali

  @loginProccess @US035TC03
  Scenario: Kullanici Store linkine tiklar, scroll yapar ve search alanlarini dogrular
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    And Kullanici Store linkine tiklar
    And Kullanici sayfayi asagi kaydirir 2 saniye bekler ve yukari kaydirir
    Then Search textBox gorunur ve aktif olmali
    And Search button gorunur ve aktif olmali

  @loginProccess @US035TC04
  Scenario: Kullanici Products sayfasinda filtreleme islemleri yapar
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    And Kullanici Store linkine tiklar

    # Free filter aktif/deactive
    When Kullanici Free toggle'ini aktif eder
    Then Free filtresi aktif olmali
    When Kullanici Free toggle'ini deactive eder
    Then Free filtresi deactive olmali

    # Free Shipping filter aktif/deactive
    When Kullanici Free Shipping toggle'ini aktif eder
    Then Free Shipping filtresi aktif olmali
    When Kullanici Free Shipping toggle'ini deactive eder
    Then Free Shipping filtresi deactive olmali

    # Discount filter aktif/deactive
    When Kullanici Discount toggle'ini aktif eder
    Then Discount filtresi aktif olmali
    When Kullanici Discount toggle'ini deactive eder
    Then Discount filtresi deactive olmali

    # Sort by dropdown - Bestsellers (value ile) ve All (metin ile - value'su bos)
    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali
    When Kullanici Sort by dropdown'dan "All" secer \(metin ile)
    Then Sort by dropdown secimi metin olarak "All" olmali
    And Kullanici Sort by dropdown icinde scroll yapar
