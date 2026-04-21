package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import pages.BecomeInstructorPage;
import utils.ClickUtils;
import utils.ReusableMethods;
import utils.SendKeysUtils;



public class US039BecomeInstructorSteps {

    private static final Logger logger = LogManager.getLogger(BecomeInstructorPage.class);
    WebDriver driver = Hooks.getDriver();
    BecomeInstructorPage becomeInstructorPage = new BecomeInstructorPage(driver);



    @Given("Kullanici registerPage sayfasina gider")
    public void kullanici_register_page_sayfasina_gider() {

        logger.debug("🐞 Kullanıcı register sayfasına gider");
        driver.get(config.ConfigReader.getProperty("register_url"));
        logger.info("🌏 Kullanıcı register sayfasına gitti");

        String expectedUrl = "https://qa.instulearn.com/register";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            logger.info("Beklenen url: "+expectedUrl+"ile"+actualUrl+"aynı degerde");

        }else{
            logger.error("Beklenen url: "+expectedUrl+"ile"+actualUrl+"farklı degerde");
        }

    }
            logger.info("Beklenen url: " + expectedUrl + "ile" + actualUrl + "aynı degerde");

        } else {
            logger.error("Beklenen url: " + expectedUrl + "ile" + actualUrl + "farklı degerde");
        }

    }

    @Given("Kullanici {string} ve {string} bilgileri ile student kayit yapar")
    public void kullanici_ve_bilgileri_ile_student_kayit_yapar(String name, String password) {

        ClickUtils.clickByXpath(driver, "//*[@class=\"font-12 cursor-pointer px-15 py-10\"]");
        String dynamicStudentMail = name+System.currentTimeMillis()+"@gmail.com";
        String dynamicStudentMail = name + System.currentTimeMillis() + "@gmail.com";

        ReusableMethods.bekle(2);

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"email\"]", dynamicStudentMail);

        ReusableMethods.bekle(2);

        SendKeysUtils.sendByXpath(driver, "//input[@name=\"full_name\"]", name);

        ReusableMethods.bekle(2);

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"password\"]", password);

        ReusableMethods.bekle(2);

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"confirm_password\"]", password);

        ReusableMethods.bekle(2);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('term').checked = true;");
        System.out.println("✅ Terms kabul edildi");
        logger.info("Kullanici checkBox kutusunu isaretler");

        ReusableMethods.bekle(2);


        ClickUtils.clickByXpath(driver, "//button[@class=\"btn btn-primary btn-block mt-20\"]");









    }
    }

    @Given("Kullanici {int} saniye bekler")
    public void kullanici_saniye_bekler(Integer int1) {

        ReusableMethods.bekle(20);

    }


    @When("Kullanici becomeInstructorButtona tiklar")
    public void kullaniciBecomeInstructorButtonaTiklar() {


        ClickUtils.clickByXpath(driver, "//*[@href=\"/become-instructor\"]");
        ReusableMethods.bekle(2);

        // //*[@href="/become-instructor"]

        //  dosya yolu yuklenen pdf dosyasının   /store/2203/sertifikate.pdf

        //  //*[@for="checkbox943"]  --> matematik  tag için geçerli olan
    }

    @When("Kullanici Instructors butonuna basar")
    public void kullaniciInstructorsButonunaBasar() {

        ClickUtils.clickByXpath(driver, "//*[@href=\"/instructors\"]");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici webDesign secenegini secer")
    public void kullaniciWebDesignSeceneginiSecer() {

        // todo ########### deneme 1 ################
        //  todo  WebElement webDesignCheckBox = driver.findElement(By.id("checkbox520"));
        //    JavascriptExecutor js = (JavascriptExecutor) driver;
        //    js.executeScript("arguments[0].scrollIntoView(true);", webDesignCheckBox);
        //    webDesignCheckBox.click();

        // todo ########### deneme 2 ################
        // todo  WebElement webDesignCheckBox = driver.findElement(By.id("checkbox520"));
        //    JavascriptExecutor js = (JavascriptExecutor) driver;
        //    js.executeScript("arguments[0].scrollIntoView(true);", webDesignCheckBox);
        //  Actions actions = new Actions(driver);
        //  actions.moveToElement(webDesignCheckBox).click().perform();

        // todo ########### deneme 3 ################
        // todo  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //    WebElement webDesignCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox520")));
        //    JavascriptExecutor js = (JavascriptExecutor) driver;
        //    js.executeScript("arguments[0].scrollIntoView(true);", webDesignCheckBox);
        //    js.executeScript("arguments[0].click();", webDesignCheckBox);


        // todo ########### deneme 4 ################
        WebElement webDesignCheckBox = driver.findElement(By.id("checkbox520"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webDesignCheckBox);
        js.executeScript("arguments[0].click();", webDesignCheckBox);
        ReusableMethods.bekle(2);


        // checkbox621
        WebElement healthCoachhing = driver.findElement(By.id("checkbox621"));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].scrollIntoView(true);", healthCoachhing);
        js2.executeScript("arguments[0].click();", healthCoachhing);
        ReusableMethods.bekle(2);


        //  sertifikate


    }

    @When("Kullanici sertificate bolumunu doldurur")
    public void kullaniciSertificateBolumunuDoldurur() {
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"certificate\"]", "/store/2203/sertifikate.pdf");
        ReusableMethods.bekle(2);
    }

    @When("Teacher student Buttonunu secer")
    public void teacherStudentButtonunuSecer() {


    }

    @When("Kullanici payout account olarak Stripe secer")
    public void kullaniciPayoutAccountOlarakStripeSecer() {

        // todo ###### deneme 1 #######
        //  JavascriptExecutor js = (JavascriptExecutor) driver;
        //    js.executeScript("document.querySelector('select[name=\"bank_id\"]').value='5'");
        //    // Change event'ini tetiklemek için
        //    js.executeScript("document.querySelector('select[name=\"bank_id\"]').dispatchEvent(new Event('change'))");


        // todo ###### deneme 2 #######
        //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //
        //    // Dropdown'ın görünür olmasını bekle
        //    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("bank_id")));
        //
        //    Select select = new Select(dropdown);
        //    select.selectByVisibleText("Stripe");
        // // Veya value ile seçim
        //    // select.selectByValue("5");

        WebElement dropdown = driver.findElement(By.name("bank_id"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Stripe");
        // veya
        // select.selectByValue("5");


    }

    @When("Kullanici accountHolder bolumunu doldurur")
    public void kullaniciAccountHolderBolumunuDoldurur() {
        // //input[@name="bank_specifications[12]"]
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"bank_specifications[12]\"]", "Nihat Ozturk");
        ReusableMethods.bekle(2);
    }


    @When("Kullanici certificates bolumunde sertifika secimi yapar")
    public void kullaniciCertificatesBolumundeSertifikaSecimiYapar() {
        // /store/2205/sertifikate.pdf

        SendKeysUtils.sendByXpath(driver, "//input[@id=\"certificate\"]", "/store/2205/sertifikate.pdf");

    }

    @When("Kullanici accountID bolumunu doldurur")
    public void kullaniciAccountIDBolumunuDoldurur() {
        //   /store/2205/identityScan.pdf
        // //input[@name="bank_specifications[13]"]

        SendKeysUtils.sendByXpath(driver, "//input[@name=\"bank_specifications[13]\"]", "321");
    }

    @When("Kullanici identityScan bolumunu secer")
    public void kullaniciIdentityScanBolumunuSecer() {
        //  //*[@id="identity_scan"]
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"identity_scan\"]", "/store/2205/identityScan.pdf");

    }

    @When("Kullanici descriptionBox bolumunu doldurur")
    public void kullaniciDescriptionBoxBolumunuDoldurur() {
        //textarea[@name="description"]
        // description ne girebilriz.  Matemetikle alakalı bir şeyler verirmisin yorum içinde
        String description = "Uzman matematik öğretmeniyim. İlkokul, ortaokul ve lise seviyesindeki öğrencilere özel ders vermekteyim. Matematiği sevdiren, pratik çözüm yöntemleriyle başarıyı artıran bir eğitim anlayışım var.";
        SendKeysUtils.sendByXpath(driver, "//textarea[@name=\"description\"]", description);
        ReusableMethods.bekle(2);
    }

    @When("Kullanici sendRequestButton a tiklar")
    public void kullaniciSendRequestButtonATiklar() {

        ClickUtils.clickByXpath(driver, "//button[@class=\"btn btn-primary btn-block mt-20\"]");
    }

    @When("Kullanici acilan sayfada arama kutusuna {string} kelimesini aratir")
    public void kullaniciAcilanSayfadaAramaKutusunaKelimesiniAratir(String name) {

        // //input[@class="form-control mr-5 rounded"]

        SendKeysUtils.sendByXpath(driver, "//input[@class=\"form-control mr-5 rounded\"]", name);

        driver.findElement(By.xpath("//input[@class=\"form-control mr-5 rounded\"]")).sendKeys(Keys.ENTER);

    }

    @When("Kullanici arama sonucu cikan yazinin {string} oldugunu dogrular")
    public void kullaniciAramaSonucuCikanYazininOldugunuDogrular(String expectedResult) {
        // //*[@class="font-16 font-weight-bold text-dark-blue mt-10"]
        WebElement actualResultElement = driver.findElement(By.xpath("//*[@class=\"font-16 font-weight-bold text-dark-blue mt-10\"]"));
        String actualResult = actualResultElement.getText();

        if (actualResult.equals(expectedResult)) {
            logger.info("Beklenen deger ile gerceklesen deger aynı" + expectedResult + "=" + actualResult);
        } else {
            logger.error("Beklenen deger ile gerceklesen deger farklı" + expectedResult + "=" + actualResult);
        }
    }


}
