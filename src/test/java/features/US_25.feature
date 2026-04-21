
Feature: Quiz sidebar navigasyonu

  @US_25
  Scenario: TC_01 Quiz linkleri sidebar'da gorunmelidir
    * kullanici qa.instulearn.com sayfasina gider
    * Kullanici login page sayfasina kecid eder
    * Kullanici email kutusuna gecerli mail adresi yazar
    * Kullanici password kutusuna gecerli password yazar
    * Kullanici submitButton basar
    * kullanici sisteme basarili giris yapar
    * kullanici Quizzes butonunu tiklar
    * New Quiz linki gorunur olmali
    * List linki gorunur olmali
    * Results linki gorunur olmali
    * My results linki gorunur olmali
    * Not Participated linki gorunur olmali


  @US_25
  Scenario: TC_02 Quiz linkleri aktif olmalidir

    * kullanici qa.instulearn.com sayfasina gecer
    * Kullanici login page sayfasina yonelir
    * Kullanici email kutusuna gecerli mail adresini yazar
    * Kullanici password kutusuna gecerli passwordunu yazar
    * Kullanici submitButton click yapar
    * kullanici sisteme giris yapmis
    * kullanici Quizzes butonunu secer
    * kullanici New Quiz linkine tiklar
    * kullanici New Quiz sayfasina yonlendirilmelidir
    * kullanici List linkine tiklar
    * kullanici Comments statistics sayfasina yonlendirilmelidir
    * kullanici Results linkine tiklar
    * kullanici Student Results sayfasina yonlendirilmelidir
    * kullanici My Results linkine tiklar
    * kullanici My quizzes sayfasina yonlendirilmelidir
    * kullanici Not Participated linkine tiklar
    * kullanici Filter Results sayfasina yonlendirilmelidir