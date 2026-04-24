package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

/**
 * HoverUtilities - Selenium Actions sinifi kullanarak hover islemleri icin
 * yeniden kullanilabilir yardimci methodlar saglar.
 *
 * <p><b>Ne zaman kullanilir?</b></p>
 * <ul>
 *   <li>Basit hover islemleri (mouse bir elementin uzerine gelsin)</li>
 *   <li>Hover ile ortaya cikan gizli butonlara erisim (e-ticaret "Add to Cart" ikonu gibi)</li>
 *   <li>Dropdown menu acilmasi icin parent elemente hover</li>
 *   <li>Tooltip'lerin goruntulenmesi icin hover</li>
 *   <li>Scroll + hover kombinasyonu gereken durumlar</li>
 *   <li>Hover + click zinciri (gizli buton goruyor, sonra tikla)</li>
 * </ul>
 *
 * <p><b>Tum metodlar STATIC'tir</b>, direkt cagrilabilir:</p>
 * <pre>
 *   HoverUtilities.hover(driver, element);
 * </pre>
 *
 * <p><b>Neden static?</b> Util siniflari genelde state tutmaz, her cagri bagimsizdir.
 * Bu yuzden instance olusturmak gereksizdir.</p>
 *
 * <p><b>Varsayilan bekleme sureleri:</b></p>
 * <ul>
 *   <li>Hover sonrasi kisa pause: 1 saniye (hover efektinin render olmasi icin)</li>
 *   <li>Scroll sonrasi pause: 1 saniye (smooth scroll'un tamamlanmasi icin)</li>
 * </ul>
 *
 * @author QA Automation Team
 * @version 1.0
 */
public class HoverUtilities {

    // ========== PRIVATE CONSTANTS ==========

    /** Hover sonrasi default bekleme suresi (milisaniye). */
    private static final long DEFAULT_HOVER_PAUSE_MS = 1000L;

    /** Scroll sonrasi default bekleme suresi (milisaniye). */
    private static final long DEFAULT_SCROLL_PAUSE_MS = 1000L;

    /** Private constructor - static util sinifinin instance'i olusturulmamali. */
    private HoverUtilities() {
        throw new UnsupportedOperationException(
                "HoverUtilities is a utility class and cannot be instantiated. " +
                "Use static methods directly: HoverUtilities.hover(driver, element)");
    }


    // ========== 1. TEMEL HOVER METODLARI ==========

