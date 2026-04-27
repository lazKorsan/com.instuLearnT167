package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ExamplePage;
import pages.RegisterPage;
import utils.*;

import java.time.Duration;

public class RegisterSteps {

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(RegisterSteps.class);
    org.openqa.selenium.WebDriver driver = Hooks.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);
    ExamplePage examplePage = new ExamplePage(driver);


    @Given("Teacher registerPage sayfasina gider")
    public void teacher_register_page_sayfasina_gider() {
        String registerUrl = config.ConfigReader.getProperty("register_url");
        logger.debug("🐞 Teacher kayıt sayfasına gidiliyor: {}", registerUrl);
        driver.get(registerUrl);
        logger.info("🌏 Teacher kayıt sayfasına başarıyla gidildi.");

        String expectedUrl = "https://qa.instulearn.com/register";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            logger.info("Beklenen url: " + expectedUrl + "ile" + actualUrl + "aynı degerde");

        } else {
            logger.error("Beklenen url: " + expectedUrl + "ile" + actualUrl + "farklı degerde");
        }
    }

    @Given("Teacher instructor Buttonunu secer")
    public void teacher_instructor_buttonunu_secer() {

        logger.info(" Teacher kullanicisi " + "instructorButton seçti");

        ClickUtils.clickByXpath(driver, "(//*[@class='font-12 cursor-pointer px-15 py-10'])[2]");
        //JSUtilities.scrollToElement(driver, registerPage.instructorButton);
        //        registerPage.instructorButton.click();
        ReusableMethods.bekle(2);

        logger.info(" Teacher kullanicisi " + "instructorButton seçti");

    }

    @Given("Teacher mailBox kutusuna {string} degerini girer")
    public void teacher_mail_box_kutusuna_degerini_girer(String mail) {
        //examplePage.mailBox.sendKeys(mail);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='email'])[1]", mail);
        ReusableMethods.bekle(2);

        logger.info("Teacher mail adresini doldurdu ");

    }

    @Given("Teacher fullnameBox kutusuna {string} degerini girer")
    public void teacher_fullname_box_kutusuna_degerini_girer(String fullName) {
        //registerPage.fullName.sendKeys(fullName);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='fullname'])[1]", fullName);
        ReusableMethods.bekle(2);

        logger.info("Teacher fullname adresini doldurdu ");


    }

    @Given("Teacher passwordBox kotusuna {string} degerini gire")
    public void teacher_password_box_kotusuna_degerini_gire(String password) {
        //examplePage.passwordBox.sendKeys(password);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='password'])[1]", password);
        ReusableMethods.bekle(2);

        logger.info("Teacher password adresini doldurdu ");


    }

    @Given("Teacher reTypePassword kutusuna {string} degerini girer")
    public void teacher_re_type_password_kutusuna_degerini_girer(String rePassword) {
        //registerPage.confirmPassword.sendKeys(rePassword);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='confirm_password'])[1]", rePassword);
        ReusableMethods.bekle(2);

        logger.info("Teacher rePassword adresini doldurdu ");


    }


    @When("Kullanici checkBox kutusunu isaretler")
    public void kullaniciCheckBoxKutusunuIsaretler() {

        // JSUtilities.scrollToElement(driver, RegisterPage.checkBox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('term').checked = true;");
        System.out.println("✅ Terms kabul edildi");
        logger.info("Kullanici checkBox kutusunu isaretler");
    }


    @When("Teacher {string} ve {string} bilgileri ile gecerli kayıt olusturur")
    public void teacherVeBilgileriIleGecerliKayıtOlusturur(String name, String password) {

        String dynamicEmail = name + System.currentTimeMillis() + "@InstuLearn.com";
       // examplePage.mailBox.sendKeys(dynamicEmail);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='email'])[1]", dynamicEmail);
        ReusableMethods.bekle(2);
        logger.info("Testte olusturulan :" + dynamicEmail);

       // registerPage.fullName.sendKeys(name);
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"full_name\"]", name);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" + name);

       // examplePage.passwordBox.sendKeys(password);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='password'])[1]", password);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" + password);


        //registerPage.confirmPassword.sendKeys(password);
        SendKeysUtils.sendByXpath(driver, "(//*[@id='confirm_password'])[1]", password);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" + password);

        ExcelUtils.saveTestData(name, dynamicEmail, password);

    }


    @When("Instructor {string} mail adresi ve {string} sifresi kayit olur")
    public void instructorMailAdresiVeSifresiKayitOlur(String mail, String password) {

        driver.get("https://qa.instulearn.com/register");


        ClickUtils.clickByXpath(driver, "(//*[@class=\"font-12 cursor-pointer px-15 py-10\"])[2]");
        ReusableMethods.bekle(2);
        logger.info("Instructor Butonuna tiklandi");

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"email\"]", mail);
        ReusableMethods.bekle(2);
        logger.info("Instructor mail adresini doldurdu ");

        SendKeysUtils.sendByXpath(driver, "//input[@name=\"full_name\"]", "ahmet");
        ReusableMethods.bekle(2);
        logger.info("Instructor fullname adresini doldurdu ");

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"password\"]", password);
        ReusableMethods.bekle(2);
        logger.info("Instructor password adresini doldurdu ");

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"confirm_password\"]", password);
        ReusableMethods.bekle(2);
        logger.info("Instructor rePassword adresini doldurdu ");

        // JSUtilities.scrollToElement(driver, RegisterPage.checkBox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('term').checked = true;");
        System.out.println("✅ Terms kabul edildi");
        logger.info("Kullanici checkBox kutusunu isaretler");

        ClickUtils.clickByXpath(driver, "//button[@class=\"btn btn-primary btn-block mt-20\"]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici checkBox kutusunu isaretler");

    }


    @When("Teacher Register Testi Basliyor")
    public void teacherRegisterTestiBasliyor() {

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Ogretmenin Register Testi Basliyor",
                "TeacherRegisterTest",
                "Gecerli Syntax Bilgileri ile Register Testi Basliyor"

        );ReusableMethods.bekle(6);



    }

    @When("Kullanici kayit oldugunu dogrular")
    public void kullaniciKayitOldugunuDogrular() {



        ClickUtils.waitForPageLoad(driver);


        DescriptionUtils.inspect(driver, "//a[contains(text(),'Create a new course')]");
        ReusableMethods.bekle(1);


        WebElement createButton = driver.findElement(By.xpath("//a[contains(text(),'Create a new course')]"));
        Assertions.assertTrue(createButton.isDisplayed(), "❌ HATA: Buton sayfada görülmüyor!");
        logger.info("✅ Buton görünürlüğü doğrulandı.");


        ReusableMethods.bekle(1);
        BannerUtils.displayProgressBanner(
                driver,
                "TEST BAŞARILI",
                "Teacher Register Module",
                "Kullanıcı başarıyla kaydedildi ve 'Course Create' butonu doğrulandı."
        );
        ReusableMethods.bekle(5); // Banner'ın okunması için süre




    }
}
