package stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.BecomeInstructorPage;

public class US039BecomeInstructorSteps {

    private static final Logger logger = LogManager.getLogger(BecomeInstructorPage.class);
    WebDriver driver = Hooks.getDriver();
    BecomeInstructorPage becomeInstructorPage = new BecomeInstructorPage(driver);


}
