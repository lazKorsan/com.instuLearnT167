package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InstructorsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(InstructorsPage.class);

    public InstructorsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[.='Instructors']")
    public WebElement headerText;

    @FindBy(xpath = "(//input[@name='search'])[2]")
    public WebElement searchBox;

    @FindBy(xpath = "//button[.='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//form[@id='filtersForm']/div[2]")
    public WebElement categorySection;

    public WebElement getCategory(String categoryName, WebDriver driver){
        return driver.findElement(By.xpath("//label[contains(text(),'"+categoryName+"')]"));
    }

    //Instructor Cart Elements

    @FindBy(xpath = "//div[@id='instructorsList']")
    public WebElement instructorsList;

    @FindBy(xpath = "//div[@id='instructorsList']/div")
    public List<WebElement> instructorsListSize;



    public WebElement getInstructorImage(int index){
        return instructorsList.findElements(By.xpath(".//div["+index+"]//img")).get(1);
    }

    public WebElement getInstructorField(int index){
        return instructorsList.findElements(By.xpath(".//div["+index+"]/div/div")).get(1);
    }

    public WebElement getInstructorLike(int index){
        return instructorsList.findElement(By.xpath(".//div["+index+"]/div/div[2]//span"));
    }

    public WebElement getInstructorPrice(int index){
        return instructorsList.findElements(By.xpath(".//div["+index+"]/div/div[4]//span")).get(1);
    }

    public WebElement getReserveAMeetingButton(int index){
        return instructorsList.findElement(By.xpath(".//div["+index+"]/div/div[5]//a"));
    }

    @FindBy(xpath = "(//*[@id=\"plotId\"]/div[2]/div/div/table/tbody/tr[6]/td)[3]")
    public WebElement getDate9;

    @FindBy(xpath = "(//div[@class='position-relative available-times '])[1]/label")
    public WebElement time;

    @FindBy(xpath = "(//div[@class='meeting-type-reserve position-relative']/label)[2]")
    public WebElement meetingType;

    @FindBy(xpath = "//button[.='Reserve a Meeting']")
    public WebElement meetingSubmitButton;

}
