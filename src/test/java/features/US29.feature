
@US29
Feature: İnstructor'ın Kurs İndirim ve Promosyon Yönetimi

  Background:
    Given kullanıcı sisteme kayıtlı bir Instructor olarak giriş yapmıştır
    And kullanıcı dashboard sayfasındadır

  @US29_TC01
  Scenario: Sidebar'da Marketing başlığı altında Discounts ve Promotions linkleri görünür olmalıdır
    Given sidebar menüde Marketing başlığı görünür olmalıdır
    When kullanıcı Marketing linkine tıklar
    Then Marketing başlığı altında Discounts linki görünür ve aktif olmalıdır
    And Marketing başlığı altında Promotions linki görünür ve aktif olmalıdır


  @US29_TC02
  Scenario: Discounts ikonuna tıklandığında yeni kurs indirimi oluşturulabilmelidir
    Given kullanıcı Discounts linkine tıklar
    When Discounts sayfası açılmalıdır
    Then Create butonu görünür olmalıdır
    And kullanıcı yeni indirim bilgilerini doldurur
    Then Create butonuna tıklar
    And oluşturulan indirim listede görünmelidir

  @US29_TC03
  Scenario: Geçersiz indirim bilgileriyle kayıt yapılamamalıdır
    Given kullanıcı Discounts linkine tıklar
    When kullanıcı eksik veya geçersiz indirim bilgileri girer
    Then Create butonuna tıklar
    And indirim oluşturulmamalıdır

  @US29_TC04
  Scenario: Promotions linkine tıklandığında promosyon planları sayfada görünür olmalıdır
    Given kullanıcı Promotions linkine tıklar
    When Promotions sayfası açılmalıdır
    Then mevcut promosyon planları listede görünür olmalıdır
    And her plan seçilebilir durumda olmalıdır

  @US29_TC05
  Scenario: Kullanıcı istediği promosyon planına kayıt yapabilmelidir
    Given kullanıcı Promotions linkine tıklar
    When Promotions sayfası açılmalıdır
    When kullanıcı bir promosyon planı seçer
    And Purchase butonuna tıklar
    When Kurs bilgisini seçer
    And Pay butonuna tıklar
    When Ödeme planını seçer
    And Ödeme işlemini başlatır
    When Ödeme bilgilerini doldurur
    And Öde butonuna tıklar
    Then onay mesajı ekranda görüntülenmelidir

