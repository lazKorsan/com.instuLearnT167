package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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






















}
