

@createCourse
Feature: CreateBundele

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


  Scenario Outline: Kullanici teacher olarak kayit işlemlerini yapar
    * Instructor "<mail>" mail adresi ve "<password>" sifresi kayit olur
    * Kullanici 10 saniye bekler
    Examples:
      | mail                             | password         |
      | ahmet.instructor4@InstuLearn.com | InsruLearn.2026! |


  @preConditionRegisteration2
  Scenario Outline: Instructor Login Adimlari easy way
     * Instructor "<mail>" adresi ve "<password>" ile sisteme giris yapar
      Examples:
        | mail                             | password         |
        | ahmet.instructor2@InstuLearn.com | InsruLearn.2026! |

  @preConditionRegisteration3
  Scenario: Kullanici kurst kayit islemleri sayfasina gider
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici dashBoard menuden newButtonuna tiklar
    * Kullanici newWebinar sayfasinda oldugunu dogrular
    * Kullanici basicInformation sayfasi bilgilerini doldurur


    @oldAccount
  Scenario: eski hesap fullPage butun elementler doldurulmuş halde
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici 10 saniye bekler


    @preConditionStep2
  Scenario: step2 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici extraInformation sayfasi bilgilerini doldurur


  @preConditionStep3
  Scenario: step3 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici pricingPage sayfasi bilgilerini doldurur

  @preConditionStep4
  Scenario: step4 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici contentPage sayfasi bilgilerini doldurur

  @preConditionStep5
  Scenario: step5 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici prerequisitesPage sayfasi bilgilerini doldurur


  @preConditionStep6
  Scenario: step6 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici FAQpage sayfasi bilgilerini doldurur

  @preConditionStep7
  Scenario: step7 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici Quiz&CertificationPage sayfasi bilgilerini doldurur









