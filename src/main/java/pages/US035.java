package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @FindBy(xpath = "//button[@type='submit']")
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

    // ========== FILTER LOCATORS (TC04) ==========

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

    // ========== FILTER METHODS (TC04) ==========

    public void scrollToFilterArea() {
        logger.info("Filtre alanina scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                freeToggle);
        ReusableMethods.bekle(1);
    }

    // ---------- FREE ----------

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

    // ---------- FREE SHIPPING ----------

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

    // ---------- DISCOUNT ----------

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

    // ---------- SORT BY ----------

    public void openSortDropdown() {
        scrollToFilterArea();
        logger.info("Sort by dropdown'a tiklaniyor (aciliyor)...");
        click(sortDropdown);
        ReusableMethods.bekle(1);
    }

    /**
     * Sort by dropdown'dan value'ya gore secim yapar.
     * Kullanilabilir value'lar: newest, expensive, inexpensive, bestsellers, best_rates
     * NOT: "All" option'u value'su bos oldugu icin bu metodla secilemez.
     * "All" icin selectSortByVisibleText("All") kullanin.
     */
    public void selectSortByValue(String value) {
        logger.info("Sort by dropdown'dan value='" + value + "' seciliyor...");
        ReusableMethods.selectByValue(sortDropdown, value);
        ReusableMethods.bekle(1);
    }

    /**
     * Sort by dropdown'dan gorunur metne gore secim yapar.
     * Kullanilabilir text'ler: All, Newest, Highest Price, Lowest Price, Bestsellers, Best Rated
     * "All" option'unun value'su bos oldugu icin bu metod kullanilmalidir.
     */
    public void selectSortByVisibleText(String text) {
        logger.info("Sort by dropdown'dan text='" + text + "' seciliyor...");
        ReusableMethods.selectByVisibleText(sortDropdown, text);
        ReusableMethods.bekle(1);
    }

    public void scrollInsideSortDropdown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        logger.info("Sort dropdown icinde scroll down yapiliyor...");
        js.executeScript(
                "var sel = arguments[0];" +
                        "sel.options[sel.options.length - 1].scrollIntoView({block: 'center'});",
                sortDropdown);
        ReusableMethods.bekle(1);

        logger.info("Sort dropdown icinde scroll up yapiliyor...");
        js.executeScript(
                "var sel = arguments[0];" +
                        "sel.options[0].scrollIntoView({block: 'center'});",
                sortDropdown);
        ReusableMethods.bekle(1);
    }
}