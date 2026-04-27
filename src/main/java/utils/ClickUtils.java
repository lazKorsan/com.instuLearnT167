package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClickUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration HIGHLIGHT_DURATION = Duration.ofMillis(200);

    /**
     * Ana method - XPath ile elemente tıklar
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     */
    public static void clickByXpath(WebDriver driver, String xpath) {
        clickByXpath(driver, xpath, DEFAULT_TIMEOUT);
    }

    /**
     * Timeout belirterek tıklama
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     * @param timeout Bekleme süresi
     */
    public static void clickByXpath(WebDriver driver, String xpath, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            // 1. Element görünür olana kadar bekle
            log("🔍 Element aranıyor: " + xpath);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            log("✅ Element görünür hale geldi");

            // 2. Scroll - Elementi görünür alana getir
            scrollToElement(driver, element);

            // 3. Hover - Mouse'u elementin üzerine getir
            hoverOverElement(driver, element);

            // 4. Elementin tıklanabilirliğini test et
            if (isClickable(driver, element, timeout)) {
                log("✅ Element tıklanabilir durumda");
            } else {
                log("⚠️ Element normal şartlarda tıklanabilir değil, zorlayıcı methodlar deneniyor...");
            }

            // 5. Elementi highlight et
            highlightElement(driver, element);

            // 6. Tıklama işlemini dene (aşamalı olarak)
            boolean clicked = performClickWithFallback(driver, element);

            // 7. Highlight'ı kaldır
            unhighlightElement(driver, element);

            if (clicked) {
                log("✅ BUTONA TIKLANDI - " + xpath);
            } else {
                throw new RuntimeException("❌ Tüm tıklama methodları başarısız oldu: " + xpath);
            }

        } catch (TimeoutException e) {
            log("❌ ZAMAN AŞIMI: Element bulunamadı veya görünür olmadı: " + xpath);
            throw new RuntimeException("Element bulunamadı: " + xpath, e);
        } catch (Exception e) {
            log("❌ HATA: " + e.getMessage());
            throw new RuntimeException("Tıklama başarısız: " + xpath, e);
        }
    }

    // ============ SCROLL METHODLARI ============

    /**
     * Elemente scroll yapar
     */
    private static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            log("   📜 Elemente scroll yapılıyor...");

            // Önce elementin görünür olup olmadığını kontrol et
            boolean isInViewport = (boolean) ((JavascriptExecutor) driver).executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "return (rect.top >= 0 && rect.bottom <= window.innerHeight);",
                    element
            );

            if (!isInViewport) {
                // Method 1: ScrollIntoView
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(300);
                log("   ✓ ScrollIntoView ile scroll yapıldı");
            } else {
                log("   ✓ Element zaten görünür alanda");
            }

        } catch (Exception e) {
            log("   ⚠️ Scroll başarısız: " + e.getMessage());
            // Alternatif scroll methodu
            tryAlternativeScroll(driver, element);
        }
    }

    /**
     * Alternatif scroll methodları
     */
    private static void tryAlternativeScroll(WebDriver driver, WebElement element) {
        try {
            // Method 2: Actions ile scroll
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(300);
            log("   ✓ Actions ile scroll yapıldı");
        } catch (Exception e2) {
            try {
                // Method 3: JavaScript ile smooth scroll
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                        element
                );
                Thread.sleep(500);
                log("   ✓ Smooth scroll yapıldı");
            } catch (Exception e3) {
                log("   ✗ Tüm scroll methodları başarısız");
            }
        }
    }

    /**
     * Sayfada belirli bir konuma scroll yapar
     */
    public static void scrollToPosition(WebDriver driver, int x, int y) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + ", " + y + ");");
            log("📜 Sayfa scroll yapıldı: x=" + x + ", y=" + y);
        } catch (Exception e) {
            log("⚠️ Scroll başarısız: " + e.getMessage());
        }
    }

    /**
     * Sayfanın en altına scroll yapar
     */
    public static void scrollToBottom(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            log("📜 Sayfanın en altına scroll yapıldı");
        } catch (Exception e) {
            log("⚠️ Scroll başarısız: " + e.getMessage());
        }
    }

    /**
     * Sayfanın en üstüne scroll yapar
     */
    public static void scrollToTop(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            log("📜 Sayfanın en üstüne scroll yapıldı");
        } catch (Exception e) {
            log("⚠️ Scroll başarısız: " + e.getMessage());
        }
    }

    // ============ HOVER METHODLARI ============

    /**
     * Elementin üzerine hover yapar
     */
    private static void hoverOverElement(WebDriver driver, WebElement element) {
        try {
            log("   🖱️ Element üzerine hover yapılıyor...");

            // Method 1: Actions ile hover
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(300);
            log("   ✓ Actions ile hover başarılı");

        } catch (Exception e) {
            log("   ⚠️ İlk hover methodu başarısız: " + e.getMessage());
            tryAlternativeHover(driver, element);
        }
    }

    /**
     * Alternatif hover methodları
     */
    private static void tryAlternativeHover(WebDriver driver, WebElement element) {
        try {
            // Method 2: JavaScript ile mouse event
            String mouseOverScript =
                    "var event = new MouseEvent('mouseover', {" +
                            "  view: window," +
                            "  bubbles: true," +
                            "  cancelable: true" +
                            "});" +
                            "arguments[0].dispatchEvent(event);";

            ((JavascriptExecutor) driver).executeScript(mouseOverScript, element);
            Thread.sleep(300);
            log("   ✓ JavaScript mouseover event başarılı");

        } catch (Exception e2) {
            try {
                // Method 3: Offset ile hover
                Actions actions = new Actions(driver);
                actions.moveToElement(element, 5, 5).perform();
                Thread.sleep(300);
                log("   ✓ Offset ile hover başarılı");
            } catch (Exception e3) {
                log("   ✗ Tüm hover methodları başarısız");
            }
        }
    }

    /**
     * Belirli bir elementin üzerine hover yapar ve bekler (public version)
     */
    public static void hoverAndWait(WebDriver driver, String xpath, Duration waitTime) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(waitTime.toMillis());
            log("🖱️ Hover yapıldı ve " + waitTime.getSeconds() + " saniye beklendi: " + xpath);
        } catch (Exception e) {
            log("⚠️ Hover başarısız: " + e.getMessage());
        }
    }

    // ============ TIKLAMA METHODLARI ============

    /**
     * Elementin tıklanabilir olup olmadığını kontrol eder
     */
    private static boolean isClickable(WebDriver driver, WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Aşamalı tıklama methodları - 8 farklı yöntem dener (scroll ve hover eklendi)
     */
    private static boolean performClickWithFallback(WebDriver driver, WebElement element) {

        // METHOD 1: Normal Click
        try {
            log("   [1/8] Normal click deneniyor...");
            element.click();
            log("   ✓ Normal click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Normal click başarısız: " + e.getMessage());
        }

        // METHOD 2: Actions ile tıklama
        try {
            log("   [2/8] Actions click deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            log("   ✓ Actions click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Actions click başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript click
        try {
            log("   [3/8] JavaScript click deneniyor...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            log("   ✓ JavaScript click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript click başarısız: " + e.getMessage());
        }

        // METHOD 4: Scroll + Click (önce scroll sonra tıkla)
        try {
            log("   [4/8] Scroll + click deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            element.click();
            log("   ✓ Scroll + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + click başarısız: " + e.getMessage());
        }

        // METHOD 5: Hover + Click (önce hover sonra tıkla)
        try {
            log("   [5/8] Hover + click deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(300).click().perform();
            log("   ✓ Hover + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Hover + click başarısız: " + e.getMessage());
        }

        // METHOD 6: Scroll + Hover + Click (hepsi bir arada)
        try {
            log("   [6/8] Scroll + hover + click deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(200).click().perform();
            log("   ✓ Scroll + hover + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + hover + click başarısız: " + e.getMessage());
        }

        // METHOD 7: Koordinatlarla tıklama
        try {
            log("   [7/8] Koordinatlarla tıklama deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).moveByOffset(5, 5).click().perform();
            log("   ✓ Koordinatlarla tıklama başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Koordinatlarla tıklama başarısız: " + e.getMessage());
        }

        // METHOD 8: SendKeys Enter (eğer buton focuslanabiliyorsa)
        try {
            log("   [8/8] SendKeys Enter deneniyor...");
            element.sendKeys(Keys.ENTER);
            log("   ✓ SendKeys Enter başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ SendKeys Enter başarısız: " + e.getMessage());
        }

        return false;
    }

    /**
     * Elementi highlight eder (arka plan rengini değiştirir)
     */
    private static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");

            // Highlight efekti uygula
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    "border: 3px solid red; background-color: yellow; transition: all 0.2s;"
            );

            log("   ✨ Element highlight edildi");

            // Kısa süre bekle
            Thread.sleep(HIGHLIGHT_DURATION.toMillis());

            // Original stili geri al (varsa)
            if (originalStyle != null) {
                js.executeScript(
                        "arguments[0].setAttribute('style', arguments[1]);",
                        element,
                        originalStyle
                );
            } else {
                js.executeScript("arguments[0].removeAttribute('style');", element);
            }

        } catch (Exception e) {
            log("   ⚠️ Highlight kaldırılamadı: " + e.getMessage());
        }
    }

    /**
     * Highlight'ı kaldırır
     */
    private static void unhighlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].removeAttribute('style');", element);
        } catch (Exception e) {
            // Sessiz geç
        }
    }

    /**
     * Konsola log atar
     */
    private static void log(String message) {
        System.out.println("[ClickUtils] " + message);
    }

    // ============ EKSTRA YARDIMCI METHODLAR ============

    /**
     * Sayfa yüklenene kadar bekle
     */
    public static void waitForPageLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(driver1 -> ((JavascriptExecutor) driver1)
                    .executeScript("return document.readyState").equals("complete"));
            log("📄 Sayfa tamamen yüklendi");
        } catch (Exception e) {
            log("⚠️ Sayfa yüklenme beklemesi başarısız");
        }
    }

    /**
     * Elementi bulana kadar bekle (visibility için)
     */
    public static WebElement waitForVisibility(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Tüm sayfada iframe varsa default content'e dön
     */
    public static void switchToDefaultContent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            log("🖼️ Default content'e dönüldü");
        } catch (Exception e) {
            log("⚠️ Default content switch başarısız");
        }
    }

    /**
     * Belirtilen metne (text) sahip olan elemente tıklar.
     * Bu method mevcut clickByXpath sistemini kullandığı için 8 aşamalı tıklama ve highlight özelliklerine sahiptir.
     * @param driver WebDriver örneği
     * @param buttonName Tıklanacak butonun veya elementin üzerindeki tam metin
     */
    public static void clickByText(WebDriver driver, String buttonName) {
        // Tam metin eşleşmesi için normalize-space kullanılır (boşluk hatalarını önler)
        String xpath = "//*[normalize-space(text())='" + buttonName + "']";
        log("🏷️ Metin ile element aranıyor: " + buttonName);
        clickByXpath(driver, xpath);
    }

    /**
     * Belirtilen metni (text) İÇEREN elemente tıklar (Partial Match).
     * @param driver WebDriver örneği
     * @param partialText Elementin içinde geçen kelime veya kelime grubu
     */
    public static void clickByPartialText(WebDriver driver, String partialText) {
        String xpath = "//*[contains(text(),'" + partialText + "')]";
        log("🏷️ Parçalı metin ile element aranıyor: " + partialText);
        clickByXpath(driver, xpath);
    }
}