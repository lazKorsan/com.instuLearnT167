

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
      * Kullanici "met" ve "InstuLearn.2026!" bilgileri ile student kayit yapar

      # Stdudent kaydı yapıldıgında açılan sayfada "Become Instructor" butonu çıkıyor
      * Kullanici 10 saniye bekler

    @preCondtionLogin
    Scenario: Static Method
    Kayıtlı kullanici role degistrime. Bu test methodu tek kullanımlık. İkinci defa calismaz
      Degerlerin degistirilemsi gerekir
      * Kullanici login page sayfasina gider
      * Kullanici mail adresi "zafer.student@instuLearn.com" ve tanimli password ile  "InstuLearn.2026!" giris yapar
      * Kullanici Instructors butonuna basar
      * Kullanici becomeInstructorButtona tiklar
      * Kullanici webDesign secenegini secer
      * Kullanici payout account olarak Stripe secer
      * Kullanici accountHolder bolumunu doldurur
      * Kullanici accountID bolumunu doldurur
      * Kullanici identityScan bolumunu secer
      * Kullanici descriptionBox bolumunu doldurur
      * Kullanici sendRequestButton a tiklar
      * Kullanici acilan sayfada arama kutusuna "zafer" kelimesini aratir
      * Kullanici arama sonucu cikan yazinin "zafer" oldugunu dogrular
     # * Kullanici certificates bolumunde sertifika secimi yapar

      * Kullanici 10 saniye bekler

    @ChangeRoleDynamic
    Scenario: Dynamic Method
    Kullanici once ogrenci kaydi yapar . Arkasindan role degistirir.
      * Kullanici registerPage sayfasina gider
      * Kullanici "met" ve "InstuLearn.2026!" bilgileri ile student kayit yapar
      * Kullanici becomeInstructorButtona tiklar
      * Kullanici webDesign secenegini secer
      * Kullanici sertificate bolumunu doldurur
      * Kullanici payout account olarak Stripe secer
      * Kullanici accountHolder bolumunu doldurur
      * Kullanici accountID bolumunu doldurur
      * Kullanici identityScan bolumunu secer
      * Kullanici descriptionBox bolumunu doldurur
      * Kullanici sendRequestButton a tiklar
      * Kullanici acilan sayfada arama kutusuna "met" kelimesini aratir
      * Kullanici arama sonucu cikan yazinin "met" oldugunu dogrular