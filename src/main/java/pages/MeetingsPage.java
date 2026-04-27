package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MeetingsPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(MeetingsPage.class);


    public MeetingsPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()=\"Meeting statistics\"]")
    public WebElement MeetingStatisticsTitleByMyReservations;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[1]/div/div/div[1]")
    public WebElement openMeetingByStatistics;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[1]/div/div/div[2]")
    public WebElement totalMeetingByStatistics;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[1]/div/div/div[3]")
    public WebElement totalHoursByStatistics;

    @FindBy(xpath = "//h2[text()=\"Meetings list\"]")
    public WebElement meetingListTitleByMyReservations;

    @FindBy(className = "table-responsive")
    public WebElement meetingListTableByMyReservations;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[3]/div[2]/div/div/div/table/tbody/tr/td[3]")
    public List<WebElement> meetingListDayList;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[3]/div[2]/div/div/div/table/tbody/tr/td[1]")
    public List<WebElement> meetingListInstructorList;

    @FindBy(xpath = "//*[@id=\"day\"]")
    public WebElement meetingListDayDropdown;

    @FindBy(name = "instructor_id")
    public WebElement instructorIdDropdown;

    @FindBy(xpath = "//button[text()=\"Show Results\"]")
    public WebElement showResultsButton;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[3]/div[2]/div/div/div/table/tbody/tr/td[9]/div/button")
    public WebElement meetingButton;

    @FindBy(xpath = "//*[@id=\"panel_app\"]/div[2]/div[3]/section[3]/form/div/div")
    public WebElement showOnlyOpenMeetingSwitch;

    @FindBy(xpath = "//*[@id=\"panel_app\"]//table")
    public WebElement meetingRequestsTable;








}
