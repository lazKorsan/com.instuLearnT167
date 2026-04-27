package stepdefinitions;

import config.ConfigReader;
import drivers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * US033 / US050 testleri icin OZEL login adimlari.
 *
 * Bu class, ekip ortak dosyalari olan exampleSteps, ExamplePage, Hooks dosyalarina
 * DOKUNMADAN renderer timeout problemini cozer. Kendi step text'ini kullanir
 * ki ekip step'leriyle catismaz.
 *
 * Kullanim ornegi (feature dosyasinda):
 *   Given Kullanici "qa.instulearn.com" sitesine guvenli sekilde gider
 *   When  Kullanici "huseyin.ozger@instulearn.com" maili ve "Learn.123!" sifresi ile guvenli login olur
 */
public class US033_LoginSteps {

    private static final Logger logger = LogManager.getLogger(US033_LoginSteps.class);

    // Driver'i her step'te guncel olarak alir (LAZY)
    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    // Renderer timeout'a karsi guvenli sayfa yukleme - window.stop()
    private void safeStopPageLoad() {
        try {
            ((JavascriptExecutor) driver()).executeScript("window.stop();");
        } catch (Exception ignored) { }
    }

    // Timeout korumali get() metodu - sayfa yuklenmese bile devam eder
    private void safeGet(String url) {
        try {
            driver().get(url);
        } catch (TimeoutException te) {
            logger.warn("⏱️ Page load timeout - sayfa yuklemesi durduruldu: {}", te.getMessage());
            safeStopPageLoad();
        }
    }

    // Element bekleme yardimcisi
    private WebElement waitForVisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForClickable(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ========================================================================
    // GUVENLI SAYFA YUKLEME ADIMLARI
    // ========================================================================

    /**
     * Login sayfasina renderer timeout korumali gider.
     * exampleSteps'teki "Kullanici login page sayfasina gider"e ALTERNATIF.
     */
    @Given("Kullanici login sayfasina guvenli sekilde gider")
    public void kullanici_login_sayfasina_guvenli_gider() {
        String url = ConfigReader.getProperty("login_url");
        if (url == null || url.isEmpty()) {
            url = "https://qa.instulearn.com/login";
        }
        logger.info("🌏 Login sayfasina guvenli gidiliyor: {}", url);
        safeGet(url);
        safeStopPageLoad();
    }

    /**
     * Ana sayfaya renderer timeout korumali gider.
     */
    @Given("Kullanici ana sayfaya guvenli sekilde gider")
    public void kullanici_ana_sayfaya_guvenli_gider() {
        String url = ConfigReader.getProperty("url");
        if (url == null || url.isEmpty()) {
            url = "https://qa.instulearn.com/";
        }
        logger.info("🌏 Ana sayfaya guvenli gidiliyor: {}", url);
        safeGet(url);
        safeStopPageLoad();
    }

    /**
     * Verilen URL'e renderer timeout korumali gider.
     */
    @Given("Kullanici {string} adresine guvenli sekilde gider")
    public void kullanici_url_adresine_guvenli_gider(String url) {
        logger.info("🌏 Adresa guvenli gidiliyor: {}", url);
        safeGet(url);
        safeStopPageLoad();
    }

    // ========================================================================
    // GUVENLI LOGIN ADIMI
    // ========================================================================

    /**
     * Mail ve sifre ile guvenli login - exampleSteps'teki login adimina ALTERNATIF.
     * Renderer timeout problemine karsi korumalidir.
     */
    @When("Kullanici {string} maili ve {string} sifresi ile guvenli login olur")
    public void kullanici_guvenli_login_olur(String mail, String sifre) {
        logger.info("🔐 Guvenli login basliyor - mail: {}", mail);

        try {
            // Email kutusu
            WebElement emailBox = waitForVisible(By.id("email"), 15);
            emailBox.clear();
            emailBox.sendKeys(mail);

            // Password kutusu
            WebElement passwordBox = waitForVisible(By.id("password"), 10);
            passwordBox.clear();
            passwordBox.sendKeys(sifre);

            // Submit butonu
            try {
                WebElement submitBtn = waitForClickable(
                        By.xpath("//button[@class='btn btn-primary btn-block mt-20'] | //button[@type='submit']"),
                        10);
                submitBtn.click();
            } catch (Exception e) {
                // Click takilirsa Enter'a bas
                logger.warn("Submit click hatasi, Enter ile deneniyor: {}", e.getMessage());
                passwordBox.sendKeys(Keys.ENTER);
            }

            // Login sonrasi sayfa yuklenmesi yavas olabilir, korumali bekle
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            safeStopPageLoad();

            logger.info("✅ Guvenli login tamamlandi");

        } catch (Exception e) {
            logger.error("❌ Login sirasinda hata: {}", e.getMessage());
            safeStopPageLoad();
            throw new RuntimeException("Login basarisiz: " + e.getMessage(), e);
        }
    }

    /**
     * Hesap tipine gore config.properties'den okuyarak guvenli login.
     * Desteklenen tipler: organization, instructor, student
     */
    @When("Kullanici {string} hesabi ile guvenli login olur")
    public void kullanici_hesap_tipi_ile_guvenli_login(String hesapTipi) {
        String mail;
        String sifre = ConfigReader.getProperty("GecerliPassword");

        switch (hesapTipi.toLowerCase()) {
            case "organization":
                mail = ConfigReader.getProperty("organizationGecerliMail");
                break;
            case "instructor":
                mail = ConfigReader.getProperty("instructorGecerliMail");
                break;
            case "student":
                mail = ConfigReader.getProperty("studentGecerliMail");
                if (mail == null || mail.isEmpty()) {
                    // config.properties'deki typo'yu telafi et
                    mail = ConfigReader.getProperty("studentGecerliPassword");
                }
                break;
            default:
                throw new IllegalArgumentException("Desteklenmeyen hesap tipi: " + hesapTipi);
        }

        if (mail == null || mail.isEmpty()) {
            throw new IllegalStateException(hesapTipi + " icin mail config.properties'de bulunamadi");
        }

        kullanici_guvenli_login_olur(mail, sifre);
    }

    // ========================================================================
    // GUVENLI BEKLEME
    // ========================================================================

    /**
     * Senkronizasyon icin guvenli bekleme.
     */
    @Given("Kullanici guvenli sekilde {int} saniye bekler")
    public void kullanici_guvenli_saniye_bekler(int saniye) {
        try {
            Thread.sleep(saniye * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
