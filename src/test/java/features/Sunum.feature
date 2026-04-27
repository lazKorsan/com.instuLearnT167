
@Sunum
Feature: Team167 DemoSunum senaryolari

  @PozitiveLoginTest
  Scenario: Kullanici dogru bilgilerle ogrenci girisi yapar
    * Kullanici login page sayfasina gider
    * Pozitive Login Test Basliyor
    * Kullanici mail adresi "zafer3.student@instuLearn.com" ve tanimli password ile  "InstuLearn.2026!" giris yapar
    * Kullanici login oldugunu dogrular

  @NegativeLoginMail
  Scenario: Kullanici hatali mail adresi ile giris yapilamadigini dogrular
    * Kullanici login page sayfasina gider
    * Negative Login Test Hatali Mail
    * Kullanici mail adresi "zafer13.student@instuLearn.com" ve tanimli password ile  "InstuLearn.2026!" giris yapar
    * Kullanici hatali mail ile giris yapilamadigini dogrular

  @NegativeLoginPassword
  Scenario:  Kullanici hatali Password bilgisi ile  giris yapilamadigini dogrular
    * Kullanici login page sayfasina gider
    * Negative Login Test Hatali Password
    * Kullanici mail adresi "zafer3.student@instuLearn.com" ve tanimli password ile  "InstuLearn.2025!" giris yapar
    * Kullanici hatali password ile giris yapilamadigini dogrular

  @DynamicRegisterTeacher
  Scenario: Kullanici timeStamp atarak dynamic register olusturur
    * Teacher registerPage sayfasina gider
    * Teacher Register Testi Basliyor
    * Teacher instructor Buttonunu secer
    * Teacher "ahmet" ve "InstuLearn.2026!" bilgileri ile gecerli kayıt olusturur
    * Kullanici checkBox kutusunu isaretler
    * Kullanici submitButton tiklar
    * Kullanici kayit oldugunu dogrular

  @ChangeRoleDynamic
  Scenario: Dynamic Method
  Kullanici once ogrenci kaydi yapar . Arkasindan role degistirir.
    * Kullanici registerPage sayfasina gider
    * Kullanici "zefire0" ve "InstuLearn.2026!" bilgileri ile student kayit yapar
    * Kullanici becomeInstructorButtona tiklar
    * Kullanici webDesign secenegini secer
    * Kullanici sertificate bolumunu doldurur
    * Kullanici payout account olarak Stripe secer
    * Kullanici accountHolder bolumunu doldurur
    * Kullanici accountID bolumunu doldurur
    * Kullanici identityScan bolumunu secer
    * Kullanici descriptionBox bolumunu doldurur
    * Kullanici sendRequestButton a tiklar
    * Kullanici acilan sayfada arama kutusuna "zefire0" kelimesini aratir
    * Kullanici arama sonucu cikan yazinin "zefire0" oldugunu dogrular


  @createCourseOneScenario
  Scenario: Instructor yeni bir course olusturur
    # login islemleri
    * Instructor "ahmet.instructor3@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    # newWebinars sayfasina gecis
    * Kullanici dashBoard menuden newButtonuna tiklar
    # newWebinar sayfasi dogrulama
    * Kullanici newWebinar sayfasinda oldugunu dogrular
    # step1
    * Kullanici basicInformation sayfasi bilgilerini doldurur
    # step2
    * Kullanici extraInformation sayfasi bilgilerini doldurur
    # step3
    * Kullanici pricingPage sayfasi bilgilerini doldurur
    # step4
    * Kullanici contentPage sayfasi bilgilerini doldurur
    # step5
    * Kullanici prerequisitesPage sayfasi bilgilerini doldurur
    # step6
    * Kullanici FAQpage sayfasi bilgilerini doldurur
    # step7
    * Kullanici Quiz&CertificationPage sayfasi bilgilerini doldurur
    # todo test 5 dk kadar suruyor zorunlu bekletme ile 8 dakika info panel ile birlikte 12 dk
