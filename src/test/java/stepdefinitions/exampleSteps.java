package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ExamplePage;
import utils.BannerUtils;
import utils.ReusableMethods;

import java.time.Duration;
import java.util.Map;

public class exampleSteps {

    private static final Logger logger = LogManager.getLogger(exampleSteps.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);

    @Given("Kullanici ana sayfaya gider")
    public void kullanici_ana_sayfaya_gider() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");
    }

    @Given("Kullanici login page sayfasina gider")
    public void kullanici_login_page_sayfasina_gider() {
        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get(ConfigReader.getProperty("login_url"));
        logger.info("🌏 Kullanıcı login sayfasına gitti");
    }

    @Given("Kullanici email kutusuna gecerli mail adresi girer")
    public void kullanici_email_kutusuna_gecerli_mail_adresi_girer() {
        String email = ConfigReader.getProperty("mail");
        logger.debug("🐞 Email kutusuna: {} adresi giriliyor", email);
        examplePage.mailBox.sendKeys(email);
        logger.info("🔓 Email alanına: {} değeri başarıyla girildi", email);
    }

    @Given("Kullanici password kutusuna gecerli password girer")
    public void kullanici_password_kutusuna_gecerli_password_girer() {
        String password = ConfigReader.getProperty("password");
        logger.debug("🐞 Password kutusuna: {} değeri giriliyor", password);
        examplePage.passwordBox.sendKeys(password);
        logger.info("🔓 Password alanına: {} değeri başarıyla girildi", password);
    }

    @Given("Kullanici submitButton tiklar")
    public void kullanici_submit_button_tiklar() {
        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        examplePage.submitButton.click();
        logger.info("✅ Kullanıcı submit butonuna tıkladı");
    }
    @Given("Kullanici mail adresi {string} ve tanimli password ile  {string} giris yapar")
    @When("Kullanici mail adresi {string} ve tanimli password ile {string} giris yapar")
    public void kullaniciMailAdresiVeTanimliPasswordIleGirisYapar(String mail, String password) {
        logger.debug("🐞 Kullanıcı loginMethod çağrısı ile login oluyor");
        examplePage.loginMethod(mail, password);
        logger.info("✅ Kullanıcı loginMethod çağrısı ile login oldu");
    }

    @When("Kullanici login oldugunu dogrular")
    public void kullaniciLoginOldugunuDogrular() {

        // 1. Explicit Wait tanımlıyoruz (10 saniye boyunca URL değişene kadar pusuya yatar)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 2. URL'nin beklenen kelimeyi içermesini bekle (Kod burada sayfa yüklenene kadar durur)
        wait.until(ExpectedConditions.urlContains("panel"));

        // 3. Şimdi doğrulamayı yap (Artık URL kesin değişti, test patlamaz)
        Assertions.assertTrue(driver.getCurrentUrl().contains("panel"),
                "❌ HATA: URL 'panel' içermiyor! Mevcut URL: " + driver.getCurrentUrl());

        logger.info("✅ Therapist ana sayfasına başarıyla yönlendirildi.");


        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "TEST BASARİLİ",
                "Pozitive Login Test",
                "Dogru Bilgilerle Giris Yapıldıgı dogrulandi"

        );ReusableMethods.bekle(6);




    }

    @Given("Kullanici hatali mail ile giris yapilamadigini dogrular")
    public void kullanici_hatali_mail_ile_giris_yapilamadigini_dogrular() {

        // 1. Elementin XPath'ini tanımlayalım
        String errorXpath = "//*[@class='invalid-feedback']";

        // 2. Elementin görünür olmasını bekleyelim (Senin ClickUtils içindeki waitForVisibility mantığıyla)
        // Direkt driver üzerinden değil, bekleme ile almak senkronizasyon hatasını önler.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorXpath)));

        // 3. Element üzerindeki metni alalım
        String actualErrorMessage = errorElement.getText();
        String expectedErrorMessage = "The selected email is invalid.";

        // 4. ASSERTION: Mesaj doğru mu? (Negative testin kalbi burasıdır)
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                "❌ HATA: Beklenen hata mesajı görüntülenemedi veya metin yanlış!");

        // 5. Ekstra Kontrol: Element gerçekten görünür mü?
        Assertions.assertTrue(errorElement.isDisplayed(), "❌ HATA: Hata mesajı kutusu görünmüyor!");

        logger.info("✅ Negative Test Başarılı: Sistem hatalı maili reddetti ve doğru mesajı gösterdi.");

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "TEST BASARİLİ",
                "NegativeLoginTest",
                "Hatali Mail Adresi ile Giris Yapılamadıgı dogrulandi"

        );ReusableMethods.bekle(6);



    }

    @Given("Kullanici hatali password ile giris yapilamadigini dogrular")
    public void kullanici_hatali_password_ile_giris_yapilamadigini_dogrular() {

        // 1. Elementin XPath'ini tanımlayalım
        String errorXpath = "//*[@class='invalid-feedback']";


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorXpath)));

        // 3. Element üzerindeki metni alalım
        String actualErrorMessage = errorElement.getText();
        String expectedErrorMessage = "The password or username is incorrect.";

        // 4. ASSERTION: Mesaj doğru mu? (Negative testin kalbi burasıdır)
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                "❌ HATA: Beklenen hata mesajı görüntülenemedi veya metin yanlış!");

        // 5. Ekstra Kontrol: Element gerçekten görünür mü?
        Assertions.assertTrue(errorElement.isDisplayed(), "❌ HATA: Hata mesajı kutusu görünmüyor!");

        logger.info("✅ Negative Test Başarılı: Sistem hatalı password ile giriş yapıldı ve reddetti ve doğru mesajı gösterdi.");


        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "TEST BASARILI",
                "NegativeLoginTest",
                "Hatali Password ile Giris Yapılamadıgı dogrulandi"

        );ReusableMethods.bekle(6);







    }

    @When("Kullanici mesaj kutusuna su notu birakir:")
    public void kullaniciMesajKutusunaSuNotuBirakir(String message) {
        logger.debug("🐞 Kullanıcı mesaj kutusuna mesaj giriyor");
        logger.info("📝 Mesaj kutusuna bırakılan not: {}", message);
        // TODO: Mesaj kutusuna yazma işlemi eklenecek
        // examplePage.messageBox.sendKeys(message);
        logger.info("✅ Kullanıcı mesaj kutusuna mesaj girdi");
    }

    @When("Kullanici su bilgilerle kayit olur:")
    public void kullaniciSuBilgilerleKayitOlur(Map<String, String> data) throws InterruptedException {
        logger.debug("🐞 Kullanıcı kayıt oluyor");
        logger.info("📝 Kayıt bilgileri: {}", data);
        examplePage.loginMethod(ConfigReader.getProperty("mail"), ConfigReader.getProperty("password"));
        logger.info("✅ Kullanıcı kayıt oldu");
    }


    @Given("Kullanici mail adresi ile {string} ve tanimli password ile  {string} giris yapar")
    public void kullanici_mail_adresi_ile_ve_tanimli_password_ile_giris_yapar(String mail, String password) {

        logger.debug("🐞 Kullanıcı loginMethod çağrısı ile login oluyor");
        examplePage.loginMethod(mail, password);
        logger.info("✅ Kullanıcı loginMethod çağrısı ile login oldu");



    }


    @When("Pozitive Login Test Basliyor")
    public void pozitiveLoginTestBasliyor() {

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Pozitive Login Test Baliyor",
                "PozitiveLoginTest",
                "Dogru bilgilerle Giris yapildigi dogrulanir"

        );ReusableMethods.bekle(6);


    }

    @When("Negative Login Test Hatali Mail")
    public void negativeLoginTestHataliMail() {

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Hatali Mail Adresi ile Giris Testi Basliyor",
                "NegativeLoginTest",
                "The selected email is invalid."

        );ReusableMethods.bekle(6);
    }

    @When("Negative Login Test Hatali Password")
    public void negativeLoginTestHataliPassword() {

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Hatali Password ile Giris Testi Basliyor",
                "NegativeLoginTest",
                "The password or username is incorrect."

        );ReusableMethods.bekle(6);



    }
}