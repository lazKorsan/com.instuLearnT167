package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ReusableMethods;

public class US035 extends BasePage {

    private static final Logger logger = LogManager.getLogger(US035.class);

    public US035(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== LOGIN LOCATORS ==========

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit' and text()='Login']")
    public WebElement loginButton;

    // ========== STORE / PRODUCTS LOCATORS ==========

    @FindBy(xpath = "//a[@href='/products']")
    public WebElement storeLink;

    @FindBy(xpath = "//input[@name='search']")
    public WebElement searchBox;

    @FindBy(xpath = "//button[text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "(//a[contains(@href, '/products/')])[1]")
    public WebElement firstProductCard;

    // ========== LEFT FILTER LOCATORS (TC04) ==========

    @FindBy(xpath = "//label[@for='free']")
    public WebElement freeToggle;

    @FindBy(xpath = "//input[@id='free']")
    public WebElement freeCheckbox;

    @FindBy(xpath = "//label[@for='free_shipping']")
    public WebElement freeShippingToggle;

    @FindBy(xpath = "//input[@id='free_shipping']")
    public WebElement freeShippingCheckbox;

    @FindBy(xpath = "//label[@for='discount']")
    public WebElement discountToggle;

    @FindBy(xpath = "//input[@id='discount']")
    public WebElement discountCheckbox;

    @FindBy(xpath = "//select[@name='sort']")
    public WebElement sortDropdown;

    // ========== RIGHT FILTER LOCATORS (TC04) ==========

    @FindBy(xpath = "//label[@for='filterTypesvirtual']")
    public WebElement virtualFilterLabel;

    @FindBy(xpath = "//input[@id='filterTypesvirtual']")
    public WebElement virtualCheckbox;

    @FindBy(xpath = "//label[@for='filterTypesphysical']")
    public WebElement physicalFilterLabel;

    @FindBy(xpath = "//input[@id='filterTypesphysical']")
    public WebElement physicalCheckbox;

    @FindBy(xpath = "(//div[contains(@class,'filters-container')])[1]//button[text()='Filter items']")
    public WebElement typeFilterItemsButton;

    @FindBy(xpath = "//label[@for='filterOptionsOnlyAvailableProducts']")
    public WebElement onlyAvailableProductsLabel;

    @FindBy(xpath = "//input[@id='filterOptionsOnlyAvailableProducts']")
    public WebElement onlyAvailableProductsCheckbox;

    @FindBy(xpath = "(//div[contains(@class,'filters-container')])[2]//button[text()='Filter items']")
    public WebElement optionsFilterItemsButton;

    // ========== CATEGORIES (TC04) ==========

    @FindBy(xpath = "//span[text()='Science Tools']")
    public WebElement scienceToolsCategory;

    // ========== TC05 - PRODUCT HOVER LOCATORS ==========

    @FindBy(xpath = "//img[@alt='Updated Product Title']")
    public WebElement productImg;

    @FindBy(xpath = "//div[contains(@class, 'stars-card')]")
    public WebElement starsCard;

    @FindBy(xpath = "//button[text()='Add to Cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//button[text()='Add to Wishlist']")
    public WebElement addToWishlistButton;

    @FindBy(id = "description-tab")
    public WebElement descriptionTab;


    // ========== LOGIN METHODS ==========

    public void clickLoginLink() {
        logger.info("Login linkine tıklanıyor...");
        click(loginLink);
    }

    public void enterEmail(String email) {
        logger.info("Email giriliyor: " + email);
        type(emailBox, email);
    }

    public void enterPassword(String password) {
        logger.info("Password giriliyor...");
        type(passwordBox, password);
    }

    public void clickLoginButton() {
        logger.info("Login butonuna tıklanıyor...");
        click(loginButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login işlemi tamamlandı.");
    }

    // ========== STORE / PRODUCTS METHODS ==========

    public void clickStoreLink() {
        logger.info("Store linkine tıklanıyor...");
        click(storeLink);

        logger.info("Products sayfasinin yuklenmesi icin 4 saniye bekleniyor...");
        ReusableMethods.bekle(4);

        String currentUrl = driver.getCurrentUrl();
        logger.info("Store sonrasi URL: " + currentUrl);
        if (!currentUrl.contains("/products")) {
            logger.warn("URL /products icermiyor! Mevcut URL: " + currentUrl);
        }
    }

    public void scrollDownWaitAndScrollUp(int waitSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        logger.info("1. scroll down - ilk urune kaydiriliyor...");
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                firstProductCard);
        ReusableMethods.bekle(waitSeconds);

        logger.info("2. scroll down - biraz daha asagi kaydiriliyor...");
        js.executeScript("window.scrollBy({top: 400, behavior: 'smooth'});");
        ReusableMethods.bekle(waitSeconds);

        logger.info("Sayfanin en ustune kaydiriliyor (scroll up)...");
        js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
    }

    // ========== LEFT FILTER METHODS ==========

    public void scrollToFilterArea() {
        logger.info("Filtre alanina scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                freeToggle);
        ReusableMethods.bekle(1);
    }

    public void clickFreeToggle() {
        scrollToFilterArea();
        logger.info("Free toggle'ina tiklaniyor (aktif et)...");
        click(freeToggle);
        ReusableMethods.bekle(2);
    }

    public void deactivateFreeToggle() {
        scrollToFilterArea();
        logger.info("Free toggle'ina tiklaniyor (deactive et)...");
        click(freeToggle);
        ReusableMethods.bekle(1);
    }

    public void clickFreeShippingToggle() {
        scrollToFilterArea();
        logger.info("Free Shipping toggle'ina tiklaniyor (aktif et)...");
        click(freeShippingToggle);
        ReusableMethods.bekle(2);
    }

    public void deactivateFreeShippingToggle() {
        scrollToFilterArea();
        logger.info("Free Shipping toggle'ina tiklaniyor (deactive et)...");
        click(freeShippingToggle);
        ReusableMethods.bekle(1);
    }

    public void clickDiscountToggle() {
        scrollToFilterArea();
        logger.info("Discount toggle'ina tiklaniyor (aktif et)...");
        click(discountToggle);
        ReusableMethods.bekle(2);
    }

    public void deactivateDiscountToggle() {
        scrollToFilterArea();
        logger.info("Discount toggle'ina tiklaniyor (deactive et)...");
        click(discountToggle);
        ReusableMethods.bekle(1);
    }

    public void openSortDropdown() {
        scrollToFilterArea();
        logger.info("Sort by dropdown'a tiklaniyor (aciliyor)...");
        click(sortDropdown);
        ReusableMethods.bekle(1);
    }

    public void selectSortByValue(String value) {
        logger.info("Sort by dropdown'dan value='" + value + "' seciliyor...");
        ReusableMethods.selectByValue(sortDropdown, value);
        ReusableMethods.bekle(1);
    }

    // ========== RIGHT FILTER METHODS ==========

    public void scrollToTypeFilterArea() {
        logger.info("Type filter alanina scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                virtualFilterLabel);
        ReusableMethods.bekle(1);
    }

    public void scrollToOptionsFilterArea() {
        logger.info("Options filter alanina scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                onlyAvailableProductsLabel);
        ReusableMethods.bekle(1);
    }

    public void clickVirtualFilter() {
        scrollToTypeFilterArea();
        logger.info("Virtual filter'ina tiklaniyor...");
        click(virtualFilterLabel);
        ReusableMethods.bekle(2);
    }

    public void clickTypeFilterItemsButton() {
        scrollToTypeFilterArea();
        logger.info("Type Filter items butonuna tiklaniyor...");
        click(typeFilterItemsButton);
        ReusableMethods.bekle(2);
    }

    public void clickPhysicalFilter() {
        scrollToTypeFilterArea();
        logger.info("Physical filter'ina tiklaniyor...");
        click(physicalFilterLabel);
        ReusableMethods.bekle(2);
    }

    public void clickOnlyAvailableProductsFilter() {
        scrollToOptionsFilterArea();
        logger.info("Only Available Products filter'ina tiklaniyor...");
        click(onlyAvailableProductsLabel);
        ReusableMethods.bekle(2);
    }

    public void clickOptionsFilterItemsButton() {
        scrollToOptionsFilterArea();
        logger.info("Options Filter items butonuna tiklaniyor...");
        click(optionsFilterItemsButton);
        ReusableMethods.bekle(2);
    }

    // ========== CATEGORIES METHOD ==========

    public void clickScienceToolsCategory() {
        logger.info("Science Tools kategorisine scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                scienceToolsCategory);
        ReusableMethods.bekle(1);

        logger.info("Science Tools kategorisine tiklaniyor...");
        click(scienceToolsCategory);
        ReusableMethods.bekle(3);
        logger.info("Science Tools sayfasi acildi.");

        logger.info("Filtrelenmis urunleri gormek icin scroll down yapiliyor...");
        js.executeScript("window.scrollBy({top: 600, behavior: 'smooth'});");
        ReusableMethods.bekle(2);
        logger.info("Filtrelenmis urunler goruntulendi.");
    }

    // ========== TC05 - HOVER METHODS ==========

    /**
     * Bir elemente hover yapar (uzerine gelir), tiklamaz.
     * Selenium Actions class kullanir.
     */
    private void hoverElement(WebElement element, String elementName) {
        logger.info(elementName + " elementine scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                element);
        ReusableMethods.bekle(1);

        logger.info(elementName + " elementine HOVER yapiliyor (tiklama yok!)...");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        ReusableMethods.bekle(2); // Hover efektini gormek icin bekle
        logger.info(elementName + " hover tamamlandi.");
    }

    /**
     * Updated Product Title urununun resmine hover yapar.
     */
    public void hoverProductImg() {
        hoverElement(productImg, "Updated Product Title resmi (productImg)");
    }

    /**
     * Stars card alanina hover yapar.
     */
    public void hoverStarsCard() {
        hoverElement(starsCard, "Stars card");
    }

    /**
     * Add to Cart butonuna hover yapar (TIKLAMA YOK).
     */
    public void hoverAddToCartButton() {
        hoverElement(addToCartButton, "Add to Cart butonu");
    }

    /**
     * Add to Wishlist butonuna hover yapar (TIKLAMA YOK).
     */
    public void hoverAddToWishlistButton() {
        hoverElement(addToWishlistButton, "Add to Wishlist butonu");
    }

    /**
     * Sayfayi asagi kaydirir (description tab'ini gormek icin).
     */
    public void scrollDownForDescription() {
        logger.info("Description tab'i gormek icin sayfa asagi kaydiriliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy({top: 500, behavior: 'smooth'});");
        ReusableMethods.bekle(2);
    }

    /**
     * Description tab'a hover yapar (TIKLAMA YOK).
     */
    public void hoverDescriptionTab() {
        hoverElement(descriptionTab, "Description tab");
    }
}