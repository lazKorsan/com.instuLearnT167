package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CartPage.class);


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='container']/section/div")
    public List<WebElement> itemList;

    public boolean isItemExists(String itemName){
        for (WebElement item : itemList){
            String itemNameResult = item.findElement(By.xpath(".//div[2]//div[@class='d-flex flex-column']")).getText().trim();

            if (itemNameResult.equals(itemName))return true;
        }
        return false;
    }
}
