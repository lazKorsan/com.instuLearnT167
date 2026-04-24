package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InstructorProfilePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(InstructorProfilePage.class);

    public InstructorProfilePage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//*[@id=\"app\"]/section[2]/div/div[1]/div[1]/div[2]/h1")
    public WebElement nameText;

    @FindBy (id = "followToggle")
    public WebElement followIcon;

    @FindBy (xpath = "//a[text()=\"About\"]")
    public WebElement aboutLink;

    @FindBy (xpath = "(//a[text()=\"Courses\"])[2]")
    public WebElement coursesLink;

    @FindBy (xpath = "//a[text()=\"Articles\"]")
    public WebElement articlesLink;

    @FindBy (xpath = "//a[text()=\"Badges\"]")
    public WebElement badgesLink;

    @FindBy (xpath = "//a[text()=\"Reserve a meeting\"]")
    public WebElement reserveMeetingLink;








}
