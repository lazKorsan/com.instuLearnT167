package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.NavbarPage;
import utils.ReusableMethods;

public class US40StudentDashboard {
    private static final Logger logger = LogManager.getLogger(US40StudentDashboard.class);
    WebDriver driver = Hooks.getDriver();
    NavbarPage navbarPage = new NavbarPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);

    @When("The user hovers the mouse over the profile icon in the upper right corner")
    public void the_user_hovers_the_mouse_over_the_profile_icon_in_the_upper_right_corner() {
        logger.info("Profiling icon hover işlemi başlatılıyor.");
        ReusableMethods.hover(driver, navbarPage.navbarProfileButton);
    }

    @When("In the pop-up that opens, click the dashboard link")
    public void in_the_pop_up_that_opens_click_the_dashboard_link() {
        logger.info("Profil pop-up menüsünden Dashboard linkine tıklanıyor.");
        ReusableMethods.waitForVisibility(driver, navbarPage.profileButtonPopUpDashboardLink, 3).click();
    }

    @Then("Confirms that you were redirected to the dashboard page")
    public void confirms_that_you_were_redirected_to_the_dashboard_page() {
        logger.info("Dashboard sayfasına yönlendirme doğrulanıyor.");
        Assert.assertTrue("ERROR - Could not be redirected to the dashboard page", dashboardPage.Dashboard.isDisplayed());
    }

    @Then("The user confirms that all links on the dashboard are visible and active")
    public void the_user_confirms_that_all_links_on_the_dashboard_are_visible_and_active() {
        logger.info("Sidebar linklerinin görünürlüğü ve aktifliği kontrol ediliyor.");
        int i = 0;
        for (WebElement eachElement : dashboardPage.sidebarLinks) {
            i++;
            Assert.assertTrue("ERROR - The " + i + " element in the sidebar is not visible", eachElement.isDisplayed());
            Assert.assertTrue("ERROR - The " + i + " element in the sidebar is disable", eachElement.isEnabled());
        }
        logger.info("Toplam {} adet sidebar linki başarıyla doğrulandı.", i);
    }

    @Then("The user verifies that the {string} link is visible and active")
    public void theUserVerifiesThatTheLinkIsVisibleAndActive(String arg0) {
        logger.info("View All Events linki kontrol ediliyor.");
        Assert.assertTrue("ERROR - The viewAllEventsLink element in the dashboard page is not visible", dashboardPage.viewAllEventsLink.isDisplayed());
        Assert.assertTrue("ERROR - The viewAllEventsLink element in the dashboard page is disable", dashboardPage.viewAllEventsLink.isEnabled());
    }

    @Then("The visibility and activity of the Account Balance link are verified in the dashboard body section")
    public void theVisibilityAndActivityOfTheAccountBalanceLinkAreVerifiedInTheDashboardBodySection() {
        logger.info("Account Balance linki kontrol ediliyor.");
        Assert.assertTrue("ERROR - The AccountBalance link in the dashboard page is not visible", dashboardPage.dashboardBodyAccountBalance.isDisplayed());
        Assert.assertTrue("ERROR - The AccountBalance link in the dashboard page is disable", dashboardPage.dashboardBodyAccountBalance.isEnabled());
    }

    @And("In the dashboard body section, the links for Purchased Courses, Meetings, Support Messages, and Comments are visible and confirmed to be active")
    public void inTheDashboardBodySectionTheLinksForPurchasedCoursesMeetingsSupportMessagesAndCommentsAreVisibleAndConfirmedToBeActive() {
        logger.info("Dashboard body bölümündeki Purchased Courses, Meetings, Support Messages ve Comments linkleri doğrulanıyor.");
        for (int i = 1; i < dashboardPage.dashboardBodyLinks.size(); i++) {
            Assert.assertTrue("ERROR - The " + i + " element in the dashboard body section is not visible", dashboardPage.dashboardBodyLinks.get(i).isDisplayed());
            Assert.assertTrue("ERROR - The " + i + " element in the dashboard body section is disable", dashboardPage.dashboardBodyLinks.get(i).isEnabled());
        }
    }

    @Given("the user is on the dashboard page")
    public void theUserIsOnTheDashboardPage() {
        logger.info("Dashboard sayfa kontrolü yapılıyor.");
        if (!driver.getCurrentUrl().equals("https://qa.instulearn.com/panel")) {
            logger.warn("Dashboard görüntülenemedi, URL ile yönlendirme yapılıyor.");
            driver.get("https://qa.instulearn.com/panel");
        }
    }

    @Then("The advertisements and their details should be visible on the Noticeboard.")
    public void asAUserIWantToBeAbleToViewAdvertisementsAndTheirDetailsOnTheJobBoard() {
        logger.info("Noticeboard üzerindeki duyuruların detayları doğrulanıyor.");
        for (int i = 0; i < dashboardPage.noticeBoard.size(); i++) {
            Assert.assertTrue("ERROR - Noticeboard Title is NOT displayed at index: " + i, dashboardPage.getNoticeBoardElementTitle(driver, i).isDisplayed());
            Assert.assertTrue("ERROR - Noticeboard Creator info is NOT displayed at index: " + i, dashboardPage.getNoticeBoardElementCreator(driver, i).isDisplayed());
            Assert.assertTrue("ERROR - Noticeboard Created Time is NOT displayed at index: " + i, dashboardPage.getNoticeBoardElementCreatedTime(driver, i).isDisplayed());
            Assert.assertTrue("ERROR - Noticeboard 'More Info' button is NOT displayed at index: " + i, dashboardPage.getNoticeBoardElementMoreInfoButton(driver, i).isDisplayed());
            Assert.assertTrue("ERROR - Noticeboard 'More Info' button is NOT enabled at index: " + i, dashboardPage.getNoticeBoardElementMoreInfoButton(driver, i).isEnabled());
        }
        logger.info("Noticeboard doğrulaması tamamlandı.");
    }

    @And("the monthly learning table should be visible to the user")
    public void theMonthlyLearningTableShouldBeVisibleToTheUser() {
        logger.info("Monthly Learning Schedule tablosunun görünürlüğü kontrol ediliyor.");
        Assert.assertTrue("ERROR - Monthly Learning Schedule table is NOT displayed on the dashboard!", dashboardPage.monthlyLearningSchedule.isDisplayed());
    }
}