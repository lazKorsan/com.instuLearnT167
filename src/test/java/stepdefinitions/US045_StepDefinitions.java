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
import pages.FinancialPage;

public class US045_StepDefinitions {

    private static final Logger logger = LogManager.getLogger(US045_StepDefinitions.class);
    private FinancialPage financialPage = new FinancialPage(DriverManager.getDriver());

    // ========== LOGIN STEPS ==========

    @Given("Kullanici Financial icin anasayfaya gider")
    public void kullanici_financial_icin_anasayfaya_gider() {
        String url = ConfigReader.getProperty("url");
        DriverManager.getDriver().get(url);
        logger.info("Anasayfaya gidildi: " + url);
    }

    @When("Kullanici Financial senaryosu icin Login linkine tiklar")
    public void kullanici_financial_senaryosu_icin_login_linkine_tiklar() {
        financialPage.clickLoginLink();
    }

    @And("Kullanici Financial senaryosu icin email girer")
    public void kullanici_financial_senaryosu_icin_email_girer() {
        String email = ConfigReader.getProperty("tsMail");
        financialPage.enterEmail(email);
    }

    @And("Kullanici Financial senaryosu icin password girer")
    public void kullanici_financial_senaryosu_icin_password_girer() {
        String password = ConfigReader.getProperty("password");
        financialPage.enterPassword(password);
    }

    @And("Kullanici Financial senaryosu icin Login butonuna tiklar")
    public void kullanici_financial_senaryosu_icin_login_butonuna_tiklar() {
        financialPage.clickLoginButton();
    }

    // ========== TC01 - FINANCIAL SIDEBAR STEPS ==========

    @When("Kullanici Financial kategorisine tiklayip accordion'u acar")
    public void kullanici_financial_kategorisine_tiklayip_accordion_u_acar() {
        financialPage.clickFinancialCategory();
    }

    @When("Kullanici Financial Summary linkine tiklar")
    public void kullanici_financial_summary_linkine_tiklar() {
        financialPage.clickFinancialSummaryLink();
    }

    @Then("Financial Summary sayfasi acilmis olmali")
    public void financial_summary_sayfasi_acilmis_olmali() {
        String currentUrl = financialPage.getCurrentUrl();
        String expectedUrlPart = "/panel/financial/summary";
        logger.info("Beklenen URL parcasi: '" + expectedUrlPart + "', Gercek URL: '" + currentUrl + "'");

        Assert.assertTrue(
                "Financial Summary sayfasi acilmadi! Beklenen URL '" + expectedUrlPart +
                        "' icermiyor, mevcut URL: " + currentUrl,
                currentUrl.contains(expectedUrlPart));
        logger.info("Financial Summary sayfasi acilmasi dogrulandi.");
    }

    @When("Kullanici Payout linkine tiklar")
    public void kullanici_payout_linkine_tiklar() {
        financialPage.clickPayoutLink();
    }

    @Then("Payout sayfasi acilmis olmali")
    public void payout_sayfasi_acilmis_olmali() {
        String currentUrl = financialPage.getCurrentUrl();
        String expectedUrlPart = "/panel/financial/payout";
        logger.info("Beklenen URL parcasi: '" + expectedUrlPart + "', Gercek URL: '" + currentUrl + "'");

        Assert.assertTrue(
                "Payout sayfasi acilmadi! Beklenen URL '" + expectedUrlPart +
                        "' icermiyor, mevcut URL: " + currentUrl,
                currentUrl.contains(expectedUrlPart));
        logger.info("Payout sayfasi acilmasi dogrulandi.");
    }

    @When("Kullanici Charge Account linkine tiklar")
    public void kullanici_charge_account_linkine_tiklar() {
        financialPage.clickChargeAccountLink();
    }

    @Then("Charge Account sayfasi acilmis olmali")
    public void charge_account_sayfasi_acilmis_olmali() {
        String currentUrl = financialPage.getCurrentUrl();
        String expectedUrlPart = "/panel/financial/account";
        logger.info("Beklenen URL parcasi: '" + expectedUrlPart + "', Gercek URL: '" + currentUrl + "'");

        Assert.assertTrue(
                "Charge Account sayfasi acilmadi! Beklenen URL '" + expectedUrlPart +
                        "' icermiyor, mevcut URL: " + currentUrl,
                currentUrl.contains(expectedUrlPart));
        logger.info("Charge Account sayfasi acilmasi dogrulandi.");
    }

    @When("Kullanici Subscribe linkine tiklar")
    public void kullanici_subscribe_linkine_tiklar() {
        financialPage.clickSubscribeLink();
    }

    @Then("Subscribe sayfasi acilmis olmali")
    public void subscribe_sayfasi_acilmis_olmali() {
        String currentUrl = financialPage.getCurrentUrl();
        String expectedUrlPart = "/panel/financial/subscribes";
        logger.info("Beklenen URL parcasi: '" + expectedUrlPart + "', Gercek URL: '" + currentUrl + "'");

        Assert.assertTrue(
                "Subscribe sayfasi acilmadi! Beklenen URL '" + expectedUrlPart +
                        "' icermiyor, mevcut URL: " + currentUrl,
                currentUrl.contains(expectedUrlPart));
        logger.info("Subscribe sayfasi acilmasi dogrulandi.");
    }

    @And("Kullanici sayfada scroll down yapar ve bir saniye bekler")
    public void kullanici_sayfada_scroll_down_yapar_ve_bir_saniye_bekler() {
        financialPage.scrollDownPageAndWait();
    }

    // ========== TC02 - CHARGE ACCOUNT PAYMENT STEPS ==========

    @When("Kullanici Stripe odeme yontemini secer")
    public void kullanici_stripe_odeme_yontemini_secer() {
        financialPage.selectStripePaymentOption();
    }

    @And("Kullanici Amount alanina {string} dolar girer")
    public void kullanici_amount_alanina_dolar_girer(String amount) {
        financialPage.enterAmount(amount);
    }

    @And("Kullanici Charge Account Pay butonuna tiklar")
    public void kullanici_charge_account_pay_butonuna_tiklar() {
        financialPage.clickPayButton();
    }

    @And("Kullanici Stripe odeme formunu doldurur ve odemeyi tamamlar")
    public void kullanici_stripe_odeme_formunu_doldurur_ve_odemeyi_tamamlar() {
        // Stripe TEST karti - gercek para cekilmez
        String testCardNumber = "4242 4242 4242 4242";
        String testExpiry = "01/30";
        String testCvc = "321";
        String testCardholder = "Nihat Ozturk";

        financialPage.fillStripeFormSimple(
                testCardNumber, testExpiry, testCvc, testCardholder);
    }

    @Then("Charge Account odemesi basariyla tamamlanmis olmali")
    public void charge_account_odemesi_basariyla_tamamlanmis_olmali() {
        boolean success = financialPage.verifyPaymentSuccessMessage();

        Assert.assertTrue(
                "Charge Account odeme basari mesaji ('Your payment successfully done...') gorunmedi!",
                success);

        logger.info("TC02 BASARIYLA TAMAMLANDI! Charge Account odemesi gerceklesti.");
    }
}