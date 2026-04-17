
@LoginIslemleri
Feature: Kullanici base_url acar
  @openSite
  Scenario: Kullanici base_url acar
    * Kullanici ana sayfaya gider


  @loginProccess
  Scenario: Kullanici login islemlerini yapar
    * Kullanici login page sayfasina gider
    * Kullanici email kutusuna gecerli mail adresi girer
    * Kullanici password kutusuna gecerli password girer
    * Kullanici submitButton tiklar

  @loginWithMethod
  Scenario: Kullanici method cagrisi ile login olur
    * Kullanici login page sayfasina gider
    * Kullanici mail adresi "ahmet.student@InstuLearn.com" ve tanimli password ile  "InstuLearn.2026!" giris yapar


  @scanarioOutline
  Scenario Outline: scenario outline denemesi
    * Kullanici login page sayfasina gider
    * Kullanici mail adresi ile "<mail>" ve tanimli password ile  "<password>" giris yapar
    Examples:
      | mail                         | password         |
      | ahmet.student@InstuLearn.com | InstuLearn.2026! |


  @sendLongMessage
  Scenario: Kullanici destek mesajı gönderir
    * Kullanici login page sayfasina gider
    * Kullanici mesaj kutusuna su notu birakir:
      """
      Merhaba TeamLead,
      Sisteme giriş yaparken hata alıyorum.
      Lütfen acil destek rica ederim.
      Saygılarımla.
      """


  @registerProcess
  Scenario: Yeni kullanici kayit islemi
    * Kullanici login page sayfasina gider
    * Kullanici su bilgilerle kayit olur:
      | email    | ahmet.student@InstuLearn.com |
      | password | InstuLearn.2026!             |

