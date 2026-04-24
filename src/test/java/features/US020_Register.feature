
  Feature: US020 -Bir kullanici olarak sitenin fonksiyonlarindan faydalanabilmek icin siteye kayit olabilmek istiyorum

    Background:
    Given Kullanici "https://qa.instulearn.com/" adresine gider

    @register @smoke @TC01
    Scenario: TC01 - Ana sayfa ust barinda Register linki gorunur ve aktif olmalidir
    When  Kullanici ana sayfanin ust barini kontrol eder
    Then  "Register" linkinin gorunur oldugu dogrulanir
    And   "Register" linkinin aktif (tiklanabilir) oldugu dogrulanir

    @register @smoke @TC02
    Scenario: TC02 - Register sayfasinin sol bolumunde ilgili resim gorunur olmalidir
    When  Kullanici ust bardaki "Register" linkine tiklar
    Then  Kullanici Register sayfasina yonlendirilir
    And   Register sayfasinin sol bolumundeki resim gorunur olmalidir

    @register @smoke @TC03
    Scenario: TC03 - Register sayfasinin sag bolumunde Signup formu gorunur olmalidir
    When  Kullanici ust bardaki "Register" linkine tiklar
    Then  Register sayfasinin sag bolumunde "Signup" formu gorunur olmalidir

    @register @smoke @TC04
    Scenario: TC04 - Signup formunun altinda Signup butonu gorunur ve aktif olmalidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Signup formunu kontrol eder
    Then  Signup formunun altinda "Signup" butonunun gorunur oldugu dogrulanir
    And   "Signup" butonunun aktif (tiklanabilir) oldugu dogrulanir

    @register @functional @TC05
    Scenario Outline: TC05 - Signup formu icerisinde Account type butonlari gorunur ve secilebilir olmalidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Account type alanindaki "<accountType>" butonunu kontrol eder
    Then  "<accountType>" butonunun gorunur oldugu dogrulanir
    And   "<accountType>" butonunun secilebilir oldugu dogrulanir

    Examples:
    | accountType  |
    | Student      |
    | Instructor   |
    | Organization |

    @register @functional @TC06
    Scenario Outline: TC06 - Signup formu icerisinde bos birakilamaz alanlar dogrulanmalidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici "<alan>" alanini bos birakir
    And   Diger tum zorunlu alanlari gecerli degerlerle doldurur
    And   "Signup" butonuna tiklar
    Then  "<alan>" alani icin zorunlu alan uyari mesaji gorunur olmalidir
    And   Kullanici Register sayfasinda kalmalidir

    Examples:
    | alan            |
    | Email           |
    | Full Name       |
    | Password        |
    | Retype Password |

    @register @functional @TC07
    Scenario: TC07 - Terms & rules checkbox secilmeden kayit olunamamalidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici tum zorunlu alanlari gecerli degerlerle doldurur
    And   "I agree with terms & rules" checkbox'ini secmeden birakir
    And   "Signup" butonuna tiklar
    Then  "The term field is required." uyari mesaji gorunur olmalidir
    And   Kullanici Register sayfasinda kalmalidir

    @register @functional @TC07_positive
    Scenario: TC07_Pozitif - Terms & rules checkbox secildiginde kayit islemi devam edebilmelidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici tum zorunlu alanlari gecerli degerlerle doldurur
    And   "I agree with terms & rules" checkbox'ini secer
    Then  "I agree with terms & rules" checkbox'inin secili oldugu dogrulanir
    And   "Signup" butonu tiklanabilir olmalidir

    @register @validation @TC08
    Scenario Outline: TC08 - Email alani gecerli email formatini saglamalidir - Negatif
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Email alanina gecersiz "<gecersizEmail>" degerini girer
    And   Diger tum zorunlu alanlari gecerli degerlerle doldurur
    And   "I agree with terms & rules" checkbox'ini secer
    And   "Signup" butonuna tiklar
    Then  Email alani icin gecersiz email format uyari mesaji gorunur olmalidir
    And   Kullanici Register sayfasinda kalmalidir

    Examples:
    | gecersizEmail       |
    | plainaddress        |
    | @missingusername.com|
    | user@.com           |
    | user@domain         |
    | user domain.com     |
    | user@@domain.com    |

    @register @validation @TC08_positive
    Scenario: TC08_Pozitif - Email alani gecerli email formatinda oldugunda kabul edilmelidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Email alanina gecerli "test.user@instulearn.com" degerini girer
    Then  Email alani icin hicbir format uyari mesaji gorunmemelidir

    @register @validation @TC09
    Scenario Outline: TC09 - Password alani minimum 8 karakter kriterini saglamalidir - Negatif
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Password alanina 8 karakterden az olan "<kisaSifre>" degerini girer
    And   Retype Password alanina ayni "<kisaSifre>" degerini girer
    And   Diger tum zorunlu alanlari gecerli degerlerle doldurur
    And   "I agree with terms & rules" checkbox'ini secer
    And   "Signup" butonuna tiklar
    Then  Password alani icin minimum karakter uyari mesaji gorunur olmalidir
    And   Kullanici Register sayfasinda kalmalidir

    Examples:
    | kisaSifre |
    | 123       |
    | abc       |
    | 1234567   |
    | pass12    |

    @register @validation @TC09_positive
    Scenario: TC09_Pozitif - Password alani minimum 8 karakter kriterini sagladiginda kabul edilmelidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici Password alanina 8 karakter veya daha fazla olan "Test1234" degerini girer
    And   Retype Password alanina ayni "Test1234" degerini girer
    Then  Password alani icin minimum karakter uyari mesaji gorunmemelidir

    @register @navigation @TC10
    Scenario: TC10 - Signup sayfasindan Login sayfasina gecis yapilabilmelidir
    Given Kullanici "Register" sayfasinda bulunuyor
    When  Kullanici "Already have an account?" bolumundeki "Login" linkini kontrol eder
    Then  "Login" linkinin gorunur oldugu dogrulanir
    And   "Login" linkinin aktif (tiklanabilir) oldugu dogrulanir
    When  Kullanici "Login" linkine tiklar
    Then  Kullanici Login sayfasina yonlendirilir
    And   Login sayfasi URL'inin "/login" icerdigi dogrulanir
