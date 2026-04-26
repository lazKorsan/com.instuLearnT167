package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReusableMethods;

import java.time.Duration;

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

    // ========== TC02 - CHARGE ACCOUNT LOCATORS ==========

    @FindBy(xpath = "//label[@for='Stripe']")
    public WebElement stripePaymentOption;

    @FindBy(name = "amount")
    public WebElement amountInput;

    @FindBy(id = "submitChargeAccountForm")
    public WebElement payButton;

    // Stripe odeme formu locatorlari
    @FindBy(id = "email")
    public WebElement stripeEmailBox;

    @FindBy(id = "cardNumber")
    public WebElement cardNumberBox;

    @FindBy(id = "cardExpiry")
    public WebElement cardExpiryBox;

    @FindBy(id = "cardCvc")
    public WebElement cardCvcBox;

    @FindBy(id = "billingName")
    public WebElement billingNameBox;

    // Save my information + Telefon
    @FindBy(id = "enableStripePass")
    public WebElement saveInfoCheckbox;

    @FindBy(xpath = "//input[@aria-label='Phone number']")
    public WebElement phoneNumberByAria;

    // YENI: Modal kapat butonu (Stripe Link OTP modal'i icin)
    @FindBy(xpath = "//button[@aria-label='close']")
    public WebElement closeButton;

    // Stripe Pay butonu
    @FindBy(xpath = "//div[contains(@class, 'SubmitButton-IconContainer')]")
    public WebElement stripePayButton;

    // Basari mesaji
    @FindBy(xpath = "//p[text()='Your payment successfully done...']")
    public WebElement paymentSuccessMessage;


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

    public void scrollToFinancialCategory() {
        logger.info("Sidebar'daki Financial kategorisine scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                financialCategory);
        ReusableMethods.bekle(1);
    }

    public void clickFinancialCategory() {
        scrollToFinancialCategory();
        logger.info("Financial kategorisine tiklaniyor (accordion aciliyor)...");
        click(financialCategory);
        ReusableMethods.bekle(2);
        logger.info("Financial accordion acildi, alt linkler artik goruniyor.");
    }

    public void clickFinancialSummaryLink() {
        logger.info("Financial Summary linkine tiklaniyor...");
        click(financialSummaryLink);
        ReusableMethods.bekle(2);
        logger.info("Financial Summary tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    public void clickPayoutLink() {
        logger.info("Payout linkine tiklaniyor...");
        click(payoutLink);
        ReusableMethods.bekle(2);
        logger.info("Payout tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    public void clickChargeAccountLink() {
        logger.info("Charge Account linkine tiklaniyor...");
        click(chargeAccountLink);
        ReusableMethods.bekle(2);
        logger.info("Charge Account tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    public void clickSubscribeLink() {
        logger.info("Subscribe linkine tiklaniyor...");
        click(subscribeLink);
        ReusableMethods.bekle(2);
        logger.info("Subscribe tiklamasi sonrasi URL: " + driver.getCurrentUrl());
    }

    public void scrollDownPageAndWait() {
        logger.info("Sayfada 400px scroll down yapiliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy({top: 400, behavior: 'smooth'});");
        ReusableMethods.bekle(1);
        logger.info("Scroll down tamamlandi, 1 saniye beklendi.");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ========== TC02 - CHARGE ACCOUNT METHODS ==========

    /**
     * Charge Account sayfasinda Stripe odeme yontemini secer.
     */
    public void selectStripePaymentOption() {
        logger.info("Stripe odeme yontemine scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(stripePaymentOption));

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                stripePaymentOption);
        ReusableMethods.bekle(1);

        logger.info("Stripe odeme yontemine tiklaniyor...");
        click(stripePaymentOption);
        ReusableMethods.bekle(1);
    }

    /**
     * Amount alanina tutar girer.
     */
    public void enterAmount(String amount) {
        logger.info("Amount alanina '" + amount + "' giriliyor...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(amountInput));

        amountInput.clear();
        amountInput.sendKeys(amount);
        ReusableMethods.bekle(1);
        logger.info("Amount alanina '" + amount + "' yazildi.");
    }

    /**
     * Charge Account sayfasindaki Pay butonuna tiklar.
     * Tiklamadan sonra DIREKT Stripe odeme formu acilir (OTP yok).
     */
    public void clickPayButton() {
        logger.info("Pay butonuna scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                payButton);
        ReusableMethods.bekle(1);

        logger.info("Pay butonuna tiklaniyor...");
        click(payButton);

        logger.info("Stripe odeme sayfasinin yuklenmesi icin 5 saniye bekleniyor...");
        ReusableMethods.bekle(5);
    }

    /**
     * Stripe odeme formunu doldurur (sadelestirilmis hali).
     *
     * <p><b>Akis:</b></p>
     * <ol>
     *   <li>Email kontrolu (bos ise doldur)</li>
     *   <li>Kart bilgileri (no, expiry, cvc, name)</li>
     *   <li>OTP MODAL kontrolu - varsa X butonu ile kapat</li>
     *   <li>Pay butonuna scroll + tikla</li>
     * </ol>
     *
     * <p><b>NOT:</b> Modal kapatildiktan sonra "Save my information" ve "Phone number"
     * alanlari Stripe tarafindan KALDIRILIYOR (otomatik olarak). Bu yuzden onlari
     * doldurmaya calismiyoruz.</p>
     *
     * @param cardNumber Kart numarasi
     * @param expiry     MM/YY formati
     * @param cvc        CVC kodu
     * @param cardholder Kart sahibi adi
     */
    public void fillStripeFormSimple(String cardNumber, String expiry, String cvc,
                                     String cardholder) {
        logger.info("=== STRIPE ODEME FORMU DOLDURULUYOR (SADELESTIRILMIS) ===");

        // 1. Email kontrolu - bos ise doldur
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(stripeEmailBox));
            String currentEmail = stripeEmailBox.getAttribute("value");
            logger.info("Stripe email mevcut deger: '" + currentEmail + "'");
            if (currentEmail == null || currentEmail.isEmpty()) {
                logger.info("Email bos, dolduruluyor...");
                stripeEmailBox.sendKeys("tural.seyidov.student@instulearn.com");
                ReusableMethods.bekle(1);
            } else {
                logger.info("Email zaten dolu.");
            }
        } catch (Exception e) {
            logger.warn("Email alani bulunamadi: " + e.getMessage());
        }

        // 2. Kart bilgilerini doldur
        logger.info("Kart numarasi giriliyor: " + cardNumber);
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        shortWait.until(ExpectedConditions.visibilityOf(cardNumberBox));
        cardNumberBox.clear();
        cardNumberBox.sendKeys(cardNumber);
        ReusableMethods.bekle(1);

        logger.info("Son kullanma tarihi giriliyor: " + expiry);
        cardExpiryBox.clear();
        cardExpiryBox.sendKeys(expiry);
        ReusableMethods.bekle(1);

        logger.info("CVC giriliyor: " + cvc);
        cardCvcBox.clear();
        cardCvcBox.sendKeys(cvc);
        ReusableMethods.bekle(1);

        logger.info("Cardholder name giriliyor: " + cardholder);
        billingNameBox.clear();
        billingNameBox.sendKeys(cardholder);
        ReusableMethods.bekle(2); // Modal aciliyor olabilir, biraz bekle

        // 3. OTP MODAL KONTROLU - varsa X butonu ile kapat
        handleOtpModalIfPresent();

        // 4. Pay butonuna scroll + tikla
        logger.info("'Pay' butonuna scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait payWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        payWait.until(ExpectedConditions.elementToBeClickable(stripePayButton));

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                stripePayButton);
        ReusableMethods.bekle(2);

        logger.info("'Pay' butonuna TIKLANIYOR...");
        click(stripePayButton);

        logger.info("Odeme islemi bekleniyor...");
    }

    /**
     * Stripe Link OTP modal'ini handle eder (varsa kapatir).
     *
     * <p><b>Stripe Link davranisi:</b> Eger kullanicinin email'i Stripe'in saved info
     * sisteminde kayitliysa, Cardholder name yazilirken (veya bazen otomatik)
     * "Confirm it's you" modal'i acilir ve OTP kodu ister.</p>
     *
     * <p><b>Cozum:</b> Modal'in X butonuna tiklayarak kapatma. Bilinen locator:
     * //button[@aria-label='close']</p>
     *
     * <p>Modal yoksa hicbir sey yapmaz, normal akis devam eder.</p>
     */
    private void handleOtpModalIfPresent() {
        logger.info("OTP modal kontrolu yapiliyor (3 saniye bekleniyor)...");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            // Modal'in X butonunun gorunup gorunmedigini kontrol et
            WebElement closeBtn = shortWait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='close']")));

            logger.info("⚠️  OTP MODAL ACILDI! X (close) butonuna tiklayarak kapatiliyor...");

            try {
                closeBtn.click();
            } catch (Exception e) {
                logger.warn("Normal click basarisiz, JS click deneniyor: " + e.getMessage());
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", closeBtn);
            }

            ReusableMethods.bekle(3); // Modal kapanma animasyonu
            logger.info("✅ Modal kapatildi, normal akis devam edecek.");
        } catch (Exception e) {
            logger.info("OTP modal acilmadi (veya zaten kapali), normal akis devam ediyor.");
        }
    }

    /**
     * Odeme sonrasi basari mesajini dogrular.
     */
    public boolean verifyPaymentSuccessMessage() {
        logger.info("Odeme basari mesajinin gorunmesi bekleniyor (max 30 saniye)...");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement successMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[text()='Your payment successfully done...']")));

            logger.info("BASARI MESAJI YAKALANDI!");
            logger.info("Mesaj metni: '" + successMsg.getText() + "'");

            ReusableMethods.bekle(2);
            return true;
        } catch (Exception e) {
            logger.error("Basari mesaji 30 saniye icinde bulunamadi!");
            logger.error("Mevcut URL: " + driver.getCurrentUrl());
            return false;
        }
    }
}