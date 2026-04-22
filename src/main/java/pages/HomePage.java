package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(HomePage.class);


    public HomePage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }


    @FindBy (xpath = "(//a[@href=\"/login\"])[1]")
    public WebElement homePageLoginLink;

    @FindBy(xpath = "(//img[@class=\"img-cover\"])[1]")
    public WebElement instulearnLogo;

    @FindBy(xpath = "//ul[@class=\"my-8\"]")
    public WebElement instructorProfilIconDropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[2]/div[2]")
    public WebElement instructorProfileIconInHeader;

    @FindBy(xpath = "//span[text()=\"Profile\"]")
    public WebElement profileLinkInInstructorProfileIconDropdownMenu;










}
