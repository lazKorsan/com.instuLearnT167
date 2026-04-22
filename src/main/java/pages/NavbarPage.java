package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavbarPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(NavbarPage.class);

    public NavbarPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getNavbarLink(String linkText, WebDriver driver) {
        return driver.findElement(By.xpath("//ul[@class='navbar-nav mr-auto d-flex align-items-center']//a[.='" + linkText + "']"));
    }

    @FindBy(xpath = "(//button[@id='navbarShopingCart'])[1]")
    public WebElement navbarShoppingCart;
}
