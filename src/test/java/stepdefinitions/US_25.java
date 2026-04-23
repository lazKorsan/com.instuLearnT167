package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.DashboardPage;
import pages.ExamplePage;
import pages.HomePage;
import pages.RegisterPage;
import utils.ClickUtils;
import utils.ReusableMethods;
import utils.SendKeysUtils;

import java.time.Duration;

public class US_25 {


    private static final Logger logger = LogManager.getLogger(US_25.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    SendKeysUtils sendKeysUtils = new SendKeysUtils();



    //----------TC_01 Quiz linkleri sidebar'da gorunmelidir-------------//

    @Given("kullanici qa.instulearn.com sayfasina gider")
    public void kullanici_qa_instulearn_com_sayfasina_gider() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");
    }

    @Given("Kullanici login page sayfasina kecid eder")
    public void kullanici_login_page_sayfasina_gider() {
        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get(ConfigReader.getProperty("login_url"));
        logger.info("🌏 Kullanıcı login sayfasına gitti");
    }

    @Given("Kullanici email kutusuna gecerli mail adresi yazar")
    public void kullanici_email_kutusuna_gecerli_mail_adresi_girer() {
        String email = ConfigReader.getProperty("inTeacherGecerliMail");
        logger.debug("🐞 Email kutusuna: {} adresi giriliyor", email);
        examplePage.mailBox.sendKeys(email);
        logger.info("🔓 Email alanına: {} değeri başarıyla girildi", email);
    }

    @Given("Kullanici password kutusuna gecerli password yazar")
    public void kullanici_password_kutusuna_gecerli_password_girer() {
        String password = ConfigReader.getProperty("inTeacherGecerliPassword");
        logger.debug("🐞 Password kutusuna: {} değeri giriliyor", password);
        examplePage.passwordBox.sendKeys(password);
        logger.info("🔓 Password alanına: {} değeri başarıyla girildi", password);
    }

    @Given("Kullanici submitButton basar")
    public void kullanici_submit_button_tiklar() {
        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        examplePage.submitButton.click();
        logger.info("✅ Kullanıcı submit butonuna tıkladı");
    }

    @Given("kullanici sisteme basarili giris yapar")
    public void kullanici_sisteme_basarili_giris_yapar() {
        logger.debug("🐞 Kullanıcı Dashboard sayfasına gelir");
        Assertions.assertTrue(dashboardPage.Dashboard.isDisplayed());
        logger.info("✅ Kullanıcı Dashboard sayfasını gore bildi");
    }

    @Given("kullanici Quizzes butonunu tiklar")
    public void kullanici_quizzes_butonunu_tiklar() {
        logger.debug("🐞 Kullanıcı Quizzes butonunu  tıklıyor");
        dashboardPage.QuizzesButtonu.click();
        logger.info("✅ Kullanıcı Quizzes butonuna tıkladı");
    }

    @Given("New Quiz linki gorunur olmali")
    public void new_quiz_linki_gorunur_olmali() {
        logger.debug("🐞 New Quiz linki gorunur olmalidir");
        Assertions.assertTrue(dashboardPage.NewquizButtonu.isDisplayed());
        logger.info("✅ New Quiz linki gorundu");
    }

    @Given("List linki gorunur olmali")
    public void list_linki_gorunur_olmali() {
        logger.debug("🐞 List linki gorunur olmalidir");
        Assertions.assertTrue(dashboardPage.ListButtonu.isDisplayed());
        logger.info("✅ List linki gorundu");
    }

    @Given("Results linki gorunur olmali")
    public void results_linki_gorunur_olmali() {
        logger.debug("🐞 Results linki gorunur olmalidir");
        Assertions.assertTrue(dashboardPage.ResultButtonu.isDisplayed());
        logger.info("✅ Results linki gorundu");
    }

    @Given("My results linki gorunur olmali")
    public void my_results_linki_gorunur_olmali() {
        logger.debug("🐞 My results linki gorunur olmalidir");
        Assertions.assertTrue(dashboardPage.MyresultsButtonu.isDisplayed());
        logger.info("✅ My results linki gorundu");
    }

