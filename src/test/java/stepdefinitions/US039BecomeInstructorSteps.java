package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    @Given("Kullanici {string} ve {string} bilgileri ile student kayit yapar")
    public void kullanici_ve_bilgileri_ile_student_kayit_yapar(String name, String password) {

        ClickUtils.clickByXpath(driver, "//*[@class=\"font-12 cursor-pointer px-15 py-10\"]");
        String dynamicStudentMail = name+System.currentTimeMillis()+"@gmail.com";

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
    @Given("Kullanici {int} saniye bekler")
    public void kullanici_saniye_bekler(Integer int1) {

        ReusableMethods.bekle(20);

    }


    @When("Kullanici becomeInstructorButtona tiklar")
    public void kullaniciBecomeInstructorButtonaTiklar() {

        // //*[@href="/become-instructor"]

        //  dosya yolu yuklenen pdf dosyasının   /store/2203/sertifikate.pdf

        //  //*[@for="checkbox943"]  --> matematik  tag için geçerli olan
    }
}
