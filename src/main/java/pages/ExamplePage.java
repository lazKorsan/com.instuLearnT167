package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableMethods;


public class ExamplePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(ExamplePage.class);


    public ExamplePage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

     @FindBy(xpath = "//input[@id='email']")
     public WebElement mailBox;

     @FindBy(xpath = "//input[@id=\"password\"]")
     public WebElement passwordBox;

     @FindBy(xpath = "//button[@class='btn btn-primary btn-block mt-20']")
     public WebElement submitButton;


     public void loginMethod(String mail, String password)  {

         logger.debug("🐞 Kullanıcı loginMethod çağrısı ile login oluyor");
         mailBox.sendKeys(mail);
         ReusableMethods.bekle(2);
         passwordBox.sendKeys(password);
         ReusableMethods.bekle(2);
         submitButton.click();
         ReusableMethods.bekle(2);
     }


}