    @Given("Not Participated linki gorunur olmali")
    public void not_participated_linki_gorunur_olmali() {
        logger.debug("🐞 Not Participated linki gorunur olmalidir");
        Assertions.assertTrue(dashboardPage.NotParticipatedButtonu.isDisplayed());
        logger.info("✅ Not Participated linki gorundu");
    }



    //------------TC_02 Quiz linkleri aktif olmalidir-------------//



    @Given("kullanici qa.instulearn.com sayfasina gecer")
    public void kullanici_qa_instulearn_com_sayfasina_gecer() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");
    }

    @Given("Kullanici login page sayfasina yonelir")
    public void kullanici_login_page_sayfasina_yonelir() {
        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get(ConfigReader.getProperty("login_url"));
        logger.info("🌏 Kullanıcı login sayfasına gitti");
    }

    @Given("Kullanici email kutusuna gecerli mail adresini yazar")
    public void kullanici_email_kutusuna_gecerli_mail_adresi_yazar() {
        String email = ConfigReader.getProperty("inTeacherGecerliMail");
        logger.debug("🐞 Email kutusuna: {} adresi giriliyor", email);
        examplePage.mailBox.sendKeys(email);
        logger.info("🔓 Email alanına: {} değeri başarıyla girildi", email);
    }

    @Given("Kullanici password kutusuna gecerli passwordunu yazar")
    public void kullanici_password_kutusuna_gecerli_password_yazar() {
        String password = ConfigReader.getProperty("inTeacherGecerliPassword");
        logger.debug("🐞 Password kutusuna: {} değeri giriliyor", password);
        examplePage.passwordBox.sendKeys(password);
        logger.info("🔓 Password alanına: {} değeri başarıyla girildi", password);
    }

    @Given("Kullanici submitButton click yapar")
    public void kullanici_submit_button_yapar() {
        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        examplePage.submitButton.click();
        logger.info("✅ Kullanıcı submit butonuna tıkladı");
    }

    @Given("kullanici sisteme giris yapmis")
    public void kullanici_sisteme_giris_yapmis() {
        logger.debug("🐞 Kullanıcı Dashboard sayfasına gider");
        Assertions.assertTrue(dashboardPage.Dashboard.isDisplayed());
        logger.info("✅ Kullanıcı Dashboard sayfası gorundu");

    }

    @Given("kullanici Quizzes butonunu secer")
    public void kullanici_quizzes_butonunu_secer() {
        logger.debug("🐞 Kullanıcı Quizzes butonunu  tıklıyor");
        dashboardPage.QuizzesButtonu.click();
        logger.info("✅ Kullanıcı Quizzes butonuna tıkladı");
    }

    @Given("kullanici New Quiz linkine tiklar")
    public void kullanici_new_quiz_linkine_tiklar() {
        logger.debug("🐞 Kullanıcı New Quiz linkine tiklar");
        dashboardPage.NewquizButtonu.click();
        Assertions.assertTrue(dashboardPage.NewquizButtonu.isEnabled());
        logger.info("✅ Kullanıcı New Quiz linkinin tiklanildigini test etdi");
    }

    @Given("kullanici New Quiz sayfasina yonlendirilmelidir")
    public void kullanici_new_quiz_sayfasina_yonlendirilmelidir() {
        logger.debug("🐞 Kullanıcı New Quiz sayfasina yonlendi");
        Assertions.assertTrue(dashboardPage.NewQuiz.isDisplayed());
        logger.info("✅ Kullanıcı New Quiz sayfasinin gorunduyunu test etdi");
    }

    @Given("kullanici List linkine tiklar")
    public void kullanici_list_linkine_tiklar() {
        logger.debug("🐞 Kullanıcı List linkine tiklar");
        dashboardPage.ListButtonu.click();
        Assertions.assertTrue(dashboardPage.ListButtonu.isEnabled());
        logger.info("✅ Kullanıcı List linkinin tiklanildigini test etdi");
    }

    @Given("kullanici Comments statistics sayfasina yonlendirilmelidir")
    public void kullanici_comments_statistics_sayfasina_yonlendirilmelidir() {
        logger.debug("🐞 Kullanıcı Comments statistics sayfasina gidiyor");
        Assertions.assertTrue(dashboardPage.CommentsStatistics.isDisplayed());
        logger.info("✅ Kullanıcı Comments statistics gorunduyunu test etdi");
    }

    @Given("kullanici Results linkine tiklar")
    public void kullanici_results_linkine_tiklar() {
        logger.debug("🐞 Kullanıcı Results linkine tiklar");
        dashboardPage.ResultButtonu.click();
        Assertions.assertTrue(dashboardPage.ResultButtonu.isEnabled());
        logger.info("✅ Kullanıcı Results linkinin tiklanildigini test etdi");
    }

    @Given("kullanici Student Results sayfasina yonlendirilmelidir")
    public void kullanici_student_results_sayfasina_yonlendirilmelidir() {
        logger.debug("🐞 Kullanıcı Student Results sayfasina gidiyor");
        Assertions.assertTrue(dashboardPage.StudentResults.isDisplayed());
        logger.info("✅ Kullanıcı Student Results gorunduyunu test etdi");
    }

    @Given("kullanici My Results linkine tiklar")
    public void kullanici_my_results_linkine_tiklar() {
        logger.debug("🐞 Kullanıcı My Results linkine tiklar");
        dashboardPage.MyresultsButtonu.click();
        Assertions.assertTrue(dashboardPage.MyresultsButtonu.isEnabled());
        logger.info("✅ Kullanıcı My Results linkinin tiklanildigini test etdi");
    }

    @Given("kullanici My quizzes sayfasina yonlendirilmelidir")
    public void kullanici_my_quizzes_sayfasina_yonlendirilmelidir() {
        logger.debug("🐞 Kullanıcı My quizzes sayfasina gidiyor");
        Assertions.assertTrue(dashboardPage.MyQuizzess.isDisplayed());
        logger.info("✅ Kullanıcı My quizzes gorunduyunu test etdi");
    }

    @Given("kullanici Not Participated linkine tiklar")
    public void kullanici_not_participated_linkine_tiklar() {
        logger.debug("🐞 Kullanıcı Not Participated linkine tiklar");
        dashboardPage.NotParticipatedButtonu.click();
        Assertions.assertTrue(dashboardPage.NotParticipatedButtonu.isEnabled());
        logger.info("✅ Kullanıcı Not Participated linkinin tiklanildigini test etdi");;
    }

    @Given("kullanici Filter Results sayfasina yonlendirilmelidir")
    public void kullanici_filter_results_sayfasina_yonlendirilmelidir() {
        logger.debug("🐞 Kullanıcı Filter Results sayfasina gidiyor");
        Assertions.assertTrue(dashboardPage.FilterResults.isDisplayed());
        logger.info("✅ Kullanıcı Filter Results gorunduyunu test etdi");
    }



    //------------TC_03 Instructor yeni quiz yarada bilmelidir-------------//



    @Given("kullanici sisteme giris yapar")
    public void kullanici_qa_instulearn_com_sayfasina_giris_eder() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");

        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get(ConfigReader.getProperty("login_url"));
        logger.info("🌏 Kullanıcı login sayfasına gitti");

        String email = ConfigReader.getProperty("inTeacherGecerliMail");
        logger.debug("🐞 Email kutusuna: {} adresi giriliyor", email);
        examplePage.mailBox.sendKeys(email);
        logger.info("🔓 Email alanına: {} değeri başarıyla girildi", email);

        String password = ConfigReader.getProperty("inTeacherGecerliPassword");
        logger.debug("🐞 Password kutusuna: {} değeri giriliyor", password);
        examplePage.passwordBox.sendKeys(password);
        logger.info("🔓 Password alanına: {} değeri başarıyla girildi", password);

        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        examplePage.submitButton.click();
        logger.info("✅ Kullanıcı submit butonuna tıkladı");

        logger.debug("🐞 Kullanıcı Quizzes butonunu  tıklıyor");
        dashboardPage.QuizzesButtonu.click();
        logger.info("✅ Kullanıcı Quizzes butonuna tıkladı");

    }

    @Given("kullanici New Quiz buttonuna tiklar")
    public void kullanici_new_quiz_butonuna_tiklar() {
        logger.debug("🐞 Kullanıcı New Quiz linkine tiklar");
        dashboardPage.NewquizButtonu.click();
        logger.info("✅ Kullanıcı New quiz title baslik görunur");
    }

    @Given("kullanici Quiz title dahil eder")
    public void kullanici_quiz_title_daxil_edir() {

        dashboardPage.QuizTitleInput.sendKeys("Selenium1");

    }

    @Given("kullanici Pass mark dahil edir")
    public void kullanici_pass_mark_daxil_edir() {

        dashboardPage.PassMarkInput.sendKeys("5");
    }

    @Given("kullanici Create duymesine tiklar")
    public void kullanici_save_duymesine_tiklarr() {
        actions.scrollToElement(dashboardPage.CreateButton);
        dashboardPage.CreateButton.click();

    }

    @Given("kullanici list sayfasina gider")
    public void kullanici_list_linkine_gidre() {
        logger.debug("🐞 Kullanıcı List linkine tiklar");
        dashboardPage.ListButtonu.click();
        logger.info("✅ Kullanıcı List satfasinda");
    }


    @Given("yeni sinav basariyla olusdurulmalidir")
    public void yeni_quiz_ugurla_yaradilmali() {
        String yenisinav = "Selenium1";
        String actionTitle = dashboardPage.NewQuizTableTitle.getText();
        Assertions.assertTrue(actionTitle.contains(yenisinav));



    }



    //------------TC_04 Add multiple choice butonu gorunur ve aktif olmalidir-------------//


    @Given("kullanici siteye giris yapar")
    public void kullanici_siteye_giris_yapar() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");

        logger.debug("🐞 Kullanıcı login sayfasına gider");
        driver.get(ConfigReader.getProperty("login_url"));
        logger.info("🌏 Kullanıcı login sayfasına gitti");

        String email = ConfigReader.getProperty("inTeacherGecerliMail");
        logger.debug("🐞 Email kutusuna: {} adresi giriliyor", email);
        examplePage.mailBox.sendKeys(email);
        logger.info("🔓 Email alanına: {} değeri başarıyla girildi", email);

        String password = ConfigReader.getProperty("inTeacherGecerliPassword");
        logger.debug("🐞 Password kutusuna: {} değeri giriliyor", password);
        examplePage.passwordBox.sendKeys(password);
        logger.info("🔓 Password alanına: {} değeri başarıyla girildi", password);

        logger.debug("🐞 Kullanıcı submit butonuna tıklıyor");
        examplePage.submitButton.click();
        logger.info("✅ Kullanıcı submit butonuna tıkladı");

        logger.debug("🐞 Kullanıcı Quizzes butonunu  tıklıyor");
        dashboardPage.QuizzesButtonu.click();
        logger.info("✅ Kullanıcı Quizzes butonuna tıkladı");

        logger.debug("🐞 Kullanıcı New Quiz linkine tiklar");
        dashboardPage.NewquizButtonu.click();
        logger.info("✅ Kullanıcı New quiz title baslik görunur");

        dashboardPage.QuizTitleInput.sendKeys("Selenium1");
        dashboardPage.PassMarkInput.sendKeys("5");

        actions.scrollToElement(dashboardPage.CreateButton);
        dashboardPage.CreateButton.click();

    }

    @Given("Add a multiple choice butonu gorunur olmali")
    public void add_a_multiple_choice_butonu_gorunur_olmali() {
        Assertions.assertTrue(dashboardPage.Add_a_Multiple_choice.isDisplayed());

    }

    @Given("Add a multiple choice butonu aktif olmali")
    public void add_a_multiple_choice_butonu_aktif_olmali() {
        Assertions.assertTrue(dashboardPage.Add_a_Multiple_choice.isEnabled());

    }

    @Given("kullanici Add a multiple choice butonuna tiklar")
    public void kullanici_add_a_multiple_choice_butonuna_tiklar() {
        dashboardPage.Add_a_Multiple_choice.click();

    }

    @Given("Multiple choice question yaratma sayfasi acilmalidir")
    public void multiple_choice_question_yaratma_sayfasi_acilmalidir() {
        dashboardPage.MultipleChoiceQuestion.isDisplayed();

    }


    @Given("kullanici question title soru kaydi daxil edir")
    public void kullanici_question_title_soru_kaydi_daxil_edir() {

        dashboardPage.QuestionTitleInput.sendKeys("Playwright");
    }

    @Given("kullanici grade soru kaydi daxil edir")
    public void kullanici_grade_soru_kaydi_daxil_edir() {
        dashboardPage.GradeInput.sendKeys("10");

    }

    @Given("kullanici Add an Answer butonunu tiklar")
    public void kullanici_add_an_answer_butonunu_tiklar() {

        dashboardPage.Add_an_AnswerButtonu.click();
    }

    @Given("Answer title bolumunu cavab ekler")
    public void answer_title_bolumunu_cavab_ekler() {

    dashboardPage.AnswetTitleInput.sendKeys("Hello Yes");
    }

    @Given("kullanici correct answer secir")
    public void kullanici_correct_answer_secir() {
        js.executeScript("arguments[0].click();", dashboardPage.CorrectAnswerButtonu);

    }

    @Given("kullanici yeniden Answer title bolumunu cavab ekler")
    public void kullanici_yeniden_answer_title_bolumunu_cavab_ekler() {
       dashboardPage.AnswetTitleInput2.sendKeys("Python");

    }

    @Given("kullanici save duymesine tiklar")
    public void kullanici_save_duymesine_tiklar() {
        dashboardPage.SaveButtonu.click();

        ;
    }

    @Given("sual ugurla yaradilmali")
    public void sual_ugurla_yaradilmali() {
       int actualResoult = dashboardPage.QuestionsList.size();
       Assertions.assertTrue(actualResoult>0);
        ReusableMethods.bekle(5);


    }


    //------------TC_05 Add Descriptive butonu gorunur ve aktifdir-------------/



    @Given("Add a Descriptive butonu gorunur olmali")
    public void add_a_descriptive_butonu_gorunur_olmali() {
        Assertions.assertTrue(dashboardPage.AddDescriptive.isDisplayed());


    }

    @Given("Add a Descriptive butonu aktif olmali")
    public void add_a_descriptive_butonu_aktif_olmali() {
        Assertions.assertTrue(dashboardPage.AddDescriptive.isEnabled());


    }

    @Given("kullanici Add a Descriptive butonuna tiklar")
    public void kullanici_add_a_descriptive_butonuna_tiklar() {

        dashboardPage.AddDescriptive.click();
    }

    @Given("New descriptive question question sayfasi acilmalidir")
    public void new_descriptive_question_question_sayfasi_acilmalidir() {

        Assertions.assertTrue(dashboardPage.NewDescriptiveQuestion.isDisplayed());

    }

    @Given("kullanici Question title  daxil edir")
    public void kullanici_question_title_daxil_edir() {

            dashboardPage.QuestionDescriptive.sendKeys("Java haqqinda sual");

    }

    @Given("kullanici Grade daxil edir")
    public void kullanici_grade_daxil_edir() {

        dashboardPage.GradeDescriptive.sendKeys("6");


    }

    @Given("kullanici Correct answer bilgileri daxil edir")
    public void kullanici_correct_answer_bilgileri_daxil_edir() {

        String correctAnswerDynamic = "Java candir selenium heyacan";
        SendKeysUtils.sendByXpath(driver,"(//*[@*='ajax[correct]'])[2]" , correctAnswerDynamic);

    }

    @Given("kullanici Save duymesine tiklar")
    public void kullanici_Save_duymesine_tiklar() {

        dashboardPage.SaveDescriptive.click();

    }

    @Given("kullanici List linkine gecis yapar")
    public void kullanici_List_duymesine_tiklar() {
        ReusableMethods.bekle(1);
        dashboardPage.List2.click();


    }

    @Given("Soru kaydi  ugurla yaradildi")
    public void descriptive_question_ugurla_yaradilmali() {
        actions.scrollByAmount(0, 500).perform();;
        int actualResoult = dashboardPage.Questions.size();
        Assertions.assertTrue(actualResoult>0);


    }




}
