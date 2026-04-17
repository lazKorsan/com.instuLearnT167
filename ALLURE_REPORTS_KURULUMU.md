Allure Raporları Kurulum ve Kullanım Rehberi
Allure, test otomasyonu sonuçlarınızı zengin, interaktif ve anlaşılır web raporlarına dönüştüren esnek bir raporlama aracıdır. Bu rehber, Maven tabanlı bir Java projesine Allure'un nasıl entegre edileceğini adım adım açıklamaktadır.

1. Adım: Maven Bağımlılıklarını Ekleme (pom.xml)
Allure'un testleriniz sırasında veri toplayabilmesi için projenizin pom.xml dosyasına gerekli bağımlılıkları eklemeniz gerekir.

Allure Adaptörü Ekleme: Kullandığınız test çatısına (TestNG, JUnit 5 vb.) uygun Allure adaptörünü ekleyin.

TestNG için:

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.21.0</version> <!-- En güncel sürümü kontrol edin -->
</dependency>
JUnit 5 için:

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-junit5</artifactId>
    <version>2.21.0</version> <!-- En güncel sürümü kontrol edin -->
</dependency>
AspectJ Weaver Eklenmesi (ÇOK ÖNEMLİ): Allure'un @Step gibi anotasyonları doğru bir şekilde işleyebilmesi için aspectjweaver gereklidir. Bu, Maven Surefire Plugin'in bir parçası olarak yapılandırılır. pom.xml dosyanızın <build> bölümüne ekleyin:

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
            <configuration>
                <argLine>
                    -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar"
                </argLine>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>org.aspectj</groupId>
                    <artifactId>aspectjweaver</artifactId>
                    <version>1.9.7</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
Not: aspectjweaver versiyonunu Maven Central'dan kontrol edebilirsiniz.

2. Adım: Allure Komut Satırı Aracını Kurma
Testler çalıştıktan sonra oluşan JSON dosyalarından HTML raporunu oluşturmak için Allure'un komut satırı aracına (Commandline Tool) ihtiyacınız vardır.

Windows için (Scoop ile):

scoop install allure
macOS için (Homebrew ile):

brew install allure
Manuel Kurulum (Tüm İşletim Sistemleri):

Maven Central adresinden en son allure-commandline.zip dosyasını indirin.
Zip dosyasını istediğiniz bir klasöre (örn: C:/allure) çıkarın.
Çıkardığınız klasörün içindeki bin dizinini (C:/allure/bin) sisteminizin PATH ortam değişkenine ekleyin.
Kurulum Doğrulama: Terminali yeniden başlatıp aşağıdaki komutu çalıştırın. Eğer versiyon numarası görüyorsanız, kurulum başarılıdır.

allure --version
3. Adım: Rapor Oluşturma ve Görüntüleme
Testleri Çalıştırın: Projenizin ana dizininde terminali açın ve standart Maven test komutunu çalıştırın.

mvn clean test
Bu komut, testler tamamlandığında target/allure-results adında bir klasör oluşturacak ve içine test sonuçlarını içeren JSON dosyaları üretecektir.

Raporu Oluşturun ve Görüntüleyin: Testler bittikten sonra, raporu oluşturmak için aşağıdaki komutu kullanın.

allure serve target/allure-results
Bu komut, allure-results klasöründeki verileri okur, bir web raporu oluşturur ve bu raporu otomatik olarak tarayıcınızda geçici bir web sunucusu üzerinden açar. Terminali kapattığınızda rapor da kapanır.

Kalıcı Rapor Oluşturma (CI/CD için): Raporu paylaşmak veya bir sunucuda barındırmak isterseniz, aşağıdaki komutu kullanın:

allure generate target/allure-results -o target/allure-report --clean
Bu komut, target/allure-report adında statik bir HTML raporu oluşturur. Bu klasörü zipleyip başkalarına gönderebilir veya bir web sunucusuna yükleyebilirsiniz.

4. Adım (İsteğe Bağlı): Raporları Zenginleştirme
Allure'un gücü, kodunuza ekleyeceğiniz basit anotasyonlarla raporlarınızı çok daha anlamlı hale getirmesinden gelir.

@Description("Kullanıcı adı ve şifre doğru girildiğinde başarılı bir şekilde giriş yapılmalıdır.")
@Epic("Kimlik Doğrulama")
@Feature("Login Fonksiyonu")
@Story("Başarılı Giriş Senaryosu")
@Step("Login butonuna tıkla")
@Attachment(value = "Ekran Görüntüsü", type = "image/png")
Bu anotasyonları test metodlarınıza ve yardımcı fonksiyonlarınıza ekleyerek raporlarınızda daha detaylı adımlar, açıklamalar ve hiyerarşiler oluşturabilirsiniz.