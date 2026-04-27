Feature: US033_Instructor - Instructor kullanicisi olarak anasayfada kurslari gorebilmek, bilgi alabilmek ve kurs secebilecegim bir sayfa olmasini istiyorum

  Background:
    Given Kullanici login sayfasina guvenli sekilde gider
    And   Kullanici guvenli sekilde 2 saniye bekler
    When  Kullanici "huseyin.ozger.teacher@instulearn.com" maili ve "Learn.123!" sifresi ile guvenli login olur
    And   Kullanici guvenli sekilde 2 saniye bekler

  @courses @smoke @US033 @instructor @TC01
  Scenario: TC01 - Ana sayfada Courses linki gorunur ve aktif olmali, tiklandiginda Courses sayfasi acilmali
   # When  Kullanici ana sayfanin ust barini kontrol eder
    #Then  "Courses" linkinin gorunur oldugu dogrulanir
    #And   "Courses" linkinin aktif (tiklanabilir) oldugu dogrulanir
    When  Kullanici ust bardaki "Courses" linkine tiklar
    And   Kullanici guvenli sekilde 1 saniye bekler
    Then  Kullanici Courses sayfasina yonlendirilir
    And   Courses sayfasinda en az bir kurs karti gorunur olmalidir

  @courses @smoke @US033 @instructor @TC02
  Scenario: TC02 - Courses sayfasinda Search textbox ve Search buton gorunur ve aktif olmali
    When  Kullanici ust bardaki "Courses" linkine tiklar
    Then  Courses sayfasinda Search textbox'i gorunur ve aktif olmalidir
    And   Courses sayfasinda Search butonu gorunur ve aktif olmalidir

  @courses @functional @US033 @instructor @TC03
  Scenario: TC03 - Kurs secmeden once filtreleme/siralama islemi yapilabilmelidir
    When  Kullanici ust bardaki "Courses" linkine tiklar
    Then  Courses sayfasinda filtreleme veya siralama alanlari gorunur olmalidir
    When  Kullanici siralama alanindan bir secim yapar
    Then  Filtrelenmis kurslar listelenir

  @courses @functional @US033 @instructor @TC04
  Scenario: TC04 - Secilen kurslarin ders adi, instructor, tarih ve fiyat bilgileri gorunur olmalidir
    When  Kullanici ust bardaki "Courses" linkine tiklar
    Then  Listelenen her kurs icin ders adi gorunur olmalidir
    And   Listelenen her kurs icin instructor bilgisi gorunur olmalidir
    And   Listelenen her kurs icin tarih bilgisi gorunur olmalidir
    And   Listelenen her kurs icin fiyat bilgisi gorunur olmalidir

  @courses @functional @US033 @instructor @TC05
  Scenario: TC05 - Istenen kurs secildiginde kurs detay sayfasina yonlendirme yapilmali
    When  Kullanici ust bardaki "Courses" linkine tiklar
    And   Kullanici listedeki ilk kursa tiklar
    Then  Kullanici kurs detay sayfasina yonlendirilir
    And   Kurs detay sayfasinda satin alma veya enroll butonu gorunur ve aktif olmalidir
    And   Kurs detay sayfasinda "About this course" bolumu gorunur olmalidir
    And   Kurs detay sayfasinda kursun puanlamasi gorunur olmalidir
    And   Kurs detay sayfasinda Information, Content ve Reviews tab'lari gorunur olmalidir

  @courses @functional @US033 @instructor @TC06
  Scenario: TC06 - Kurs ile ilgili yorum yapilabilmeli
    When  Kullanici ust bardaki "Courses" linkine tiklar
    And   Kullanici listedeki ilk kursa tiklar
    Then  Kullanici kurs detay sayfasinda yorum alanini gorur
    When  Kullanici yorum alanina "Bu kurs gercekten cok faydali oldu!" yazisini girer
    And   Kullanici yorumu gonder butonuna tiklar
    Then  Yorumun basariyla eklendigi dogrulanir

  @courses @functional @US033 @instructor @TC07
  Scenario: TC07 - Instructor profil sayfasi goruntulenebilmelidir
    When  Kullanici ust bardaki "Courses" linkine tiklar
    And   Kullanici listedeki ilk kursa tiklar
    And   Kullanici kursun instructor linkine tiklar
    Then  Instructor profil sayfasi goruntulenmelidir
    And   Instructor profil sayfasinda instructor bilgileri gorunur olmalidir

  @courses @e2e @US033 @instructor @TC08
  Scenario: TC08 - Kullanici secilen kursu basariyla satin alabilmelidir
    When  Kullanici ust bardaki "Courses" linkine tiklar
    And   Kullanici listedeki ilk kursa tiklar

    When  Kullanici Add_to_Cart ikonuna tiklar
    Then  Added to cart toast mesaji beklenen metni icermeli

    When  Kullanici navbar'da sepet ikonuna tiklar
    And   Kullanici Go_to_cart butonuna tiklar
    And   Kullanici Checkout butonuna scroll edip tiklar
    And   Kullanici Pay with Stripe'i secer ve Start Payment butonuna tiklar
    And   Kullanici Stripe odeme formunu doldurur ve Pay butonuna tiklar
    Then  Odeme basariyla tamamlanmis olmali ve basari mesaji gorunmeli
