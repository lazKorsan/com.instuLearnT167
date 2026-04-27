package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage extends BasePage {

    public BlogPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "(//a[@class='nav-link'])[5]")
    public WebElement BlogButton;

    @FindBy(xpath = "//h1[@class='text-white font-30 mb-15']")
    public WebElement BlogText;

    @FindBy(xpath = "(//input[@name='search'])[2]")
    public WebElement SearchTextboxButtonu;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement SearchButtonu;

    @FindBy(xpath = "(//a[@class='font-14 text-dark-blue d-block mt-15'])[4]")
    public WebElement OnlineeEducation;

    @FindBy(xpath = "//*[@alt='TESTmakaleTEST']")
    public WebElement OnlineeEducationResault;

    @FindBy(xpath = "//span[@class='badge created-at d-flex align-items-center']")
    public WebElement productDate;

    @FindBy(xpath = "//h3[@class='blog-grid-title mt-10']")
    public WebElement blogIcerigiTitle;

    @FindBy(xpath = "//div[@class='mt-20 blog-grid-desc']")
    public WebElement blogIcerigi;

    @FindBy(xpath = "//div[@class='blog-grid-footer d-flex align-items-center justify-content-between mt-15']")
    public WebElement productManager;

    @FindBy(xpath = "//textarea[@name='comment']")
    public WebElement commentText;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary']")
    public WebElement postCommet;


















}