package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.*;
import utils.ReusableMethods;

import java.util.Random;

public class US29 {

    private static final Logger logger = LogManager.getLogger(US29.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    HomePage homePage = new HomePage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    InstructorDashboardPage instructorDashboardPage = new InstructorDashboardPage(driver);
    Actions actions = new Actions(driver);
    MarketingPage marketingPage = new MarketingPage(driver);
    Random random = new Random();
    US035 us035=new US035(driver);




    @Given("kullanıcı sisteme kayıtlı bir Instructor olarak giriş yapmıştır")
    public void kullanıcı_sisteme_kayıtlı_bir_ınstructor_olarak_giriş_yapmıştır() {
        driver.get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);
        homePage.homePageLoginLink.click();
        examplePage.loginMethod("burak.yilmaz.teacher@instulearn.com","Learn.123!");
    }

    @And("kullanıcı dashboard sayfasındadır")
    public void kullanıcı_dashboard_sayfasındadır() {
        Assertions.assertTrue(instructorDashboardPage.dashboardPageTitle.isDisplayed());
    }





    @Given("sidebar menüde Marketing başlığı görünür olmalıdır")
    public void sidebarMenüdeMarketingBaşlığıGörünürOlmalıdır() {
        actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
        ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMarketingLink);
        Assertions.assertTrue(instructorDashboardPage.sidebarMarketingLink.isDisplayed());
    }

    @When("kullanıcı Marketing linkine tıklar")
    public void kullanıcıMarketingLinkineTıklar() {
        instructorDashboardPage.sidebarMarketingLink.click();
        ReusableMethods.bekle(1);
    }

    @Then("Marketing başlığı altında Discounts linki görünür ve aktif olmalıdır")
    public void marketing_Başlığı_Altında_Discounts_Linki_Görünür_Ve_Aktif_Olmalıdır() {
        Assertions.assertTrue(instructorDashboardPage.discountsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(instructorDashboardPage.discountsLinkByMarketing.isEnabled());
    }

    @And("Marketing başlığı altında Promotions linki görünür ve aktif olmalıdır")
    public void marketingBaşlığıAltındaPromotionsLinkiGörünürVeAktifOlmalıdır() {
        Assertions.assertTrue(instructorDashboardPage.promotionsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(instructorDashboardPage.promotionsLinkByMarketing.isEnabled());
    }






    @Given("kullanıcı Discounts linkine tıklar")
    public void kullanıcıDiscountsLinkineTıklar() {
        actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
        ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMarketingLink);
        instructorDashboardPage.sidebarMarketingLink.click();
        instructorDashboardPage.discountsLinkByMarketing.click();
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
        marketingPage.fromBox.click();
        marketingPage.fromBox.sendKeys("2026.04.30");
        marketingPage.fromBoxApply.click();
        ReusableMethods.bekle(1);
        marketingPage.toBox.click();
        marketingPage.toBox.sendKeys("2026.05.30");
        marketingPage.toBoxApply.click();
        ReusableMethods.bekle(1);
    }

    @Then("Create butonuna tıklar")
    public void createButonunaTıklar() {
        marketingPage.createButton.click();
    }

    @And("oluşturulan indirim listede görünmelidir")
    public void oluşturulan_indirim_listede_görünmelidir() {
        ReusableMethods.bekle(3);
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
        actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
        ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMarketingLink);
        instructorDashboardPage.sidebarMarketingLink.click();
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
        randomIndex =random.nextInt(marketingPage.promotionsPurchaseButtonList.size());
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
        ReusableMethods.waitForVisibility(driver,us035.stripePayButton,10);
    }

    @When("Ödeme bilgilerini doldurur")
    public void ödemeBilgileriniDoldurur() {
        us035.stripeEmailBox.sendKeys("burak.yilmaz.teacher@instulearn.com");
        us035.cardNumberBox.sendKeys("4242424242424242");
        us035.cardExpiryBox.sendKeys("0130");
        us035.cardCvcBox.sendKeys("321");
        us035.billingNameBox.sendKeys("NİHAT ÖZTÜRK");
    }

    @And("Öde butonuna tıklar")
    public void ödeButonunaTıklar() {
        ReusableMethods.scrollToElement(driver,marketingPage.creditCardPayButton);
        ReusableMethods.bekle(1);
        marketingPage.creditCardPayButton.click();
        ReusableMethods.bekle(1);

    }

    @Then("onay mesajı ekranda görüntülenmelidir")
    public void onay_mesajı_ekranda_görüntülenmelidir() {
        ReusableMethods.waitForVisibility(driver,marketingPage.succesMessage,15);
        Assertions.assertTrue(marketingPage.succesMessage.isDisplayed());
    }



}
