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

    # 4 sayfayi sirayla ac ve URL kontrolu ile acildigini dogrula
    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali

    When Kullanici Payout linkine tiklar
    Then Payout sayfasi acilmis olmali

    When Kullanici Charge Account linkine tiklar
    Then Charge Account sayfasi acilmis olmali

    When Kullanici Subscribe linkine tiklar
    Then Subscribe sayfasi acilmis olmali

    # Tekrar Financial Summary sayfasina don + scroll down + 1 saniye bekle
    When Kullanici Financial Summary linkine tiklar
    Then Financial Summary sayfasi acilmis olmali
    And Kullanici sayfada scroll down yapar ve bir saniye bekler
