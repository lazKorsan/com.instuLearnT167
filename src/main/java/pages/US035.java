package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class US035 extends BasePage {

    private static final Logger logger = LogManager.getLogger(US035.class);

    public US035(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== LOCATORS ==========

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;


    // ========== METHODS ==========

    /**
     * Anasayfadaki Login linkine tıklar
     */
    public void clickLoginLink() {
        logger.info("Login linkine tıklanıyor...");
        click(loginLink);
    }

    /**
     * Email alanına değer girer
     */
    public void enterEmail(String email) {
        logger.info("Email giriliyor: " + email);
        type(emailBox, email);
    }

    /**
     * Password alanına değer girer
     */
    public void enterPassword(String password) {
        logger.info("Password giriliyor...");
        type(passwordBox, password);
    }

    /**
     * Login butonuna tıklar
     */
    public void clickLoginButton() {
        logger.info("Login butonuna tıklanıyor...");
        click(loginButton);
    }

    /**
     * Tam login akışı - email + password + submit
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login işlemi tamamlandı.");
    }
}
