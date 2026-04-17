package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import pages.ExamplePage;
import pages.RegisterPage;
import utils.ExcelUtils;
import utils.JSUtilities;
import utils.ReusableMethods;

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
            logger.info("Beklenen url: "+expectedUrl+"ile"+actualUrl+"aynı degerde");

        }else{
            logger.error("Beklenen url: "+expectedUrl+"ile"+actualUrl+"farklı degerde");
        }
    }

    @Given("Teacher instructor Buttonunu secer")
    public void teacher_instructor_buttonunu_secer() {

        logger.info(" Teacher kullanicisi "+"instructorButton seçti" );

        JSUtilities.scrollToElement(driver, registerPage.instructorButton);
        registerPage.instructorButton.click();
        ReusableMethods.bekle(2);

        logger.info(" Teacher kullanicisi "+"instructorButton seçti" );

    }
    @Given("Teacher mailBox kutusuna {string} degerini girer")
    public void teacher_mail_box_kutusuna_degerini_girer(String mail) {
        examplePage.mailBox.sendKeys(mail);
        ReusableMethods.bekle(2);

        logger.info("Teacher mail adresini doldurdu ");

    }
    @Given("Teacher fullnameBox kutusuna {string} degerini girer")
    public void teacher_fullname_box_kutusuna_degerini_girer(String fullName) {
        registerPage.fullName.sendKeys(fullName);
        ReusableMethods.bekle(2);

        logger.info("Teacher fullname adresini doldurdu ");


    }
    @Given("Teacher passwordBox kotusuna {string} degerini gire")
    public void teacher_password_box_kotusuna_degerini_gire(String password) {
        examplePage.passwordBox.sendKeys(password);
        ReusableMethods.bekle(2);

        logger.info("Teacher password adresini doldurdu ");



    }
    @Given("Teacher reTypePassword kutusuna {string} degerini girer")
    public void teacher_re_type_password_kutusuna_degerini_girer(String rePassword) {
        registerPage.confirmPassword.sendKeys(rePassword);
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

        String dynamicEmail=name+System.currentTimeMillis()+"@InstuLearn.com";
        examplePage.mailBox.sendKeys(dynamicEmail);
        ReusableMethods.bekle(2);
        logger.info("Testte olusturulan :" +dynamicEmail);

        registerPage.fullName.sendKeys(name);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" +name);

        examplePage.passwordBox.sendKeys(password);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" +password);


        registerPage.confirmPassword.sendKeys(password);
        ReusableMethods.bekle(2);
        logger.info("Testte kullanilan :" +password);

        ExcelUtils.saveTestData(name, dynamicEmail, password);

    }
}
