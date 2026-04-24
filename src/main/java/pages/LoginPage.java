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

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	@FindBy(xpath = "//h1[normalize-space()='Login'] | //h2[normalize-space()='Login']")
	public WebElement loginTitle;

	@FindBy(xpath = "//input[@id='email' or @name='email' or @type='email']")
	public WebElement emailInput;




	// Login işlemi
	public void login(String username, String password) {
		type(usernameInput, username);
		type(passwordInput, password);
		click(loginButton);
	}


}
