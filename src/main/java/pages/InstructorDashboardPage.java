package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InstructorDashboardPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(InstructorDashboardPage.class);


    public InstructorDashboardPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h1[text()=\"Dashboard\"]")
    public WebElement dashboardPageTitle;

    @FindBy (xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]")
    public WebElement dashboardPageSidebar;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[14]/a/span[2]")
    public WebElement sidebarMyProfileLink;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[9]/a/span[2]")
    public WebElement sidebarMarketingLink;

    @FindBy(xpath = "//*[@id=\"marketingCollapse\"]/ul/li[1]/a")
    public WebElement discountsLinkByMarketing;

    @FindBy(xpath = "//*[@id=\"marketingCollapse\"]/ul/li[2]/a")
    public WebElement promotionsLinkByMarketing;

    @FindBy(xpath = "//span[text()=\"Meetings\"]")
    public WebElement sidebarMeetingsLink;

    @FindBy(xpath = "//a[text()=\"My reservations\"]")
    public WebElement myReservationsByMeetings;

    @FindBy(xpath = "//a[text()=\"Requests\"]")
    public WebElement requestByMeetings;

    @FindBy(xpath = "//a[text()=\"Settings\"]")
    public WebElement settingsByMeetings;

    @FindBy(xpath = "//a[text()=\"Requests\"]")
    public WebElement requestLinkByMeetings;





}
