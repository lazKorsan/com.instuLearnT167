package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.ExamplePage;
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
}