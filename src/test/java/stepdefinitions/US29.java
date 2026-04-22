package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
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




    @Given("kullanıcı sisteme kayıtlı bir Instructor olarak giriş yapmıştır")
    public void kullanıcı_sisteme_kayıtlı_bir_ınstructor_olarak_giriş_yapmıştır() {
        driver.get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);
        homePage.homePageLoginLink.click();
        ReusableMethods.bekle(1);
        examplePage.loginMethod("burak.yilmaz.teacher@instulearn.com","Learn.123!");
        ReusableMethods.bekle(1);
    }

    @Given("kullanıcı dashboard sayfasındadır")
    public void kullanıcı_dashboard_sayfasındadır() {
        Assertions.assertTrue(dashboardPage.dashboardTitle.isDisplayed());
    }





    @Then("sidebar menüde Marketing başlığı görünür olmalıdır")
    public void sidebarMenüdeMarketingBaşlığıGörünürOlmalıdır() {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMarketingLink);
        Assertions.assertTrue(dashboardPage.sidebarMarketingLink.isDisplayed());
    }

    @Then("kullanıcı Marketing linkine tıklar")
    public void kullanıcıMarketingLinkineTıklar() {
        dashboardPage.sidebarMarketingLink.click();
    }

    @And("Marketing başlığı altında Discounts linki görünür ve aktif olmalıdır")
    public void marketing_Başlığı_Altında_Discounts_Linki_Görünür_Ve_Aktif_Olmalıdır() {
        Assertions.assertTrue(dashboardPage.discountsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(dashboardPage.discountsLinkByMarketing.isEnabled());
    }

    @And("Marketing başlığı altında Promotions linki görünür ve aktif olmalıdır")
    public void marketingBaşlığıAltındaPromotionsLinkiGörünürVeAktifOlmalıdır() {
        Assertions.assertTrue(dashboardPage.promotionsLinkByMarketing.isDisplayed());
        Assertions.assertTrue(dashboardPage.promotionsLinkByMarketing.isEnabled());
    }






    @When("kullanıcı Discounts linkine tıklar")
    public void kullanıcıDiscountsLinkineTıklar() {
        dashboardPage.discountsLinkByMarketing.click();
    }

    @Then("Discounts sayfası açılmalıdır")
    public void discounts_sayfası_açılmalıdır() {
        Assertions.assertTrue(marketingPage.discountsTitle.isDisplayed());
    }

    @And("Create butonu görünür olmalıdır")
    public void createButonuGörünürOlmalıdır() {
        Assertions.assertTrue(marketingPage.createButton.isDisplayed());
    }

    String titleText = RandomStringUtils.randomAlphabetic(5);
    @When("kullanıcı yeni indirim bilgilerini doldurur")
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

    @And("Create butonuna tıklar")
    public void createButonunaTıklar() {
        marketingPage.createButton.click();
    }

    @Then("yeni kurs indirimi başarıyla oluşturulmalıdır")
    public void yeni_kurs_indirimi_başarıyla_oluşturulmalıdır() {
        Assertions.assertTrue(marketingPage.successfullyMessage.isDisplayed());
    }

    @Then("oluşturulan indirim listede görünmelidir")
    public void oluşturulan_indirim_listede_görünmelidir() {

    }





    @Given("kullanıcı {string} linkine tıklar")
    public void when_kullanıcı_linkine_tıklar(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("kullanıcı eksik veya geçersiz indirim bilgileri girer")
    public void kullanıcı_eksik_veya_geçersiz_indirim_bilgileri_girer() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("hata mesajı görüntülenmelidir")
    public void hata_mesajı_görüntülenmelidir() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("indirim oluşturulmamalıdır")
    public void indirim_oluşturulmamalıdır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }






    @Then("Promotions sayfası açılmalıdır")
    public void promotions_sayfası_açılmalıdır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("mevcut promosyon planları listede görünür olmalıdır")
    public void mevcut_promosyon_planları_listede_görünür_olmalıdır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("her plan seçilebilir durumda olmalıdır")
    public void her_plan_seçilebilir_durumda_olmalıdır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }





    @Given("kullanıcı Promotions sayfasındadır")
    public void kullanıcı_promotions_sayfasındadır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("kullanıcı bir promosyon planı seçer")
    public void kullanıcı_bir_promosyon_planı_seçer() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("{string} butonuna tıklar")
    public void butonuna_tıklar(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("kullanıcı seçilen plana başarıyla kayıt olmalıdır")
    public void kullanıcı_seçilen_plana_başarıyla_kayıt_olmalıdır() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("onay mesajı ekranda görüntülenmelidir")
    public void onay_mesajı_ekranda_görüntülenmelidir() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
