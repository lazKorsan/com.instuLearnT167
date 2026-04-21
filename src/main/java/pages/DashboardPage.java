package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h1[@class=\"section-title\"]")
    public WebElement dashboardTitle;

    @FindBy (xpath = "(//div[@class=\"simplebar-mask\"])[10]")
    public WebElement sidebar;

    @FindBy (xpath = "//*[text()=\"My Profile\"]")
    public WebElement sidebarMyProfileLink;

}
