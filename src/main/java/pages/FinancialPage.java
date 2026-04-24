package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ReusableMethods;

public class FinancialPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(FinancialPage.class);

    public FinancialPage(WebDriver driver) {
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

    // ========== TC01 - SIDEBAR FINANCIAL LOCATORS ==========

    @FindBy(xpath = "//span[text()='Financial']")
    public WebElement financialCategory;

    @FindBy(xpath = "//a[text()='Financial summary']")
    public WebElement financialSummaryLink;

    @FindBy(xpath = "//a[text()='Payout']")
    public WebElement payoutLink;

    @FindBy(xpath = "//a[text()='Charge account']")
    public WebElement chargeAccountLink;

    @FindBy(xpath = "//a[text()='Subscribe']")
    public WebElement subscribeLink;


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

    // ========== TC01 - FINANCIAL SIDEBAR METHODS ==========

    /**
     * Financial kategorisine scroll eder.
     */
    public void scrollToFinancialCategory() {
        logger.info("Sidebar'daki Financial kategorisine scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                financialCategory);
        ReusableMethods.bekle(1);
    }

    /**
     * Financial kategorisine tiklar (accordion'u acar).
     */
    public void clickFinancialCategory() {
        scrollToFinancialCategory();
        logger.info("Financial kategorisine tiklaniyor (accordion aciliyor)...");
        click(financialCategory);
        ReusableMethods.bekle(2);
        logger.info("Financial accordion acildi, alt linkler artik goruniyor.");
    }

    /**
     * Financial Summary linkine tiklar ve sayfanin acilmasi icin 2 saniye bekler.
     */
    public void clickFinancialSummaryLink() {
        logger.info("Financial Summary linkine tiklaniyor...");
        click(financialSummaryLink);
        ReusableMethods.bekle(2);
        logger.info("Financial Summary tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    /**
     * Payout linkine tiklar ve sayfanin acilmasi icin 2 saniye bekler.
     */
    public void clickPayoutLink() {
        logger.info("Payout linkine tiklaniyor...");
        click(payoutLink);
        ReusableMethods.bekle(2);
        logger.info("Payout tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    /**
     * Charge Account linkine tiklar ve sayfanin acilmasi icin 2 saniye bekler.
     */
    public void clickChargeAccountLink() {
        logger.info("Charge Account linkine tiklaniyor...");
        click(chargeAccountLink);
        ReusableMethods.bekle(2);
        logger.info("Charge Account tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    /**
     * Subscribe linkine tiklar ve sayfanin acilmasi icin 2 saniye bekler.
     */
    public void clickSubscribeLink() {
        logger.info("Subscribe linkine tiklaniyor...");
        click(subscribeLink);
        ReusableMethods.bekle(2);
        logger.info("Subscribe tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    /**
     * Sayfada genel scroll down yapar (400 pixel asagi) ve 1 saniye bekler.
     */
    public void scrollDownPageAndWait() {
        logger.info("Sayfada 400px scroll down yapiliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy({top: 400, behavior: 'smooth'});");
        ReusableMethods.bekle(1);
        logger.info("Scroll down tamamlandi, 1 saniye beklendi.");
    }

    /**
     * Mevcut URL'i doner. Step definition'da URL dogrulama yapmak icin kullanilir.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}