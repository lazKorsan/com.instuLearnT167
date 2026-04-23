package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.CreateBundlePage;
import pages.ExamplePage;
import utils.ClickUtils;
import utils.ReusableMethods;
import utils.SendKeysUtils;
import utils.SwitchToWindow;

public class US023CreateBundleSteps {

    private static final Logger logger = LogManager.getLogger(US023CreateBundleSteps.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    CreateBundlePage createBundlePage = new CreateBundlePage(driver);

    public static final String nextButton="//button[@id='getNextStep']";


    @Given("Instructor {string} adresi ve {string} ile sisteme giris yapar")
    public void ınstructor_adresi_ve_ile_sisteme_giris_yapar(String mail, String password) {

        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get("https://qa.instulearn.com/login");
        logger.info("🌏 Kullanıcı login sayfasına gitti");


        logger.debug("🐞 Kullanici mail adresini giriyor");
        SendKeysUtils.sendByXpath(driver, "//input[@id='email']", mail);
        logger.info("✅ Kullanıcı mail adresini girdi");

        logger.debug("🐞 Kullanici password adresini giriyor");
        SendKeysUtils.sendByXpath(driver, "//input[@id='password']", password);
        logger.info("✅ Kullanıcı password adresini girdi");

        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        ClickUtils.clickByXpath(driver, "//button[@class='btn btn-primary btn-block mt-20']");
        logger.info("✅ Kullanıcı submit butonuna tıkladı");

    }


    @When("Kullanici dashBoard menuden newButtonuna tiklar")
    public void kullaniciDashBoardMenudenNewButtonunaTiklar() {


        Actions actions = new Actions(driver);
        WebElement dashboardMenu = driver.findElement(By.xpath("(//span[@class='font-14 text-dark-blue font-weight-500'])[1]"));
        actions.moveToElement(dashboardMenu).perform();
        ReusableMethods.bekle(2);
        logger.info("✅  Kullanici dashBoard Menuye Hover yapti");

        ClickUtils.clickByXpath(driver, "(//span[@class='font-14 text-dark-blue font-weight-500'])[2]");
        ReusableMethods.bekle(2);
        logger.info("✅ Kullanici dashboard menu altindan coursesButtona tikladi ");


        logger.info("✅ Kullanici newCourses Butonuna tikladi");


        ClickUtils.clickByXpath(driver, "//*[@class=\"mt-5 \"]");
        ReusableMethods.bekle(2);
        logger.info("✅ Kullanici newCourses Butonuna tikladi");

    }

    @When("Kullanici newWebinar sayfasinda oldugunu dogrular")
    public void kullaniciNewWebinarSayfasindaOldugunuDogrular() {
        driver.get("https://qa.instulearn.com/panel/webinars/new");
        logger.info("✅ Kullanici newWebinars sayfasina gitti ");
        ReusableMethods.bekle(2);

        String expectedUrl="https://qa.instulearn.com/register";
        String actualUrl=driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)){
            logger.info("Beklenen url: " + expectedUrl + "ile" + actualUrl + "aynı degerde");

        }else{
            logger.error("Beklenen url: " + expectedUrl + "ile" + actualUrl + "farklı degerde");
        }

