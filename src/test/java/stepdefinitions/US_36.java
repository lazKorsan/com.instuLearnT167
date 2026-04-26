package stepdefinitions;

import com.beust.ah.A;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BlogPage;
import pages.ExamplePage;
import utils.ReusableMethods;

public class US_36 {

    private static final Logger logger = LogManager.getLogger(US_36.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    BlogPage blogPage = new BlogPage(driver);
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;


    @Given("kullanıcı anasayfaya gider")
    public void kullanıcı_anasayfaya_gider() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");

    }

    @Given("Blog linki görünür olmalıdır")
    public void blog_linki_görünür_olmalıdır() {

        Assertions.assertTrue(blogPage.BlogButton.isDisplayed());

    }

    @Given("Blog linki aktiv olmalidir")
    public void blog_linki_aktiv_olmalidir() {

        Assertions.assertTrue(blogPage.BlogButton.isEnabled());

    }

    @Given("kullanıcı Blog linkine tıklar")
    public void kullanıcı_linkine_tıklar() {

        blogPage.BlogButton.click();

    }

    @Given("sayfada Blog texti görüntülenmelidir")
    public void sayfada_texti_görüntülenmelidir() {

        Assertions.assertTrue(blogPage.BlogText.isDisplayed());

    }

    @Given("search textbox görünür olmalıdır")
    public void search_textbox_görünür_olmalıdır() {

        Assertions.assertTrue(blogPage.SearchTextboxButtonu.isDisplayed());

    }

    @Given("search textbox aktif olmalıdır")
    public void search_textbox_aktif_olmalıdır() {

        Assertions.assertTrue(blogPage.SearchTextboxButtonu.isEnabled());

    }

    @Given("search buton görünür olmalıdır")
    public void search_buton_görünür_olmalıdır() {

        Assertions.assertTrue(blogPage.SearchButtonu.isDisplayed());

    }

    @Given("search buton aktif olmalıdır")
    public void search_buton_aktif_olmalıdır() {

        Assertions.assertTrue(blogPage.SearchButtonu.isEnabled());

    }


    @Given("kullanıcı blog sayfasına gider")
    public void kullanıcı_blog_sayfasına_gider() {
        logger.debug("🐞 Kullanıcı ana sayfaya gider");
        driver.get(ConfigReader.getProperty("url"));
        logger.info("🌏 Kullanıcı ana sayfaya gitti");
        blogPage.BlogButton.click();
        actions.scrollToElement(blogPage.OnlineeEducationResault).perform();

    }

    @Given("kategorilere gore blog aramasi yapabilmelidir")
    public void kategorilere_gore_blog_aramasi_yapabilmelidir() {

        ReusableMethods.bekle(2);
        blogPage.OnlineeEducation.click();

    }

    @Given("aradigi blog gorunur olmalidir")
    public void aradigi_blog_gorunur_olmalidir() {

        actions.scrollToElement(blogPage.OnlineeEducationResault).perform();

        Assertions.assertTrue(blogPage.OnlineeEducationResault.isDisplayed());

    }

    @Given("blog tarihi görüntülenmelidir")
    public void blog_tarihi_görüntülenmelidir() {


        Assertions.assertTrue(blogPage.productDate.isDisplayed());

    }

    @Given("blog konusu başlık görüntülenmelidir")
    public void blog_konusu_başlık_görüntülenmelidir() {


        Assertions.assertTrue(blogPage.blogIcerigiTitle.isDisplayed());

    }

    @Given("blog içeriği görüntülenmelidir")
    public void blog_içeriği_görüntülenmelidir() {

        Assertions.assertTrue(blogPage.blogIcerigi.isDisplayed());


    }

    @Given("paylaşan kişi bilgisi görüntülenmelidir")
    public void paylaşan_kişi_bilgisi_görüntülenmelidir() {


        Assertions.assertTrue(blogPage.productManager.isDisplayed());

    }

    @Given("kullanici cikan sonuc blogu tiklar")
    public void cikan_sonuc_blog() {

        ReusableMethods.bekle(2);
        blogPage.blogIcerigiTitle.click();



    }

    @Given("kullanıcı yorum textbox'a Test yorum yazar")
    public void kullanıcı_yorum_textbox_a_yazar() {



        actions.scrollToElement(blogPage.postCommet).perform();
        blogPage.commentText.sendKeys("Tesekkurler hazirladiginiz icin");
    }

    @Given("kullanıcı yorum gönder butonuna tıklar")
    public void kullanıcı_yorum_gönder_butonuna_tıklar() {

        actions.scrollToElement(blogPage.postCommet).perform();
        blogPage.postCommet.click();


    }

    @Given("yorum başarıyla eklenmelidir")
    public void yorum_başarıyla_eklenmelidir() {


        // 1. Error page olmamalıdır
        boolean isError = driver.getPageSource().contains("item added successfully");
        Assertions.assertFalse(isError, "BUG: Backend error page açıldı!");


    }

}
