package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReusableMethods;

import java.time.Duration;
import java.util.List;

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

    // ========== TC05 - PRODUCT HOVER + ADD TO CART LOCATORS ==========

    @FindBy(xpath = "//img[@alt='Updated Product Title']")
    public WebElement productImg;

    @FindBy(xpath = "//div[contains(@class, 'stars-card')]")
    public WebElement starsCard;

    @FindBy(xpath = "//button[contains(@class, 'btn-add-product-to-cart')]")
    public WebElement addToCartIcon;

    @FindBy(xpath = "//h2[@class='jq-toast-heading']")
    public WebElement toastMessageHeader;

    @FindBy(xpath = "//div[contains(@class, 'jq-toast-single')]")
    public WebElement toastMessageContainer;

    // ========== TC06 - CHECKOUT & PAYMENT LOCATORS ==========

    @FindBy(id = "navbarShopingCart")
    public WebElement shoppingCartLink;

    @FindBy(xpath = "//a[text()='Go to cart']")
    public WebElement goToCartButton;

    @FindBy(xpath = "//button[text()='Checkout']")
    public WebElement checkoutButton;

    // Payment gateway sayfasi - Pay with Stripe secim kart
    @FindBy(xpath = "//*[@id=\"app\"]/section[2]/form/div[1]/div[1]/label")
    public WebElement payWithStripeOption;

    // Start Payment butonu (stripe secilince aktif olur)
    @FindBy(id = "paymentSubmit")
    public WebElement startPaymentButton;

    // Stripe formundaki email alani
    @FindBy(id = "email")
    public WebElement stripeEmailBox;

    // Stripe kart bilgileri (muhtemelen iframe icinde)
    @FindBy(id = "cardNumber")
    public WebElement cardNumberBox;

    @FindBy(id = "cardExpiry")
    public WebElement cardExpiryBox;

    @FindBy(id = "cardCvc")
    public WebElement cardCvcBox;

    @FindBy(id = "billingName")
    public WebElement billingNameBox;

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

    // ========== TC05 - HOVER + ADD TO CART METHODS ==========

    public void hoverProductImg() {
        logger.info("Urun kartina (productImg) scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                productImg);
        ReusableMethods.bekle(2);

        logger.info("Urun kartina (productImg) HOVER yapiliyor - gizli butonlar ortaya cikacak...");
        Actions actions = new Actions(driver);
        actions.moveToElement(productImg).perform();
        ReusableMethods.bekle(2);
        logger.info("Urun kartina hover tamamlandi, gizli butonlar artik goruniyor.");
    }

    public void hoverStarsCard() {
        logger.info("Stars card elementine HOVER yapiliyor (Actions zinciri)...");
        Actions actions = new Actions(driver);
        actions.moveToElement(starsCard).perform();
        ReusableMethods.bekle(2);
        logger.info("Stars card hover tamamlandi.");
    }

    public void hoverAddToCartIcon() {
        logger.info("Add to Cart ikonu icin productImg -> addToCartIcon actions zinciri baslatiliyor...");
        Actions actions = new Actions(driver);

        actions.moveToElement(productImg)
                .pause(Duration.ofSeconds(1))
                .moveToElement(addToCartIcon)
                .perform();

        ReusableMethods.bekle(2);
        logger.info("Add to Cart ikonu HOVER tamamlandi (tiklama henuz yok).");
    }

    public void clickAddToCartIcon() {
        logger.info("Add to Cart ikonu icin productImg -> addToCartIcon -> CLICK actions zinciri baslatiliyor...");
        Actions actions = new Actions(driver);

        actions.moveToElement(productImg)
                .pause(Duration.ofSeconds(1))
                .moveToElement(addToCartIcon)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();

        logger.info("Add to Cart ikonuna TIKLANDI - toast mesaji bekleniyor...");
        ReusableMethods.bekle(1);
    }

    public String getToastMessageText() {
        logger.info("Toast mesajinin gorunmesi bekleniyor (max 10 saniye)...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'jq-toast-single')]")));

        String toastText = toastElement.getText();
        logger.info("Toast mesaji yakalandi. Icerik:");
        logger.info("---BASLANGIC---");
        logger.info(toastText);
        logger.info("---BITIS---");

        return toastText;
    }

    // ========== TC06 - CHECKOUT & PAYMENT METHODS ==========

    /**
     * Navbar'daki sepet (cart) ikonuna tiklar.
     */
    public void clickShoppingCartLink() {
        logger.info("Navbar'daki sepet (cart) ikonuna tiklaniyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", shoppingCartLink);
        ReusableMethods.bekle(1);
        click(shoppingCartLink);
        ReusableMethods.bekle(2);
        logger.info("Sepet dropdown'i acildi.");
    }

    /**
     * Sepet dropdown'indaki 'Go to cart' butonuna tiklar.
     */
    public void clickGoToCartButton() {
        logger.info("'Go to cart' butonuna tiklaniyor...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        click(goToCartButton);
        ReusableMethods.bekle(3);
        logger.info("Cart sayfasi aciliyor...");
    }

    /**
     * Cart sayfasinda scroll down + Checkout butonuna tiklar.
     */
    public void scrollToAndClickCheckout() {
        logger.info("Checkout butonuna scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                checkoutButton);
        ReusableMethods.bekle(2);

        logger.info("Checkout butonuna tiklaniyor...");
        click(checkoutButton);
        ReusableMethods.bekle(3);
        logger.info("Payment gateway sayfasi aciliyor...");
    }

    /**
     * Pay with Stripe kartini secer ve Start Payment butonuna tiklar.
     */
    public void selectStripeAndStartPayment() {
        logger.info("'Pay with Stripe' secim kartina scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(payWithStripeOption));

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                payWithStripeOption);
        ReusableMethods.bekle(1);

        logger.info("'Pay with Stripe' seciliyor...");
        click(payWithStripeOption);
        ReusableMethods.bekle(2);

        logger.info("'Start Payment' butonuna scroll ediliyor...");
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                startPaymentButton);
        ReusableMethods.bekle(1);

        logger.info("'Start Payment' butonuna tiklaniyor...");
        click(startPaymentButton);

        logger.info("Stripe odeme sayfasi yuklenmesi icin 5 saniye bekleniyor...");
        ReusableMethods.bekle(5);
    }

    /**
     * Stripe odeme formunu doldurur ve Pay butonuna tiklar.
     * NOT: Stripe alanlari iframe icinde olabilir. Bu metod once iframe'e gecmeyi dener.
     *
     * @param cardNumber  Kart numarasi (Stripe test karti: 4242 4242 4242 4242)
     * @param expiry      Son kullanma tarihi (MM/YY formati: "01/30")
     * @param cvc         CVC kodu ("321")
     * @param cardholder  Kart sahibi adi ("Nihat Ozturk")
     */
    public void fillStripePaymentFormAndPay(String cardNumber, String expiry,
                                            String cvc, String cardholder) {
        logger.info("=== STRIPE ODEME FORMU DOLDURULUYOR ===");

        // 1. Email alani (muhtemelen pre-filled, ama kontrol edelim)
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(stripeEmailBox));
            String currentEmail = stripeEmailBox.getAttribute("value");
            logger.info("Stripe email alani mevcut deger: '" + currentEmail + "'");
            if (currentEmail == null || currentEmail.isEmpty()) {
                logger.info("Email bos, dolduruluyor...");
                stripeEmailBox.sendKeys("tural.seyidov.student@instulearn.com");
            } else {
                logger.info("Email zaten dolu, degistirilmiyor.");
            }
        } catch (Exception e) {
            logger.warn("Email alani bulunamadi ya da erisilemedi: " + e.getMessage());
        }

        // 2. Kart bilgilerini doldur - DEFENSIVE: Once ana sayfada dene, olmazsa iframe'leri tara
        logger.info("Kart bilgileri doldurulacak. Once ana sayfada, bulamazsa iframe'de aranacak.");

        fillCardFieldsWithIframeSupport(cardNumber, expiry, cvc, cardholder);

        // 3. Pay butonuna tikla (ana sayfada)
        logger.info("'Pay' butonuna scroll ediliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Iframe'den cikmis olmaliyiz zaten, ama garantiye alalim
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(stripePayButton));

        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                stripePayButton);
        ReusableMethods.bekle(1);

        logger.info("'Pay' butonuna TIKLANIYOR...");
        click(stripePayButton);

        logger.info("Odeme islemi bekleniyor...");
    }

    /**
     * Stripe kart alanlarini doldurur. Hem iframe icinde hem disinda calisir.
     * Defensive yaklasim: Once ana sayfada arar, bulamazsa tum iframe'leri dener.
     */
    private void fillCardFieldsWithIframeSupport(String cardNumber, String expiry,
                                                 String cvc, String cardholder) {
        // Senaryo 1: Alanlar ana sayfada (iframe YOK)
        try {
            logger.info("Senaryo 1: Alanlar ana sayfada mi kontrol ediliyor...");
            driver.switchTo().defaultContent();

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));

            logger.info("Alanlar ana sayfada bulundu, dolduruluyor...");
            fillCardFields(cardNumber, expiry, cvc, cardholder);
            logger.info("Ana sayfadaki alanlar dolduruldu.");
            return;
        } catch (Exception e) {
            logger.info("Ana sayfada bulunamadi, iframe araniyor...");
        }

        // Senaryo 2: Alanlar iframe icinde
        driver.switchTo().defaultContent();
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        logger.info("Sayfada " + iframes.size() + " adet iframe bulundu.");

        boolean filledInIframe = false;

        for (int i = 0; i < iframes.size(); i++) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(i);
                logger.info("Iframe #" + i + "'e gecildi, cardNumber araniyor...");

                WebDriverWait veryShortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                veryShortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));

                logger.info("Iframe #" + i + " icinde cardNumber BULUNDU! Dolduruluyor...");
                fillCardFields(cardNumber, expiry, cvc, cardholder);
                filledInIframe = true;
                logger.info("Iframe #" + i + " icindeki alanlar dolduruldu.");
                break;
            } catch (Exception e) {
                logger.info("Iframe #" + i + "'de cardNumber yok, diger iframe'e geciliyor...");
            }
        }

        driver.switchTo().defaultContent();

        if (!filledInIframe) {
            throw new NoSuchElementException(
                    "Kart alanlari ne ana sayfada ne de herhangi bir iframe'de bulunamadi! " +
                            "Site yapisi degismis olabilir, locator'lar kontrol edilmeli.");
        }
    }

    /**
     * Kart bilgilerini doldurur (ana sayfa veya iframe ayrimi yapmaz - o kisim caller'da).
     */
    private void fillCardFields(String cardNumber, String expiry, String cvc, String cardholder) {
        logger.info("Kart numarasi giriliyor: " + cardNumber);
        WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
        cardNumberField.clear();
        cardNumberField.sendKeys(cardNumber);
        ReusableMethods.bekle(1);

        logger.info("Son kullanma tarihi giriliyor: " + expiry);
        WebElement expiryField = driver.findElement(By.id("cardExpiry"));
        expiryField.clear();
        expiryField.sendKeys(expiry);
        ReusableMethods.bekle(1);

        logger.info("CVC giriliyor: " + cvc);
        WebElement cvcField = driver.findElement(By.id("cardCvc"));
        cvcField.clear();
        cvcField.sendKeys(cvc);
        ReusableMethods.bekle(1);

        logger.info("Cardholder name giriliyor: " + cardholder);
        WebElement nameField = driver.findElement(By.id("billingName"));
        nameField.clear();
        nameField.sendKeys(cardholder);
        ReusableMethods.bekle(1);
    }

    /**
     * Odeme sonrasi "Your payment successfully done..." mesajini dogrular.
     *
     * @return mesajin gorunur olup olmadigi
     */
    public boolean verifyPaymentSuccessMessage() {
        logger.info("Odeme basari mesajinin gorunmesi bekleniyor (max 30 saniye)...");
        logger.info("Stripe odeme islemi uzun surebilir, sabirli olun...");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement successMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[text()='Your payment successfully done...']")));

            logger.info("BASARI MESAJI YAKALANDI!");
            logger.info("Mesaj metni: '" + successMsg.getText() + "'");
            logger.info("Odeme basariyla tamamlandi!");

            ReusableMethods.bekle(2);
            return true;
        } catch (Exception e) {
            logger.error("Basari mesaji 30 saniye icinde bulunamadi!");
            logger.error("Mevcut URL: " + driver.getCurrentUrl());
            logger.error("Exception: " + e.getMessage());
            return false;
        }
    }
}