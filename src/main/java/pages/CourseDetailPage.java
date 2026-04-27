package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CourseDetailPage extends BasePage {

    public CourseDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== KURS BAŞLIK VE GENEL BİLGİ ====================
    @FindBy(xpath = "//h1 | //h2[contains(@class,'course-title') or contains(@class,'title')]")
    public WebElement courseTitle;

    // "in Math" kategori linki (breadcrumb / üst kısım)
    @FindBy(xpath = "//a[starts-with(@href,'/classes?') or contains(@href,'category=')]")
    public WebElement courseCategoryLink;

    // "Created by DiffrentialEquations"
    @FindBy(xpath = "//*[contains(normalize-space(),'Created by')]")
    public WebElement createdByText;

    // Instructor profil linki - "Created by {Name}" içindeki <a>
    @FindBy(xpath = "//*[contains(normalize-space(),'Created by')]//a | //a[contains(@href,'/instructor/') or contains(@href,'/user/') or contains(@href,'/profile/')]")
    public WebElement instructorLink;

    // "2/12 Students" - progress bar altındaki metin
    @FindBy(xpath = "//*[contains(normalize-space(),'Students')]")
    public WebElement studentsCount;

    // ==================== PUANLAMA (RATING) ====================
    // "(0 Ratings)"
    @FindBy(xpath = "//*[contains(normalize-space(),'Ratings') or contains(normalize-space(),'Rating')]")
    public WebElement ratingText;

    @FindBy(xpath = "//*[contains(@class,'rating') or contains(@class,'stars-card')]")
    public WebElement ratingSection;

    @FindBy(xpath = "//*[contains(@class,'star') and not(contains(@class,'start'))]")
    public List<WebElement> ratingStars;

    // ==================== SİDEBAR - FİYAT VE BUTONLAR ====================
    // Sidebar container: <div class="course-content-sidebar col-12 col-lg-4 mt-25 mt-lg-0">
    @FindBy(xpath = "//div[contains(@class,'course-content-sidebar')]")
    public WebElement sidebar;

    // Fiyat başlığı: "Free" veya "$99" gibi (h2.section-title)
    @FindBy(xpath = "//div[contains(@class,'course-content-sidebar')]//h2 | //h2[contains(@class,'section-title') and (normalize-space()='Free' or contains(normalize-space(),'$'))]")
    public WebElement coursePrice;

    // ==================== ÜCRETLİ KURS BUTONLARI (Image 1'den KESIN HTML) ====================
    // HTML: <button class="btn btn-primary js-course-add-to-cart-btn" type="button">Add to Cart</button>
    // Form: <form action="/cart/store" method="post">
    @FindBy(xpath = "//button[contains(@class,'js-course-add-to-cart-btn')] | //button[normalize-space()='Add to Cart'] | //button[normalize-space()='Add to cart']")
    public WebElement addToCartButton;

    // HTML: <a class="btn btn-outline-primary btn-subscribe mt-20" href="/subscribes/apply/...">Subscribe</a>
    @FindBy(xpath = "//a[contains(@class,'btn-subscribe')] | //a[normalize-space()='Subscribe']")
    public WebElement subscribeButton;

    // HTML: <button class="btn btn-outline-danger mt-20 js-course-direct-payment">Buy now!</button>
    @FindBy(xpath = "//button[contains(@class,'js-course-direct-payment')] | //button[normalize-space()='Buy now!'] | //button[normalize-space()='Buy now']")
    public WebElement buyNowButton;

    // ==================== ÜCRETSİZ KURS BUTONU (Image 3'ten) ====================
    // HTML: <a class="..." href=".../enroll">Enroll on Course</a>
    @FindBy(xpath = "//a[normalize-space()='Enroll on Course'] | //button[normalize-space()='Enroll on Course'] | //a[contains(@href,'/enroll')]")
    public WebElement enrollOnCourseButton;

    // Genel satın alma butonu (ücretli veya ücretsiz farketmez, ilk bulunan)
    @FindBy(xpath = "//button[contains(@class,'js-course-add-to-cart-btn')] | //a[normalize-space()='Enroll on Course']")
    public WebElement primaryActionButton;

    // ==================== FORM - CART/STORE ====================
    // HTML: <form action="/cart/store" method="post">
    // Hidden: <input type="hidden" name="item_id" value="4134">
    // Hidden: <input type="hidden" name="item_name" value="webinar_id">
    @FindBy(xpath = "//form[@action='/cart/store']")
    public WebElement cartStoreForm;

    @FindBy(xpath = "//form[@action='/cart/store']//input[@name='item_id']")
    public WebElement itemIdHidden;

    @FindBy(xpath = "//form[@action='/cart/store']//input[@name='item_name']")
    public WebElement itemNameHidden;

    // PriceBox: <div id="priceBox" class="d-flex align-items-center justify-content-center mt-20">
    @FindBy(xpath = "//*[@id='priceBox']")
    public WebElement priceBox;

    // ==================== FAVORİ VE PAYLAŞ ====================
    @FindBy(xpath = "//*[normalize-space()='Favorite']")
    public WebElement favoriteButton;

    @FindBy(xpath = "//*[normalize-space()='Share']")
    public WebElement shareButton;

    // "5 Days money back guarantee"
    @FindBy(xpath = "//*[contains(normalize-space(),'money back guarantee')]")
    public WebElement moneyBackGuarantee;

    // ==================== TAB SİSTEMİ (KESIN HTML - Image 3) ====================
    // HTML: <ul class="nav nav-tabs bg-secondary rounded-sm p-15 d-flex align-items-center justify-content-between" id="tabs-tab" role="tablist">
    @FindBy(xpath = "//ul[@id='tabs-tab']")
    public WebElement tabsContainer;

    // HTML: <div class="tab-pane fade show active" id="information" role="tabpanel" aria-labelledby="information-tab">
    @FindBy(xpath = "//a[@id='information-tab'] | //*[@aria-controls='information'] | //*[@role='tab'][contains(normalize-space(),'Information')]")
    public WebElement informationTab;

    // HTML: <div class="tab-pane fade" id="content" role="tabpanel" aria-labelledby="content-tab">
    @FindBy(xpath = "//a[@id='content-tab'] | //*[@aria-controls='content'] | //*[@role='tab'][contains(normalize-space(),'Content')]")
    public WebElement contentTab;

    // HTML: <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
    @FindBy(xpath = "//a[@id='reviews-tab'] | //*[@aria-controls='reviews'] | //*[@role='tab'][contains(normalize-space(),'Reviews')]")
    public WebElement reviewsTab;

    // Tab içerikleri
    @FindBy(xpath = "//div[@id='information']")
    public WebElement informationTabContent;

    @FindBy(xpath = "//div[@id='content']")
    public WebElement contentTabContent;

    @FindBy(xpath = "//div[@id='reviews']")
    public WebElement reviewsTabContent;

    // ==================== INFORMATION TAB - ABOUT THIS COURSE ====================
    // HTML: <h2 class="section-title after-line">About this course</h2>
    @FindBy(xpath = "//h2[contains(@class,'section-title') and contains(normalize-space(),'About this course')] | //h2[contains(normalize-space(),'About this course')]")
    public WebElement aboutThisCourseTitle;

    @FindBy(xpath = "//h2[contains(normalize-space(),'About this course')]/following::p[1]")
    public WebElement aboutThisCourseContent;

    // FAQ bölümü: <h2 class="section-title after-line">FAQ</h2>
    @FindBy(xpath = "//h2[contains(@class,'section-title') and normalize-space()='FAQ'] | //h2[normalize-space()='FAQ']")
    public WebElement faqSection;

    // FAQ soruları (collapsible)
    @FindBy(xpath = "//h2[normalize-space()='FAQ']/following::button[contains(@class,'collapse') or @data-toggle='collapse'] | //h2[normalize-space()='FAQ']/following::a[@data-toggle='collapse']")
    public List<WebElement> faqItems;

    // ==================== THIS COURSE INCLUDES ====================
    @FindBy(xpath = "//*[contains(normalize-space(),'This Course includes')]")
    public WebElement courseIncludesTitle;

    @FindBy(xpath = "//*[contains(normalize-space(),'Downloadable content')]")
    public WebElement downloadableContent;

    @FindBy(xpath = "//*[contains(normalize-space(),'Official certificate')]")
    public WebElement officialCertificate;

    @FindBy(xpath = "//*[contains(normalize-space(),'online quizzes') or contains(normalize-space(),'online quiz')]")
    public WebElement onlineQuizzes;

    @FindBy(xpath = "//*[contains(normalize-space(),'Instructor support')]")
    public WebElement instructorSupport;

    // ==================== YORUM ALANI (Comments) ====================
    // HTML: <h2 class="section-title after-line">Comments (0)</h2>
    @FindBy(xpath = "//h2[contains(@class,'section-title') and contains(normalize-space(),'Comments')] | //*[contains(normalize-space(),'Comments (')]")
    public WebElement commentsTitle;

    // Comments başlığından sonra ilk textarea
    @FindBy(xpath = "//*[contains(normalize-space(),'Comments (')]/following::textarea[1] | //textarea")
    public WebElement reviewTextArea;

    // Yorum gönder butonu - textarea'dan sonraki ilk submit butonu
    @FindBy(xpath = "//textarea/following::button[@type='submit'][1] | //textarea/following::button[contains(normalize-space(),'Submit') or contains(normalize-space(),'Send') or contains(normalize-space(),'Post')][1] | //textarea/following::input[@type='submit'][1]")
    public WebElement submitReviewButton;

    @FindBy(xpath = "//*[contains(@class,'alert-success') or contains(@class,'toast-success')]")
    public WebElement reviewSuccessMessage;

    // ==================== REPORT COURSE ====================
    @FindBy(xpath = "//*[contains(normalize-space(),'Report this course')]")
    public WebElement reportCourseLink;

    // ==================== TOAST MESAJLARI ====================
    @FindBy(xpath = "//*[contains(@class,'toast') or contains(@class,'alert') or contains(@class,'notify')]")
    public WebElement toastMessage;

    @FindBy(xpath = "//*[contains(text(),'Added to cart') or contains(text(),'added to cart') or contains(text(),'Added to Cart')]")
    public WebElement addedToCartToast;
}

