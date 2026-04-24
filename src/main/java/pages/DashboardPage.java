package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

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

    @FindBy(xpath = "//input[@type='text']")
    public WebElement QuizTitleInput;

    @FindBy(xpath = "(//input[@type='number'])[3]")
    public WebElement PassMarkInput;

    @FindBy(xpath = "(//button[@type='button'])[5]")
    public WebElement CreateButton;

    @FindBy(xpath = "//table[@class='table text-center custom-table']")
    public WebElement NewQuizTableTitle;

    @FindBy(xpath = "//button[@id='add_multiple_question']")
    public WebElement Add_a_Multiple_choice;

    @FindBy(xpath = "(//*[.='Multiple choice question'])[2]")
    public WebElement MultipleChoiceQuestion;

    @FindBy(xpath = "(//input[@*='ajax[title]'])[3]")
    public WebElement QuestionTitleInput;

    @FindBy(xpath = "(//input[@*='ajax[grade]'])[3]")
    public WebElement GradeInput;

    @FindBy(xpath = "(//*[@*='button'])[32]")
    public WebElement Add_an_AnswerButtonu;

    @FindBy(xpath = "(//input[@class=' form-control '])[2]")
    public WebElement AnswetTitleInput;

    @FindBy(xpath = "(//input[@class=' form-control '])[3]")
    public WebElement AnswetTitleInput2;

    @FindBy(xpath = "(//*[@*='checkbox'])[6]")
    public WebElement CorrectAnswerButtonu;

    @FindBy(xpath = "(//*[.='Save'])[4]")
    public WebElement SaveButtonu;

    @FindBy(xpath = "//*[@*='quizzes_questions']")
    public List<WebElement> QuestionsList;

    @FindBy(xpath = "//span[text()=\"Marketing\"]")
    public WebElement sidebarMarketingLink;

    @FindBy(xpath = "//a[text()=\"Discounts\"]")
    public WebElement discountsLinkByMarketing;

    @FindBy(xpath = "//a[text()=\"Promotions\"]")
    public WebElement promotionsLinkByMarketing;


    @FindBy(xpath = "//button[@id='add_descriptive_question']")
    public WebElement AddDescriptive;

    @FindBy(xpath = "(//*[@*='section-title after-line'])[6]")
    public WebElement NewDescriptiveQuestion;

    @FindBy(xpath = "(//*[@*='ajax[title]'])[3]")
    public WebElement QuestionDescriptive;

    @FindBy(xpath = "(//*[@*='ajax[grade]'])[3]")
    public WebElement GradeDescriptive;

    @FindBy(xpath = "(//*[@*='ajax[correct]'])[2]")
    public WebElement Descriptive;

    @FindBy(xpath = "(//button[@type='button'])[22]")
    public WebElement SaveDescriptive;

    @FindBy(xpath = "(//*[.='List'])[1]")
    public WebElement List2;

    @FindBy(xpath = "//table//tbody//tr/td[2]")
    public List<WebElement> Questions;

    @FindBy(xpath = "(//div[@class='simplebar-content'])[10]")
    public List<WebElement> sidebarLinks;

    @FindBy(xpath = "(//a[@href='/panel/notifications'])[2]")
    public WebElement viewAllEventsLink;

    @FindBy(xpath = "(//div[@class='row'])[1]//a")
    public List<WebElement> dashboardBodyLinks;

    @FindBy(xpath = "((//div[@class='row'])[1]//div[@class='text-center'])[1]")
    public WebElement dashboardBodyAccountBalance;

    @FindBy(xpath = "//div[@class='bg-white noticeboard rounded-sm panel-shadow py-10 py-md-20 px-15 px-md-30']//div[@class='noticeboard-item py-15']")
    public List<WebElement> noticeBoard;

    public WebElement getNoticeBoardElementTitle(WebDriver driver,int index){
        return noticeBoard.get(index).findElement( By.xpath(".//h4[@class='js-noticeboard-title font-weight-500 text-secondary']"));
    }

    public WebElement getNoticeBoardElementCreator(WebDriver driver,int index){
        return noticeBoard.get(index).findElement( By.xpath("(.//div[@class='font-12 text-gray mt-5']//span)[1]"));
    }

    public WebElement getNoticeBoardElementCreatedTime(WebDriver driver,int index){
        return noticeBoard.get(index).findElement( By.xpath("(.//div[@class='font-12 text-gray mt-5']//span)[2]"));
    }

    public WebElement getNoticeBoardElementMoreInfoButton(WebDriver driver,int index){
        return noticeBoard.get(index).findElement( By.xpath("(.//button[.='More info'])[1]"));
    }

    @FindBy(xpath = "//canvas[@id='myChart']")
    public WebElement monthlyLearningSchedule;

}
