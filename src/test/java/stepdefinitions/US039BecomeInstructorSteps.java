package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            logger.info("Beklenen url: " + expectedUrl + "ile" + actualUrl + "aynı degerde");

        } else {
            logger.error("Beklenen url: " + expectedUrl + "ile" + actualUrl + "farklı degerde");
        }

    }

    @Given("Kullanici {string} ve {string} bilgileri ile student kayit yapar")
    public void kullanici_ve_bilgileri_ile_student_kayit_yapar(String name, String password) {

        // todo studentButton secildi
        ClickUtils.clickByXpath(driver, "//*[@class=\"font-12 cursor-pointer px-15 py-10\"]");
        logger.info("Kullanici studentButton secildi");
        ReusableMethods.bekle(2);

        String dynamicStudentMail = name + System.currentTimeMillis() + "@gmail.com";

        // todo mailBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"email\"]", dynamicStudentMail);
        logger.info("Kullanici mailBox alanini doldurdu");
        ReusableMethods.bekle(2);

        // todo nameBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"full_name\"]", name);
        logger.info("Kullanici nameBox alanini doldurdu");
        ReusableMethods.bekle(2);

        // todo passwordBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"password\"]", password);
        logger.info("Kullanici passwordBox alanini doldurdu");
        ReusableMethods.bekle(2);

        // todo confirmPasswordBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"confirm_password\"]", password);
        logger.info("Kullanici confirmPasswordBox alanini doldurdu");
        ReusableMethods.bekle(2);

        // todo checkBox kutusunu isaretler --> Burasi Onemli
        WebElement checkBox = driver.findElement(By.id("term"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('term').checked = true;");
        System.out.println("✅ Terms kabul edildi");
        logger.info("Kullanici checkBox kutusunu isaretler");
        ReusableMethods.bekle(2);


        // todo submitButton islemleri
        ClickUtils.clickByXpath(driver, "//button[@class=\"btn btn-primary btn-block mt-20\"]");
        logger.info("Kullanici submitButton alanini doldurdu");
        ReusableMethods.bekle(8); // todo ---->>>> zorunlu bekleme ayrıyetten Driver sayfasinin yuklenmesini bekler

    }

    @Given("Kullanici {int} saniye bekler")
    public void kullanici_saniye_bekler(Integer int1) {

        ReusableMethods.bekle(20);

    }

    @When("Kullanici becomeInstructorButtona tiklar")
    public void kullaniciBecomeInstructorButtonaTiklar() {

        // todo becomeInstructorButtona  tiklama islemleri
        ClickUtils.clickByXpath(driver, "//*[@href=\"/become-instructor\"]");
        logger.info("Kullanici becomeInstructorButtona tiklandi");
        ReusableMethods.bekle(2);

        // //*[@href="/become-instructor"]
        //  dosya yolu yuklenen pdf dosyasının   /store/2203/sertifikate.pdf
        //  //*[@for="checkbox943"]  --> matematik  tag için geçerli olan
    }

    @When("Kullanici Instructors butonuna basar")
    public void kullaniciInstructorsButonunaBasar() {

        // todo Instructors butonuna tiklama islemleri
        ClickUtils.clickByXpath(driver, "//*[@href=\"/instructors\"]");
        logger.info("Kullanici Instructors butonuna tiklandi");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici webDesign secenegini secer")
    public void kullaniciWebDesignSeceneginiSecer() {
        // todo instructor Meslek alani secimi islemleri
        // Locateler checkbox alanı içinde

        // todo webDesign Meslek alanı secim ilelemleri
        WebElement webDesignCheckBox = driver.findElement(By.id("checkbox520"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webDesignCheckBox);
        js.executeScript("arguments[0].click();", webDesignCheckBox);
        logger.info("Kullanici webDesign secenegini secti");
        ReusableMethods.bekle(2);

        // todo healthCoachhing secim islemleri
        WebElement healthCoachhing = driver.findElement(By.id("checkbox621"));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].scrollIntoView(true);", healthCoachhing);
        js2.executeScript("arguments[0].click();", healthCoachhing);
        logger.info("Kullanici healthCoachhing secenegini secti");
        ReusableMethods.bekle(2);

        //  sertifikate

    }

    @When("Kullanici sertificate bolumunu doldurur")
    public void kullaniciSertificateBolumunuDoldurur() {

        // todo Onemli burda certificate Alani normalde button. Değişken değiştrerek dosya yolu gonderiyoruz
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"certificate\"]", "/store/2203/sertifikate.pdf");
        logger.info("Kullanici sertificate bolumunu doldurdu");
        ReusableMethods.bekle(2);
    }

    @When("Teacher student Buttonunu secer")
    public void teacherStudentButtonunuSecer() {


    }

    @When("Kullanici payout account olarak Stripe secer")
    public void kullaniciPayoutAccountOlarakStripeSecer() {

        // todo payout islemleri
        WebElement dropdown = driver.findElement(By.name("bank_id"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Stripe");
        logger.info("Kullanici payout account olarak Stripe");
        // veya
        // select.selectByValue("5");

    }

    @When("Kullanici accountHolder bolumunu doldurur")
    public void kullaniciAccountHolderBolumunuDoldurur() {

        // todo accountHolder islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"bank_specifications[12]\"]", "Nihat Ozturk");
        logger.info("Kullanici accountHolder bolumunu doldurdu");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici certificates bolumunde sertifika secimi yapar")
    public void kullaniciCertificatesBolumundeSertifikaSecimiYapar() {
        // /store/2205/sertifikate.pdf

        // todo sertifika gonderme islemleri degisken degistrme button-box
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"certificate\"]", "/store/2205/sertifikate.pdf");
        logger.info("Kullanici certificates bolumunde sertifika secimi yapti");
        ReusableMethods.bekle(2);

    }

    @When("Kullanici accountID bolumunu doldurur")
    public void kullaniciAccountIDBolumunuDoldurur() {
        //   /store/2205/identityScan.pdf
        // //input[@name="bank_specifications[13]"]

        // todo accountID islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"bank_specifications[13]\"]", "321");
        logger.info("Kullanici accountID bolumunu doldurdu");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici identityScan bolumunu secer")
    public void kullaniciIdentityScanBolumunuSecer() {
        // todo identityScan islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@id=\"identity_scan\"]", "/store/2205/identityScan.pdf");
        logger.info("Kullanici identityScan bolumunu secti");
        ReusableMethods.bekle(2);

    }

    @When("Kullanici descriptionBox bolumunu doldurur")
    public void kullaniciDescriptionBoxBolumunuDoldurur() {
        //textarea[@name="description"]
        // todo descriptionBox islemleri
        String description = "Uzman matematik öğretmeniyim. İlkokul, ortaokul ve lise seviyesindeki öğrencilere özel ders vermekteyim. Matematiği sevdiren, pratik çözüm yöntemleriyle başarıyı artıran bir eğitim anlayışım var.";
        SendKeysUtils.sendByXpath(driver, "//textarea[@name=\"description\"]", description);
        logger.info("Kullanici descriptionBox bolumunu doldurdu");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici sendRequestButton a tiklar")
    public void kullaniciSendRequestButtonATiklar() {

        // todo sendRequestButton islemleri
        ClickUtils.clickByXpath(driver, "//button[@class=\"btn btn-primary btn-block mt-20\"]");
        logger.info("Kullanici sendRequestButton a tiklandi");
        ReusableMethods.bekle(2);
    }

    @When("Kullanici acilan sayfada arama kutusuna {string} kelimesini aratir")
    public void kullaniciAcilanSayfadaAramaKutusunaKelimesiniAratir(String name) {

        // todo arama islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@class=\"form-control mr-5 rounded\"]", name);
        driver.findElement(By.xpath("//input[@class=\"form-control mr-5 rounded\"]")).sendKeys(Keys.ENTER);

    }

    @When("Kullanici arama sonucu cikan yazinin {string} oldugunu dogrular")
    public void kullaniciAramaSonucuCikanYazininOldugunuDogrular(String expectedResult) {

        // todo arama dogrulama islemleri
        WebElement actualResultElement = driver.findElement(By.xpath("//*[@class=\"font-16 font-weight-bold text-dark-blue mt-10\"]"));
        String actualResult = actualResultElement.getText();

        if (actualResult.equals(expectedResult)) {
            logger.info("Beklenen deger ile gerceklesen deger aynı" + expectedResult + "=" + actualResult);
        } else {
            logger.error("Beklenen deger ile gerceklesen deger farklı" + expectedResult + "=" + actualResult);
        }
    }


    @When("Kullanici {string} gunu icin {string} saatinde {string} tipinde toplanti ekler")
    public void kullaniciGunuIcinSaatindeTipindeToplantiEkler(String day, String time, String meetingType) {

        // todo reserve meetings islemleri
        // JavaScript ile doğrudan AJAX/Axios POST isteği gönder
        String jsScript =
                "var data = {" +
                        "    day: '" + day + "'," +
                        "    time: '" + time + "'," +
                        "    meeting_type: '" + meetingType + "'," +
                        "    description: 'Cucumber test ile eklenen toplantı'" +
                        "};" +
                        "$.post('/panel/meetings/saveTime', data, function(result) {" +
                        "    console.log('Toplantı eklendi:', result);" +
                        "});";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);

        logger.info("✅ " + day + " günü " + time + " saatinde toplantı eklendi");

        // İsteğin işlenmesi için bekle
        ReusableMethods.bekle(3);



    }
}
