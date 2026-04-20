package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage{

    public DashboardPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[.='Dashboard'])[2]")
    public WebElement Dashboard;

    @FindBy(xpath = "//span[.='Quizzes']")
    public WebElement QuizzesButtonu;

    @FindBy(xpath = "//*[.='New quiz']")
    public WebElement NewquizButtonu;

    @FindBy(xpath = "(//*[.='List'])[1]")
    public WebElement ListButtonu;

    @FindBy(xpath = "(//*[.='Results'])[1]")
    public WebElement ResultButtonu;

    @FindBy(xpath = "(//*[.='My results'])[1]")
    public WebElement MyresultsButtonu;

    @FindBy(xpath = "(//*[.='Not Participated'])[1]")
    public WebElement NotParticipatedButtonu;

    @FindBy(xpath = "//h2[.='New quiz']")
    public WebElement NewQuiz;

    @FindBy(xpath = "(//h2[@class='section-title'])[1]")
    public WebElement CommentsStatistics;

    @FindBy(xpath = "(//h2[@class='section-title'])[3]")
    public WebElement StudentResults;

    @FindBy(xpath = "(//h2[@class='section-title'])[3]")
    public WebElement MyQuizzess;

    @FindBy(xpath = "(//h2[@class='section-title'])[1]")
    public WebElement FilterResults;














}
