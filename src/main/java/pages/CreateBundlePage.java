package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ReusableMethods;

public class CreateBundlePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CreateBundlePage.class);


    public CreateBundlePage(WebDriver driver) {
        super(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    // Thumbnail yükleme metodu
    public void uploadThumbnail(String imagePath) {
        try {
            // Butona tıkla (dosya yöneticisini açar)
            WebElement thumbnailBtn = driver.findElement(By.xpath("(//button[@class='input-group-text panel-file-manager'])[1]"));
            thumbnailBtn.click();
            ReusableMethods.bekle(2);

            // Dosya yolu inputuna değer gönder
            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            fileInput.sendKeys(imagePath);
            ReusableMethods.bekle(2);

            // Confirm butonuna tıkla
            WebElement confirmBtn = driver.findElement(By.xpath("//button[@class='confirm-btn']"));
            confirmBtn.click();

            logger.info("✅ Thumbnail başarıyla yüklendi: " + imagePath);
        } catch (Exception e) {
            logger.error("❌ Thumbnail yüklenemedi: " + e.getMessage());
        }
    }

    // Cover Image yükleme metodu
    public void uploadCoverImage(String imagePath) {
        try {
            WebElement coverBtn = driver.findElement(By.xpath("(//button[@class='input-group-text panel-file-manager'])[2]"));
            coverBtn.click();
            ReusableMethods.bekle(2);

            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            fileInput.sendKeys(imagePath);
            ReusableMethods.bekle(2);

            WebElement confirmBtn = driver.findElement(By.xpath("//button[@class='confirm-btn']"));
            confirmBtn.click();

            logger.info("✅ Cover image başarıyla yüklendi: " + imagePath);
        } catch (Exception e) {
            logger.error("❌ Cover image yüklenemedi: " + e.getMessage());
        }
    }

    // Veya direkt input alanına text göndererek (eğer dosya yöneticisi destekliyorsa)
    public void setThumbnailPath(String imageUrl) {
        WebElement thumbnailInput = driver.findElement(By.id("thumbnail"));
        thumbnailInput.clear();
        thumbnailInput.sendKeys(imageUrl);
        logger.info("✅ Thumbnail path set edildi: " + imageUrl);
    }

    public void setCoverImagePath(String imageUrl) {
        WebElement coverInput = driver.findElement(By.id("cover_image"));
        coverInput.clear();
        coverInput.sendKeys(imageUrl);
        logger.info("✅ Cover image path set edildi: " + imageUrl);
    }
}
