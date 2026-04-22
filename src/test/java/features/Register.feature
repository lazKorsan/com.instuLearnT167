
@RegisterFuture
Feature: Kullanici teacher kaydi olusturur

  Scenario: Kullanci teacher kaydi olusturur
    * Teacher registerPage sayfasina gider
    * Teacher instructor Buttonunu secer
    * Teacher mailBox kutusuna "ahmet.te2acher@instuLearn.com" degerini girer
    * Teacher fullnameBox kutusuna "ahmet" degerini girer
    * Teacher passwordBox kotusuna "Instu.2026!" degerini gire
    * Teacher reTypePassword kutusuna "Instu.2026!" degerini girer
    * Kullanici checkBox kutusunu isaretler
    * Kullanici submitButton tiklar


    @RegisterScanarioOutline
  Scenario Outline: Kullanici dynamic register methodu olusturur
    * Teacher registerPage sayfasina gider
    * Teacher instructor Buttonunu secer
    * Teacher mailBox kutusuna "<mail>" degerini girer
    * Teacher fullnameBox kutusuna "<name>" degerini girer
    * Teacher passwordBox kotusuna "<password>" degerini gire
    * Teacher reTypePassword kutusuna "<password>" degerini girer
    * Kullanici checkBox kutusunu isaretler
    * Kullanici submitButton tiklar
    Examples:
      | mail                              | name  | password         |
      | ahmet11232.teacher@instuLearn.com | ahmet | InstuLearn.2026! |



    @DynamicRegisterTeacher
    Scenario: Kullanici timeStamp atarak dynamic register olusturur
      * Teacher registerPage sayfasina gider
      * Teacher instructor Buttonunu secer
      * Teacher "ahmet" ve "InstuLearn.2026!" bilgileri ile gecerli kayıt olusturur
      * Kullanici checkBox kutusunu isaretler
      * Kullanici submitButton tiklar


  @registerStudent2
  Scenario Outline: Kullanici dynamic register methodu olusturur
    * Teacher registerPage sayfasina gider
    * Teacher student Buttonunu secer
    * Teacher mailBox kutusuna "<mail>" degerini girer
    * Teacher fullnameBox kutusuna "<name>" degerini girer
    * Teacher passwordBox kotusuna "<password>" degerini gire
    * Teacher reTypePassword kutusuna "<password>" degerini girer
    * Kullanici checkBox kutusunu isaretler
    * Kullanici submitButton tiklar
    * Kullanici 10 saniye bekler
    Examples:
      | mail                          | name  | password         |
      | zafer3.student@instuLearn.com | zafer | InstuLearn.2026! |