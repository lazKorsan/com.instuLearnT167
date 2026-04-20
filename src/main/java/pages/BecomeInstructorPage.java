package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BecomeInstructorPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(BecomeInstructorPage.class);


    public BecomeInstructorPage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }






























}
