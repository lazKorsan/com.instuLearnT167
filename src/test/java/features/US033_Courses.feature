

 Feature: US033 Courses sekmesinden kurs detay sayfasına gider

   @CourseLink
   Scenario: TC01 Anasayfada 'Courses' linki görünür ve aktif olmalı
     Given Kullanici login sayfasina guvenli sekilde gider
     And   Kullanici guvenli sekilde 2 saniye bekler
     When  Kullanici "huseyin.ozger.teacher@instulearn.com" maili ve "Learn.123!" sifresi ile guvenli login olur
     And   Kullanici guvenli sekilde 3 saniye bekler
    # When  Kullanici ana sayfanin navbar bolumunu kontrol eder
     #Then  navbar'da "Courses" linkinin gorunur oldugu dogrulanir
     #And   navbar'da "Courses" linkinin aktif (tiklanabilir) oldugu dogrulanir
     When  Kullanici navbar'daki "Courses" linkine tiklar
     Then  Kullanici Courses sayfasina yonlendirilir
     And   Courses sayfasinda en az bir kurs karti gorunur olmalidir
