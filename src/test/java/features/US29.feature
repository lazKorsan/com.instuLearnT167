
@wip
Feature: İndirim ve Promosyon Yönetimi

  Background:
    Given kullanıcı sisteme kayıtlı bir Instructor olarak giriş yapmıştır
    And kullanıcı dashboard sayfasındadır

  @TC-01
  Scenario: Sidebar'da Marketing başlığı altında Discounts ve Promotions linkleri görünür olmalıdır
    Then sidebar menüde Marketing başlığı görünür olmalıdır
    Then kullanıcı Marketing linkine tıklar
    And Marketing başlığı altında Discounts linki görünür ve aktif olmalıdır
    And Marketing başlığı altında Promotions linki görünür ve aktif olmalıdır


  @TC-02
  Scenario: Discounts ikonuna tıklandığında yeni kurs indirimi oluşturulabilmelidir
    When kullanıcı Discounts linkine tıklar
    Then Discounts sayfası açılmalıdır
    And Create butonu görünür olmalıdır
    When kullanıcı yeni indirim bilgilerini doldurur
    And Create butonuna tıklar
    Then yeni kurs indirimi başarıyla oluşturulmalıdır
    And oluşturulan indirim listede görünmelidir

  @TC-03
  Scenario: Geçersiz indirim bilgileriyle kayıt yapılamamalıdır
    Given kullanıcı "Discounts" linkine tıklar
    When kullanıcı eksik veya geçersiz indirim bilgileri girer
    And "Kaydet" butonuna tıklar
    Then hata mesajı görüntülenmelidir
    And indirim oluşturulmamalıdır

  @TC-04
  Scenario: Promotions linkine tıklandığında promosyon planları sayfada görünür olmalıdır
    When kullanıcı "Promotions" linkine tıklar
    Then Promotions sayfası açılmalıdır
    And mevcut promosyon planları listede görünür olmalıdır
    And her plan seçilebilir durumda olmalıdır

  @TC-05
  Scenario: Kullanıcı istediği promosyon planına kayıt yapabilmelidir
    Given kullanıcı Promotions sayfasındadır
    When kullanıcı bir promosyon planı seçer
    And "Kaydol" butonuna tıklar
    Then kullanıcı seçilen plana başarıyla kayıt olmalıdır
    And onay mesajı ekranda görüntülenmelidir

