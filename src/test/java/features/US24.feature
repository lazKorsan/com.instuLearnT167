

@US24
Feature: US_024 - Toplantı Yönetim Sayfası
  Kayıtlı bir Organization/Instructor kullanıcısı olarak
  toplantılarımı yönetebileceğim bir sayfa olmasını istiyorum
  Böylece rezervasyonlarımı, taleplerim ve ayarlarımı tek bir yerden yönetebilirim

  Background:
    Given kullanıcı sisteme kayıtlı bir Instructor hesabıyla giriş yapmıştır
    And kullanıcı Dashboard sayfasındadır

  # ==========================================
  # Dashboard & Navigasyon
  # ==========================================

  @US24_TC01
  Scenario: TC_024_01 - Toplantılar sekmesi ve altında bulunan linklerinin Dashboard kenar çubuğunda görünür olması
    Given kullanıcı Dashboard kenar çubuğunu görüntüler
    When Dashboard sidebar'da Toplantılar linki görüntülenmelidir
    Then Toplantılar başlığı altında Rezervasyonlarım linki görünür ve aktif olmalıdır
    Then Toplantılar başlığı altında Taleplerim linki görünür ve aktif olmalıdır
    And Toplantılar başlığı altında Ayarlar linki görünür ve aktif olmalıdır


  # ==========================================
  # Rezervasyonlarım Sayfası
  # ==========================================

  @US24_TC02
  Scenario: TC_024_03 - Rezervasyonlarım ikonuna tıklandığında toplantı istatistiklerinin görüntülenmesi
    Given kullanıcı Toplantılar başlığı altında bulunan Rezervasyonlarım ikonuna tıklar
    When Rezervasyonlarım sayfası açılmalıdır
    And toplantı istatistik bilgileri sayfada görünür olmalıdır

  @US24_TC03
  Scenario Outline: TC_024_04 - Filtrelenen toplantılar üzerinde CRUD işlemleri yapılabilmesi
    Given kullanıcı Toplantılar başlığı altında bulunan Rezervasyonlarım ikonuna tıklar
    When toplantılar listesi görüntülenir
    When toplantı listesi filtrelenir
    Then Sonuçları göster butonu tıklanır
    Then filtrelenen toplantılar listede görüntülenebilir olmalıdır
    When kullanıcı bir toplantı üzerinde "<islem>" işlemini yapar
    And "<beklenen_sonuc>" olmalıdır

    Examples:
      | islem     | beklenen_sonuc                                    |
      | görüntüle | toplantı detay bilgileri ekranda görünür olmalıdır |
      | düzenle   | toplantı bilgileri güncellenebilir olmalıdır       |
      | sil       | toplantı listeden kaldırılmış olmalıdır            |


  @US24_TC04
  Scenario: TC_024_05 - Yalnızca açık toplantıları göster onay kutusunun çalışması (Rezervasyonlarım)
    When kullanıcı Rezervasyonlarım sayfasına gider
    Then Yalnızca açık toplantıları göster onay kutusu görünür ve seçilebilir olmalıdır
    When kullanıcı Yalnızca açık toplantıları göster onay kutusunu seçer
    Then yalnızca açık durumundaki toplantılar listelenmeli
    When kullanıcı Yalnızca açık toplantıları göster onay kutusunun seçimini kaldırır
    Then tüm toplantılar tekrar listelenmeli


  @US24_TC05
  Scenario: TC_024_06 - Filtre simgelerinin seçilebilir olması ve listeyi filtrelemesi
    When kullanıcı Rezervasyonlarım sayfasına gider
    Then filtre simgeleri görünür ve seçilebilir olmalıdır
    When kullanıcı istediği filtreleri seçer
    Then toplantı listesi seçilen kritere göre güncellenmeli


  # ==========================================
  # Taleplerim Sayfası
  # ==========================================


  @US24_TC06
  Scenario: TC_024_07 - Talepler linkine tıklandığında toplantı istatistiklerinin görüntülenmesi
    When kullanıcı Taleplerim linkine tıklar
    Then Taleplerim sayfası açılmalıdır
    And toplantı istatistik bilgileri sayfada görünür olmalıdır

  @US24_TC07
  Scenario: TC_024_08 - Toplantı üzerinde Add to Calendar işleminin gerçekleştirilebilmesi
    Given kullanıcı Taleplerim sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And Add to Calendar butonuna tıklar
    Then seçili toplantı kullanıcının takvimine başarıyla eklenmelidir

  @US24_TC08
  Scenario: TC_024_09 - Toplantı üzerinde Contact Instructor işleminin gerçekleştirilebilmesi
    Given kullanıcı Taleplerim sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And Contact instructor butonuna tıklar
    Then iletişim ekranı açılmalıdır
    And mesaj gönderilebilir olmalıdır

  @US24_TC09
  Scenario: TC_024_10 - Toplantı üzerinde Finish Meeting işleminin gerçekleştirilebilmesi
    Given kullanıcı Taleplerim sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And Finish meeting butonuna tıklar
    And işlem yapılan toplantı listede finished olarak işaretlenmiş olmalıdır

  @US24_TC10
  Scenario: TC_024_11 - Yalnızca açık toplantıları göster onay kutusunun çalışması (Taleplerim)
    When kullanıcı Taleplerim sayfasına gider
    Then Yalnızca açık toplantıları göster onay kutusu görünür olmalıdır
    And onay kutusu seçilebilir olmalıdır
    When kullanıcı Yalnızca açık toplantıları göster onay kutusunu seçer
    Then yalnızca açık durumdaki talepler listelenmeli

  # ==========================================
  # Ayarlar Sayfası
  # ==========================================

  @US24_TC11
  Scenario: TC_024_12 - Ayarlar sayfasında gün bazlı zaman ekleme yapılabilmesi
    When kullanıcı Ayarlar linkine tıklar
    Then Ayarlar sayfası açılmalıdır
    And gün ile ilgili zaman ekleme (add time) alanı görünür olmalıdır
    When kullanıcı gün bazlı zaman ekleme alanına geçerli bir değer girer
    And Kaydet butonuna tıklar
    Then değişiklikler başarıyla kaydedilmelidir

  @US24_TC12
  Scenario: TC_024_13 - Yüz yüze toplantılarda saatlik ücret seçiminde min-max katılımcı sayısı belirlenebilmesi
    Given kullanıcı Ayarlar sayfasındadır
    When kullanıcı grup toplantısı için saatlik ücret seçim alanına gider
    Then minimum katılımcı sayısı giriş alanı görünür olmalıdır
    And maksimum katılımcı sayısı giriş alanı görünür olmalıdır
    When kullanıcı minimum katılımcı sayısını seçer
    And kullanıcı maksimum katılımcı sayısını seçer
    And Kaydet butonuna tıklar
    Then min-max katılımcı bilgileri başarıyla kaydedilmelidir

  @US24_TC13
  Scenario: TC_024_14 - Grup toplantılarında saatlik ücret seçiminde min-max katılımcı sayısı belirlenebilmesi
    Given kullanıcı Ayarlar sayfasındadır
    When kullanıcı grup toplantısı için saatlik ücret seçim alanına gider
    Then minimum katılımcı sayısı giriş alanı görünür olmalıdır
    And maksimum katılımcı sayısı giriş alanı görünür olmalıdır
    When kullanıcı minimum katılımcı sayısını girer
    And kullanıcı maksimum katılımcı sayısını girer
    And Kaydet butonuna tıklar
    Then min-max katılımcı bilgileri başarıyla kaydedilmelidir

  @US24_TC14
  Scenario: TC_024_15 - Ayarlar sayfasında Kaydet butonunun görünür, aktif ve işlevsel olması
    When kullanıcı Ayarlar sayfasına gider
    Then Kaydet butonu sayfada görünür olmalıdır
    And Kaydet butonu aktif ve tıklanabilir olmalıdır
    When kullanıcı herhangi bir ayar değişikliği yapar
    And Kaydet butonuna tıklar
    Then değişiklikler başarıyla kaydedilmelidir
