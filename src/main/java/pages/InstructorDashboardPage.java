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

    @FindBy (xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[1]/div[1]/h1")
    public WebElement dashboardPageTitle;

    @FindBy (xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]")
    public WebElement dashboardPageSidebar;

    @FindBy(xpath = "//*[@id=\"panel-sidebar-scroll\"]/div[1]/div[2]/div/div/div/li[14]/a/span[2]")
    public WebElement sidebarMyProfileLink;


}
