
Feature: Quiz sidebar navigasyonu


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





  Scenario: TC_03 Instructor yeni quiz yarada bilmelidir

    * kullanici sisteme giris yapar
    * kullanici New Quiz buttonuna tiklar
    * kullanici Quiz title dahil eder
    * kullanici Pass mark dahil edir
    * kullanici Create duymesine tiklar
    * kullanici list sayfasina gider
    * yeni sinav basariyla olusdurulmalidir




  Scenario: TC_04 Add multiple choice butonu gorunur ve aktif olmalidir

    * kullanici siteye giris yapar
    * Add a multiple choice butonu gorunur olmali
    * Add a multiple choice butonu aktif olmali
    * kullanici Add a multiple choice butonuna tiklar
    * Multiple choice question yaratma sayfasi acilmalidir
    * kullanici question title soru kaydi daxil edir
    * kullanici grade soru kaydi daxil edir
    * kullanici Add an Answer butonunu tiklar
    * Answer title bolumunu cavab ekler
    * kullanici correct answer secir
    * kullanici yeniden Answer title bolumunu cavab ekler
    * kullanici correct answer secir
    * kullanici save duymesine tiklar
    * sual ugurla yaradilmali




  Scenario: TC_05 Add Descriptive butonu gorunur ve aktifdir

    * kullanici siteye giris yapar
    * Add a Descriptive butonu gorunur olmali
    * Add a Descriptive butonu aktif olmali
    * kullanici Add a Descriptive butonuna tiklar
    * New descriptive question question sayfasi acilmalidir
    * kullanici Question title  daxil edir
    * kullanici Grade daxil edir
    * kullanici Correct answer bilgileri daxil edir
    * kullanici Save duymesine tiklar
    * kullanici List linkine gecis yapar
    * Soru kaydi  ugurla yaradildi



  @US_25
  Scenario: TC_06 List linkine tiklandiginda quiz list sayfasi acilmalidir

    * kullanici siteye giris yapdi
    * kullanici List linkine tiklar
    * Comments statistics istatistikler gorunmelidir
    * kullanici filtreleme yapar
    * filtrelenmis sonuc gorunmelidir
    * kullanici bir quiz uzerinde edit tiklar
    * kullanici bilgilerin  guncelledigini konturol eder



  @US_25
  Scenario: TC_07 Quiz silinebilmelidir

    * kullanici List sayfasina gider
    * kullanici bir quiz uzerinde delete tiklar
    * kullanici silmeyi onaylar
    * quiz silinmelidir
















































