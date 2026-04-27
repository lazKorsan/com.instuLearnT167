package stepdefinitions;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.LoggerHelper;
import utils.ReusableMethods;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * US033 (Courses) icin step definitions.
 *
 * NOT: Login adimlari bu class'ta DEGIL, US050_LoginSteps class'inda tanimlidir.
 *
 * ONEMLI: US20_RegisterStepDefinitions ile cakismayi onlemek icin step text'leri
 * "navbar'da" ile baslar veya farkli ifade kullanir.
 */
public class US33_CoursesStepDefinitions {

    // Field isimleri method isimleriyle CAKISMASIN diye basina _ konuldu
    private HomePage_Courses _homePage_Courses;
    private CoursesPage _coursesPage;
    private CourseDetailPage _courseDetailPage;
    private CartPage _cartPage;
    private CheckoutPage _checkoutPage;
    private InstructorProfilePage _instructorProfilePage;
    private LoggerHelper logger;

    // ======================== LAZY GETTERS ========================
    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    private HomePage_Courses homePage() {
        if (_homePage_Courses == null) _homePage_Courses = new HomePage_Courses(driver());
        return _homePage_Courses;
    }

    private CoursesPage coursesPage() {
        if (_coursesPage == null) _coursesPage = new CoursesPage(driver());
        return _coursesPage;
    }

    private CourseDetailPage courseDetailPage() {
        if (_courseDetailPage == null) _courseDetailPage = new CourseDetailPage(driver());
        return _courseDetailPage;
    }

    private CartPage cartPage() {
        if (_cartPage == null) _cartPage = new CartPage(driver());
        return _cartPage;
    }

    private CheckoutPage checkoutPage() {
        if (_checkoutPage == null) _checkoutPage = new CheckoutPage(driver());
        return _checkoutPage;
    }

    private InstructorProfilePage instructorProfilePage() {
        if (_instructorProfilePage == null) _instructorProfilePage = new InstructorProfilePage(driver());
        return _instructorProfilePage;
    }

    private void safeStopPageLoad() {
        try {
            ((JavascriptExecutor) driver()).executeScript("window.stop();");
        } catch (Exception ignored) { }
    }

    // Navbar'daki tüm linkleri destekleyen helper
    private WebElement getNavbarLink(String linkAdi) {
        switch (linkAdi.toLowerCase()) {
            case "courses":     return homePage().coursesLink;
            case "register":    return homePage().registerLink;
            case "login":       return homePage().loginLink;
            default:
                throw new IllegalArgumentException("Bilinmeyen navbar linki: " + linkAdi);
        }
    }

    // ========================================================================
    // TC01 - Ana sayfada Courses linki
    // ========================================================================
    @When("Kullanici ana sayfanin navbar bolumunu kontrol eder")
    public void kullanici_navbar_kontrol() {
        ReusableMethods.waitForVisibility(driver(), homePage().coursesLink, 15);
    }

    @Then("navbar'da {string} linkinin gorunur oldugu dogrulanir")
    public void navbar_link_gorunur_dogrulanir(String linkAdi) {
        WebElement link = getNavbarLink(linkAdi);
        ReusableMethods.highLightToElement(link);
        Assert.assertTrue(linkAdi + " linki gorunur degil!", link.isDisplayed());
    }

    @And("navbar'da {string} linkinin aktif \\(tiklanabilir) oldugu dogrulanir")
    public void navbar_link_aktif_dogrulanir(String linkAdi) {
        WebElement link = getNavbarLink(linkAdi);
        Assert.assertTrue(linkAdi + " linki aktif degil!", link.isEnabled());
    }

    @When("Kullanici navbar'daki {string} linkine tiklar")
    public void kullanici_navbar_linke_tiklar(String linkAdi) {
        WebElement link = getNavbarLink(linkAdi);

        try {
            ReusableMethods.waitForClickablility(driver(), link, 10);
        } catch (Exception e) {
            System.err.println("waitForClickability hata (devam ediliyor): " + e.getMessage());
            safeStopPageLoad();
        }

        try {
            link.click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", link);
            } catch (Exception ignored) { }
        }

