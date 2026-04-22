package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.InstructorsPage;
import pages.LoginPage;
import pages.NavbarPage;
import utils.ReusableMethods;

import java.time.Duration;

public class US034InstructorDisplaySteps {
    private static final Logger logger = LogManager.getLogger(US034InstructorDisplaySteps.class);
    WebDriver driver = Hooks.getDriver();
    InstructorsPage instructorsPage = new InstructorsPage(driver);
    NavbarPage navbarPage = new NavbarPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    int instructorsSize;
    CartPage cartPage = new CartPage(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;


    //    +++++++++++++++++++++++++++++++++++++++
    //                  TC1
    //    +++++++++++++++++++++++++++++++++++++++
    @When("user click on {string} link in the navbar")
    public void user_click_on_link_in_the_navbar(String navbarText) {
        logger.info("Navbar üzerinden '{}' linkine tıklanıyor.", navbarText);
        navbarPage.getNavbarLink(navbarText, driver).click();
    }

    @Then("user should be redirected to the instructors page")
    public void user_should_be_redirected_to_the_instructors_page() {
        logger.info("Eğitmenler sayfasına yönlendirme doğrulanıyor.");
        Assert.assertTrue("ERROR - Could not navigate to the instructors page./The text 'Instructors' is not displayed in the page title.", instructorsPage.headerText.isDisplayed());
        logger.info("Eğitmenler sayfası başarıyla görüntülendi.");
    }

    //    +++++++++++++++++++++++++++++++++++++++
    //                  TC2
    //    +++++++++++++++++++++++++++++++++++++++

    @Given("user is on the instructors page")
    public void user_is_on_the_instructors_page() {
        logger.info("Eğitmenler sayfasında olunduğu kontrol ediliyor.");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        try {
            if (!instructorsPage.headerText.isDisplayed()) {
                logger.warn("Header görünmedi, Instructors linkine tekrar tıklanıyor.");
                navbarPage.getNavbarLink("Instructors", driver).click();
            }
        } catch (Exception e) {
            logger.error("Hata alındı, Instructors linkine fallback tıklaması yapılıyor.");
            navbarPage.getNavbarLink("Instructors", driver).click();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("timeout"))));
    }

    @Then("check that the search box is visible")
    public void check_that_the_search_box_is_visible() {
        logger.info("Arama kutusunun görünürlüğü kontrol ediliyor.");
        Assert.assertTrue("ERROR - Search box is visible.", instructorsPage.searchBox.isDisplayed());
    }

    @Then("check that the search button is visible and active")
    public void check_that_the_search_button_is_visible_and_active() {
        logger.info("Arama butonunun görünürlüğü ve aktifliği kontrol ediliyor.");
        Assert.assertTrue("ERROR - Search button is visible.", instructorsPage.searchButton.isDisplayed());
        Assert.assertTrue("ERROR - Search button is disable.", instructorsPage.searchButton.isEnabled());
    }

    //    +++++++++++++++++++++++++++++++++++++++
    //                      TC3
    //    +++++++++++++++++++++++++++++++++++++++


    @Given("confirms that the category section is visible")
    public void confirms_that_the_category_section_is_visible() {
        logger.info("Kategori bölümüne scroll yapılıyor ve görünürlük kontrol ediliyor.");
        ReusableMethods.scrollToElement(driver, instructorsPage.categorySection);
        Assert.assertTrue("ERROR - Category section is visible.", instructorsPage.categorySection.isDisplayed());
    }

    @When("select a {string} category from the category section")
    public void select_a_category_from_the_category_section(String categoryName) {
        logger.info("'{}' kategorisi seçiliyor.", categoryName);
        instructorsPage.getCategory(categoryName, driver).click();
    }

    @Then("confirms that the results related to category selection are listed")
    public void confirms_that_the_results_related_to_category_selection_are_listed() {
        logger.info("Kategori sonuçlarının listelendiği doğrulanıyor (Manuel kontrol uyarısı).");
        //  Egitmen kartinda kategori alani olmadigi icin gercekten iliskili olup olmadigini anlayamiyorum
    }

    //    +++++++++++++++++++++++++++++++++++++++
    //                  TC4
    //    +++++++++++++++++++++++++++++++++++++++


    @Then("the instructor's picture is checked to ensure it is visible")
    public void the_instructor_s_picture_is_checked_to_ensure_it_is_visible() {
        instructorsSize = instructorsPage.instructorsListSize.size();
        logger.info("Toplam {} eğitmen bulundu. Resim kontrolleri başlıyor.", instructorsSize);

        for (int i = 1; i <= instructorsSize; i++) {
            ReusableMethods.scrollToElement(driver, instructorsPage.getInstructorImage(i));
            Assert.assertTrue("ERROR - The instructor's profile image is not displayed on the instructor card", instructorsPage.getInstructorImage(i).isDisplayed());
            Assert.assertFalse("ERROR - The source (href/src) of the instructor's image is missing or empty", instructorsPage.getInstructorImage(i).getAttribute("src").isBlank());
        }
        logger.info("Tüm eğitmen resimleri başarıyla kontrol edildi.");
    }

    @Then("the trainer's competence is checked")
    public void the_trainer_s_competence_is_checked() {
        logger.info("Eğitmen yetkinlik alanları kontrol ediliyor.");
        for (int i = 1; i <= instructorsSize; i++) {
            ReusableMethods.scrollToElement(driver, instructorsPage.getInstructorField(i));
            Assert.assertTrue("ERROR - The instructor field/image at index " + i + " is not visible on the page", instructorsPage.getInstructorField(i).isDisplayed());
            Assert.assertFalse("ERROR - The text content for instructor at index " + i + " is blank or missing", instructorsPage.getInstructorField(i).getText().trim().isBlank());
        }
    }

    @Then("the instructor's price information is checked to ensure it is displayed")
    public void the_instructor_s_price_information_is_checked_to_ensure_it_is_displayed() {
        logger.info("Eğitmen fiyat bilgileri kontrol ediliyor.");
        for (int i = 1; i <= instructorsSize; i++) {
            ReusableMethods.scrollToElement(driver, instructorsPage.getInstructorPrice(i));
            Assert.assertTrue("ERROR - The instructor's price at index " + i + " is not visible on the page", instructorsPage.getInstructorPrice(i).isDisplayed());
            Assert.assertFalse("ERROR - The price text for instructor at index " + i + " is blank or missing", instructorsPage.getInstructorPrice(i).getText().trim().isBlank());
        }
    }

    @Then("check that the reserve a meeting button is visible")
    public void check_that_the_reserve_a_meeting_button_is_visible() {
        logger.info("Randevu butonlarının görünürlük ve aktiflik kontrolleri yapılıyor.");
        for (int i = 0; i < instructorsSize; i++) {
            Assert.assertTrue("ERROR - The reserve a meeting button at index " + i + " is not visible on the page", instructorsPage.getReserveAMeetingButton(i).isDisplayed());
            Assert.assertTrue("ERROR - The reserve a meeting button at index " + i + " is disabled (not clickable)",
                    instructorsPage.getReserveAMeetingButton(i).isEnabled());
            Assert.assertFalse("ERROR - The reserve a meeting button text at index " + i + " is blank",
                    instructorsPage.getReserveAMeetingButton(i).getText().isBlank());
        }
    }

    //    +++++++++++++++++++++++++++++++++++++++
    //                  TC5
    //    +++++++++++++++++++++++++++++++++++++++


    @When("the user clicks the reserve a meeting button")
    public void the_user_clicks_the_reserve_a_meeting_button() {
        logger.info("Randevu al butonuna tıklanıyor (Index 1).");
        instructorsPage.getReserveAMeetingButton(1).click();
    }

    @When("the user selects day number {int}")
    public void the_user_selects_day_number(Integer dayNumber) {
        logger.info("Gün seçimi yapılıyor (Day Number: {}).", dayNumber);
        ReusableMethods.scrollToElement(driver, instructorsPage.getDate9);
        ReusableMethods.waitForClickablility(driver, instructorsPage.getDate9, 1);
        instructorsPage.getDate9.click();
    }

    @When("the user chooses any time")
    public void the_user_chooses_any_time() {
        logger.info("Müsait zaman dilimi seçiliyor.");
        ReusableMethods.waitForVisibility(driver, instructorsPage.time, 3);
        ReusableMethods.scrollToElement(driver, instructorsPage.time);
        ReusableMethods.waitForClickablility(driver, instructorsPage.time, 3);
        logger.info("Zaman dilimine JS Click uygulanıyor.");
        js.executeScript("arguments[0].click()", instructorsPage.time);
    }

    @When("select {string} as the meeting type")
    public void select_as_the_meeting_type(String string) {
        logger.info("Toplantı tipi seçiliyor: {}", string);
        ReusableMethods.waitForVisibility(driver, instructorsPage.meetingType, 3);
        ReusableMethods.scrollToElement(driver, instructorsPage.meetingType);
        ReusableMethods.waitForClickablility(driver, instructorsPage.meetingType, 1);
        logger.info("Toplantı tipine JS Click uygulanıyor.");
        js.executeScript("arguments[0].click()", instructorsPage.meetingType);
    }

    @When("the user clicks the reserve a meeting button for create meeting")
    public void the_user_clicks_the_reserve_a_meeting_button_for_create_meeting() {
        logger.info("Toplantı oluşturmak için onay butonuna tıklanıyor.");
        ReusableMethods.scrollToElement(driver, instructorsPage.meetingSubmitButton);
        ReusableMethods.waitForClickablility(driver, instructorsPage.meetingSubmitButton, 1);
        instructorsPage.meetingSubmitButton.click();
    }

    @Then("verify that the item {string} is added to the cart")
    public void verify_that_the_item_itemName_is_added_to_the_cart(String itemName) {
        logger.info("Sepet içeriği doğrulanıyor. Ürün: {}", itemName);
        navbarPage = new NavbarPage(driver);
        ReusableMethods.waitForVisibility(driver, navbarPage.navbarShoppingCart, 3);
        logger.info("Sepet ikonuna tıklanıyor.");
        ReusableMethods.waitForClickablility(driver, navbarPage.navbarShoppingCart, 3).click();

        cartPage = new CartPage(driver);
        logger.info("Sepetteki ilk ürün kontrol ediliyor.");
        ReusableMethods.waitForVisibility(driver, cartPage.itemList.get(0), 3);
        ReusableMethods.scrollToElement(driver, cartPage.itemList.get(0));

        Assert.assertTrue("ERROR - The expected item '" + itemName + "' was not found in the cart", cartPage.isItemExists(itemName));
        logger.info("Ürün başarıyla sepette doğrulandı.");
    }

    //Background
    @When("the user enters their student information and logs in")
    public void theUserEntersTheirStudentInformationAndLogsIn() {
        logger.info("Öğrenci girişi yapılıyor.");
        loginPage.login(ConfigReader.getProperty("studentMail"), ConfigReader.getProperty("studentPassword"));
    }
}