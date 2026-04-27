package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoursesPage extends BasePage {

    public CoursesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // ==================== SAYFA BAŞLIĞI ====================
    // URL: /classes?sort=newest
    @FindBy(xpath = "//*[@id='app']/section/div/div/div/div/h1")
    public WebElement coursesTitle;

    // ==================== ARAMA ====================
    // Navbar search kutusu - her sayfada var
    @FindBy(xpath = "//input[@placeholder='Search...' or @type='search']")
    public WebElement searchTextBox;

    // Search ikonu/butonu
    @FindBy(xpath = "//button[@type='submit' and (.//i[contains(@class,'search')] or contains(@class,'search'))] | //form//button[.//svg]")
    public WebElement searchButton;

    // ==================== FİLTRELEME / SIRALAMA ====================
    @FindBy(xpath = "//select | //a[contains(@href,'sort=') or contains(@href,'category=')] | //*[contains(@class,'filter')]")
    public List<WebElement> filterOptions;

    @FindBy(xpath = "//select[contains(@name,'sort')] | //a[contains(@href,'sort=')]")
    public WebElement sortDropdown;

    @FindBy(xpath = "//button[contains(normalize-space(),'Categories')] | //a[contains(normalize-space(),'Categories')]")
    public WebElement categoriesDropdown;

    // ==================== KURS KARTLARI (KESIN HTML) ====================
    // <div class="webinar-card"> - Image 2'den teyit edildi
    @FindBy(xpath = "//*[@id=\"filtersForm\"]/div[2]/div[1]")
    public List<WebElement> courseCards;

    // İlk kursun linki: <a href="https://qa.instulearn.com/course/Math-251">
    @FindBy(xpath = "//div[@class='webinar-card']//a[contains(@href,'/course/')])[1]")
    public WebElement firstCourseCard;

    // Tüm kurs linkleri
    @FindBy(xpath = "//div[@class='webinar-card']//a[contains(@href,'/course/')]")
    public List<WebElement> allCourseLinks;

    // ==================== KURS BİLGİLERİ ====================
    // Başlık: <h3 class="mt-15 webinar-title font-weight-bold font-16 text-dark-blue">Math-251</h3>
    @FindBy(xpath = "//h3[contains(@class,'webinar-title')]")
    public List<WebElement> courseTitles;

    // Instructor container: <div class="user-inline-avatar d-flex align-items-center">
    @FindBy(xpath = "//div[contains(@class,'user-inline-avatar')]")
    public List<WebElement> courseInstructors;

    // Kategori: <span class="d-block font-14 mt-10"> "in Math"
    @FindBy(xpath = "//div[@class='webinar-card']//span[contains(@class,'d-block') and contains(@class,'font-14')]")
    public List<WebElement> courseCategories;

    // Stars & tarih container: <div class="stars-card d-flex align-items-center mt-15">
    @FindBy(xpath = "//div[contains(@class,'stars-card')]")
    public List<WebElement> courseStarsContainers;

    // "d-flex justify-content-between" container - saat + tarih burada
    // "2:00 Hours" ve "22 Apr 2026" gibi bilgiler bu container'da
    @FindBy(xpath = "//div[@class='webinar-card']//div[contains(@class,'d-flex') and contains(@class,'justify-content-between')]")
    public List<WebElement> courseTimeContainers;

    // Tarih bilgileri (takvim ikonu yanındaki)
    @FindBy(xpath = "//div[@class='webinar-card']//*[contains(text(),'Apr') or contains(text(),'May') or contains(text(),'Jun') or contains(text(),'Jul') or contains(text(),'Aug') or contains(text(),'Sep') or contains(text(),'Oct') or contains(text(),'Nov') or contains(text(),'Dec') or contains(text(),'Jan') or contains(text(),'Feb') or contains(text(),'Mar')]")
    public List<WebElement> courseDates;

    // Süre bilgisi: "2:00 Hours"
    @FindBy(xpath = "//div[@class='webinar-card']//*[contains(text(),'Hours') or contains(text(),'hours')]")
    public List<WebElement> courseDurations;

    // Fiyat kutusu: <div class="webinar-price-box mt-25">
    @FindBy(xpath = "//div[contains(@class,'webinar-price-box')]")
    public List<WebElement> coursePrices;

    // Fiyat metinleri ($ veya Free)
    @FindBy(xpath = "//div[contains(@class,'webinar-price-box')]//*[contains(text(),'$') or contains(text(),'Free') or contains(text(),'free')]")
    public List<WebElement> priceTexts;

    // ==================== KURS LİSTESİ CONTAINER ====================
    // <div class="row mt-20"> (flex)
    @FindBy(xpath = "//div[contains(@class,'row')][.//div[@class='webinar-card']]")
    public WebElement coursesGrid;

    //Sepet dropdown'indaki 'Go to cart' butonuna tiklar.
    @FindBy(xpath = "(//a[text()='Go to cart'])[1]")
    public WebElement goToCartbutton;
}
