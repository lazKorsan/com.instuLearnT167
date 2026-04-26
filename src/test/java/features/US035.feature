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

    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali

    When Kullanici Virtual filter'ini aktif eder
    Then Virtual filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Physical filter'ini aktif eder
    Then Physical filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Only Available Products filter'ini aktif eder
    Then Only Available Products filter aktif olmali
    And Kullanici Options bolumundeki Filter items butonuna tiklar

    When Kullanici Science Tools kategorisine tiklar

  @loginProccess @hover @addToCart @US035TC05
  Scenario: Kullanici urun kartinda hover yapar, Add to Cart tiklar ve sepete ekleme onayini goruyor
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    And Kullanici Store linkine tiklar

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

    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali

    When Kullanici Virtual filter'ini aktif eder
    Then Virtual filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Physical filter'ini aktif eder
    Then Physical filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Only Available Products filter'ini aktif eder
    Then Only Available Products filter aktif olmali
    And Kullanici Options bolumundeki Filter items butonuna tiklar

    When Kullanici Science Tools kategorisine tiklar

    When Kullanici Updated Product Title urun resmine hover yapar
    And Kullanici stars card alanina hover yapar
    And Kullanici Add to Cart ikonuna hover yapar

    When Kullanici Add to Cart ikonuna tiklar
    Then Added to cart toast mesaji beklenen metni icermeli

  @loginProccess @purchase @US035TC06
  Scenario: Kullanici sepete eklenen urunu satin alir ve odemeyi tamamlar
    # ==== TC05 adimlarinin tamami (login + filter + hover + Add to Cart) ====
    When Kullanici Login linkine tiklar
    And Kullanici email alanina gecerli email girer
    And Kullanici password alanina gecerli password girer
    And Kullanici Login butonuna tiklar
    And Kullanici Store linkine tiklar

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

    When Kullanici Sort by dropdown'ini acar
    And Kullanici Sort by dropdown'dan "bestsellers" secer
    Then Sort by dropdown secimi "bestsellers" olmali

    When Kullanici Virtual filter'ini aktif eder
    Then Virtual filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Physical filter'ini aktif eder
    Then Physical filter aktif olmali
    And Kullanici Type bolumundeki Filter items butonuna tiklar

    When Kullanici Only Available Products filter'ini aktif eder
    Then Only Available Products filter aktif olmali
    And Kullanici Options bolumundeki Filter items butonuna tiklar

    When Kullanici Science Tools kategorisine tiklar

    When Kullanici Updated Product Title urun resmine hover yapar
    And Kullanici stars card alanina hover yapar
    And Kullanici Add to Cart ikonuna hover yapar

    When Kullanici Add to Cart ikonuna tiklar
    Then Added to cart toast mesaji beklenen metni icermeli

    # ==== TC06 YENI ADIMLAR: CART -> CHECKOUT -> STRIPE ODEME ====
    When Kullanici navbar'daki sepet ikonuna tiklar
    And Kullanici Go to cart butonuna tiklar
    And Kullanici Checkout butonuna scroll edip tiklar
    And Kullanici Pay with Stripe'i secer ve Start Payment butonuna tiklar
    And Kullanici Stripe odeme formunu doldurur ve Pay butonuna tiklar
    Then Odeme basariyla tamamlanmis olmali ve basari mesaji gorunmeli