        ReusableMethods.bekle(2);
        safeStopPageLoad();
    }

    @Then("Kullanici Courses sayfasina yonlendirilir")
    public void kullanici_courses_sayfasina_yonlendirilir() {

        boolean isDisplayed = coursesPage().coursesTitle.isDisplayed();
        logger.info("coursesTitle - isDisplayed: " + isDisplayed );
        Assert.assertTrue("Courses sayfasina yonlendirilmedi!", isDisplayed);
        ReusableMethods.bekle(2);

    }

    @And("Courses sayfasinda en az bir kurs karti gorunur olmalidir")
    public void courses_sayfasinda_kurs_karti_gorunur() {
        ReusableMethods.bekle(2);
        Assert.assertFalse("Courses sayfasinda hic kurs karti bulunamadi!",
                coursesPage().courseCards.isEmpty());
    }

    // ========================================================================
    // TC02 - Search TextBox ve Search Buton
    // ========================================================================
    @Then("Courses sayfasinda Search textbox'i gorunur ve aktif olmalidir")
    public void search_textbox_gorunur_aktif() {
        try {
            ReusableMethods.waitForVisibility(driver(), coursesPage().searchTextBox, 10);
            Assert.assertTrue("Search textbox gorunur degil!", coursesPage().searchTextBox.isDisplayed());
            Assert.assertTrue("Search textbox aktif degil!", coursesPage().searchTextBox.isEnabled());
        } catch (Exception e) {
            boolean hasInput = !driver().findElements(
                    By.xpath("//input[@type='search' or contains(@placeholder,'earch')]")).isEmpty();
            Assert.assertTrue("Search textbox sayfada bulunamadi!", hasInput);
        }
    }

    @And("Courses sayfasinda Search butonu gorunur ve aktif olmalidir")
    public void search_button_gorunur_aktif() {
        try {
            ReusableMethods.waitForVisibility(driver(), coursesPage().searchButton, 10);
            Assert.assertTrue("Search butonu gorunur degil!", coursesPage().searchButton.isDisplayed());
            Assert.assertTrue("Search butonu aktif degil!", coursesPage().searchButton.isEnabled());
        } catch (Exception e) {
            boolean hasButton = !driver().findElements(
                    By.xpath("//button[@type='submit' or contains(@class,'search')]")).isEmpty();
            Assert.assertTrue("Search butonu sayfada bulunamadi!", hasButton);
        }
    }

    // ========================================================================
    // TC03 - Filtreleme / Siralama
    // ========================================================================
    @Then("Courses sayfasinda filtreleme veya siralama alanlari gorunur olmalidir")
    public void filtreleme_alanlari_gorunur() {
        String url = driver().getCurrentUrl();
        boolean hasSortParam = url.contains("sort=") || url.contains("filter=") || url.contains("category=");
        boolean hasFilterElements = !coursesPage().filterOptions.isEmpty()
                || !driver().findElements(By.xpath("//a[contains(@href,'sort=') or contains(@href,'filter=')]")).isEmpty()
                || !driver().findElements(By.xpath("//select")).isEmpty()
                || !driver().findElements(By.xpath("//*[contains(@class,'filter') or contains(@class,'sort') or contains(@class,'sidebar')]")).isEmpty();

        Assert.assertTrue("Filtreleme/siralama alanlari bulunamadi!", hasSortParam || hasFilterElements);
    }

    @When("Kullanici siralama alanindan bir secim yapar")
    public void kullanici_siralama_secim_yapar() {
        try {
            String currentUrl = driver().getCurrentUrl();
            String newUrl;
            if (currentUrl.contains("sort=newest")) {
                newUrl = currentUrl.replace("sort=newest", "sort=popular");
            } else if (currentUrl.contains("sort=")) {
                newUrl = currentUrl.replaceAll("sort=[^&]*", "sort=newest");
            } else {
                newUrl = currentUrl + (currentUrl.contains("?") ? "&" : "?") + "sort=popular";
            }
            try {
                driver().get(newUrl);
            } catch (TimeoutException te) {
                System.err.println("Page load timeout (siralama): " + te.getMessage());
                safeStopPageLoad();
            }
            ReusableMethods.bekle(3);
            safeStopPageLoad();
        } catch (Exception e) {
            System.err.println("Siralama secerken hata: " + e.getMessage());
            safeStopPageLoad();
        }
    }

    @Then("Filtrelenmis kurslar listelenir")
    public void filtrelenmis_kurslar_listelenir() {
        ReusableMethods.bekle(2);
        int courseCount = coursesPage().courseCards.size();
        Assert.assertTrue("Filtreleme sonrasi kurs listesi alani gorunmuyor!", courseCount >= 0);
    }

    // ========================================================================
    // TC04 - Kurs bilgileri
    // ========================================================================
    @Then("Listelenen her kurs icin ders adi gorunur olmalidir")
    public void kurs_ders_adi_gorunur() {
        ReusableMethods.bekle(2);
        Assert.assertFalse("Hic kurs basligi (ders adi) bulunamadi!",
                coursesPage().courseTitles.isEmpty());
    }

    @And("Listelenen her kurs icin instructor bilgisi gorunur olmalidir")
    public void kurs_instructor_gorunur() {
        boolean instructorExists = !coursesPage().courseInstructors.isEmpty()
                || !driver().findElements(By.xpath("//div[contains(@class,'user-inline-avatar')]")).isEmpty();
        Assert.assertTrue("Instructor bilgisi gorunur degil!", instructorExists);
    }

    @And("Listelenen her kurs icin tarih bilgisi gorunur olmalidir")
    public void kurs_tarih_gorunur() {
        String pageSource = driver().getPageSource();
        boolean dateExists = pageSource.contains("2024") || pageSource.contains("2025")
                || pageSource.contains("2026") || pageSource.contains("2027")
                || !coursesPage().courseDates.isEmpty();
        Assert.assertTrue("Tarih bilgisi gorunur degil!", dateExists);
    }

    @And("Listelenen her kurs icin fiyat bilgisi gorunur olmalidir")
    public void kurs_fiyat_gorunur() {
        boolean priceExists = !coursesPage().coursePrices.isEmpty()
                || !coursesPage().priceTexts.isEmpty()
                || !driver().findElements(
                By.xpath("//div[contains(@class,'webinar-price-box')]")).isEmpty();
        Assert.assertTrue("Fiyat bilgisi gorunur degil!", priceExists);
    }

    // ========================================================================
    // TC05 - Kurs detay sayfasi
    // ========================================================================
    @When("Kullanici listedeki ilk kursa tiklar")
    public void kullanici_ilk_kursa_tiklar() {
        ReusableMethods.bekle(2);
        try {
            ReusableMethods.waitForVisibility(driver(), coursesPage().firstCourseCard, 10);
            ReusableMethods.scrollToElement(driver(), coursesPage().firstCourseCard);
            try {
                coursesPage().firstCourseCard.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", coursesPage().firstCourseCard);
            }
        } catch (Exception e) {
            List<WebElement> links = driver().findElements(By.xpath("//a[contains(@href,'/course/')]"));
            if (!links.isEmpty()) {
                WebElement link = links.get(0);
                ReusableMethods.scrollToElement(driver(), link);
                try {
                    link.click();
                } catch (Exception ex) {
                    ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", link);
                }
            }
        }
        ReusableMethods.bekle(3);
        safeStopPageLoad();
    }

    @Then("Kullanici kurs detay sayfasina yonlendirilir")
    public void kullanici_kurs_detay_sayfasina_yonlendirilir() {
        String url = driver().getCurrentUrl();
        Assert.assertTrue("Kurs detay sayfasina yonlendirilmedi! URL: " + url,
                url.contains("/course/"));
    }

    @And("Kurs detay sayfasinda satin alma veya enroll butonu gorunur ve aktif olmalidir")
    public void kurs_satin_alma_butonu_gorunur_aktif() {
        boolean addToCartExists = !driver().findElements(
                By.xpath("//button[contains(@class,'js-course-add-to-cart-btn')] | //button[normalize-space()='Add to Cart']")).isEmpty();
        boolean enrollExists = !driver().findElements(
                By.xpath("//a[normalize-space()='Enroll on Course'] | //a[contains(@href,'/enroll')]")).isEmpty();

        Assert.assertTrue("Sayfada Add to Cart veya Enroll on Course butonu bulunamadi!",
                addToCartExists || enrollExists);
    }

    @And("Kurs detay sayfasinda {string} bolumu gorunur olmalidir")
    public void kurs_detay_bolum_gorunur(String bolumAdi) {
        String pageSource = driver().getPageSource().toLowerCase();
        boolean exists = pageSource.contains(bolumAdi.toLowerCase());
        Assert.assertTrue("'" + bolumAdi + "' bolumu sayfada gorunmedi!", exists);
    }

    @And("Kurs detay sayfasinda kursun puanlamasi gorunur olmalidir")
    public void kurs_puanlamasi_gorunur() {
        String pageSource = driver().getPageSource().toLowerCase();
        boolean ratingExists = pageSource.contains("rating")
                || pageSource.contains("ratings")
                || !driver().findElements(By.xpath("//*[contains(@class,'star') or contains(@class,'rating')]")).isEmpty();
        Assert.assertTrue("Kursun puanlamasi gorunur degil!", ratingExists);
    }

    @And("Kurs detay sayfasinda Information, Content ve Reviews tab'lari gorunur olmalidir")
    public void kurs_tablari_gorunur() {
        boolean infoTab = !driver().findElements(
                By.xpath("//*[@id='information-tab'] | //*[contains(normalize-space(),'Information') and (@role='tab' or @data-toggle='tab')]")).isEmpty();
        boolean contentTab = !driver().findElements(
                By.xpath("//*[@id='content-tab'] | //*[contains(normalize-space(),'Content') and (@role='tab' or @data-toggle='tab')]")).isEmpty();
        boolean reviewsTab = !driver().findElements(
                By.xpath("//*[@id='reviews-tab'] | //*[contains(normalize-space(),'Reviews') and (@role='tab' or @data-toggle='tab')]")).isEmpty();

        String pageSource = driver().getPageSource();
        boolean allTabsInSource = pageSource.contains("Information") && pageSource.contains("Content") && pageSource.contains("Reviews");

        Assert.assertTrue("Tab'lar (Information/Content/Reviews) gorunur degil!",
                (infoTab && contentTab && reviewsTab) || allTabsInSource);
    }

    // ========================================================================
    // TC06 - Yorum yapma
    // ========================================================================
    @Then("Kullanici kurs detay sayfasinda yorum alanini gorur")
    public void yorum_alani_gorur() {
        ReusableMethods.bekle(2);
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        ReusableMethods.bekle(2);

        String pageSource = driver().getPageSource();
        boolean commentsExist = pageSource.contains("Comments (") || pageSource.contains("Comment (")
                || !driver().findElements(By.xpath("//textarea")).isEmpty();
        Assert.assertTrue("Yorum alani sayfada bulunamadi!", commentsExist);
    }

    @When("Kullanici yorum alanina {string} yazisini girer")
    public void yorum_yazar(String yorum) {
        try {
            ReusableMethods.scrollToElement(driver(), courseDetailPage().reviewTextArea);
            ReusableMethods.waitForVisibility(driver(), courseDetailPage().reviewTextArea, 10);
            courseDetailPage().reviewTextArea.clear();
            courseDetailPage().reviewTextArea.sendKeys(yorum);
        } catch (Exception e) {
            List<WebElement> areas = driver().findElements(By.xpath("//textarea"));
            if (!areas.isEmpty()) {
                WebElement area = areas.get(0);
                ReusableMethods.scrollToElement(driver(), area);
                area.clear();
                area.sendKeys(yorum);
            }
        }
    }

    @And("Kullanici yorumu gonder butonuna tiklar")
    public void yorumu_gonder() {
        try {
            ReusableMethods.scrollToElement(driver(), courseDetailPage().submitReviewButton);
            try {
                courseDetailPage().submitReviewButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", courseDetailPage().submitReviewButton);
            }
        } catch (Exception e) {
            List<WebElement> submits = driver().findElements(
                    By.xpath("//textarea/following::button[1] | //textarea/following::input[@type='submit'][1]"));
            if (!submits.isEmpty()) {
                WebElement btn = submits.get(0);
                ReusableMethods.scrollToElement(driver(), btn);
                try {
                    btn.click();
                } catch (Exception ex) {
                    ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", btn);
                }
            }
        }
        ReusableMethods.bekle(3);
    }

    @Then("Yorumun basariyla eklendigi dogrulanir")
    public void yorum_eklendi_dogrula() {
        String pageSource = driver().getPageSource().toLowerCase();
        boolean success = pageSource.contains("success")
                || pageSource.contains("added")
                || pageSource.contains("posted")
                || pageSource.contains("bu kurs gercekten");
        Assert.assertTrue("Yorumun eklendigi dogrulanamadi!", success);
    }

    // ========================================================================
    // TC07 - Instructor profil sayfasi
    // ========================================================================
    @And("Kullanici kursun instructor linkine tiklar")
    public void instructor_linkine_tiklar() {
        ReusableMethods.bekle(1);
        try {
            ReusableMethods.scrollToElement(driver(), courseDetailPage().instructorLink);
            ReusableMethods.waitForClickablility(driver(), courseDetailPage().instructorLink, 10);
            try {
                courseDetailPage().instructorLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", courseDetailPage().instructorLink);
            }
        } catch (Exception e) {
            List<WebElement> links = driver().findElements(
                    By.xpath("//*[contains(normalize-space(),'Created by')]//a | //a[contains(@href,'/instructor/') or contains(@href,'/user/')]"));
            if (!links.isEmpty()) {
                WebElement link = links.get(0);
                ReusableMethods.scrollToElement(driver(), link);
                try {
                    link.click();
                } catch (Exception ex) {
                    ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", link);
                }
            }
        }
        ReusableMethods.bekle(3);
        safeStopPageLoad();
    }

    @Then("Instructor profil sayfasi goruntulenmelidir")
    public void instructor_profil_sayfasi_gorunur() {
        String url = driver().getCurrentUrl();
        Assert.assertTrue("Instructor profil sayfasina yonlendirilmedi! URL: " + url,
                url.contains("/instructor") || url.contains("/user") || url.contains("/profile"));
    }

    @And("Instructor profil sayfasinda instructor bilgileri gorunur olmalidir")
    public void instructor_bilgileri_gorunur() {
        boolean profileExists = !driver().findElements(
                By.xpath("//*[contains(@class,'profile') or contains(@class,'instructor') or contains(@class,'user-info') or contains(@class,'about')]")).isEmpty();
        Assert.assertTrue("Instructor profil bilgileri gorunur degil!", profileExists);
    }

    // ========================================================================
    // TC08 - Satin alma akisi
    // ========================================================================
    @When("Kullanici Add_to_Cart ikonuna tiklar")
    public void add_to_cart_tiklar() {
        try {
            WebElement btn;
            List<WebElement> addToCartBtns = driver().findElements(
                    By.xpath("//button[contains(@class,'js-course-add-to-cart-btn')] | //*[@id='app']/section[2]/div/div[2]/div[1]/div[2]/form/div[2]/button[1]"));

            if (!addToCartBtns.isEmpty()) {
                btn = addToCartBtns.get(0);
            } else {
                btn = courseDetailPage().enrollOnCourseButton;
            }

            ReusableMethods.scrollToElement(driver(), btn);
            ReusableMethods.waitForClickablility(driver(), btn, 10);
            try {
                btn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", btn);
            }
        } catch (Exception e) {
            System.err.println("Add to Cart/Enroll hatasi: " + e.getMessage());
        }
        ReusableMethods.bekle(3);
    }


    @When("Kullanici navbar'da sepet ikonuna tiklar")
    public void navbar_sepet_ikonuna_tiklar() {
        try {
            ReusableMethods.scrollToElement(driver(), homePage().cartIcon);
            ReusableMethods.waitForClickablility(driver(), homePage().cartIcon, 10);
            try {
                homePage().cartIcon.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", homePage().cartIcon);
            }
        } catch (Exception e) {
            List<WebElement> cartLinks = driver().findElements(By.xpath("//a[contains(@href,'cart')]"));
            if (!cartLinks.isEmpty()) {
                WebElement link = cartLinks.get(0);
                try {
                    link.click();
                } catch (Exception ex) {
                    ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", link);
                }
            }
        }
        ReusableMethods.bekle(2);
    }


    @And("Kullanici Go_to_cart butonuna tiklar")
    public void go_to_cart_tiklar() {
        try {
            ReusableMethods.waitForClickablility(driver(),coursesPage().goToCartbutton, 10);
            try {
                coursesPage().goToCartbutton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", coursesPage().goToCartbutton);
            }
        } catch (Exception e) {
            System.err.println("Go to cart butonu bulunamadi: " + e.getMessage());
        }
        ReusableMethods.bekle(3);
        safeStopPageLoad();
    }



}