        ReusableMethods.bekle(15);
    }

    @When("Kullanici basicInformation sayfasi bilgilerini doldurur")
    public void kullaniciBasicInformationSayfasiBilgileriniDoldurur() {

        ClickUtils.clickByXpath(driver, "//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select");
        logger.info("Kullanici courseType Container alanina tikladi");
        ReusableMethods.bekle(2);

        ClickUtils.clickByXpath(driver, "//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select/option[2]");
        logger.info("Kullanici courseType Container alanindan 2nci opsiyonu secti.VideoCourse Option");
        ReusableMethods.bekle(2);

        SendKeysUtils.sendByXpath(driver, "//input[@name=\"title\"]","Math-251");
        logger.info("Kullanici Title alanina Math-251 yazdi");
        ReusableMethods.bekle(2);


        SendKeysUtils.sendByXpath(driver, "//input[@name=\"seo_description\"]", "Math,VideoCourse,Certificate");
        logger.info("Kullanici seo_description alanina Math,VideoCourse,Certificate yazdi");
        ReusableMethods.bekle(2);


        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[1]");
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[1]");
        logger.info("Kullanici thumbnail alanina tiklandi");
        ReusableMethods.bekle(4);


        SwitchToWindow.switchToWindowByIndex(driver,1);
        logger.info("Kullanici switchToWindow ozelligi ile ikinci pencereye gecti");

        ClickUtils.clickByXpath(driver,"(//*[@class='item_name text-truncate'])[2]");
        logger.info("Kullanici matematik thumbnail secti");
        ReusableMethods.bekle(2);

        ClickUtils.clickByXpath(driver, "//*[@class=\"fas fa-check\"]");
        logger.info("Kullanici confirmButtn a tikladi ");


        SwitchToWindow.switchToWindowByIndex(driver,0);
        logger.info("Kullanici switchToWindow ozelligi ile ilk pencereye gecti");
        ReusableMethods.bekle(2);


        // todo
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[2]");
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[2]");


        SwitchToWindow.switchToWindowByIndex(driver,1);
        logger.info("Kullanici switchToWindow ozelligi ile ikinci pencereye gecti");

        ClickUtils.clickByXpath(driver,"(//*[@class='item_name text-truncate'])[1]");
        logger.info("Kullanici matematik thumbnail secti");
        ReusableMethods.bekle(2);

        ClickUtils.clickByXpath(driver, "//*[@class=\"fas fa-check\"]");
        logger.info("Kullanici confirmButtn a tikladi ");


        SwitchToWindow.switchToWindowByIndex(driver,0);
        logger.info("Kullanici switchToWindow ozelligi ile ilk pencereye gecti");
        ReusableMethods.bekle(2);

        String description="Limit, türev ve integralin fizikteki kullanım alanları. Anlık hız, ivme, iş, enerji, elektrik alan ve akışkanlar mekaniği problemlerinin matematiksel çözümlemesi.";

        SendKeysUtils.sendByXpath(driver, "//div[@role='textbox']", description);
        logger.info("Kullanici course descirption alanini doldurdu");
        ReusableMethods.bekle(2);

        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

    }

    @When("Kullanici extraInformation sayfasi bilgilerini doldurur")
    public void kullaniciExtraInformationSayfasiBilgileriniDoldurur() {

        // Bu satir sonra silinecek
        driver.get("https://qa.instulearn.com/panel/webinars/4130/step/2");
        ReusableMethods.bekle(4);

        // capacityBox
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"capacity\"]", "12");
        ReusableMethods.bekle(2);
        logger.info("Kullanici capacityBox alanini doldurdu");

        // durationOfMinutesBox
        String durationOfMinutesBox="//input[@name=\"duration\"]";
        SendKeysUtils.sendByXpath(driver, durationOfMinutesBox, "120");
        ReusableMethods.bekle(2);
        logger.info("Kullanici durationOfMinutesBox alanini doldurdu");

        // supportCheckBox
        WebElement supportCheckbox = driver.findElement(By.id("supportSwitch"));
        ReusableMethods.highLightToElement(supportCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].checked = true;", supportCheckbox);
        logger.info(" ✅ Kullanici supportCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // completionCertificateBox
        // certificateSwitch
        WebElement completionCertificateBox = driver.findElement(By.id("certificateSwitch"));
        ReusableMethods.highLightToElement(completionCertificateBox);
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].checked = true;", completionCertificateBox);
        logger.info(" ✅ Kullanici completionCertificateBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // downloadableCheckBox
        // downloadableSwitch
        WebElement downloadableCheckBox = driver.findElement(By.id("downloadableSwitch"));
        ReusableMethods.highLightToElement(downloadableCheckBox);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].checked = true;", downloadableCheckBox);
        logger.info(" ✅ Kullanici downloadableCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // tagsBox
        String tagsBox="//input[@placeholder=\"Type tag name and press enter (Max : 5)\"]";
        SendKeysUtils.sendByXpath(driver, tagsBox, "Math");
        ReusableMethods.bekle(2);
        logger.info("Kullanici tagsBox alanini doldurdu");

        // CategoryDropDownMenu
        // 1027
        // dropDownMenu selecet options ile doldur
        // todo  ***Dikkat  buradaki xpath iki kisimdan olusuyor
         String targetXpath = "//select[@name=\"category_id\"]/option[@value='1027']";
         ClickUtils.clickByXpath(driver, targetXpath);
         logger.info("Kullanici CategoryDropDownMenu alanini doldurdu");
         ReusableMethods.bekle(2);

         // nextButton
         ClickUtils.clickByXpath(driver, nextButton);
         ReusableMethods.bekle(3);

         // https://qa.instulearn.com/panel/webinars/4130/step/3

    }

    @When("Kullanici pricingPage sayfasi bilgilerini doldurur")
    public void kullaniciPricingPageSayfasiBilgileriniDoldurur() {

        // bu satır sonra silinecek
        driver.get("https://qa.instulearn.com/panel/webinars/4130/step/3");

        // subscribeSwitchCheckBox
        // id = subscribeSwitch
        WebElement subscribeSwitchCheckBox = driver.findElement(By.id("subscribeSwitch"));
        ReusableMethods.highLightToElement(subscribeSwitchCheckBox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].checked = true;", subscribeSwitchCheckBox);
        logger.info(" ✅ Kullanici subscribeSwitchCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // accessPeriodBox = "//input[@name="access_days"]"
        String accesPeriodBox ="//input[@name=\"access_days\"]";
        SendKeysUtils.sendByXpath(driver, accesPeriodBox, "30");
        ReusableMethods.bekle(2);
        logger.info("Kullanici accessPeriodBox alanini doldurdu");

        // priceBox ="//input[@placeholder="Enter 0 for Free"]"
        String priceBox ="//input[@placeholder=\"Enter 0 for Free\"]";
        SendKeysUtils.sendByXpath(driver, priceBox, "100");
        ReusableMethods.bekle(2);
        logger.info("Kullanici priceBox alanini doldurdu");

        // nextStepButton
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // https://qa.instulearn.com/panel/webinars/4130/step/4




    }

    @When("Kullanici contentPage sayfasi bilgilerini doldurur")
    public void kullaniciContentPageSayfasiBilgileriniDoldurur() {

        // bu satır sonra sil
        driver.get("https://qa.instulearn.com/panel/webinars/4130/step/4");

        // newSectionButton="//button[@class="js-add-chapter btn btn-primary btn-sm mt-15"]"
        String newSectionButton="//button[@class=\"js-add-chapter btn btn-primary btn-sm mt-15\"]";
        ClickUtils.clickByXpath(driver, newSectionButton);
        ReusableMethods.bekle(2);
        logger.info("Kullanici newSectionButton alanini doldurdu");

        // sectionTitleBox="(//input[@class=\"form-control js-ajax-title\"])[2]"
        SendKeysUtils.sendByXpath(driver, "(//input[@class=\"form-control js-ajax-title\"])[2]","Spring Semester Courses");
        ReusableMethods.bekle(2);
        logger.info("Kullanici sectionTitleBox alanini doldurdu");


        // modalSavebutton="(//button[@class="save-chapter btn btn-sm btn-primary"])[2]";
        String modalSavebutton="(//button[@class=\"save-chapter btn btn-sm btn-primary\"])[2]";
        ClickUtils.clickByXpath(driver, modalSavebutton);
        ReusableMethods.bekle(2);
        logger.info("Kullanici modalSavebutton alanini doldurdu");

        // nextStepButton
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // https://qa.instulearn.com/panel/webinars/4130/step/5

    }

    @When("Kullanici prerequisitesPage sayfasi bilgilerini doldurur")
    public void kullaniciPrerequisitesPageSayfasiBilgileriniDoldurur() {

        // bu satır sonra sil
        driver.get("https://qa.instulearn.com/panel/webinars/4130/step/5");

        // todo newPrerequisitesButton islemleri
        // newPrerequisitesButton="//button[@id="webinarAddPrerequisites"]";
        String newPrerequisitesButton="//button[@id=\"webinarAddPrerequisites\"]";
        ClickUtils.clickByXpath(driver, newPrerequisitesButton);
        ReusableMethods.bekle(4);
        logger.info("Kullanici newPrerequisitesButton alanini doldurdu");

        // todo selectPrerequisiteDropDownMenu islemleri
        // selectPrerequisiteDropDownMenu ="//*[@class='select2 select2-container select2-container--default']";
        String selectPrerequisiteDropDownMenu ="//*[@class='select2 select2-container select2-container--default']";
        ClickUtils.clickByXpath(driver, selectPrerequisiteDropDownMenu);
        ReusableMethods.bekle(2);
        logger.info("Kullanici selectPrerequisiteDropDownMenu alanini doldurdu");

        // todo searchBox islemleri
        // searchBox="//input[@class="select2-search__field"]"
        // arancak kelime =advenced
        String searchBox = "//input[@class='select2-search__field']";
       // 1. Kelimeyi gönder (Static metodumuzla hem yazıyor hem vurguluyor)
        SendKeysUtils.sendByXpath(driver, searchBox, "adv");
        ReusableMethods.bekle(2);

        // 2. KLİT NOKTA: Aşağı Ok tuşuna bas (Artık bu da static metodumuzun içinde)
        // Bu adım, açılan listenin ilk elemanını 'aktif' hale getirir.
        SendKeysUtils.pressArrowDown(driver, searchBox);
        ReusableMethods.bekle(1);
        logger.info("🔍 [Step] Arama sonuçları arasında aşağı inildi.");

        // 3. ENTER ile seçimi onayla
        SendKeysUtils.pressEnter(driver, searchBox);
        ReusableMethods.bekle(1);
        logger.info("✅ [Step] Seçim yapıldı ve onaylandı.");
        ReusableMethods.bekle(2);


        // todo requiredCheckBoxButton islemleri
        // 1. XPath'i tanımlıyoruz (Statik ID yerine dinamik olanı kullandım)
        String requiredCheckBoxButton = "//*[@class=\"custom-control custom-switch\"]";

        // 2. Elementi önce Selenium ile buluyoruz
        WebElement checkboxElement = driver.findElement(By.xpath(requiredCheckBoxButton));
        // 3. Vurgulama (Highlight): Senin ReusableMethods sınıfındaki metodu kullanıyoruz
        // Bu sayede yeni arkadaşlar hangi kutunun tıklandığını sarı renkle görecek
        ReusableMethods.highLightToElement(checkboxElement);

        // 4. JavaScriptExecutor ile "Arka Kapıdan" tıklama
        // Bu yöntem, elementin üzerinde başka bir katman olsa bile doğrudan DOM üzerinden tetikler
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkboxElement);

        // 5. Bekleme ve Log
        ReusableMethods.bekle(2);
        logger.info(" ✅ Kullanici requiredCheckBoxButton alanini JS Executor ile başarıyla doldurdu");

        // nextStepButton
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // https://qa.instulearn.com/panel/webinars/4130/step/6


    }

    @When("Kullanici FAQpage sayfasi bilgilerini doldurur")
    public void kullaniciFAQpageSayfasiBilgileriniDoldurur() {

        // bu satır sonra sil

        // git add .
        //git commit -m "feat: step5 tamamlandi "





































    }
}
