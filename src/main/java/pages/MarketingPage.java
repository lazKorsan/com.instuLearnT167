package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(xpath = "//table[contains(@class,'custom-table')]//tbody/tr/td[1]")
    public List<WebElement> discountsNameList;

    @FindBy(xpath = "//a[text()=\"Promotions\"]")
    public WebElement promotionsLink;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section/div/div")
    public List<WebElement> promotionsList;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section/div//button")
    public List<WebElement> promotionsPurchaseButtonList;

    @FindBy(xpath = "//*[@id=\"payPromotionModal\"]/form/div[1]/div/div[3]/select")
    public WebElement selectCourseInPromotions;

    @FindBy(xpath = "(//button[text()=\"Pay\"])[2]")
    public WebElement promotionsPay;

    @FindBy(xpath = "//*[@id=\"app\"]/section[2]/form/div[1]/div[1]")
    public WebElement stripeÖdemeYöntemi;

    @FindBy(id = "paymentSubmit")
    public WebElement paymentSubmitButton;









}
