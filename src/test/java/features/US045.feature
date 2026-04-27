Feature: US045 - Kullanici finansal islemlerini goruntuler ve yonetir

  Background:
    Given Kullanici Financial icin anasayfaya gider

  @US045 @financial @US045TC01
  Scenario: Kullanici Financial altindaki tum sayfalari sirayla acar ve URL'leri dogrular
    # Login islemleri
    When Kullanici Financial senaryosu icin Login linkine tiklar
    And Kullanici Financial senaryosu icin email girer
    And Kullanici Financial senaryosu icin password girer
    And Kullanici Financial senaryosu icin Login butonuna tiklar

    # Financial accordion'u ac
    When Kullanici Financial kategorisine tiklayip accordion'u acar

    # 4 sayfayi sirayla ac ve URL kontrolu
    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali

    When Kullanici Payout linkine tiklar
    Then Payout sayfasi acilmis olmali

    When Kullanici Charge Account linkine tiklar
    Then Charge Account sayfasi acilmis olmali

    When Kullanici Subscribe linkine tiklar
    Then Subscribe sayfasi acilmis olmali

    # Tekrar Financial Summary + scroll down
    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali
    And Kullanici sayfada scroll down yapar ve bir saniye bekler


  @US045 @financial @charge @US045TC02
  Scenario: Kullanici Charge Account uzerinden cuzdana 200 dolar yukler ve odemeyi tamamlar
    # ==== TC01 adimlarinin tamami ====
    When Kullanici Financial senaryosu icin Login linkine tiklar
    And Kullanici Financial senaryosu icin email girer
    And Kullanici Financial senaryosu icin password girer
    And Kullanici Financial senaryosu icin Login butonuna tiklar

    When Kullanici Financial kategorisine tiklayip accordion'u acar

    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali

    When Kullanici Payout linkine tiklar
    Then Payout sayfasi acilmis olmali

    When Kullanici Charge Account linkine tiklar
    Then Charge Account sayfasi acilmis olmali

    When Kullanici Subscribe linkine tiklar
    Then Subscribe sayfasi acilmis olmali

    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali
    And Kullanici sayfada scroll down yapar ve bir saniye bekler

    # ==== TC02 YENI ADIMLAR: CHARGE ACCOUNT ODEME AKISI ====
    # 1. Charge Account sayfasina geri don
    When Kullanici Charge Account linkine tiklar
    Then Charge Account sayfasi acilmis olmali

    # 2. Stripe odeme yontemi sec + amount + Pay
    When Kullanici Stripe odeme yontemini secer
    And Kullanici Amount alanina "200" dolar girer
    And Kullanici Charge Account Pay butonuna tiklar

    # 3. Stripe odeme formu doldur (Save info + telefon dahil)
    # NOT: OTP sayfasi otomasyonda acilmadigi icin direkt Stripe formu dolduruluyor
    And Kullanici Stripe odeme formunu doldurur ve odemeyi tamamlar

    # 4. Basari mesajini dogrula
    Then Charge Account odemesi basariyla tamamlanmis olmali
