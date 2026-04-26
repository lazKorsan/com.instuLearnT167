

@US24
Feature: US_024 - Toplantı Yönetim Sayfası
  Kayıtlı bir Organization/Instructor kullanıcısı olarak
  toplantılarımı yönetebileceğim bir sayfa olmasını istiyorum
  Böylece rezervasyonlarımı, taleplerim ve ayarlarımı tek bir yerden yönetebilirim

  Background:
    Given kullanıcı sisteme kayıtlı bir Organization/Instructor hesabıyla giriş yapmıştır
    And kullanıcı Dashboard sayfasındadır

  # ==========================================
  # Dashboard & Navigasyon
  # ==========================================

  @TC_024_01 @Navigation @Smoke
  Scenario: TC_024_01 - Toplantılar sekmesi ve altında bulunan linklerinin Dashboard kenar çubuğunda görünür olması
    Given kullanıcı Dashboard kenar çubuğunu görüntüler
    When Dashboard sidebar'da Toplantılar linki görüntülenmelidir
    Then Toplantılar başlığı altında Rezervasyonlarım linki görünür ve aktif olmalıdır
    Then Toplantılar başlığı altında Taleplerim linki görünür ve aktif olmalıdır
    And Toplantılar başlığı altında Ayarlar linki görünür ve aktif olmalıdır


  # ==========================================
  # Rezervasyonlarım Sayfası
  # ==========================================

  @TC_024_03 @Reservations @Statistics
  Scenario: TC_024_03 - Rezervasyonlarım ikonuna tıklandığında toplantı istatistiklerinin görüntülenmesi
    Given kullanıcı Toplantılat başlığı altında bulunan Rezervasyonlarım ikonuna tıklar
    When Rezervasyonlarım sayfası açılmalıdır
    And toplantı istatistik bilgileri sayfada görünür olmalıdır

  @TC_024_04 @Reservations @CRUD
  Scenario Outline: TC_024_04 - Filtrelenen toplantılar üzerinde CRUD işlemleri yapılabilmesi
    Given kullanıcı Toplantılat başlığı altında bulunan Rezervasyonlarım ikonuna tıklar
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

  @TC_024_05 @Reservations @Filter
  Scenario: TC_024_05 - Yalnızca açık toplantıları göster onay kutusunun çalışması (Rezervasyonlarım)
    When kullanıcı "Rezervasyonlarım" sayfasına gider
    Then "Yalnızca açık toplantıları göster" onay kutusu görünür olmalıdır
    And onay kutusu seçilebilir olmalıdır
    When kullanıcı "Yalnızca açık toplantıları göster" onay kutusunu seçer
    Then yalnızca açık durumundaki toplantılar listelenmeli
    When kullanıcı "Yalnızca açık toplantıları göster" onay kutusunun seçimini kaldırır
    Then tüm toplantılar tekrar listelenmeli

  @TC_024_06 @Reservations @Filter
  Scenario: TC_024_06 - Filtre simgelerinin seçilebilir olması ve listeyi filtrelemesi
    When kullanıcı "Rezervasyonlarım" sayfasına gider
    Then filtre simgeleri görünür ve seçilebilir olmalıdır
    When kullanıcı bir filtre simgesini seçer
    Then toplantı listesi seçilen kritere göre güncellenmeli

  # ==========================================
  # Taleplerim Sayfası
  # ==========================================

  @TC_024_07 @Requests @Statistics
  Scenario: TC_024_07 - Talepler linkine tıklandığında toplantı istatistiklerinin görüntülenmesi
    When kullanıcı "Taleplerim" linkine tıklar
    Then "Taleplerim" sayfası açılmalıdır
    And toplantı istatistik bilgileri sayfada görünür olmalıdır

  @TC_024_08 @Requests @Actions
  Scenario: TC_024_08 - Toplantı üzerinde Add to Calendar işleminin gerçekleştirilebilmesi
    Given kullanıcı "Taleplerim" sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And "Add to Calendar" butonuna tıklar
    Then seçili toplantı kullanıcının takvimine başarıyla eklenmelidir
    And onay mesajı görüntülenmelidir

  @TC_024_09 @Requests @Actions
  Scenario: TC_024_09 - Toplantı üzerinde Contact Instructor işleminin gerçekleştirilebilmesi
    Given kullanıcı "Taleplerim" sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And "Contact instructor" butonuna tıklar
    Then eğitmene iletişim ekranı açılmalıdır
    And mesaj gönderilebilir olmalıdır

  @TC_024_10 @Requests @Actions
  Scenario: TC_024_10 - Toplantı üzerinde Finish Meeting işleminin gerçekleştirilebilmesi
    Given kullanıcı "Taleplerim" sayfasındadır
    And toplantı talep listesi görüntülenmektedir
    When kullanıcı bir toplantı seçer
    And "Finish meeting" butonuna tıklar
    Then toplantı "tamamlandı" olarak işaretlenmelidir
    And toplantı listesi güncellenmiş olarak görüntülenmelidir

  @TC_024_11 @Requests @Filter
  Scenario: TC_024_11 - Yalnızca açık toplantıları göster onay kutusunun çalışması (Taleplerim)
    When kullanıcı "Taleplerim" sayfasına gider
    Then "Yalnızca açık toplantıları göster" onay kutusu görünür olmalıdır
    And onay kutusu seçilebilir olmalıdır
    When kullanıcı "Yalnızca açık toplantıları göster" onay kutusunu seçer
    Then yalnızca açık durumdaki talepler listelenmeli

  # ==========================================
  # Ayarlar Sayfası
  # ==========================================

  @TC_024_12 @Settings @DayTime
  Scenario: TC_024_12 - Ayarlar sayfasında gün bazlı zaman ekleme yapılabilmesi
    When kullanıcı "Ayarlar" linkine tıklar
    Then "Ayarlar" sayfası açılmalıdır
    And gün ile ilgili zaman ekleme (add time) alanı görünür olmalıdır
    When kullanıcı gün bazlı zaman ekleme alanına geçerli bir değer girer
    And "Kaydet" butonuna tıklar
    Then değişiklikler başarıyla kaydedilmelidir

  @TC_024_13 @Settings @HourlyRate @FaceToFace
  Scenario: TC_024_13 - Yüz yüze toplantılarda saatlik ücret seçiminde min-max katılımcı sayısı belirlenebilmesi
    Given kullanıcı "Ayarlar" sayfasındadır
    When kullanıcı yüz yüze toplantı için saatlik ücret seçim alanına gider
    Then minimum katılımcı sayısı giriş alanı görünür olmalıdır
    And maksimum katılımcı sayısı giriş alanı görünür olmalıdır
    When kullanıcı minimum katılımcı sayısını "1" olarak girer
    And kullanıcı maksimum katılımcı sayısını "10" olarak girer
    And "Kaydet" butonuna tıklar
    Then min-max katılımcı bilgileri başarıyla kaydedilmelidir

  @TC_024_14 @Settings @HourlyRate @GroupMeeting
  Scenario: TC_024_14 - Grup toplantılarında saatlik ücret seçiminde min-max katılımcı sayısı belirlenebilmesi
    Given kullanıcı "Ayarlar" sayfasındadır
    When kullanıcı grup toplantısı için saatlik ücret seçim alanına gider
    Then minimum katılımcı sayısı giriş alanı görünür olmalıdır
    And maksimum katılımcı sayısı giriş alanı görünür olmalıdır
    When kullanıcı minimum katılımcı sayısını "2" olarak girer
    And kullanıcı maksimum katılımcı sayısını "20" olarak girer
    And "Kaydet" butonuna tıklar
    Then min-max katılımcı bilgileri başarıyla kaydedilmelidir

  @TC_024_15 @Settings @SaveButton @Smoke
  Scenario: TC_024_15 - Ayarlar sayfasında Kaydet butonunun görünür, aktif ve işlevsel olması
    When kullanıcı "Ayarlar" sayfasına gider
    Then "Kaydet" butonu sayfada görünür olmalıdır
    And "Kaydet" butonu aktif ve tıklanabilir olmalıdır
    When kullanıcı herhangi bir ayar değişikliği yapar
    And "Kaydet" butonuna tıklar
    Then değişiklikler başarıyla kaydedilmelidir
    And başarı onay mesajı görüntülenmelidir
