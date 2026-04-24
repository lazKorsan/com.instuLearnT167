package stepdefinitions;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ReusableMethods;

public class US20_RegisterStepDefinitions {

    // Field isimleri method isimleriyle ÇAKIŞMASIN diye başına _ konuldu
    // Sadece homePage() / registerPage() / loginPage() METOTLARI üzerinden erişiyoruz
    private HomePage _homePage;
    private RegisterPage _registerPage;
    private LoginPage _loginPage;

    // Scenario Outline'da hangi alanın boş bırakılacağını taşımak için
    private static String bosBirakilacakAlan = "";

    // Driver'ı her adımda güncel olarak alan yardımcı metot
    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    // Page nesnelerini ilk ihtiyaç anında oluşturan lazy initializer'lar
    private HomePage homePage() {
        if (_homePage == null) _homePage = new HomePage(driver());
        return _homePage;
    }

    private RegisterPage registerPage() {
        if (_registerPage == null) _registerPage = new RegisterPage(driver());
        return _registerPage;
    }

    private LoginPage loginPage() {
        if (_loginPage == null) _loginPage = new LoginPage(driver());
        return _loginPage;
    }

    // ========================================================================
    // BACKGROUND
    // ========================================================================
    @Given("Kullanici {string} adresine gider")
    public void kullanici_adrese_gider(String url) {
        try {
            driver().get(url);
        } catch (org.openqa.selenium.TimeoutException te) {
            System.err.println("Page load timeout, stopping page load and continuing: " + te.getMessage());
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver()).executeScript("window.stop();");
            } catch (Exception ignored) { }
        }
    }

    // ========================================================================
    // TC01 - Ana sayfa üst barında Register linki görünür ve aktif olmalıdır
    // ========================================================================
    @When("Kullanici ana sayfanin ust barini kontrol eder")
    public void kullanici_ana_sayfa_ust_bar_kontrol() {
        ReusableMethods.waitForVisibility(driver(), homePage().registerLink, 10);
    }

    @Then("{string} linkinin gorunur oldugu dogrulanir")
    public void link_gorunur_dogrulanir(String linkAdi) {
        WebElement link = getLinkByName(linkAdi);
        ReusableMethods.highLightToElement(link);
        Assert.assertTrue(linkAdi + " linki görünür değil!", link.isDisplayed());
    }

    @And("{string} linkinin aktif \\(tiklanabilir) oldugu dogrulanir")
    public void link_aktif_dogrulanir(String linkAdi) {
        WebElement link = getLinkByName(linkAdi);
        Assert.assertTrue(linkAdi + " linki aktif değil!", link.isEnabled());
    }

    private WebElement getLinkByName(String linkAdi) {
        String url = driver().getCurrentUrl();
        if (linkAdi.equalsIgnoreCase("Register")) {
            return homePage().registerLink;
        }
        if (linkAdi.equalsIgnoreCase("Login")) {
            return url.contains("/register") ? registerPage().loginLink : homePage().loginLink;
        }
        throw new IllegalArgumentException("Bilinmeyen link: " + linkAdi);
    }

    // ========================================================================
    // TC02 & TC03 - Register sayfasına yönlendirme + sol resim + sağ form
    // ========================================================================
    @When("Kullanici ust bardaki {string} linkine tiklar")
    public void kullanici_ust_bar_linke_tiklar(String linkAdi) {
        if (linkAdi.equalsIgnoreCase("Register")) {
            ReusableMethods.waitForClickablility(driver(), homePage().registerLink, 10).click();
        } else if (linkAdi.equalsIgnoreCase("Login")) {
            ReusableMethods.waitForClickablility(driver(), homePage().loginLink, 10).click();
        }
        ReusableMethods.bekle(2);
    }

    @Then("Kullanici Register sayfasina yonlendirilir")
    public void kullanici_register_sayfasina_yonlendirilir() {
        String currentUrl = driver().getCurrentUrl();
        Assert.assertTrue("Register sayfasına yönlendirilmedi! URL: " + currentUrl,
                currentUrl.contains("/register"));
    }

    @And("Register sayfasinin sol bolumundeki resim gorunur olmalidir")
    public void register_sol_bolum_resim_gorunur() {
        ReusableMethods.waitForVisibility(driver(), registerPage().leftSideImage, 10);
        ReusableMethods.highLightToElement(registerPage().leftSideImage);
        Assert.assertTrue("Sol bölümdeki resim görünür değil!",
                registerPage().leftSideImage.isDisplayed());
    }

    @Then("Register sayfasinin sag bolumunde {string} formu gorunur olmalidir")
    public void register_sag_bolum_form_gorunur(String formAdi) {
        ReusableMethods.waitForVisibility(driver(), registerPage().signupTitle, 10);
        ReusableMethods.highLightToElement(registerPage().signupTitle);
        Assert.assertTrue(formAdi + " formu görünür değil!",
                registerPage().signupTitle.isDisplayed());
    }

    // ========================================================================
    // TC04 - Signup butonu görünür ve aktif olmalıdır
    // ========================================================================
    @Given("Kullanici {string} sayfasinda bulunuyor")
    public void kullanici_sayfasinda_bulunuyor(String sayfa) {
        String baseUrl = "https://qa.instulearn.com/";
        String hedefPath = "/" + sayfa.toLowerCase();
        if (!driver().getCurrentUrl().contains(hedefPath)) {
            driver().get(baseUrl + sayfa.toLowerCase());
        }
        ReusableMethods.bekle(2);
    }

    @When("Kullanici Signup formunu kontrol eder")
    public void kullanici_signup_formunu_kontrol_eder() {
        ReusableMethods.scrollToElement(driver(), registerPage().signupButton);
    }

    @Then("Signup formunun altinda {string} butonunun gorunur oldugu dogrulanir")
    public void signup_buton_gorunur_dogrulanir(String butonAdi) {
        ReusableMethods.highLightToElement(registerPage().signupButton);
        Assert.assertTrue(butonAdi + " butonu görünür değil!",
                registerPage().signupButton.isDisplayed());
    }

    @And("{string} butonunun aktif \\(tiklanabilir) oldugu dogrulanir")
    public void signup_buton_aktif_dogrulanir(String butonAdi) {
        WebElement button = butonAdi.equalsIgnoreCase("Signup")
                ? registerPage().signupButton
                : registerPage().loginLink;
        Assert.assertTrue(butonAdi + " butonu aktif değil!", button.isEnabled());
    }

    // ========================================================================
    // TC05 - Account Type butonları (Student / Instructor / Organization)
    // ========================================================================
    @When("Kullanici Account type alanindaki {string} butonunu kontrol eder")
    public void account_type_buton_kontrol(String accountType) {
        WebElement button = getAccountTypeButton(accountType);
        ReusableMethods.scrollToElementWithWait(driver(), button, 10);
    }

    @Then("{string} butonunun gorunur oldugu dogrulanir")
    public void account_type_gorunur_dogrulanir(String accountType) {
        WebElement button = getAccountTypeButton(accountType);
        ReusableMethods.highLightToElement(button);
        Assert.assertTrue(accountType + " butonu görünür değil!", button.isDisplayed());
    }

    @And("{string} butonunun secilebilir oldugu dogrulanir")
    public void account_type_secilebilir_dogrulanir(String accountType) {
        WebElement button = getAccountTypeButton(accountType);
        try {
            ReusableMethods.waitForClickablility(driver(), button, 10).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", button);
        }
        ReusableMethods.bekle(1);
        Assert.assertTrue(accountType + " butonu seçilemiyor!", button.isEnabled());
    }

    private WebElement getAccountTypeButton(String type) {
        switch (type.toLowerCase()) {
            case "student":      return registerPage().studentButton;
            case "instructor":   return registerPage().instructorButton;
            case "organization": return registerPage().organizationButton;
            default:
                throw new IllegalArgumentException("Bilinmeyen account type: " + type);
        }
    }

    // ========================================================================
    // TC06 - Zorunlu alanlar (Email, Full Name, Password, Retype Password)
    // ========================================================================
    @When("Kullanici {string} alanini bos birakir")
    public void alan_bos_birakir(String alan) {
        bosBirakilacakAlan = alan;
        System.out.println(">>> Boş bırakılacak alan: " + alan);
    }

    @And("Diger tum zorunlu alanlari gecerli degerlerle doldurur")
    public void diger_tum_zorunlu_alanlari_doldurur() {
        if (!bosBirakilacakAlan.equalsIgnoreCase("Email")) {
            registerPage().emailInput.clear();
            registerPage().emailInput.sendKeys("testuser" + System.currentTimeMillis() + "@instulearn.com");
        }
        if (!bosBirakilacakAlan.equalsIgnoreCase("Full Name")) {
            registerPage().fullName.clear();
            registerPage().fullName.sendKeys("Test User");
        }
        if (!bosBirakilacakAlan.equalsIgnoreCase("Password")) {
            registerPage().passwordInput.clear();
            registerPage().passwordInput.sendKeys("Test1234");
        }
        if (!bosBirakilacakAlan.equalsIgnoreCase("Retype Password")) {
            registerPage().confirmPassword.clear();
            registerPage().confirmPassword.sendKeys("Test1234");
        }
    }

    @And("{string} butonuna tiklar")
    public void butona_tiklar(String butonAdi) {
        WebElement button = butonAdi.equalsIgnoreCase("Signup")
                ? registerPage().signupButton
                : registerPage().loginLink;
        ReusableMethods.scrollToElement(driver(), button);
        try {
            ReusableMethods.waitForClickablility(driver(), button, 10).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", button);
        }
        ReusableMethods.bekle(2);
    }

    @Then("{string} alani icin zorunlu alan uyari mesaji gorunur olmalidir")
    public void zorunlu_alan_uyari_mesaji_gorunur(String alan) {
        String pageSource = driver().getPageSource().toLowerCase();
        boolean errorExists = !registerPage().errorMessages.isEmpty()
                || pageSource.contains("required")
                || pageSource.contains("zorunlu")
                || pageSource.contains("field is required");
        Assert.assertTrue(alan + " alanı için zorunlu alan uyarısı gözükmedi!", errorExists);

        // Sonraki senaryo için state'i temizle
        bosBirakilacakAlan = "";
    }

    @And("Kullanici Register sayfasinda kalmalidir")
    public void kullanici_register_sayfasinda_kalmalidir() {
        String url = driver().getCurrentUrl();
        Assert.assertTrue("Kullanıcı Register sayfasında kalmadı! URL: " + url,
                url.contains("/register"));
    }

    // ========================================================================
    // TC07 - Terms & rules checkbox zorunluluğu
    // ========================================================================
    @When("Kullanici tum zorunlu alanlari gecerli degerlerle doldurur")
    public void tum_zorunlu_alanlari_gecerli_doldurur() {
        registerPage().emailInput.clear();
        registerPage().emailInput.sendKeys("testuser" + System.currentTimeMillis() + "@instulearn.com");

        registerPage().fullName.clear();
        registerPage().fullName.sendKeys("Test User");

        registerPage().passwordInput.clear();
        registerPage().passwordInput.sendKeys("Test1234");

        registerPage().confirmPassword.clear();
        registerPage().confirmPassword.sendKeys("Test1234");
    }

    @And("{string} checkbox'ini secmeden birakir")
    public void checkbox_secmeden_birakir(String checkboxLabel) {
        if (registerPage().termsCheckbox.isSelected()) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", registerPage().termsCheckbox);
        }
        Assert.assertFalse("Terms checkbox seçili kalmış!", registerPage().termsCheckbox.isSelected());
    }

    @Then("{string} uyari mesaji gorunur olmalidir")
    public void uyari_mesaji_gorunur(String mesaj) {
        String pageSource = driver().getPageSource();
        Assert.assertTrue("Beklenen uyarı mesajı sayfada gözükmüyor: " + mesaj,
                pageSource.contains(mesaj) || pageSource.contains("term field is required"));
    }

    // TC07 Pozitif
    @And("{string} checkbox'ini secer")
    public void checkbox_secer(String checkboxLabel) {
        if (!registerPage().termsCheckbox.isSelected()) {
            try {
                registerPage().termsCheckbox.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", registerPage().termsCheckbox);
            }
        }
    }

    @Then("{string} checkbox'inin secili oldugu dogrulanir")
    public void checkbox_secili_dogrulanir(String checkboxLabel) {
        Assert.assertTrue("Terms checkbox seçili değil!", registerPage().termsCheckbox.isSelected());
    }

    @And("{string} butonu tiklanabilir olmalidir")
    public void buton_tiklanabilir(String butonAdi) {
        WebElement button = butonAdi.equalsIgnoreCase("Signup")
                ? registerPage().signupButton
                : registerPage().loginLink;
        Assert.assertTrue(butonAdi + " butonu tıklanabilir değil!", button.isEnabled());
    }

    // ========================================================================
    // TC08 - Email format validasyonu
    // ========================================================================
    @When("Kullanici Email alanina gecersiz {string} degerini girer")
    public void gecersiz_email_girer(String gecersizEmail) {
        registerPage().emailInput.clear();
        registerPage().emailInput.sendKeys(gecersizEmail);
    }

    @Then("Email alani icin gecersiz email format uyari mesaji gorunur olmalidir")
    public void email_format_uyari_mesaji_gorunur() {
        String validationMsg = (String) ((JavascriptExecutor) driver())
                .executeScript("return arguments[0].validationMessage;", registerPage().emailInput);

        String pageSource = driver().getPageSource().toLowerCase();
        boolean serverSideError = pageSource.contains("valid email")
                || pageSource.contains("email format")
                || pageSource.contains("invalid email")
                || pageSource.contains("email must");

        boolean clientSideError = validationMsg != null && !validationMsg.isEmpty();

        Assert.assertTrue("Geçersiz email uyarı mesajı gözükmedi!",
                clientSideError || serverSideError);
    }

    // TC08 Pozitif
    @When("Kullanici Email alanina gecerli {string} degerini girer")
    public void gecerli_email_girer(String gecerliEmail) {
        registerPage().emailInput.clear();
        registerPage().emailInput.sendKeys(gecerliEmail);
    }

    @Then("Email alani icin hicbir format uyari mesaji gorunmemelidir")
    public void email_format_uyarisi_yok() {
        String validationMsg = (String) ((JavascriptExecutor) driver())
                .executeScript("return arguments[0].validationMessage;", registerPage().emailInput);
        Assert.assertTrue("Geçerli email'e rağmen uyarı var: " + validationMsg,
                validationMsg == null || validationMsg.isEmpty());
    }

    // ========================================================================
    // TC09 - Password minimum 8 karakter kriteri
    // ========================================================================
    @When("Kullanici Password alanina 8 karakterden az olan {string} degerini girer")
    public void kisa_sifre_girer(String kisaSifre) {
        registerPage().passwordInput.clear();
        registerPage().passwordInput.sendKeys(kisaSifre);
    }

    @And("Retype Password alanina ayni {string} degerini girer")
    public void ayni_sifreyi_retype_girer(String sifre) {
        registerPage().confirmPassword.clear();
        registerPage().confirmPassword.sendKeys(sifre);
    }

    @Then("Password alani icin minimum karakter uyari mesaji gorunur olmalidir")
    public void sifre_minimum_karakter_uyarisi_gorunur() {
        String validationMsg = (String) ((JavascriptExecutor) driver())
                .executeScript("return arguments[0].validationMessage;", registerPage().passwordInput);

        String pageSource = driver().getPageSource().toLowerCase();
        boolean serverSideError = pageSource.contains("at least 8")
                || pageSource.contains("minimum")
                || pageSource.contains("8 character")
                || pageSource.contains("password must");

        boolean clientSideError = validationMsg != null && !validationMsg.isEmpty();

        Assert.assertTrue("Kısa şifre için uyarı gözükmedi!",
                clientSideError || serverSideError);
    }

    // TC09 Pozitif
    @When("Kullanici Password alanina 8 karakter veya daha fazla olan {string} degerini girer")
    public void gecerli_uzunlukta_sifre_girer(String sifre) {
        registerPage().passwordInput.clear();
        registerPage().passwordInput.sendKeys(sifre);
    }

    @Then("Password alani icin minimum karakter uyari mesaji gorunmemelidir")
    public void sifre_minimum_karakter_uyarisi_yok() {
        String validationMsg = (String) ((JavascriptExecutor) driver())
                .executeScript("return arguments[0].validationMessage;", registerPage().passwordInput);
        Assert.assertTrue("Geçerli şifreye rağmen uyarı var: " + validationMsg,
                validationMsg == null || validationMsg.isEmpty());
    }

    // ========================================================================
    // TC10 - Register sayfasından Login sayfasına geçiş
    // ========================================================================
    @When("Kullanici {string} bolumundeki {string} linkini kontrol eder")
    public void bolumdeki_linki_kontrol(String bolum, String linkAdi) {
        ReusableMethods.scrollToElementWithWait(driver(), registerPage().loginLink, 10);
    }

    @When("Kullanici {string} linkine tiklar")
    public void kullanici_linke_tiklar(String linkAdi) {
        if (linkAdi.equalsIgnoreCase("Login")) {
            try {
                ReusableMethods.waitForClickablility(driver(), registerPage().loginLink, 10).click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", registerPage().loginLink);
            }
        }
        ReusableMethods.bekle(2);
    }

    @Then("Kullanici Login sayfasina yonlendirilir")
    public void kullanici_login_sayfasina_yonlendirilir() {
        String url = driver().getCurrentUrl();
        Assert.assertTrue("Login sayfasına yönlendirilmedi! URL: " + url,
                url.contains("/login"));
    }

    @And("Login sayfasi URL'inin {string} icerdigi dogrulanir")
    public void login_url_icerik_dogrulanir(String beklenenParca) {
        String url = driver().getCurrentUrl();
        Assert.assertTrue("URL beklenen parçayı içermiyor! URL: " + url,
                url.contains(beklenenParca));
    }
}
