package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[.='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//h1[normalize-space()='Login'] | //h2[normalize-space()='Login']")
	public WebElement loginTitle;



	// Login işlemi
	public void login(String email, String password) {
		type(emailInput, email);
		type(passwordInput, password);
		click(loginButton);
	}


}
