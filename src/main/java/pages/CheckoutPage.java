package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== CHECKOUT SAYFASI ====================
    @FindBy(xpath = "//h1[contains(normalize-space(),'Checkout')] | //h2[contains(normalize-space(),'Checkout')]")
    public WebElement checkoutTitle;

    // Pay with Stripe radio/label
    @FindBy(xpath = "//label[contains(normalize-space(),'Pay with Stripe')] | //input[@value='stripe'] | //*[contains(normalize-space(),'Pay with Stripe')]")
    public WebElement payWithStripeOption;

    // Start Payment butonu
    @FindBy(xpath = "//button[normalize-space()='Start Payment'] | //a[normalize-space()='Start Payment'] | //button[contains(normalize-space(),'Start Payment')]")
    public WebElement startPaymentButton;

    // ==================== STRIPE ÖDEME FORMU ====================
    // Stripe iframe (kart bilgileri iframe içinde)
    @FindBy(xpath = "//iframe[contains(@name,'stripe') or contains(@title,'Secure') or contains(@src,'stripe')]")
    public WebElement stripeIframe;

    // Stripe email input
    @FindBy(xpath = "//input[@id='email' or @name='email' or @type='email']")
    public WebElement stripeEmailInput;

    // Kart numarası
    @FindBy(xpath = "//input[@name='cardnumber' or @id='cardNumber' or @autocomplete='cc-number']")
    public WebElement cardNumberInput;

    // Kart son kullanma
    @FindBy(xpath = "//input[@name='exp-date' or @id='cardExpiry' or @autocomplete='cc-exp']")
    public WebElement cardExpiryInput;

    // CVC
    @FindBy(xpath = "//input[@name='cvc' or @id='cardCvc' or @autocomplete='cc-csc']")
    public WebElement cardCvcInput;

    // Kart sahibi adı
    @FindBy(xpath = "//input[@name='billingName' or @id='billingName' or @autocomplete='cc-name']")
    public WebElement cardHolderNameInput;

    // Ülke dropdown
    @FindBy(xpath = "//select[@name='billingCountry' or @id='billingCountry']")
    public WebElement countryDropdown;

    // Pay butonu (Stripe sayfasında)
    @FindBy(xpath = "//button[contains(normalize-space(),'Pay')] | //button[@type='submit' and contains(@class,'SubmitButton')] | //*[@id='stripe-payment-button']")
    public WebElement payButton;

    // ==================== BAŞARI MESAJI ====================
    @FindBy(xpath = "//*[contains(text(),'Payment successful') or contains(text(),'Thank you') or contains(text(),'success') or contains(text(),'Order')]")
    public WebElement paymentSuccessMessage;

    @FindBy(xpath = "//*[contains(@class,'success') or contains(@class,'thank-you') or contains(@class,'confirmation')]")
    public WebElement successContainer;
}

