package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.DashboardPage;
import pages.ExamplePage;
import pages.HomePage;
import pages.InstructorProfilePage;
import utils.ReusableMethods;

public class US32 {
    private static final Logger logger = LogManager.getLogger(US32.class);
    WebDriver driver = Hooks.getDriver();
    HomePage homePage = new HomePage(driver);
    ExamplePage examplePage = new ExamplePage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    Actions actions = new Actions(driver);
    InstructorProfilePage instructorProfilePage = new InstructorProfilePage(driver);

    @Given("kullanıcı login olur")
    public void kullanıcı_login_olur() {
        driver.get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);
        homePage.homePageLoginLink.click();
        ReusableMethods.bekle(1);
        examplePage.loginMethod("burak.yilmaz.teacher@instulearn.com","Learn.123!");
        ReusableMethods.bekle(1);

    }

    @When("dashboard sayfasının açıldığı doğrulanır")
    public void dashboard_sayfasının_açıldığı_doğrulanır() {
        Assertions.assertTrue(dashboardPage.dashboardTitle.isDisplayed());
    }

    @Then("sol sidebar'da {string} linki görünür olmalıdır")
    public void sol_sidebar_da_linki_görünür_olmalıdır(String string) {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMyProfileLink);
        Assertions.assertTrue(dashboardPage.sidebarMyProfileLink.isDisplayed());
    }

    @Then("{string} linki tıklanabilir durumda olmalıdır")
    public void linki_tıklanabilir_durumda_olmalıdır(String string) {
        Assertions.assertTrue(dashboardPage.sidebarMyProfileLink.isEnabled());
    }





    @Then("kullanıcı anasayfayı açar")
    public void kullanıcı_anasayfayı_açar() {
        homePage.instulearnLogo.click();
    }

    @When("mouse ile profil ikonunun üzerine geldiğinde dropdown açılır")
    public void mouse_ile_profil_ikonunun_üzerine_geldiğinde_dropdown_açılır() {
        ReusableMethods.bekle(2);
        actions.moveToElement(homePage.instructorProfileIconInHeader);
        ReusableMethods.bekle(1);
        Assertions.assertTrue(homePage.instructorProfilIconDropdownMenu.isDisplayed());
    }

    @Then("açılan dropdown'da {string} linki görünür olmalıdır")
    public void açılan_dropdown_da_linki_görünür_olmalıdır(String string) {
        Assertions.assertTrue(homePage.profileLinkInInstructorProfileIconDropdownMenu.isDisplayed());
    }

    @Then("{string} linki aktif olmalıdır")
    public void linki_aktif_olmalıdır(String string) {
        Assertions.assertTrue(homePage.profileLinkInInstructorProfileIconDropdownMenu.isEnabled());
    }





    @When("sol sidebar da bulunan {string} linkine tıklar")
    public void sol_sidebar_da_bulunan_linkine_tıklar(String string) {
        actions.moveToElement(dashboardPage.sidebar).perform();
        ReusableMethods.scrollToElement(driver,dashboardPage.sidebarMyProfileLink);
        dashboardPage.sidebarMyProfileLink.click();
    }

    @Then("profil sayfasının açıldığı doğrulanır")
    public void profil_sayfasının_açıldığı_doğrulanır() {
       String actualResult = instructorProfilePage.nameText.getText();
       String expectedResult = homePage.instructorProfileIconInHeader.getText();
       Assertions.assertEquals(expectedResult,actualResult);
    }

    @Then("ilgili profili takip edebilmelidir")
    public void ilgili_profili_takip_edebilmelidir() {
        Assertions.assertTrue(instructorProfilePage.followIcon.isEnabled());
    }


    @Then("about linki altında ilgili içerik görüntülenir")
    public void about_linki_altında_ilgili_içerik_görüntülenir() {

    }
    @Then("courses linki altında ilgili içerik görüntülenir")
    public void courses_linki_altında_ilgili_içerik_görüntülenir() {

    }
    @Then("articles linki altında ilgili içerik görüntülenir")
    public void articles_linki_altında_ilgili_içerik_görüntülenir() {

    }
    @Then("badges linki altında ilgili içerik görüntülenir")
    public void badges_linki_altında_ilgili_içerik_görüntülenir() {

    }
    @Then("reserve a meeting linki altında ilgili içerik görüntülenir")
    public void reserve_a_meeting_linki_altında_ilgili_içerik_görüntülenir() {

    }








}
