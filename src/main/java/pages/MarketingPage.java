package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketingPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(MarketingPage.class);


    public MarketingPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h2[text()=\"Discounts\"]")
    public WebElement discountsTitle;

    @FindBy (id = "formSubmit")
    public WebElement createButton;

    @FindBy (xpath = "(//*[@type=\"text\"])[1]")
    public WebElement titleBox;

    @FindBy (xpath = "(//*[@type=\"text\"])[2]")
    public WebElement amountBox;

    @FindBy (xpath = "(//*[@type=\"text\"])[3]")
    public WebElement fromBox;

    @FindBy (xpath = "(//*[@type=\"text\"])[4]")
    public WebElement toBox;

    @FindBy (name = "webinar_id")
    public WebElement courseBox;

    @FindBy (xpath = "/html/body/script[12]")
    public WebElement successfullyMessage;








}
