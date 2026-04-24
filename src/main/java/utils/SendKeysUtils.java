package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendKeysUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration HIGHLIGHT_DURATION = Duration.ofMillis(300);

    /**
     * Ana method - XPath ile elemente text gönderir
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     * @param text Gönderilecek metin
     */
    public static void sendByXpath(WebDriver driver, String xpath, String text) {
        sendByXpath(driver, xpath, text, DEFAULT_TIMEOUT);
    }

    /**
     * Timeout belirterek text gönderme
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     * @param text Gönderilecek metin
     * @param timeout Bekleme süresi
     */
    public static void sendByXpath(WebDriver driver, String xpath, String text, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            // 1. Element görünür olana kadar bekle
            log("🔍 Element aranıyor: " + xpath);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            log("✅ Element görünür hale geldi");

            // 2. Elementin tıklanabilirliğini test et (input alanları için)
            if (isInteractable(driver, element, timeout)) {
                log("✅ Element ile etkileşim kurulabilir durumda");
            } else {
                log("⚠️ Element normal şartlarda etkileşime uygun değil, zorlayıcı methodlar deneniyor...");
            }

            // 3. Elementi highlight et
            highlightElement(driver, element);

            // 4. Input alanını temizle (opsiyonel, isteğe bağlı)
            clearFieldWithFallback(driver, element);

            // 5. Text gönderme işlemini dene (aşamalı olarak)
            boolean sent = performSendKeysWithFallback(driver, element, text);

            // 6. Highlight'ı kaldır
            unhighlightElement(driver, element);

            if (sent) {
                log("✅ TEXT GÖNDERİLDİ - '" + text + "' -> " + xpath);

                // 7. Doğrulama - Text'in gerçekten yazıldığını kontrol et
                verifyTextEntered(driver, element, text);
            } else {
                throw new RuntimeException("❌ Tüm text gönderme methodları başarısız oldu: " + xpath);
            }

        } catch (TimeoutException e) {
            log("❌ ZAMAN AŞIMI: Element bulunamadı veya görünür olmadı: " + xpath);
            throw new RuntimeException("Element bulunamadı: " + xpath, e);
        } catch (Exception e) {
            log("❌ HATA: " + e.getMessage());
            throw new RuntimeException("Text gönderme başarısız: " + xpath, e);
        }
    }

    /**
     * Element ile etkileşim kurulabilir mi kontrolü
     */
    private static boolean isInteractable(WebDriver driver, WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Input alanını temizleme (aşamalı methodlar)
     */
    private static void clearFieldWithFallback(WebDriver driver, WebElement element) {
        log("   🧹 Input alanı temizleniyor...");

        // METHOD 1: Normal clear
        try {
            element.clear();
            log("   ✓ Normal clear başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ Normal clear başarısız: " + e.getMessage());
        }

        // METHOD 2: CTRL+A + DELETE
        try {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            log("   ✓ CTRL+A + DELETE ile temizleme başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ CTRL+A + DELETE başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript ile temizleme
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            log("   ✓ JavaScript ile temizleme başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ JavaScript temizleme başarısız: " + e.getMessage());
        }

        // METHOD 4: Backspace ile temizleme
        try {
            String value = element.getAttribute("value");
            if (value != null) {
                for (int i = 0; i < value.length(); i++) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
                log("   ✓ Backspace ile temizleme başarılı");
            }
        } catch (Exception e) {
            log("   ✗ Backspace temizleme başarısız: " + e.getMessage());
        }
    }

    /**
     * Aşamalı text gönderme methodları - 8 farklı yöntem
     */
    private static boolean performSendKeysWithFallback(WebDriver driver, WebElement element, String text) {

        // METHOD 1: Normal sendKeys
        try {
            log("   [1/8] Normal sendKeys deneniyor...");
            element.sendKeys(text);
            log("   ✓ Normal sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Normal sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 2: Actions ile sendKeys
        try {
            log("   [2/8] Actions sendKeys deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(text).perform();
            log("   ✓ Actions sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Actions sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript ile value atama
        try {
            log("   [3/8] JavaScript value atama deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, text);

            // JavaScript ile atanan değeri tetikle (change event)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    element
            );
            log("   ✓ JavaScript value atama başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript value atama başarısız: " + e.getMessage());
        }

        // METHOD 4: Scroll + sendKeys
        try {
            log("   [4/8] Scroll + sendKeys deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
            element.click();
            element.sendKeys(text);
            log("   ✓ Scroll + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 5: Click + sendKeys (önce tıkla sonra yaz)
        try {
            log("   [5/8] Click + sendKeys deneniyor...");
            element.click();
            Thread.sleep(200);
            element.sendKeys(text);
            log("   ✓ Click + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Click + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 6: Karakter karakter gönderme (yavaş ama garantili)
        try {
            log("   [6/8] Karakter karakter gönderme deneniyor...");
            element.click();
            for (char c : text.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                Thread.sleep(50); // Her karakter arasında 50ms bekle
            }
            log("   ✓ Karakter karakter gönderme başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Karakter karakter gönderme başarısız: " + e.getMessage());
        }

        // METHOD 7: JavaScript ile focus + sendKeys
        try {
            log("   [7/8] JavaScript focus + sendKeys deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
            Thread.sleep(200);
            element.sendKeys(text);
            log("   ✓ JavaScript focus + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript focus + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 8: Robot sınıfı benzeri - paste ile yapıştırma (JavaScript)
        try {
            log("   [8/8] Paste ile yapıştırma deneniyor...");
            element.click();

            // Text'i geçici bir değişkene ata ve paste event'i tetikle
            ((JavascriptExecutor) driver).executeScript(
                    "var text = arguments[1];" +
                            "arguments[0].value = text;" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    element, text
            );
            log("   ✓ Paste ile yapıştırma başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Paste ile yapıştırma başarısız: " + e.getMessage());
        }

        return false;
    }

    /**
     * Text'in gerçekten elemente yazıldığını doğrula
     */
    private static void verifyTextEntered(WebDriver driver, WebElement element, String expectedText) {
        try {
            String actualText = "";

            // Method 1: getAttribute("value")
            actualText = element.getAttribute("value");

            // Method 2: Eğer value boşsa getText() dene
            if (actualText == null || actualText.isEmpty()) {
                actualText = element.getText();
            }

            // Method 3: JavaScript ile değeri al
            if (actualText == null || actualText.isEmpty()) {
                actualText = (String) ((JavascriptExecutor) driver).executeScript(
                        "return arguments[0].value;", element
                );
            }

            if (expectedText.equals(actualText)) {
                log("   ✓ Text doğrulaması başarılı: '" + actualText + "'");
            } else {
                log("   ⚠️ Text doğrulaması: Beklenen='" + expectedText + "', Gerçekleşen='" + actualText + "'");
            }

        } catch (Exception e) {
            log("   ⚠️ Text doğrulaması yapılamadı: " + e.getMessage());
        }
    }

    /**
     * Elementi highlight eder
     */
    private static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");

            // Input alanları için farklı highlight rengi (mavi)
            String tagName = element.getTagName();
            String highlightColor = "input".equalsIgnoreCase(tagName) || "textarea".equalsIgnoreCase(tagName)
                    ? "border: 3px solid blue; background-color: lightblue; transition: all 0.2s;"
                    : "border: 3px solid orange; background-color: yellow; transition: all 0.2s;";

            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    highlightColor
            );

            log("   ✨ Element highlight edildi (renk: " + (tagName.equals("input") ? "mavi" : "sarı") + ")");

            Thread.sleep(HIGHLIGHT_DURATION.toMillis());

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
            log("   ⚠️ Highlight işlemi başarısız: " + e.getMessage());
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
        System.out.println("[SendKeysUtils] " + message);
    }

    // ============ EKSTRA YARDIMCI METHODLAR ============

    /**
     * Sadece elemente tıklar ve text gönderir (önceden temizlemeden)
     */
    public static void appendByXpath(WebDriver driver, String xpath, String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            scrollToElement(driver, element);
            highlightElement(driver, element);
            element.sendKeys(text);
            log("✅ TEXT EKLENDİ - '" + text + "' -> " + xpath);
            unhighlightElement(driver, element);
        } catch (Exception e) {
            log("❌ Text ekleme başarısız: " + e.getMessage());
            throw new RuntimeException("Text ekleme başarısız: " + xpath, e);
        }
    }

    /**
     * Elemente scroll yapar
     */
    private static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
        } catch (Exception e) {
            log("   ⚠️ Scroll başarısız: " + e.getMessage());
        }
    }

    /**
     * Enter tuşuna basar
     */
    public static void pressEnter(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.sendKeys(Keys.ENTER);
            log("⏎ ENTER tuşuna basıldı: " + xpath);
        } catch (Exception e) {
            log("❌ ENTER basılamadı: " + e.getMessage());
        }
    }

    /**
     * Tab tuşuna basar
     */
    public static void pressTab(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.sendKeys(Keys.TAB);
            log("⇆ TAB tuşuna basıldı: " + xpath);
        } catch (Exception e) {
            log("❌ TAB basılamadı: " + e.getMessage());
        }
    }

    /**
     * Input alanındaki text'i alır
     */
    public static String getText(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = element.getAttribute("value");
            if (value == null || value.isEmpty()) {
                value = element.getText();
            }
            log("📝 Text alındı: '" + value + "' -> " + xpath);
            return value;
        } catch (Exception e) {
            log("❌ Text alınamadı: " + e.getMessage());
            return "";
        }
    }

    /**
     * Statik Metot: Belirtilen element üzerinde klavyeden Aşağı Ok (Arrow Down) tuşuna basar.
     * Genellikle dinamik arama listelerinde ilk sıradaki öğeyi seçmek için kullanılır.
     * * @param driver WebDriver örneği
     * @param xpath İşlem yapılacak elementin XPath adresi
     */
    public static void pressArrowDown(WebDriver driver, String xpath) {
        try {
            // Elementi bul
            WebElement element = driver.findElement(By.xpath(xpath));

            // Klavye eylemini gerçekleştir
            element.sendKeys(Keys.ARROW_DOWN);

            System.out.println("⬇️ [SendKeys] ARROW_DOWN tuşuna basıldı: " + xpath);
        } catch (Exception e) {
            System.err.println("❌ [SendKeys] Ok tuşuna basılırken hata oluştu: " + e.getMessage());
        }
    }
}