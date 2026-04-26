
Feature: Blog sayfasına erişim


  Scenario: TC_01 Anasayfada Blog linkinin görünmes

    * kullanıcı anasayfaya gider
    * Blog linki görünür olmalıdır
    * Blog linki aktiv olmalidir
    * kullanıcı Blog linkine tıklar
    * sayfada Blog texti görüntülenmelidir
    * search textbox görünür olmalıdır
    * search textbox aktif olmalıdır
    * search buton görünür olmalıdır
    * search buton aktif olmalıdır

  @US_36
  Scenario: TC_02 Blog sayfasında kategorilere göre arama yapılabilmeli
    * kullanıcı blog sayfasına gider
    * kategorilere gore blog aramasi yapabilmelidir
    * aradigi blog gorunur olmalidir
    * blog tarihi görüntülenmelidir
    * blog konusu başlık görüntülenmelidir
    * blog içeriği görüntülenmelidir
    * paylaşan kişi bilgisi görüntülenmelidir
    * kullanici cikan sonuc blogu tiklar
    * kullanıcı yorum textbox'a Test yorum yazar
    * kullanıcı yorum gönder butonuna tıklar
    * yorum başarıyla eklenmelidir
























