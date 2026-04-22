package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[.='Login']")
	private WebElement loginButton;

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Login işlemi
	public void login(String email, String password) {
		type(emailInput, email);
		type(passwordInput, password);
		click(loginButton);
	}


}
