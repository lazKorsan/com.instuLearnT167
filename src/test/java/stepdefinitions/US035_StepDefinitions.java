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
import org.openqa.selenium.support.ui.Select;
import pages.US035;

public class US035_StepDefinitions {

    private static final Logger logger = LogManager.getLogger(US035_StepDefinitions.class);
    private US035 us035Page = new US035(DriverManager.getDriver());

    // ========== LOGIN STEPS ==========

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
        Assert.assertFalse("Kullanici hala login sayfasinda!",
                currentUrl.contains("/login"));
    }

    @Then("Kullanici giris yapamamis olmali")
    public void kullanici_giris_yapamamis_olmali() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        logger.info("Basarisiz giris sonrasi URL: " + currentUrl);
        Assert.assertTrue("Kullanici login sayfasindan cikmis, bu beklenmiyordu!",
                currentUrl.contains("/login"));
    }

    // ========== STORE / PRODUCTS STEPS (TC03) ==========

    @And("Kullanici Store linkine tiklar")
    public void kullanici_store_linkine_tiklar() {
        us035Page.clickStoreLink();
    }

    @And("Kullanici sayfayi asagi kaydirir {int} saniye bekler ve yukari kaydirir")
    public void kullanici_sayfayi_asagi_kaydirir_bekler_yukari_kaydirir(int saniye) {
        us035Page.scrollDownWaitAndScrollUp(saniye);
    }

    @Then("Search textBox gorunur ve aktif olmali")
    public void search_textbox_gorunur_ve_aktif_olmali() {
        boolean isDisplayed = us035Page.searchBox.isDisplayed();
        boolean isEnabled = us035Page.searchBox.isEnabled();
        logger.info("Search textBox - isDisplayed: " + isDisplayed + ", isEnabled: " + isEnabled);

        Assert.assertTrue("Search textBox gorunur degil!", isDisplayed);
        Assert.assertTrue("Search textBox aktif degil!", isEnabled);
    }

    @And("Search button gorunur ve aktif olmali")
    public void search_button_gorunur_ve_aktif_olmali() {
        boolean isDisplayed = us035Page.searchButton.isDisplayed();
        boolean isEnabled = us035Page.searchButton.isEnabled();
        logger.info("Search button - isDisplayed: " + isDisplayed + ", isEnabled: " + isEnabled);

        Assert.assertTrue("Search button gorunur degil!", isDisplayed);
        Assert.assertTrue("Search button aktif degil!", isEnabled);
    }

    // ========== FILTER STEPS (TC04) ==========

    // ---------- FREE ----------

    @When("Kullanici Free toggle'ini aktif eder")
    public void kullanici_free_toggle_ini_aktif_eder() {
        us035Page.clickFreeToggle();
    }

    @Then("Free filtresi aktif olmali")
    public void free_filtresi_aktif_olmali() {
        boolean isSelected = us035Page.freeCheckbox.isSelected();
        logger.info("Free filtresi isSelected: " + isSelected);
        Assert.assertTrue("Free filtresi aktif degil!", isSelected);
    }

    @When("Kullanici Free toggle'ini deactive eder")
    public void kullanici_free_toggle_ini_deactive_eder() {
        us035Page.deactivateFreeToggle();
    }

    @Then("Free filtresi deactive olmali")
    public void free_filtresi_deactive_olmali() {
        boolean isSelected = us035Page.freeCheckbox.isSelected();
        logger.info("Free filtresi isSelected: " + isSelected);
        Assert.assertFalse("Free filtresi hala aktif!", isSelected);
    }

    // ---------- FREE SHIPPING ----------

    @When("Kullanici Free Shipping toggle'ini aktif eder")
    public void kullanici_free_shipping_toggle_ini_aktif_eder() {
        us035Page.clickFreeShippingToggle();
    }

    @Then("Free Shipping filtresi aktif olmali")
    public void free_shipping_filtresi_aktif_olmali() {
        boolean isSelected = us035Page.freeShippingCheckbox.isSelected();
        logger.info("Free Shipping filtresi isSelected: " + isSelected);
        Assert.assertTrue("Free Shipping filtresi aktif degil!", isSelected);
    }

    @When("Kullanici Free Shipping toggle'ini deactive eder")
    public void kullanici_free_shipping_toggle_ini_deactive_eder() {
        us035Page.deactivateFreeShippingToggle();
    }

    @Then("Free Shipping filtresi deactive olmali")
    public void free_shipping_filtresi_deactive_olmali() {
        boolean isSelected = us035Page.freeShippingCheckbox.isSelected();
        logger.info("Free Shipping filtresi isSelected: " + isSelected);
        Assert.assertFalse("Free Shipping filtresi hala aktif!", isSelected);
    }

    // ---------- DISCOUNT ----------

    @When("Kullanici Discount toggle'ini aktif eder")
    public void kullanici_discount_toggle_ini_aktif_eder() {
        us035Page.clickDiscountToggle();
    }

    @Then("Discount filtresi aktif olmali")
    public void discount_filtresi_aktif_olmali() {
        boolean isSelected = us035Page.discountCheckbox.isSelected();
        logger.info("Discount filtresi isSelected: " + isSelected);
        Assert.assertTrue("Discount filtresi aktif degil!", isSelected);
    }

    @When("Kullanici Discount toggle'ini deactive eder")
    public void kullanici_discount_toggle_ini_deactive_eder() {
        us035Page.deactivateDiscountToggle();
    }

    @Then("Discount filtresi deactive olmali")
    public void discount_filtresi_deactive_olmali() {
        boolean isSelected = us035Page.discountCheckbox.isSelected();
        logger.info("Discount filtresi isSelected: " + isSelected);
        Assert.assertFalse("Discount filtresi hala aktif!", isSelected);
    }

    // ---------- SORT BY ----------

    @When("Kullanici Sort by dropdown'ini acar")
    public void kullanici_sort_by_dropdown_ini_acar() {
        us035Page.openSortDropdown();
    }

    @When("Kullanici Sort by dropdown'dan {string} secer")
    public void kullanici_sort_by_dropdown_dan_secer(String value) {
        us035Page.selectSortByValue(value);
    }

    /**
     * "All" option'u value'su bos oldugu icin visible text ile secmek gerekli.
     * Bu step, value yerine gorunur metin ile secim yapar.
     */
    @When("Kullanici Sort by dropdown'dan {string} secer (metin ile)")
    public void kullanici_sort_by_dropdown_dan_metin_ile_secer(String text) {
        us035Page.selectSortByVisibleText(text);
    }

    @Then("Sort by dropdown secimi {string} olmali")
    public void sort_by_dropdown_secimi_olmali(String expectedValue) {
        Select select = new Select(us035Page.sortDropdown);
        String actualValue = select.getFirstSelectedOption().getAttribute("value");
        logger.info("Sort by secilmis value: '" + actualValue + "'");

        Assert.assertEquals("Sort by secimi beklenen deger ile eslesmedi!",
                expectedValue, actualValue);
    }

    /**
     * Sort by secimi visible text ile dogrular.
     * "All" gibi value'su bos olan option'lar icin kullanilir.
     */
    @Then("Sort by dropdown secimi metin olarak {string} olmali")
    public void sort_by_dropdown_secimi_metin_olarak_olmali(String expectedText) {
        Select select = new Select(us035Page.sortDropdown);
        String actualText = select.getFirstSelectedOption().getText();
        logger.info("Sort by secilmis text: '" + actualText + "'");

        Assert.assertEquals("Sort by secimi beklenen metin ile eslesmedi!",
                expectedText, actualText);
    }

    @And("Kullanici Sort by dropdown icinde scroll yapar")
    public void kullanici_sort_by_dropdown_icinde_scroll_yapar() {
        us035Page.scrollInsideSortDropdown();
    }
}