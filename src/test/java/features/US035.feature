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

    # Sol taraftaki filtreler (Free/FreeShipping/Discount)
    When Kullanici Free toggle'ini aktif eder
    Then Free filtresi aktif olmali
    When Kullanici Free toggle'ini deactive eder
    Then Free filtresi deactive olmali

    When Kullanici Free Shipping toggle'ini aktif eder
    Then Free Shipping filtresi aktif olmali
    When Kullanici Free Shipping toggle'ini deactive eder
    Then Free Shipping filtresi deactive olmali

    When Kullanici Discount toggle'ini aktif eder
    Then Discount filtresi aktif olmali
    When Kullanici Discount toggle'ini deactive eder
    Then Discount filtresi deactive olmali

    # Sort by dropdown - Bestsellers secimi
    # NOT: "All" secenegi site bug'i nedeniyle test edilemiyor (value=bos).
    # Detaylar icin: BUG_REPORT_InstuLearn_SortBy_All.docx
    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali

    # Sag taraftaki filtreler (Type - Virtual/Physical, Options - Only Available)
    When Kullanici Virtual filter'ini aktif eder
    Then Virtual filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Physical filter'ini aktif eder
    Then Physical filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Only Available Products filter'ini aktif eder
    Then Only Available Products filter aktif olmali
    And Kullanici Options bolumundeki Filter items butonuna tiklar

    # Categories - Science Tools kategorisine tiklama
    When Kullanici Science Tools kategorisine tiklar

  @loginProccess @hover @US035TC05
  Scenario: Kullanici urun uzerinde hover islemleri yapar (TIKLAMA YOK)
    # Login ve Store islemi
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    And Kullanici Store linkine tiklar

    # Sort by Bestsellers + Science Tools kategorisi
    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali

    When Kullanici Science Tools kategorisine tiklar

    # HOVER ISLEMLERI - Hic bir elemana tiklama yapilmayacak!
    # Sadece mouse elementin uzerine gelecek ve hover efekti gorulecek
    When Kullanici Updated Product Title urun resmine hover yapar
    And Kullanici stars card alanina hover yapar
    And Kullanici Add to Cart butonuna hover yapar
    And Kullanici Add to Wishlist butonuna hover yapar

    # Description tab icin scroll down + hover
    And Kullanici sayfayi asagi kaydirir
    And Kullanici Description tab'a hover yapar
