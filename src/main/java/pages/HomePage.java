package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Ana sayfa üst barındaki Register linki
    @FindBy(xpath = "//a[contains(@href,'/register')][normalize-space()='Register']")
    public WebElement registerLink;

    // Ana sayfa üst barındaki Login linki
    @FindBy(xpath = "//a[contains(@href,'/login')][normalize-space()='Login']")
    public WebElement loginLink;

    // Site logosu
    @FindBy(xpath = "//img[@alt='site logo']")
    public WebElement siteLogo;
}