    /**
     * Bir elementin uzerine mouse'u getirir (hover yapar).
     *
     * <p><b>Kullanim:</b></p>
     * <pre>
     *   HoverUtilities.hover(driver, menuElement);
     * </pre>
     *
     * <p><b>Ne zaman kullanilir?</b></p>
     * <ul>
     *   <li>Basit hover efektleri icin</li>
     *   <li>Tooltip goruntulemek icin</li>
     *   <li>Dropdown menu'yu acmak icin</li>
     * </ul>
     *
     * @param driver  WebDriver instance'i
     * @param element Uzerine hover yapilacak element
     * @throws IllegalArgumentException driver veya element null ise
     */
    public static void hover(WebDriver driver, WebElement element) {
        validateParameters(driver, element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Bir elementin uzerine mouse'u getirir ve belirtilen sure bekler.
     *
     * <p><b>Ne zaman kullanilir?</b></p>
     * <ul>
     *   <li>Hover sonrasi CSS animasyonun tamamlanmasini beklemek icin</li>
     *   <li>Hover ile tetiklenen ajax cagrilarinin yuklenmesi icin</li>
     *   <li>Gorsel dogrulama yaparken (demo testleri) yeterli sureyi gormek icin</li>
     * </ul>
     *
     * @param driver           WebDriver instance'i
     * @param element          Uzerine hover yapilacak element
     * @param waitAfterSeconds Hover sonrasi kac saniye beklenecegi
     */
    public static void hoverWithWait(WebDriver driver, WebElement element, int waitAfterSeconds) {
        validateParameters(driver, element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        sleep(waitAfterSeconds * 1000L);
    }


    // ========== 2. SCROLL + HOVER METODLARI ==========

    /**
     * Elementi gorus alanina (viewport'a) kaydirir, sonra uzerine hover yapar.
     *
     * <p><b>Neden scroll gerekli?</b> Selenium Actions, ekranda GORUNMEYEN elementlere
     * hover yapamaz. Element ekran disindaysa (asagi/yukari) hover calismaz. Bu metod
     * once JavaScript ile elementi ortaya kaydirir, sonra hover yapar.</p>
     *
     * <p><b>Kullanim:</b></p>
     * <pre>
     *   HoverUtilities.scrollAndHover(driver, footerLink);
     * </pre>
     *
     * @param driver  WebDriver instance'i
     * @param element Scroll edilip hover yapilacak element
     */
    public static void scrollAndHover(WebDriver driver, WebElement element) {
        validateParameters(driver, element);

        scrollElementIntoViewCenter(driver, element);
        sleep(DEFAULT_SCROLL_PAUSE_MS);

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    // ========== 3. HOVER ZINCIRI METODLARI (E-TICARET ICIN KRITIK) ==========

    /**
     * Once parent elemente hover yapar, sonra zincir halinde child elemente hover yapar.
     *
     * <p><b>Ne zaman kullanilir?</b> Bazi web sitelerinde butonlar <b>gizli</b> olur ve
     * sadece parent elemente hover yapildiginda ortaya cikarlar. Ornek: e-ticaret
     * urun kartlarindaki "Add to Cart" veya "Quick View" butonlari.</p>
     *
     * <p><b>KRITIK:</b> Normal yaklasim (hover parent → scroll → hover child) calismaz!
     * Cunku scroll yapildiginda mouse parent elementi terk eder, child element tekrar
     * gizlenir. <b>Bu metod Actions zinciri kullanir, mouse hic parent'i terk etmez.</b></p>
     *
     * <p><b>Gercek dunya ornegi:</b></p>
     * <pre>
     *   // E-ticaret urun kartinda Add to Cart ikonunu goster
     *   HoverUtilities.hoverChain(driver, productCard, addToCartIcon);
     * </pre>
     *
     * @param driver        WebDriver instance'i
     * @param parentElement Once hover yapilacak parent (urun karti gibi)
     * @param childElement  Parent hover ile ortaya cikan child element
     */
    public static void hoverChain(WebDriver driver, WebElement parentElement, WebElement childElement) {
        validateParameters(driver, parentElement);
        validateParameters(driver, childElement);

        Actions actions = new Actions(driver);
        actions.moveToElement(parentElement)
                .pause(Duration.ofMillis(DEFAULT_HOVER_PAUSE_MS))
                .moveToElement(childElement)
                .perform();
    }


    // ========== 4. HOVER + CLICK METODLARI ==========

    /**
     * Elementin uzerine hover yapar, sonra tiklar.
     *
     * <p><b>Ne zaman kullanilir?</b></p>
     * <ul>
     *   <li>Dropdown menu'den item secmek</li>
     *   <li>Hover ile aktif olan butonlara tiklamak</li>
     *   <li>Normal click'in calismadigi durumlarda</li>
     * </ul>
     *
     * <p><b>Not:</b> Element zaten gorunurse standart click metodu yeterlidir.
     * Bu metod Actions kullandigi icin bazi edge case'lerde daha guvenilir.</p>
     *
     * @param driver  WebDriver instance'i
     * @param element Hover yapilip tiklanacak element
     */
    public static void hoverAndClick(WebDriver driver, WebElement element) {
        validateParameters(driver, element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .pause(Duration.ofMillis(500)) // Kisa pause, hover stabilize olsun
                .click()
                .perform();
    }

    /**
     * Elementi scroll eder, hover yapar ve tiklar. Tum islemi tek satirda yapar.
     *
     * <p><b>En yaygin kullanim senaryosu:</b> Ekran disindaki butona erismek.
     * Klasik "scroll yap → tikla" yerine daha guvenilir bir yaklasim sunar.</p>
     *
     * <p><b>Kullanim:</b></p>
     * <pre>
     *   HoverUtilities.scrollAndHoverAndClick(driver, submitButton);
     * </pre>
     *
     * @param driver  WebDriver instance'i
     * @param element Scroll edilip hover yapilip tiklanacak element
     */
    public static void scrollAndHoverAndClick(WebDriver driver, WebElement element) {
        validateParameters(driver, element);

        scrollElementIntoViewCenter(driver, element);
        sleep(DEFAULT_SCROLL_PAUSE_MS);

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }


    // ========== 5. HOVER ZINCIRI + CLICK (E-TICARET ICIN ALTIN METOD) ==========

    /**
     * Parent elemente hover yapar, child element ortaya cikinca child'a tiklar.
     *
     * <p><b>Ne zaman kullanilir?</b> Gizli butonlara tiklamak icin ALTIN METOD.
     * Ornek: Urun kartina hover yapinca ortaya cikan "Add to Cart" butonuna tiklamak.</p>
     *
     * <p><b>Neden boyle bir metoda ihtiyac var?</b> Gizli butonlar icin asagidaki
     * yaklasimlarin hepsi BASARISIZ olur:</p>
     * <ul>
     *   <li>❌ Direkt click: Element gorunmuyor, NoSuchElementException</li>
     *   <li>❌ Hover sonra click: Scroll veya baska actions mouse'u elementten uzaklastirir</li>
     *   <li>❌ JS click: Bazi sitelerde hover state gerektiren event handler'lar var</li>
     * </ul>
     *
     * <p><b>Bu metod Actions zinciri kullanir:</b> moveToElement(parent) →
     * pause → moveToElement(child) → click → perform. Tek atomik operasyon
     * olarak calistigi icin mouse pozisyonu hic kaybolmaz.</p>
     *
     * <p><b>Gercek dunya ornegi:</b></p>
     * <pre>
     *   // E-ticaret: Urun kartinda gizli "Add to Cart" butonuna tikla
     *   HoverUtilities.hoverChainAndClick(driver, productCard, addToCartButton);
     * </pre>
     *
     * @param driver        WebDriver instance'i
     * @param parentElement Once hover yapilacak parent element (gizli butonu ortaya cikarir)
     * @param childElement  Ortaya cikan ve tiklanacak child element
     */
    public static void hoverChainAndClick(WebDriver driver, WebElement parentElement, WebElement childElement) {
        validateParameters(driver, parentElement);
        validateParameters(driver, childElement);

        Actions actions = new Actions(driver);
        actions.moveToElement(parentElement)
                .pause(Duration.ofMillis(DEFAULT_HOVER_PAUSE_MS))
                .moveToElement(childElement)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }


    // ========== 6. COKLU HOVER METODU ==========

    /**
     * Birden fazla elemente sirayla hover yapar. Actions zincirini koruyarak yapar.
     *
     * <p><b>Ne zaman kullanilir?</b></p>
     * <ul>
     *   <li>Nested menu'lerde gezinmek (Ana Menu → Alt Menu → Alt Alt Menu)</li>
     *   <li>Ardisik hover-visible elementleri ziyaret etmek</li>
     *   <li>Bir sayfadaki birden fazla elementin hover efektlerini test etmek</li>
     * </ul>
     *
     * <p><b>Kullanim:</b></p>
     * <pre>
     *   // 3 seviyeli menu'de hover ile gezin
     *   HoverUtilities.hoverMultiple(driver, mainMenu, subMenu, finalItem);
     * </pre>
     *
     * @param driver   WebDriver instance'i
     * @param elements Hover yapilacak elementler (siraya gore)
     */
    public static void hoverMultiple(WebDriver driver, WebElement... elements) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("En az bir element saglanmalidir");
        }

        Actions actions = new Actions(driver);
        for (WebElement element : elements) {
            if (element == null) {
                throw new IllegalArgumentException("Element listesinde null element var");
            }
            actions.moveToElement(element).pause(Duration.ofMillis(DEFAULT_HOVER_PAUSE_MS));
        }
        actions.perform();
    }


    // ========== 7. PRIVATE HELPER METODLARI ==========

    /**
     * Elementi JavaScript ile ekranin ortasina kaydirir (smooth scroll).
     * {block: 'center'} sayesinde element yukari/asaginin hemen kenarina degil,
     * ortasina gelir (hover icin ideal).
     */
    private static void scrollElementIntoViewCenter(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                element);
    }

    /**
     * Belirtilen milisaniye kadar thread'i bekletir. InterruptedException'i bastirir.
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Interrupt flag'i geri koy
        }
    }

    /**
     * Driver ve element parametrelerini dogrular. Null olurlarsa IllegalArgumentException firlatir.
     * Boylece erken hata yakalanir, karmasik NullPointerException stack trace'i yerine net mesaj gorurSunuz.
     */
    private static void validateParameters(WebDriver driver, WebElement element) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        if (element == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
    }
}
