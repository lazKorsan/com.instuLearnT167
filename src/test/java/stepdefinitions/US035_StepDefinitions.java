package stepdefinitions;

import config.ConfigReader;
import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import pages.US035;
import utils.ReusableMethods;

public class US035_StepDefinitions {

    private static final Logger logger = LogManager.getLogger(US035_StepDefinitions.class);
    private US035 us035Page = new US035(DriverManager.getDriver());

    @Given("Kullanici InstuLearn anasayfasina gider")
    public void kullanici_instulearn_anasayfasina_gider() {
        String url = ConfigReader.getProperty("url");
        DriverManager.getDriver().get(url);
        logger.info("Anasayfaya gidildi: " + url);
    }

    @When("Kullanici Login linkine tiklar")
    public void kullanici_login_linkine_tiklar() {
        us035Page.clickLoginLink();
    }

    @And("Kullanici email alanina gecerli email girer")
    public void kullanici_email_alanina_gecerli_email_girer() {
        String email = ConfigReader.getProperty("tsMail");
        us035Page.enterEmail(email);
    }

    @And("Kullanici password alanina gecerli password girer")
    public void kullanici_password_alanina_gecerli_password_girer() {
        String password = ConfigReader.getProperty("password");
        us035Page.enterPassword(password);
    }

    @And("Kullanici email alanina {string} girer")
    public void kullanici_email_alanina_girer(String email) {
        us035Page.enterEmail(email);
    }

    @And("Kullanici password alanina {string} girer")
    public void kullanici_password_alanina_girer(String password) {
        us035Page.enterPassword(password);
    }

    @And("Kullanici Login butonuna tiklar")
    public void kullanici_login_butonuna_tiklar() {
        us035Page.clickLoginButton();
    }

    @Then("Kullanici basariyla giris yapmis olmali")
    public void kullanici_basariyla_giris_yapmis_olmali() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        logger.info("Giris sonrasi URL: " + currentUrl);
        // Login basarili olunca /login URL'inden cikmali
        Assert.assertFalse("Kullanici hala login sayfasinda!",
                currentUrl.contains("/login"));
    }

    @Then("Kullanici giris yapamamis olmali")
    public void kullanici_giris_yapamamis_olmali() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        logger.info("Basarisiz giris sonrasi URL: " + currentUrl);
        // Login basarisiz olunca hala login sayfasinda kalmali
        Assert.assertTrue("Kullanici login sayfasindan cikmis, bu beklenmiyordu!",
                currentUrl.contains("/login"));
    }
}
