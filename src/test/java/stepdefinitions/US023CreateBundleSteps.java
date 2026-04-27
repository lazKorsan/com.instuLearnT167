package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.CreateBundlePage;
import pages.ExamplePage;
import utils.*;

import java.time.Duration;

public class US023CreateBundleSteps {

    private static final Logger logger = LogManager.getLogger(US023CreateBundleSteps.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    CreateBundlePage createBundlePage = new CreateBundlePage(driver);
    public static final String nextButton="//button[@id='getNextStep']";




    @Given("Instructor {string} adresi ve {string} ile sisteme giris yapar")
    public void ınstructor_adresi_ve_ile_sisteme_giris_yapar(String mail, String password) {

        // todo Kullanıcı login sayfasına gider
        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get("https://qa.instulearn.com/login");
        logger.info("🌏 Kullanıcı login sayfasına gitti");

        // BANNER BANNER 1
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici ogretmen girisi yapar",
                "US023CrateCourse",
                "Başarılı Instructor Girisi Yapar"

        );ReusableMethods.bekle(6);

        // todo Kullanıcı mail adresini giriyor
        logger.debug("🐞 Kullanici mail adresini giriyor");
        SendKeysUtils.sendByXpath(driver, "//input[@id='email']", mail);
        logger.info("✅ Kullanıcı mail adresini girdi");

        // todo Kullanıcı password adresini giriyor
        logger.debug("🐞 Kullanici password adresini giriyor");
        SendKeysUtils.sendByXpath(driver, "//input[@id='password']", password);
        logger.info("✅ Kullanıcı password adresini girdi");

        // todo Kullanıcı submit butonuna basıyor
        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        ClickUtils.clickByXpath(driver, "//button[@class='btn btn-primary btn-block mt-20']");
        logger.info("✅ Kullanıcı submit butonuna tıkladı");

        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici Basarili Bir Şekilde Instructor girisi yapti",
                "US023CrateCourse",
                "Instructor Login Islemleri"

        );ReusableMethods.bekle(6);

    }


    @When("Kullanici dashBoard menuden newButtonuna tiklar")
    public void kullaniciDashBoardMenudenNewButtonunaTiklar() {

        // todo BANNER BANNER 2
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Instructor Course Olusturma İşlemlerine Başlar",
                "US023CrateCourse",
                "Kullanici dashBoard menuden newButtonuna tiklar"

        );ReusableMethods.bekle(6);

        // todo dashBoardMenu ye hoover islemleri
        Actions actions = new Actions(driver);
        WebElement dashboardMenu = driver.findElement(By.xpath("(//span[@class='font-14 text-dark-blue font-weight-500'])[1]"));
        actions.moveToElement(dashboardMenu).perform();
        ReusableMethods.bekle(2);
        logger.info("✅  Kullanici dashBoard Menuye Hover yapti");

        // todo dashboard icinde coursesBox secim islemleri
        ClickUtils.clickByXpath(driver, "(//span[@class='font-14 text-dark-blue font-weight-500'])[2]");
        ReusableMethods.bekle(2);
        logger.info("✅ Kullanici dashboard menu altindan coursesButtona tikladi ");

        // todo newCourses islemleri
        ClickUtils.clickByXpath(driver, "//*[@class=\"mt-5 \"]");
        ReusableMethods.bekle(2);
        logger.info("✅ Kullanici newCourses Butonuna tikladi");

    }

    @When("Kullanici newWebinar sayfasinda oldugunu dogrular")
    public void kullaniciNewWebinarSayfasindaOldugunuDogrular() {
        // todo
        driver.get("https://qa.instulearn.com/panel/webinars/new");
        logger.info("✅ Kullanici newWebinars sayfasina gitti ");
        ReusableMethods.bekle(2);

        // todo BANNER BANNER 3
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici newWebinar sayfasinda oldugunu dogrular",
                "US023CrateCourse",
                "if-else Methodu"

        );ReusableMethods.bekle(6);

        // todo url dogrulama adimlari
        String expectedUrl="https://qa.instulearn.com/register";
        String actualUrl=driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)){
            logger.info("Beklenen url: " + expectedUrl + "ile" + actualUrl + "aynı degerde");

        }else{
            logger.error("Beklenen url: " + expectedUrl + "ile" + actualUrl + "farklı degerde");
        }

        ReusableMethods.bekle(4); // fixme bekleme suresi aayarlanacak

        // todo BANNER BANNER 4
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici newWebinar sayfasinda oldugunu dogruladi",
                "US023CrateCourse",
                "if-else Methodu"

        );ReusableMethods.bekle(6);
    }

    @When("Kullanici basicInformation sayfasi bilgilerini doldurur")
    public void kullaniciBasicInformationSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 5
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici basicInformation sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                ""

        );ReusableMethods.bekle(6);

        // todo courseTypeButton islemleri
        ClickUtils.clickByXpath(driver, "//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select");
        logger.info("Kullanici courseType Container alanina tikladi");
        ReusableMethods.bekle(2);

        // todo courseTypeDropDown Menuden VideoCourse Type secimi islemleri
        ClickUtils.clickByXpath(driver, "//*[@id=\"webinarForm\"]/div[2]/div/div[1]/select/option[2]");
        logger.info("Kullanici courseType Container alanindan 2nci opsiyonu secti.VideoCourse Option");
        ReusableMethods.bekle(2);

        // todo titleBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"title\"]","Math-251");
        logger.info("Kullanici Title alanina Math-251 yazdi");
        ReusableMethods.bekle(2);

        // todo ceoDescriptionsBox alani islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"seo_description\"]", "Math,VideoCourse,Certificate");
        logger.info("Kullanici seo_description alanina Math,VideoCourse,Certificate yazdi");
        ReusableMethods.bekle(2);

        // todo ===============FOTO1===START============================================
        // todo  SWİTCH WİNDOW İSLEMLERİNİN OLDUGU BOLUM
        // todo thunbnail yukleme islemleri  1nc. foto yukleme adimlari
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[1]");
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[1]");
        logger.info("Kullanici thumbnail alanina tiklandi");
        ReusableMethods.bekle(4);

        // todo switchWindow ile ikinci pencereye gecme
        SwitchToWindow.switchToWindowByIndex(driver,1);
        logger.info("Kullanici switchToWindow ozelligi ile ikinci pencereye gecti");

        // todo foto secme islemleri
        ClickUtils.clickByXpath(driver,"(//*[@class='item_name text-truncate'])[2]");
        logger.info("Kullanici matematik thumbnail secti");
        ReusableMethods.bekle(2);

        // todo comfirm islemleri
        ClickUtils.clickByXpath(driver, "//*[@class=\"fas fa-check\"]");
        logger.info("Kullanici confirmButtn a tikladi ");

        // todo switchWindow ile ilk pencereye gecme
        SwitchToWindow.switchToWindowByIndex(driver,0);
        logger.info("Kullanici switchToWindow ozelligi ile ilk pencereye gecti");
        ReusableMethods.bekle(2);
        // todo ===============FOTO1===END============================================

        // todo ===============FOTO2===START============================================
        // todo 2.foto yukleme islemleri
        // todo çift tiklama alternatif olarak ReusableMethods içinden cagirilabilir
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[2]");
        ClickUtils.clickByXpath(driver,"(//button[@class=\"input-group-text panel-file-manager\"])[2]");

        // todo switchWindow ile ikinci pencereye gecme
        SwitchToWindow.switchToWindowByIndex(driver,1);
        logger.info("Kullanici switchToWindow ozelligi ile ikinci pencereye gecti");

        ClickUtils.clickByXpath(driver,"(//*[@class='item_name text-truncate'])[2]");
        logger.info("Kullanici matematik thumbnail secti");
        ReusableMethods.bekle(2);

        // WebElement mathPng= driver.findElement(By.xpath("content"));
        //        ReusableMethods.highLightToElement(mathPng);
        //        JavascriptExecutor js = (JavascriptExecutor) driver;
        //        js.executeScript("arguments[0].click();", mathPng);
        //        logger.info("Kullanici matematik thumbnail secti");
        //        ReusableMethods.bekle(2);

        // todo comfirm islemleri
        ClickUtils.clickByXpath(driver, "//*[@class=\"fas fa-check\"]");
        logger.info("Kullanici confirmButtn a tikladi ");

        // todo switchWindow ile ilk pencereye gecme
        SwitchToWindow.switchToWindowByIndex(driver,0);
        logger.info("Kullanici switchToWindow ozelligi ile ilk pencereye gecti");
        ReusableMethods.bekle(2);
        // todo switchWindow islemleri bitti . Driverin o indexine donmesi gerekir tekrardan
        // todo ===============FOTO2===END============================================



        // todo description islemleri
        String description="Limit, türev ve integralin fizikteki kullanım alanları. Anlık hız, ivme, iş, enerji, elektrik alan ve akışkanlar mekaniği problemlerinin matematiksel çözümlemesi.";
        SendKeysUtils.sendByXpath(driver, "//div[@role='textbox']", description);
        logger.info("Kullanici course descirption alanini doldurdu");
        ReusableMethods.bekle(2);

        // todo nextButton islemleri
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // todo BANNER BANNER 6
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici basicInformation sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                ""

        );ReusableMethods.bekle(6);

    }

    @When("Kullanici extraInformation sayfasi bilgilerini doldurur")
    public void kullaniciExtraInformationSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 7
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici extraInformation sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "SwitchToWindow Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
       // driver.get("https://qa.instulearn.com/panel/webinars/4130/step/2");
        ReusableMethods.bekle(4);

        // todo capacityBox islemleri
        SendKeysUtils.sendByXpath(driver, "//input[@name=\"capacity\"]", "12");
        ReusableMethods.bekle(2);
        logger.info("Kullanici capacityBox alanini doldurdu");

        // todo durationOfMinutesBox islemleri
        String durationOfMinutesBox="//input[@name=\"duration\"]";
        SendKeysUtils.sendByXpath(driver, durationOfMinutesBox, "120");
        ReusableMethods.bekle(2);
        logger.info("Kullanici durationOfMinutesBox alanini doldurdu");

        //  todo supportCheckBox islemleri
        WebElement supportCheckbox = driver.findElement(By.id("supportSwitch"));
        ReusableMethods.highLightToElement(supportCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].checked = true;", supportCheckbox);
        logger.info(" ✅ Kullanici supportCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        //  todo completionCertificateBox islemleri
        WebElement completionCertificateBox = driver.findElement(By.id("certificateSwitch"));
        ReusableMethods.highLightToElement(completionCertificateBox);
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].checked = true;", completionCertificateBox);
        logger.info(" ✅ Kullanici completionCertificateBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        //  todo downloadableCheckBox islemleri
        WebElement downloadableCheckBox = driver.findElement(By.id("downloadableSwitch"));
        ReusableMethods.highLightToElement(downloadableCheckBox);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].checked = true;", downloadableCheckBox);
        logger.info(" ✅ Kullanici downloadableCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // todo  tagsBox islemleri
        String tagsBox="//input[@placeholder=\"Type tag name and press enter (Max : 5)\"]";
        SendKeysUtils.sendByXpath(driver, tagsBox, "Math");
        ReusableMethods.bekle(2);
        logger.info("Kullanici tagsBox alanini doldurdu");

        // todo CategoryDropDownMenu islemleri
         String targetXpath = "//select[@name=\"category_id\"]/option[@value='1027']";
         ClickUtils.clickByXpath(driver, targetXpath);
         logger.info("Kullanici CategoryDropDownMenu alanini doldurdu");
         ReusableMethods.bekle(2);

         // todo nextButton
         ClickUtils.clickByXpath(driver, nextButton);
         ReusableMethods.bekle(3);

         // todo BANNER BANNER 8
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici extraInformation sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "SwitchToWindow Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);



         // https://qa.instulearn.com/panel/webinars/4130/step/3

    }

    @When("Kullanici pricingPage sayfasi bilgilerini doldurur")
    public void kullaniciPricingPageSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 9
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici pricingPage sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "JavaExcecuter Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
        //driver.get("https://qa.instulearn.com/panel/webinars/4130/step/3");

        // todo subscribeSwitchCheckBox islemleri
        WebElement subscribeSwitchCheckBox = driver.findElement(By.id("subscribeSwitch"));
        ReusableMethods.highLightToElement(subscribeSwitchCheckBox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].checked = true;", subscribeSwitchCheckBox);
        logger.info(" ✅ Kullanici subscribeSwitchCheckBox alanini vurguladı ve doldurdu");
        ReusableMethods.bekle(2);

        // todo accessPeriodBox islemleri
        String accesPeriodBox ="//input[@name=\"access_days\"]";
        SendKeysUtils.sendByXpath(driver, accesPeriodBox, "30");
        ReusableMethods.bekle(2);
        logger.info("Kullanici accessPeriodBox alanini doldurdu");

        // todo priceBox islemleri
        String priceBox ="//input[@placeholder=\"Enter 0 for Free\"]";
        SendKeysUtils.sendByXpath(driver, priceBox, "100");
        ReusableMethods.bekle(2);
        logger.info("Kullanici priceBox alanini doldurdu");

        // todo nextStepButton
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // todo BANNER BANNER 10
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici pricingPage sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "JavaExcecuter Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);

        // https://qa.instulearn.com/panel/webinars/4130/step/4

    }

    @When("Kullanici contentPage sayfasi bilgilerini doldurur")
    public void kullaniciContentPageSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 11
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici contentPage sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "Değişken değiştirerek Button Uzeirnden Text Gonderilemsi"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
        //driver.get("https://qa.instulearn.com/panel/webinars/4130/step/4");

        // todo newSectionButton islemleri
        String newSectionButton="//button[@class=\"js-add-chapter btn btn-primary btn-sm mt-15\"]";
        ClickUtils.clickByXpath(driver, newSectionButton);
        ReusableMethods.bekle(2);
        logger.info("Kullanici newSectionButton alanini doldurdu");

        // todo sectionTitleBox islemleri
        SendKeysUtils.sendByXpath(driver, "(//input[@class=\"form-control js-ajax-title\"])[2]","Spring Semester Courses");
        ReusableMethods.bekle(2);
        logger.info("Kullanici sectionTitleBox alanini doldurdu");

        // todo modalSavebutton islemleri
        // modalSavebutton="(//button[@class="save-chapter btn btn-sm btn-primary"])[2]";
        String modalSavebutton="(//button[@class=\"save-chapter btn btn-sm btn-primary\"])[2]";
        ClickUtils.clickByXpath(driver, modalSavebutton);
        ReusableMethods.bekle(2);
        logger.info("Kullanici modalSavebutton alanini doldurdu");

        // todo nextStepButton islemleri
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // https://qa.instulearn.com/panel/webinars/4130/step/5

        // todo BANNER BANNER 12
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici contentPage sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "Değişken değiştirerek Button Uzeirnden Text Gonderilemsi"

        );ReusableMethods.bekle(6);


    }

    @When("Kullanici prerequisitesPage sayfasi bilgilerini doldurur")
    public void kullaniciPrerequisitesPageSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 13
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici prerequisitesPage sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "Klavye ArrowDown Ozelliğinin Kullanılmasi"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
        //driver.get("https://qa.instulearn.com/panel/webinars/4130/step/5");

        // todo newPrerequisitesButton islemleri
        String newPrerequisitesButton="//button[@id=\"webinarAddPrerequisites\"]";
        ClickUtils.clickByXpath(driver, newPrerequisitesButton);
        ReusableMethods.bekle(4);
        logger.info("Kullanici newPrerequisitesButton alanini doldurdu");

        // todo selectPrerequisiteDropDownMenu islemleri
        String selectPrerequisiteDropDownMenu ="//*[@class='select2 select2-container select2-container--default']";
        ClickUtils.clickByXpath(driver, selectPrerequisiteDropDownMenu);
        ReusableMethods.bekle(2);
        logger.info("Kullanici selectPrerequisiteDropDownMenu alanini doldurdu");

        // todo searchBox islemleri
        String searchBox = "//input[@class='select2-search__field']";
        SendKeysUtils.sendByXpath(driver, searchBox, "adv");
        ReusableMethods.bekle(2);

        // todo  burasi onemli bir ozellik. ArrowOzelligi ile dropdown menuden secme islemi
        // KLİT NOKTA: Aşağı Ok tuşuna bas (Artık bu da static metodumuzun içinde)
        // Bu adım, açılan listenin ilk elemanını 'aktif' hale getirir.
        SendKeysUtils.pressArrowDown(driver, searchBox);
        ReusableMethods.bekle(1);
        logger.info("🔍 [Step] Arama sonuçları arasında aşağı inildi.");

        // todo arrowDown sonrası KEYS_ENTER islemi
        SendKeysUtils.pressEnter(driver, searchBox);
        ReusableMethods.bekle(1);
        logger.info("✅ [Step] Seçim yapıldı ve onaylandı.");
        ReusableMethods.bekle(2);

        // todo requiredCheckBoxButton islemleri
        String requiredCheckBoxButton = "//*[@class=\"custom-control custom-switch\"]";
        WebElement checkboxElement = driver.findElement(By.xpath(requiredCheckBoxButton));
        ReusableMethods.highLightToElement(checkboxElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkboxElement);
        ReusableMethods.bekle(2);
        logger.info(" ✅ Kullanici requiredCheckBoxButton alanini JS Executor ile başarıyla doldurdu");

        // todo  nextStepButton
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(3);

        // https://qa.instulearn.com/panel/webinars/4130/step/6

        // todo BANNER BANNER 14
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici prerequisitesPage sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "Klavye ArrowDown Ozelliğinin Kullanılmasi"

        );ReusableMethods.bekle(6);


    }

    @When("Kullanici FAQpage sayfasi bilgilerini doldurur")
    public void kullaniciFAQpageSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 15
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici FAQpage sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "Değişen sayfa yapısını yakalamak"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
        //driver.get("https://qa.instulearn.com/panel/webinars/4130/step/6");

        // todo newFAQButton islemleri
        String newFAQButton="//button[@id=\"webinarAddFAQ\"]";
        ClickUtils.clickByXpath(driver, newFAQButton);
        ReusableMethods.bekle(4);
        logger.info("Kullanici newFAQButton alanini doldurdu");

        // todo titleBox islemleri
        String titleBox="//input[@name=\"ajax[new][title]\"]";
        String faqQuestion="Sınav veya proje var mı? Başarı nasıl ölçülüyor?";
        SendKeysUtils.sendByXpath(driver, titleBox, faqQuestion);
        ReusableMethods.bekle(2);

        // todo answerBox islemleri
        String answerBox="//textarea[@name=\"ajax[new][answer]\"]";
        String answer="Evet, bu kursta hem sınav hem de proje bulunmaktadır. Başarı ölçme yaklaşımımız, teorik bilgiyi ve uygulamalı beceriyi birlikte değerlendirmeyi hedefler.";
        SendKeysUtils.sendByXpath(driver, answerBox, answer);
        ReusableMethods.bekle(2);
        logger.info("Kullanici answerBox alanini doldurdu");

        // todo saveFAQButton islemleri
        String saveButton="//button[@class=\"js-save-faq btn btn-sm btn-primary\"]";
        ClickUtils.clickByXpath(driver, saveButton);
        ReusableMethods.bekle(3);
        logger.info("Kullanici saveButton alanini doldurdu");

        // todo nextButton islemleri
        ClickUtils.clickByXpath(driver, nextButton);
        ReusableMethods.bekle(2);
        logger.info("Kullanici nextButton alanini doldurdu");

        // todo BANNER BANNER 16
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici FAQpage sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "Değişen sayfa yapısını yakalamak"

        );ReusableMethods.bekle(6);
    }

    @When("Kullanici Quiz&CertificationPage sayfasi bilgilerini doldurur")
    public void kullaniciQuizCertificationPageSayfasiBilgileriniDoldurur() {

        // todo BANNER BANNER 17
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici Quiz&CertificationPage sayfasi bilgilerini doldurur",
                "US023CrateCourse",
                "JavaExcecuter Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);

        // todo ÖNEMLİ DYNAMİK TESTE GEÇERKEN BURAYI YORUMA AL
        //driver.get("https://qa.instulearn.com/panel/webinars/4130/step/7");

        // todo newQuizButton islemleri
        String newQuizButton="//button[@id=\"webinarAddQuiz\"]";
        ClickUtils.clickByXpath(driver, newQuizButton);
        ReusableMethods.bekle(4);
        logger.info("Kullanici newQuizButton alanini doldurdu");

        // todo quiztitleBox islemleri
        String quiztitleBox="//input[@name=\"ajax[new][title]\"]";
        String title="achievement-1";
        SendKeysUtils.sendByXpath(driver, quiztitleBox, title);
        ReusableMethods.bekle(2);
        logger.info("Kullanici quiztitleBox alanini doldurdu");

        // todo Time (Minutes) islemleri
        String timeBox="//input[@name=\"ajax[new][time]\"]";
        String duration="60";
        SendKeysUtils.sendByXpath(driver, timeBox, duration);
        ReusableMethods.bekle(2);
        logger.info("Kullanici timeBox alanini doldurdu");

        // todo Number of attempts islemleri
        String numberOfAttemptsBox="//input[@name=\"ajax[new][attempt]\"]";
        String attempCounts="2";
        SendKeysUtils.sendByXpath(driver, numberOfAttemptsBox, attempCounts);
        ReusableMethods.bekle(2);
        logger.info("Kullanici numberOfAttemptsBox alanini doldurdu");

        // todo Pass mark islemleri
        String passMarkBox="//input[@name=\"ajax[new][pass_mark]\"]";
        String passMarkCount="70";
        SendKeysUtils.sendByXpath(driver, passMarkBox, passMarkCount);
        ReusableMethods.bekle(2);
        logger.info("Kullanici passMarkBox alanini doldurdu");

        // todo Expiry days islemleri
        // String expiryDaysBox="//input[@name="ajax[new][expiry_days]"]";
        // String expiryDays="30";
        String expiryDaysBox="//input[@name=\"ajax[new][expiry_days]\"]";
        String expiryDays="30";
        SendKeysUtils.sendByXpath(driver, expiryDaysBox, expiryDays);
        ReusableMethods.bekle(2);
        logger.info("Kullanici expiryDaysBox alanini doldurdu");

        // todo Display questions randomly Box  islemleri javaexceture ile
        ClickUtils.clickByXpath(driver, "//*[@class=\"custom-control custom-switch\"]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici Display questions randomly Box alanini JS Executor ile başarıyla doldurdu");

        // todo Certificate included CheckBox islemleri
        ClickUtils.clickByXpath(driver, "(//*[@class=\"custom-control custom-switch\"])[2]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici Certificate included CheckBox alanini JS Executor ile başarıyla doldurdu");

        // todo Active quiz CheckBox islemleri
        ClickUtils.clickByXpath(driver,"(//*[@class=\"custom-control custom-switch\"])[3]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici Active quiz CheckBox alanini JS Executor ile başarıyla doldurdu");

        // todo create islemleri
        ClickUtils.clickByXpath(driver, "//button[@class=\"js-submit-quiz-form btn btn-sm btn-primary\"]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici createButton alanini doldurdu");
        ReusableMethods.bekle(6);

        // todo publish islemleri
        ClickUtils.clickByXpath(driver, "//button[@id=\"sendForReview\"]");
        ReusableMethods.bekle(2);
        logger.info("Kullanici publishButton alanini doldurdu");

        ReusableMethods.bekle(6);

        // todo BANNER BANNER 18
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici Quiz&CertificationPage sayfasi bilgilerini doldurdu",
                "US023CrateCourse",
                "JavaExcecuter Ozelliklerinin Kullanilmasi"

        );ReusableMethods.bekle(6);

        // todo BANNER BANNER 19
        ReusableMethods.bekle(2);
        BannerUtils.displayProgressBanner(
                driver,
                "Kullanici myCourses sayfasinda Course Olustugunu Dogrular ",
                "US023CrateCourse",
                "if-else Statment"

        );ReusableMethods.bekle(6);



    }
}
