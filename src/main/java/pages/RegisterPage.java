package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[@class='font-12 cursor-pointer px-15 py-10'])[2]")
    public WebElement instructorButton;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullName;

    @FindBy(xpath = "//input[@id='confirm_password']")
    public WebElement confirmPassword;


    // ==================== US20 İÇİN EKLENENLER ====================

    // Account Type butonları (Student ve Organization)
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div/form/div[1]/div/div[1]/label")
    public WebElement studentButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div/form/div[1]/div/div[2]/label")
    public WebElement instructorbutton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div/form/div[1]/div/div[3]/label")
    public WebElement organizationButton;

    // Sol bölümdeki resim (signup formunun yanındaki büyük fotoğraf)
    @FindBy(xpath = "//*[@id='app']/div[3]/div/div[1]/img")
    public WebElement leftSideImage;

    // Signup form başlığı
    @FindBy(xpath = "//h2[normalize-space()='Signup'] | //h1[normalize-space()='Signup']")
    public WebElement signupTitle;

    // Email input
    @FindBy(id = "email")
    public WebElement emailInput;

    // Password input
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Terms & rules checkbox
    // <div class="custom-control custom-checkbox"> içinde bulunan checkbox
    @FindBy(xpath = "//div[@class='custom-control custom-checkbox']//input[@type='checkbox']")
    public WebElement termsCheckbox;

    // "I agree with terms & rules" linki
    @FindBy(xpath = "//a[normalize-space()='terms & rules']")
    public WebElement termsLink;

    // Signup butonu
    @FindBy(xpath = "//*[@id='app']/div[3]/div/div[2]/div/form/button")
    public WebElement signupButton;

    // Already have an account > Login linki (form altındaki)
    @FindBy(xpath = "//a[@href='/login'][normalize-space()='Login']")
    public WebElement loginLink;

    // "Already have an account?" metni
    @FindBy(xpath = "//span[contains(text(),'Already have an account')]")
    public WebElement alreadyHaveAccountText;

    // Hata/Uyarı mesajları (genel)
    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'alert-danger') or contains(@class,'help-block') or contains(@class,'text-danger')]")
    public List<WebElement> errorMessages;

    // Terms zorunlu alan mesajı
    @FindBy(xpath = "//*[contains(text(),'The term field is required')]")
    public WebElement termsRequiredMessage;





}
