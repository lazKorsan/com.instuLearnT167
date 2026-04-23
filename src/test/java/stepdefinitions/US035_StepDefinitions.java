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

    // ========== STORE / PRODUCTS STEPS ==========

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

    // ========== LEFT FILTER STEPS ==========

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

    @When("Kullanici Sort by dropdown'ini acar")
    public void kullanici_sort_by_dropdown_ini_acar() {
        us035Page.openSortDropdown();
    }

    @When("Kullanici Sort by dropdown'dan {string} secer")
    public void kullanici_sort_by_dropdown_dan_secer(String value) {
        us035Page.selectSortByValue(value);
    }

    @Then("Sort by dropdown secimi {string} olmali")
    public void sort_by_dropdown_secimi_olmali(String expectedValue) {
        Select select = new Select(us035Page.sortDropdown);
        String actualValue = select.getFirstSelectedOption().getAttribute("value");
        logger.info("Sort by secilmis value: '" + actualValue + "'");

        Assert.assertEquals("Sort by secimi beklenen deger ile eslesmedi!",
                expectedValue, actualValue);
    }

    // ========== RIGHT FILTER STEPS ==========

    @When("Kullanici Virtual filter'ini aktif eder")
    public void kullanici_virtual_filter_ini_aktif_eder() {
        us035Page.clickVirtualFilter();
    }

    @Then("Virtual filter aktif olmali")
    public void virtual_filter_aktif_olmali() {
        boolean isSelected = us035Page.virtualCheckbox.isSelected();
        logger.info("Virtual filter isSelected: " + isSelected);
        Assert.assertTrue("Virtual filter aktif degil!", isSelected);
    }

    @When("Kullanici Physical filter'ini aktif eder")
    public void kullanici_physical_filter_ini_aktif_eder() {
        us035Page.clickPhysicalFilter();
    }

    @Then("Physical filter aktif olmali")
    public void physical_filter_aktif_olmali() {
        boolean isSelected = us035Page.physicalCheckbox.isSelected();
        logger.info("Physical filter isSelected: " + isSelected);
        Assert.assertTrue("Physical filter aktif degil!", isSelected);
    }

    @And("Kullanici Type bolumundeki Filter items butonuna tiklar")
    public void kullanici_type_bolumundeki_filter_items_butonuna_tiklar() {
        us035Page.clickTypeFilterItemsButton();
    }

    @When("Kullanici Only Available Products filter'ini aktif eder")
    public void kullanici_only_available_products_filter_ini_aktif_eder() {
        us035Page.clickOnlyAvailableProductsFilter();
    }

    @Then("Only Available Products filter aktif olmali")
    public void only_available_products_filter_aktif_olmali() {
        boolean isSelected = us035Page.onlyAvailableProductsCheckbox.isSelected();
        logger.info("Only Available Products filter isSelected: " + isSelected);
        Assert.assertTrue("Only Available Products filter aktif degil!", isSelected);
    }

    @And("Kullanici Options bolumundeki Filter items butonuna tiklar")
    public void kullanici_options_bolumundeki_filter_items_butonuna_tiklar() {
        us035Page.clickOptionsFilterItemsButton();
    }

    // ========== CATEGORIES STEP ==========

    @When("Kullanici Science Tools kategorisine tiklar")
    public void kullanici_science_tools_kategorisine_tiklar() {
        us035Page.clickScienceToolsCategory();
    }

    // ========== TC05 - HOVER + ADD TO CART STEPS ==========

    @When("Kullanici Updated Product Title urun resmine hover yapar")
    public void kullanici_updated_product_title_urun_resmine_hover_yapar() {
        us035Page.hoverProductImg();
    }

    @And("Kullanici stars card alanina hover yapar")
    public void kullanici_stars_card_alanina_hover_yapar() {
        us035Page.hoverStarsCard();
    }

    @And("Kullanici Add to Cart ikonuna hover yapar")
    public void kullanici_add_to_cart_ikonuna_hover_yapar() {
        us035Page.hoverAddToCartIcon();
    }

    @When("Kullanici Add to Cart ikonuna tiklar")
    public void kullanici_add_to_cart_ikonuna_tiklar() {
        us035Page.clickAddToCartIcon();
    }

    @Then("Added to cart toast mesaji beklenen metni icermeli")
    public void added_to_cart_toast_mesaji_beklenen_metni_icermeli() {
        String actualText = us035Page.getToastMessageText();

        String expectedFullText =
                "Added to cart!\n" +
                        "You can continue shopping or go to cart to finalize your order.";

        String actualCleaned = actualText.replace("×", "").trim();
        logger.info("Kapatma butonu (×) temizlendi.");

        String actualNormalized = actualCleaned.replaceAll("\\s+", " ").trim();
        String expectedNormalized = expectedFullText.replaceAll("\\s+", " ").trim();

        logger.info("Beklenen metin (normalized): '" + expectedNormalized + "'");
        logger.info("Gercek metin   (normalized): '" + actualNormalized + "'");

        Assert.assertEquals("Toast mesaji beklenen metinle eslesmedi!",
                expectedNormalized, actualNormalized);

        logger.info("Toast mesaji dogrulandi! Urun basariyla sepete eklendi.");
    }

    // ========== TC06 - CHECKOUT & PAYMENT STEPS ==========

    @When("Kullanici navbar'daki sepet ikonuna tiklar")
    public void kullanici_navbar_daki_sepet_ikonuna_tiklar() {
        us035Page.clickShoppingCartLink();
    }

    @And("Kullanici Go to cart butonuna tiklar")
    public void kullanici_go_to_cart_butonuna_tiklar() {
        us035Page.clickGoToCartButton();
    }

    @And("Kullanici Checkout butonuna scroll edip tiklar")
    public void kullanici_checkout_butonuna_scroll_edip_tiklar() {
        us035Page.scrollToAndClickCheckout();
    }

    @And("Kullanici Pay with Stripe'i secer ve Start Payment butonuna tiklar")
    public void kullanici_pay_with_stripe_i_secer_ve_start_payment_butonuna_tiklar() {
        us035Page.selectStripeAndStartPayment();
    }

    @And("Kullanici Stripe odeme formunu doldurur ve Pay butonuna tiklar")
    public void kullanici_stripe_odeme_formunu_doldurur_ve_pay_butonuna_tiklar() {
        // Stripe'in resmi TEST karti - gercek para cekilmez
        String testCardNumber = "4242 4242 4242 4242";
        String testExpiry = "01/30";
        String testCvc = "321";
        String testCardholder = "Nihat Ozturk";

        us035Page.fillStripePaymentFormAndPay(testCardNumber, testExpiry,
                testCvc, testCardholder);
    }

    @Then("Odeme basariyla tamamlanmis olmali ve basari mesaji gorunmeli")
    public void odeme_basariyla_tamamlanmis_olmali_ve_basari_mesaji_gorunmeli() {
        boolean success = us035Page.verifyPaymentSuccessMessage();

        Assert.assertTrue(
                "Odeme basari mesaji ('Your payment successfully done...') gorunmedi! " +
                        "Odeme basarisiz olmus olabilir veya mesaj locator'i farkli olabilir.",
                success);

        logger.info("TC06 BASARIYLA TAMAMLANDI! Odeme gerceklesti, basari mesaji dogrulandi.");
    }
}