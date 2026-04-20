

  Feature:
    @preCondition1
    Scenario: Kullanici timeStamp atarak dynamic register olusturur
      * Teacher registerPage sayfasina gider
      * Teacher instructor Buttonunu secer
      * Teacher "ahmet" ve "InstuLearn.2026!" bilgileri ile gecerli kayıt olusturur
      * Kullanici checkBox kutusunu isaretler
      * Kullanici submitButton tiklar


      @studentRegister
    Scenario: Kullanici student olarak sayfaya kayt olur
      * Kullanici registerPage sayfasina gider
      * Kullanici "ahmet" ve "InstuLearn.2026!" bilgileri ile student kayit yapar
      # Stdudent kaydı yapıldıgında açılan sayfada "Become Instructor" butonu çıkıyor
      * Kullanici 10 saniye bekler

    @preCondtionLogin
    Scenario:
      * Kullanici login page sayfasina gider
      * Kullanici mail adresi "ahmet.student@InstuLearn.com" ve tanimli password ile  "InstuLearn.2026!" giris yapar
      * Kullanici becomeInstructorButtona tiklar
      * Kullanici 10 saniye bekler