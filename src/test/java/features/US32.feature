
@US32
Feature: Kayıtlı bir kullanıcı olarak profil bilgilerimi görüntüleyebilecegim bir alan olmasını istiyorum

  @US32_TC01
  Scenario: Login olmuş instructor Dashboard sidebar'da My Profile linkine erişebilmeli

    Given kullanıcı login olur
    When dashboard sayfasının açıldığı doğrulanır
    Then sol sidebar'da "My Profile" linki görünür olmalıdır
    And "My Profile" linki tıklanabilir durumda olmalıdır


  @US32_TC02
  Scenario: Login olmuş instructor anasayfa profile ikonundan My Profile linkine erişir

    Given kullanıcı login olur
    Then kullanıcı anasayfayı açar
    When mouse ile profil ikonunun üzerine geldiğinde dropdown açılır
    Then açılan dropdown'da "My Profile" linki görünür olmalıdır
    And "My Profile" linki aktif olmalıdır


  @US32_TC03
  Scenario: instructor My profile linkinden profil bilgilerini görüntüleyebilmeli

    Given kullanıcı login olur
    Then dashboard sayfasının açıldığı doğrulanır
    When sol sidebar da bulunan "My profile" linkine tıklar
    Then profil sayfasının açıldığı doğrulanır
    And ilgili profili takip edebilmelidir

  @US32_TC04
  Scenario: instructor profilinden About,Courses,Articles,Badges,Reserve a meeting  bilgilerini görüntüleyebilmeli

    Given kullanıcı login olur
    When sol sidebar da bulunan "My profile" linkine tıklar
    Then about linki altında ilgili içerik görüntülenir
    Then courses linki altında ilgili içerik görüntülenir
    Then articles linki altında ilgili içerik görüntülenir
    Then badges linki altında ilgili içerik görüntülenir
    Then reserve a meeting linki altında ilgili içerik görüntülenir






