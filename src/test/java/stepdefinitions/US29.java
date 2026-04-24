package stepdefinitions;

import com.github.dockerjava.api.model.Driver;
import config.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.DashboardPage;
import pages.ExamplePage;
import pages.HomePage;
import pages.MarketingPage;
import utils.ReusableMethods;

import java.util.Random;

public class US29 {

    private static final Logger logger = LogManager.getLogger(US29.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    HomePage homePage = new HomePage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    Actions actions = new Actions(driver);
    MarketingPage marketingPage = new MarketingPage(driver);
    Random random = new Random();




    @Given("kullanıcı sisteme kayıtlı bir Instructor olarak giriş yapmıştır")
    public void kullanıcı_sisteme_kayıtlı_bir_ınstructor_olarak_giriş_yapmıştır() {
        driver.get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);
        homePage.homePageLoginLink.click();
        ReusableMethods.bekle(1);
        examplePage.loginMethod("burak.yilmaz.teacher@instulearn.com","Learn.123!");
        ReusableMethods.bekle(1);
    }

    @And("kullanıcı dashboard sayfasındadır")
    public void kullanıcı_dashboard_sayfasındadır() {
        Assertions.assertTrue(dashboardPage.dashboardTitle.isDisplayed());
    }





    @Given("sidebar menüde Marketing başlığı görünür olmalıdır")
    public void sidebarMenüdeMarketingBaşlığıGörünürOlmalıdır() {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMarketingLink);
        Assertions.assertTrue(dashboardPage.sidebarMarketingLink.isDisplayed());
    }

    @When("kullanıcı Marketing linkine tıklar")
    public void kullanıcıMarketingLinkineTıklar() {
        dashboardPage.sidebarMarketingLink.click();
        ReusableMethods.bekle(1);
    }

    @Then("Marketing başlığı altında Discounts linki görünür ve aktif olmalıdır")
    public void marketing_Başlığı_Altında_Discounts_Linki_Görünür_Ve_Aktif_Olmalıdır() {
        Assertions.assertTrue(dashboardPage.discountsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(dashboardPage.discountsLinkByMarketing.isEnabled());
    }

    @And("Marketing başlığı altında Promotions linki görünür ve aktif olmalıdır")
    public void marketingBaşlığıAltındaPromotionsLinkiGörünürVeAktifOlmalıdır() {
        Assertions.assertTrue(dashboardPage.promotionsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(dashboardPage.promotionsLinkByMarketing.isEnabled());
    }






    @Given("kullanıcı Discounts linkine tıklar")
    public void kullanıcıDiscountsLinkineTıklar() {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMarketingLink);
        dashboardPage.sidebarMarketingLink.click();
        dashboardPage.discountsLinkByMarketing.click();
    }

    @When("Discounts sayfası açılmalıdır")
    public void discounts_sayfası_açılmalıdır() {
        Assertions.assertTrue(marketingPage.discountsTitle.isDisplayed());
    }

    @Then("Create butonu görünür olmalıdır")
    public void createButonuGörünürOlmalıdır() {
        Assertions.assertTrue(marketingPage.createButton.isDisplayed());
    }

    String titleText = RandomStringUtils.randomAlphabetic(5);
    @And("kullanıcı yeni indirim bilgilerini doldurur")
    public void kullanıcı_yeni_indirim_bilgilerini_doldurur() {
        marketingPage.titleBox.sendKeys(titleText);
        ReusableMethods.bekle(1);
        Select select = new Select(marketingPage.courseBox);
        select.selectByIndex(1);
        ReusableMethods.bekle(1);
        marketingPage.amountBox.sendKeys("10");
        ReusableMethods.bekle(1);
        marketingPage.fromBox.sendKeys("2026.04.30");
        ReusableMethods.bekle(1);
        marketingPage.toBox.sendKeys("2026.05.30");
        ReusableMethods.bekle(1);
    }

    @Then("Create butonuna tıklar")
    public void createButonunaTıklar() {
        marketingPage.createButton.click();
       // marketingPage.createButton.click();
        ReusableMethods.bekle(10);
        // fixme ahmet
    }

    @And("oluşturulan indirim listede görünmelidir")
    public void oluşturulan_indirim_listede_görünmelidir() {
        Assertions.assertTrue(marketingPage.discountsNameList.get(0).getText().contains(titleText));
    }







    @When("kullanıcı eksik veya geçersiz indirim bilgileri girer")
    public void kullanıcı_eksik_veya_geçersiz_indirim_bilgileri_girer() {
        marketingPage.titleBox.sendKeys(titleText);
        ReusableMethods.bekle(1);
        Select select = new Select(marketingPage.courseBox);
        select.selectByIndex(1);
        ReusableMethods.bekle(1);
        marketingPage.amountBox.sendKeys("110");
        ReusableMethods.bekle(1);
        marketingPage.fromBox.sendKeys("2025.04.30");
        ReusableMethods.bekle(1);
        marketingPage.toBox.sendKeys("2025.05.30");
        ReusableMethods.bekle(1);

    }

    @And("indirim oluşturulmamalıdır")
    public void indirim_oluşturulmamalıdır() {
        Assertions.assertFalse(marketingPage.discountsNameList.get(0).getText().contains(titleText),
                "Hatalı discounts bilgileri testi/negatif test");

    }





    @Given("kullanıcı Promotions linkine tıklar")
    public void kullanıcıPromotionsLinkineTıklar() {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMarketingLink);
        dashboardPage.sidebarMarketingLink.click();
        marketingPage.promotionsLink.click();
    }

    @When("Promotions sayfası açılmalıdır")
    public void promotions_sayfası_açılmalıdır() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("promotions"));
    }

    @Then("mevcut promosyon planları listede görünür olmalıdır")
    public void mevcut_promosyon_planları_listede_görünür_olmalıdır() {
        Assertions.assertFalse(marketingPage.promotionsList.isEmpty());
    }

    @And("her plan seçilebilir durumda olmalıdır")
    public void her_plan_seçilebilir_durumda_olmalıdır() {
        for (int i = 0; i < marketingPage.promotionsList.size(); i++) {
            Assertions.assertTrue(marketingPage.promotionsList.get(i).isEnabled());
        }
    }







    int randomIndex;
    @When("kullanıcı bir promosyon planı seçer")
    public void kullanıcı_bir_promosyon_planı_seçer() {
        randomIndex =random.nextInt(marketingPage.promotionsList.size());
        marketingPage.promotionsList.get(randomIndex).click();
    }

    @And("Purchase butonuna tıklar")
    public void purchaseButonunaTıklar() {
        marketingPage.promotionsPurchaseButtonList.get(randomIndex).click();
    }

    @When("Kurs bilgisini seçer")
    public void kursBilgisiniSeçer() {
        Select select = new Select(marketingPage.selectCourseInPromotions);
        select.selectByIndex(1);
    }

    @And("Pay butonuna tıklar")
    public void payButonunaTıklar() {
        marketingPage.promotionsPay.click();
    }

    @When("Ödeme planını seçer")
    public void ödemePlanınıSeçer() {
        marketingPage.stripeÖdemeYöntemi.click();
    }

    @And("Ödeme işlemini başlatır")
    public void ödemeIşleminiBaşlatır() {
        ReusableMethods.bekle(1);
        marketingPage.paymentSubmitButton.click();
    }

    @When("Ödeme bilgilerini doldurur")
    public void ödemeBilgileriniDoldurur() {

    }

    @And("Öde butonuna tıklar")
    public void ödeButonunaTıklar() {

    }

    @And("kullanıcı seçilen plana başarıyla kayıt olmalıdır")
    public void kullanıcı_seçilen_plana_başarıyla_kayıt_olmalıdır() {

    }
    @Then("onay mesajı ekranda görüntülenmelidir")
    public void onay_mesajı_ekranda_görüntülenmelidir() {

    }



}
