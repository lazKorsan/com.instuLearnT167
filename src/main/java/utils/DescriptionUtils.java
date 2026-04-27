package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║              DescriptionUtils.java  — UI Test Element Inspector      ║
 * ║              Selenium tabanlı gelişmiş element analiz aracı          ║
 * ║              Java versiyonu — Python'dan daha güçlü!                 ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * Kullanım:
 *     DescriptionUtils.inspect(driver, "//button[@id='submit']");
 *     DescriptionUtils.inspect(driver, By.id("email"));
 *
 *     // Detaylı analiz için:
 *     ElementInfo info = DescriptionUtils.analyze(driver, "//input[@name='email']");
 *     System.out.println(info.getBestLocator());
 *     System.out.println(info.isClickable());
 */

public class DescriptionUtils {

    private static final String ORIGINAL_STYLE_KEY = "__desc_original_style__";

    // ANSI renk kodları (konsol çıktısı için)
    private static final String RESET   = "\u001B[0m";
    private static final String BOLD    = "\u001B[1m";
    private static final String DIM     = "\u001B[2m";
    private static final String CYAN    = "\u001B[96m";
    private static final String GREEN   = "\u001B[92m";
    private static final String YELLOW  = "\u001B[93m";
    private static final String RED     = "\u001B[91m";
    private static final String MAGENTA = "\u001B[95m";
    private static final String BLUE    = "\u001B[94m";
    private static final String WHITE   = "\u001B[97m";

    // ══════════════════════════════════════════════════════════════════════
    //  ANA METODLAR (Kolay Kullanım için)
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Element analizi yapar (XPath ile)
     * @param driver WebDriver instance
     * @param xpath XPath locator
     * @return ElementInfo objesi
     */
    public static ElementInfo inspect(WebDriver driver, String xpath) {
        return inspect(driver, By.xpath(xpath));
    }

    /**
     * Element analizi yapar (By ile)
     * @param driver WebDriver instance
     * @param by By locator
     * @return ElementInfo objesi
     */
    public static ElementInfo inspect(WebDriver driver, By by) {
        return analyze(driver, by, "#FFD700", "#FF4500", true, true, 10);
    }

    /**
     * Detaylı element analizi (XPath ile)
     */
    public static ElementInfo analyze(WebDriver driver, String xpath,
                                      String highlightColour, String circleColour,
                                      boolean hover, boolean scroll, int timeout) {
        return analyze(driver, By.xpath(xpath), highlightColour, circleColour, hover, scroll, timeout);
    }

    /**
     * Detaylı element analizi (By ile) — ANA METOD
     */
    public static ElementInfo analyze(WebDriver driver, By by,
                                      String highlightColour, String circleColour,
                                      boolean hover, boolean scroll, int timeout) {

        printBanner("🔍 ELEMENT INSPECTOR — DescriptionUtils", MAGENTA);

        // 0. Elementi bul
        WebElement element = resolveElement(driver, by, timeout);
        if (element == null) {
            printFail("Element bulunamadı veya zaman aşımı!");
            return null;
        }

        // 1. Scroll
        if (scroll) doScroll(driver, element);

        // 2. Hover
        if (hover) doHover(driver, element);

        // 3. Highlight
        doHighlight(driver, element, highlightColour);

        // 4. Dairesel animasyon
        doCircleAnimation(driver, element, circleColour, 3);

        // 5. İnsan gözü tarama efekti
        humanScanEffect(driver, element, highlightColour);

        // 6. Etkileşim testleri
        InteractionProps props = testInteractions(driver, element);

        // 7. Tip tespiti
        String elType = detectType(element);

        // 8. Locator kombinasyonları
        List<LocatorInfo> locators = generateLocators(driver, element);

        // 9. Koordinatlar
        Map<String, Object> coords = getCoordinates(element);

        // 10. Link bilgisi
        String linkTarget = getLinkTarget(driver, element);

        // 11. Ekstra özellikler
        Map<String, Object> extras = getExtraProperties(driver, element);

        // 12. Sayfa URL
        String pageUrl = driver.getCurrentUrl();

        // Raporu yazdır
        printReport(elType, props, locators, coords, pageUrl, linkTarget, extras,
                highlightColour, circleColour);

        // Highlight'ı kaldır
        removeHighlight(driver, element);

        printBanner("✅ ANALİZ TAMAMLANDI", GREEN);

        return new ElementInfo(element, elType, props, locators, coords, pageUrl, linkTarget, extras);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  YARDIMCI METODLAR
    // ══════════════════════════════════════════════════════════════════════

    private static WebElement resolveElement(WebDriver driver, By by, int timeout) {
        try {
            printOk("Locator: " + by + " bekleniyor...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            printOk("Element DOM'da bulundu.");
            return element;
        } catch (TimeoutException e) {
            printFail("Timeout (" + timeout + "s): Element bulunamadı → " + by);
            return null;
        } catch (Exception e) {
            printFail("Element çözümlenirken hata: " + e.getMessage());
            return null;
        }
    }

    private static void doScroll(WebDriver driver, WebElement element) {
        printSection("Scroll İşlemi");
        
        try {
            boolean inView = (boolean) ((JavascriptExecutor) driver).executeScript(
                    "const r = arguments[0].getBoundingClientRect();" +
                            "return r.top >= 0 && r.bottom <= window.innerHeight;",
                    element
            );
            if (!inView) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({behavior:'smooth', block:'center', inline:'center'});",
                        element
                );
                Thread.sleep(600);
                printOk("Element görünür alana smooth-scroll ile getirildi.");
            } else {
                printOk("Element zaten görünür alanda, scroll gerekmedi.");
            }
        } catch (Exception e) {
            printWarn("Scroll sırasında hata: " + e.getMessage());
        }
    }

    private static void doHover(WebDriver driver, WebElement element) {
        printSection("Hover İşlemi");
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(400);
            printOk("Hover uygulandı (ActionChains).");
        } catch (Exception e) {
            printWarn("Hover sırasında hata: " + e.getMessage());
        }
    }

    private static void doHighlight(WebDriver driver, WebElement element, String colour) {
        printSection("Highlight [" + colour + "]");
        try {
            String original = element.getAttribute("style");
            if (original == null) original = "";
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('" + ORIGINAL_STYLE_KEY + "', arguments[1]);",
                    element, original
            );
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.outline = arguments[1];" +
                            "arguments[0].style.outlineOffset = '2px';" +
                            "arguments[0].style.transition = 'outline 0.2s ease';",
                    element, "3px solid " + colour
            );
            Thread.sleep(800);
            printOk("Element 3px " + colour + " çerçeve ile işaretlendi.");
        } catch (Exception e) {
            printWarn("Highlight sırasında hata: " + e.getMessage());
        }
    }

    private static void removeHighlight(WebDriver driver, WebElement element) {
        try {
            String original = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].getAttribute('" + ORIGINAL_STYLE_KEY + "');",
                    element
            );
            if (original != null) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].setAttribute('style', arguments[1]);" +
                                "arguments[0].removeAttribute('" + ORIGINAL_STYLE_KEY + "');",
                        element, original
                );
            }
        } catch (Exception ignored) {}
    }

    private static void doCircleAnimation(WebDriver driver, WebElement element, String colour, int repeat) {
        printSection("Dairesel Animasyon [" + colour + "] ×" + repeat);
        String js = """
            (function(el, colour, repeat) {
                var rect = el.getBoundingClientRect();
                var cx = rect.left + window.scrollX;
                var cy = rect.top + window.scrollY + rect.height / 2;
                var W = rect.width;
                var circle = document.createElement('div');
                circle.id = '__desc_circle__';
                Object.assign(circle.style, {
                    position: 'absolute',
                    width: '24px',
                    height: '24px',
                    borderRadius: '50%',
                    background: colour,
                    opacity: '0.85',
                    zIndex: '999999',
                    pointerEvents: 'none',
                    boxShadow: '0 0 8px 3px ' + colour,
                    top: (cy - 12) + 'px',
                    left: (cx - 12) + 'px'
                });
                document.body.appendChild(circle);
                var step = 0, steps = 40, run = 0;
                function sweep() {
                    if (run >= repeat) { circle.remove(); return; }
                    if (step <= steps) {
                        var progress = step / steps;
                        var eased = 0.5 - Math.cos(Math.PI * progress) / 2;
                        var x = cx + eased * W;
                        var bounce = Math.sin(Math.PI * progress) * 8;
                        circle.style.left = (x - 12) + 'px';
                        circle.style.top = (cy - 12 - bounce) + 'px';
                        step++;
                        setTimeout(sweep, 18);
                    } else {
                        step = 0;
                        run++;
                        setTimeout(sweep, 120);
                    }
                }
                sweep();
            })(arguments[0], arguments[1], arguments[2]);
            """;
        try {
            ((JavascriptExecutor) driver).executeScript(js, element, colour, repeat);
            Thread.sleep((long) (repeat * (40 * 0.018 + 0.15) + 0.3) * 1000);
            printOk("Dairesel animasyon " + repeat + "× tamamlandı.");
        } catch (Exception e) {
            printWarn("Animasyon sırasında hata: " + e.getMessage());
        }
    }

    private static void humanScanEffect(WebDriver driver, WebElement element, String colour) {
        printSection("İnsan Gözü Tarama Efekti");
        String js = """
            (function(el, colour) {
                var rect = el.getBoundingClientRect();
                var scrollX = window.scrollX || window.pageXOffset;
                var scrollY = window.scrollY || window.pageYOffset;
                var line = document.createElement('div');
                line.id = '__desc_scanline__';
                Object.assign(line.style, {
                    position: 'absolute',
                    width: '2px',
                    height: rect.height + 'px',
                    background: 'linear-gradient(to bottom, transparent, ' + colour + ', transparent)',
                    opacity: '0.9',
                    zIndex: '999998',
                    pointerEvents: 'none',
                    top: (rect.top + scrollY) + 'px',
                    left: (rect.left + scrollX) + 'px',
                    transition: 'left 0.05s linear'
                });
                document.body.appendChild(line);
                var box = document.createElement('div');
                Object.assign(box.style, {
                    position: 'absolute',
                    width: rect.width + 'px',
                    height: rect.height + 'px',
                    background: colour,
                    opacity: '0',
                    zIndex: '999997',
                    pointerEvents: 'none',
                    top: (rect.top + scrollY) + 'px',
                    left: (rect.left + scrollX) + 'px',
                    transition: 'opacity 0.3s ease'
                });
                document.body.appendChild(box);
                var steps = 30, step = 0, W = rect.width;
                function scan() {
                    if (step <= steps) {
                        var x = rect.left + scrollX + (step / steps) * W;
                        line.style.left = x + 'px';
                        box.style.opacity = String(0.08 * Math.sin(Math.PI * step / steps));
                        step++;
                        setTimeout(scan, 20);
                    } else {
                        box.style.opacity = '0.18';
                        setTimeout(function() {
                            box.style.opacity = '0';
                            setTimeout(function() {
                                line.remove();
                                box.remove();
                            }, 300);
                        }, 250);
                    }
                }
                scan();
            })(arguments[0], arguments[1]);
            """;
        try {
            ((JavascriptExecutor) driver).executeScript(js, element, colour);
            Thread.sleep(1200);
            printOk("Tarama efekti tamamlandı.");
        } catch (Exception e) {
            printWarn("Tarama efekti sırasında hata: " + e.getMessage());
        }
    }

    private static InteractionProps testInteractions(WebDriver driver, WebElement element) {
        printSection("Etkileşim Testleri");
        InteractionProps props = new InteractionProps();

        // Görünürlük
        try {
            props.visible = element.isDisplayed();
            if (props.visible) printOk("Görünürlük (is_displayed) → true");
            else printFail("Görünürlük (is_displayed) → false");
        } catch (Exception e) {
            props.visible = false;
            printFail("Görünürlük testi hata: " + e.getMessage());
        }

        // Etkinlik
        try {
            props.enabled = element.isEnabled();
            if (props.enabled) printOk("Etkinlik (is_enabled) → true");
            else printFail("Etkinlik (is_enabled) → false");
        } catch (Exception e) {
            props.enabled = false;
            printFail("Etkinlik testi hata: " + e.getMessage());
        }

        // Seçilmiş mi
        try {
            props.selected = element.isSelected();
            printInfo("Seçili (is_selected) →", props.selected);
        } catch (Exception e) {
            props.selected = null;
        }

        // Tıklanabilirlik
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            props.clickable = true;
            printOk("Tıklanabilir (EC.clickable) → true");
        } catch (Exception e) {
            props.clickable = false;
            printFail("Tıklanabilir (EC.clickable) → false");
        }

        // Yazılabilirlik
        String tag = element.getTagName().toLowerCase();
        String type = element.getAttribute("type") != null ? element.getAttribute("type").toLowerCase() : "";
        String readonly = element.getAttribute("readonly");
        String disabled = element.getAttribute("disabled");
        Set<String> writableTags = new HashSet<>(Arrays.asList("input", "textarea"));
        Set<String> writableTypes = new HashSet<>(Arrays.asList(
                "text", "email", "password", "number", "search", "tel", "url",
                "date", "time", "datetime-local", "month", "week", "color", "", null
        ));
        props.writable = writableTags.contains(tag) && writableTypes.contains(type)
                && readonly == null && disabled == null;
        printInfo("Yazılabilir (writable) →", props.writable);

        // CSS pointer-events
        try {
            props.pointerEvents = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0]).pointerEvents;", element
            );
            printInfo("CSS pointer-events →", props.pointerEvents);
        } catch (Exception e) {
            props.pointerEvents = "unknown";
        }

        // Opacity / z-index
        try {
            props.opacity = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0]).opacity;", element
            );
            props.zIndex = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0]).zIndex;", element
            );
            printInfo("CSS opacity →", props.opacity);
            printInfo("CSS z-index →", props.zIndex);
        } catch (Exception ignored) {}

        return props;
    }

    private static String detectType(WebElement element) {
        printSection("Element Tip Tespiti");
        String tag = element.getTagName().toLowerCase();
        String type = element.getAttribute("type") != null ? element.getAttribute("type").toLowerCase() : "";
        String role = element.getAttribute("role") != null ? element.getAttribute("role").toLowerCase() : "";

        Set<String> buttonTags = new HashSet<>(Arrays.asList("button", "a"));
        Set<String> buttonTypes = new HashSet<>(Arrays.asList("button", "submit", "reset", "image"));
        Set<String> buttonRoles = new HashSet<>(Arrays.asList("button", "link", "menuitem", "tab"));
        Set<String> inputTags = new HashSet<>(Arrays.asList("input", "textarea", "select"));
        Set<String> inputTypes = new HashSet<>(Arrays.asList(
                "text", "email", "password", "number", "search", "tel", "url",
                "date", "time", "datetime-local", "month", "week", "color"
        ));

        String label;
        String colour;
        if (buttonTags.contains(tag) || buttonTypes.contains(type) || buttonRoles.contains(role)) {
            label = "BUTTON / Tıklanabilir Element";
            colour = BLUE;
        } else if (inputTags.contains(tag) || inputTypes.contains(type)) {
            label = "INPUT / Yazma Kutusu";
            colour = MAGENTA;
        } else if (tag.equals("select")) {
            label = "SELECT / Açılır Menü";
            colour = CYAN;
        } else if (tag.matches("img|svg|canvas")) {
            label = "MEDIA / Görsel Element";
            colour = YELLOW;
        } else if (tag.equals("form")) {
            label = "FORM";
            colour = GREEN;
        } else {
            label = "DİĞER (" + tag + ")";
            colour = WHITE;
        }

        System.out.printf("%n  %s%s  ┌─ ELEMENT TİPİ ──────────────────────────────┐%s%n",
                colour, BOLD, RESET);
        System.out.printf("  │  %-44s│%n", label);
        System.out.printf("  │  tag=%-12s type=%-12s role=%s  │%n",
                "'" + tag + "'", "'" + type + "'", "'" + role + "'");
        System.out.printf("  └─────────────────────────────────────────────┘%s%n", RESET);

        return label;
    }

    private static List<LocatorInfo> generateLocators(WebDriver driver, WebElement element) {
        printSection("Locator Kombinasyonları (Güçlüden → Zayıfa)");
        List<LocatorInfo> locators = new ArrayList<>();

        // 1. data-testid / data-cy / data-qa
        for (String attr : Arrays.asList("data-testid", "data-cy", "data-qa", "data-test", "data-id")) {
            String val = element.getAttribute(attr);
            if (val != null && !val.isEmpty()) {
                String css = "[" + attr + "=\"" + val + "\"]";
                locators.add(new LocatorInfo(10, "CSS[" + attr + "]", css, isUnique(driver, By.cssSelector(css))));
            }
        }

        // 2. ID
        String id = element.getAttribute("id");
        if (id != null && !id.isEmpty()) {
            locators.add(new LocatorInfo(9, "ID", id, isUnique(driver, By.id(id))));
            locators.add(new LocatorInfo(9, "CSS #id", "#" + id, isUnique(driver, By.cssSelector("#" + id))));
        }

        // 3. Name
        String name = element.getAttribute("name");
        if (name != null && !name.isEmpty()) {
            locators.add(new LocatorInfo(8, "NAME", name, isUnique(driver, By.name(name))));
            locators.add(new LocatorInfo(8, "CSS[name]", "[name=\"" + name + "\"]",
                    isUnique(driver, By.cssSelector("[name=\"" + name + "\"]"))));
        }

        // 4. aria-label
        for (String attr : Arrays.asList("aria-label", "aria-labelledby", "aria-describedby")) {
            String val = element.getAttribute(attr);
            if (val != null && !val.isEmpty()) {
                String css = "[" + attr + "=\"" + val + "\"]";
                locators.add(new LocatorInfo(7, "CSS[" + attr + "]", css, isUnique(driver, By.cssSelector(css))));
            }
        }

        // 5. Placeholder
        String placeholder = element.getAttribute("placeholder");
        if (placeholder != null && !placeholder.isEmpty()) {
            String cssPh = "[placeholder=\"" + placeholder + "\"]";
            String xpathPh = "//*[@placeholder=\"" + placeholder + "\"]";
            locators.add(new LocatorInfo(6, "CSS[placeholder]", cssPh, isUnique(driver, By.cssSelector(cssPh))));
            locators.add(new LocatorInfo(6, "XPATH[@placeholder]", xpathPh, isUnique(driver, By.xpath(xpathPh))));
        }

        // 6. Link text
        if (element.getTagName().equalsIgnoreCase("a")) {
            try {
                String text = element.getText().trim();
                if (!text.isEmpty()) {
                    locators.add(new LocatorInfo(5, "LINK_TEXT", text, isUnique(driver, By.linkText(text))));
                }
            } catch (Exception ignored) {}
        }

        // 7. Text içeriği XPath
        try {
            String text = element.getText().trim();
            if (!text.isEmpty() && text.length() <= 80) {
                String xpathText = "//*[normalize-space(text())=\"" + text.replace("\"", "'") + "\"]";
                locators.add(new LocatorInfo(5, "XPATH[text()]", xpathText, isUnique(driver, By.xpath(xpathText))));
            }
        } catch (Exception ignored) {}

        // 8. tag + class
        try {
            String tag = element.getTagName().toLowerCase();
            String cls = element.getAttribute("class") != null ? element.getAttribute("class") : "";
            List<String> classParts = Arrays.stream(cls.split("\\s+"))
                    .filter(c -> c.length() > 1 && !c.matches("^[0-9_-].*"))
                    .limit(3)
                    .collect(Collectors.toList());
            if (!classParts.isEmpty()) {
                String cssCls = tag + "." + String.join(".", classParts);
                locators.add(new LocatorInfo(4, "CSS tag.class", cssCls, isUnique(driver, By.cssSelector(cssCls))));
            }
        } catch (Exception ignored) {}

        // 9. XPath mutlak
        try {
            String xpathAbs = (String) ((JavascriptExecutor) driver).executeScript("""
                function getXPath(el) {
                    if (el.id) return '//*[@id=\"' + el.id + '\"]';
                    if (el === document.body) return '/html/body';
                    var ix = 0;
                    var siblings = el.parentNode ? el.parentNode.childNodes : [];
                    for (var i = 0; i < siblings.length; i++) {
                        var sib = siblings[i];
                        if (sib === el) {
                            var p = getXPath(el.parentNode);
                            return p + '/' + el.tagName.toLowerCase() + '[' + (ix + 1) + ']';
                        }
                        if (sib.nodeType === 1 && sib.tagName === el.tagName) ix++;
                    }
                }
                return getXPath(arguments[0]);
                """, element);
            if (xpathAbs != null && !xpathAbs.isEmpty()) {
                locators.add(new LocatorInfo(2, "XPATH Mutlak", xpathAbs, isUnique(driver, By.xpath(xpathAbs))));
            }
        } catch (Exception ignored) {}

        // 10. CSS tam yol
        try {
            String cssFull = (String) ((JavascriptExecutor) driver).executeScript("""
                function getCSS(el) {
                    if (el.id) return '#' + el.id;
                    var path = [];
                    while (el && el.nodeType === 1) {
                        var sel = el.nodeName.toLowerCase();
                        if (el.id) { sel = '#' + el.id; path.unshift(sel); break; }
                        var sib = el, nth = 1;
                        while ((sib = sib.previousElementSibling))
                            if (sib.nodeName === el.nodeName) nth++;
                        if (nth !== 1) sel += ':nth-of-type(' + nth + ')';
                        path.unshift(sel);
                        el = el.parentElement;
                    }
                    return path.join(' > ');
                }
                return getCSS(arguments[0]);
                """, element);
            if (cssFull != null && !cssFull.isEmpty()) {
                locators.add(new LocatorInfo(1, "CSS Tam Yol", cssFull, isUnique(driver, By.cssSelector(cssFull))));
            }
        } catch (Exception ignored) {}

        // Sıralama ve yazdırma
        locators.sort((a, b) -> Integer.compare(b.strength, a.strength));

        Map<Integer, String> strengthLabels = new HashMap<>();
        strengthLabels.put(10, "★★★★★ (MÜKEMMEL)");
        strengthLabels.put(9,  "★★★★☆ (ÇOK GÜÇLÜ)");
        strengthLabels.put(8,  "★★★★☆ (GÜÇLÜ)");
        strengthLabels.put(7,  "★★★☆☆ (İYİ)");
        strengthLabels.put(6,  "★★★☆☆ (ORTA-İYİ)");
        strengthLabels.put(5,  "★★★☆☆ (ORTA)");
        strengthLabels.put(4,  "★★☆☆☆ (ZAYIF)");
        strengthLabels.put(3,  "★★☆☆☆ (ZAYIF)");
        strengthLabels.put(2,  "★☆☆☆☆ (ÇOK ZAYIF)");
        strengthLabels.put(1,  "☆☆☆☆☆ (KÖTÜ)");

        for (int i = 0; i < locators.size(); i++) {
            LocatorInfo loc = locators.get(i);
            String sl = strengthLabels.getOrDefault(loc.strength, "?");
            String u = loc.unique == Boolean.TRUE ? "✔ Tekil" : (loc.unique == Boolean.FALSE ? "✘ Tekil Değil" : "?");
            System.out.printf("  %s%2d. [%s]%s  %s%-22s%s%s%s  %s[%s]%s%n",
                    CYAN, i+1, sl, RESET,
                    YELLOW, loc.by, RESET,
                    WHITE, loc.value.length() > 70 ? loc.value.substring(0, 70) : loc.value, RESET,
                    DIM, u, RESET);
        }

        return locators;
    }

    private static Boolean isUnique(WebDriver driver, By by) {
        try {
            return driver.findElements(by).size() == 1;
        } catch (Exception e) {
            return null;
        }
    }

    private static Map<String, Object> getCoordinates(WebElement element) {
        printSection("Element Koordinatları");
        Map<String, Object> coords = new HashMap<>();
        try {
            Point loc = element.getLocation();
            Dimension size = element.getSize();
            int x1 = loc.getX(), y1 = loc.getY();
            int x2 = x1 + size.getWidth(), y2 = y1;
            int x3 = x2, y3 = y1 + size.getHeight();
            int x4 = x1, y4 = y3;
            int cx = x1 + size.getWidth() / 2;
            int cy = y1 + size.getHeight() / 2;

            coords.put("top_left", new int[]{x1, y1});
            coords.put("top_right", new int[]{x2, y2});
            coords.put("bottom_right", new int[]{x3, y3});
            coords.put("bottom_left", new int[]{x4, y4});
            coords.put("center", new int[]{cx, cy});
            coords.put("width", size.getWidth());
            coords.put("height", size.getHeight());

            System.out.printf("  %sSol Üst   (x1,y1)%s  → %s(%d, %d)%s%n", CYAN, RESET, WHITE, x1, y1, RESET);
            System.out.printf("  %sSağ Üst   (x2,y2)%s  → %s(%d, %d)%s%n", CYAN, RESET, WHITE, x2, y2, RESET);
            System.out.printf("  %sSağ Alt   (x3,y3)%s  → %s(%d, %d)%s%n", CYAN, RESET, WHITE, x3, y3, RESET);
            System.out.printf("  %sSol Alt   (x4,y4)%s  → %s(%d, %d)%s%n", CYAN, RESET, WHITE, x4, y4, RESET);
            System.out.printf("  %sMerkez    (cx,cy)%s  → %s(%d, %d)%s%n", CYAN, RESET, WHITE, cx, cy, RESET);
            System.out.printf("  %sBoyut     W×H    %s  → %s%d×%d px%s%n", CYAN, RESET, WHITE, size.getWidth(), size.getHeight(), RESET);
        } catch (Exception e) {
            printWarn("Koordinat alınamadı: " + e.getMessage());
        }
        return coords;
    }

    private static String getLinkTarget(WebDriver driver, WebElement element) {
        printSection("Link / Yönlendirme Bilgisi");
        try {
            String tag = element.getTagName().toLowerCase();
            String href = element.getAttribute("href");
            String onclick = element.getAttribute("onclick") != null ? element.getAttribute("onclick") : "";
            String dataHref = element.getAttribute("data-href") != null ? element.getAttribute("data-href") :
                    (element.getAttribute("data-url") != null ? element.getAttribute("data-url") : "");

            if (href != null && !href.isEmpty()) {
                printOk("href → " + href);
                return href;
            }

            Pattern pattern = Pattern.compile("(?:window\\.location|location\\.href)\\s*=\\s*['\"]([^'\"]+)['\"]");
            Matcher matcher = pattern.matcher(onclick);
            if (matcher.find()) {
                printOk("onclick URL → " + matcher.group(1));
                return matcher.group(1);
            }

            if (!dataHref.isEmpty()) {
                printOk("data-href → " + dataHref);
                return dataHref;
            }

            try {
                WebElement form = element.findElement(By.xpath("ancestor::form[1]"));
                String action = form.getAttribute("action");
                if (action != null && !action.isEmpty()) {
                    printInfo("Form action →", action);
                    return action;
                }
            } catch (Exception ignored) {}

            printInfo("Link hedefi →", "YOK / Bulunamadı");
            return null;
        } catch (Exception e) {
            printWarn("Link bilgisi alınamadı: " + e.getMessage());
            return null;
        }
    }

    private static Map<String, Object> getExtraProperties(WebDriver driver, WebElement element) {
        printSection("Ekstra Element Özellikleri");
        Map<String, Object> extras = new HashMap<>();

        // DOM nitelikleri
        List<String> attrsToCheck = Arrays.asList(
                "class", "style", "title", "tabindex", "autocomplete", "autofocus",
                "required", "maxlength", "minlength", "pattern", "min", "max",
                "step", "accept", "multiple", "form", "formaction", "target", "rel", "download"
        );
        for (String attr : attrsToCheck) {
            String val = element.getAttribute(attr);
            if (val != null && !val.isEmpty()) {
                extras.put(attr, val);
                printInfo(attr, val.length() > 80 ? val.substring(0, 80) : val);
            }
        }

        // İç metin
        try {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                extras.put("text", text);
                printInfo("Görünen Metin", text.length() > 80 ? text.substring(0, 80) : text);
            }
        } catch (Exception ignored) {}

        // Computed CSS
        List<String> importantCss = Arrays.asList(
                "display", "visibility", "position", "cursor", "overflow",
                "backgroundColor", "color", "fontSize", "border"
        );
        Map<String, String> cssVals = new HashMap<>();
        for (String prop : importantCss) {
            try {
                String val = (String) ((JavascriptExecutor) driver).executeScript(
                        "return window.getComputedStyle(arguments[0])['" + prop + "'];", element
                );
                if (val != null && !val.isEmpty() && !val.equals("none") && !val.equals("auto")
                        && !val.equals("normal") && !val.equals("0px")) {
                    cssVals.put(prop, val);
                    printInfo("CSS " + prop, val.length() > 60 ? val.substring(0, 60) : val);
                }
            } catch (Exception ignored) {}
        }
        extras.put("computed_css", cssVals);

        // Shadow DOM
        try {
            Object shadow = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot;", element);
            boolean hasShadow = shadow != null;
            extras.put("has_shadow_dom", hasShadow);
            if (hasShadow) printWarn("⚡ Shadow DOM mevcut! Locator çalışmayabilir.");
            else printInfo("Shadow DOM", "Yok");
        } catch (Exception ignored) {}

        // iframe içinde mi?
        try {
            boolean inIframe = (boolean) ((JavascriptExecutor) driver).executeScript("return window.self !== window.top;");
            extras.put("in_iframe", inIframe);
            if (inIframe) printWarn("⚡ Element bir iframe içinde!");
            else printInfo("iframe içinde", "Hayır");
        } catch (Exception ignored) {}

        // DOM derinliği
        try {
            Long depth = (Long) ((JavascriptExecutor) driver).executeScript("""
                var d = 0, el = arguments[0];
                while (el.parentElement) { d++; el = el.parentElement; }
                return d;
                """, element);
            extras.put("dom_depth", depth);
            printInfo("DOM Derinliği", depth);
        } catch (Exception ignored) {}

        // Çocuk sayısı
        try {
            Long children = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].children.length;", element);
            extras.put("children_count", children);
            printInfo("Çocuk Element Sayısı", children);
        } catch (Exception ignored) {}

        // ARIA
        String role = element.getAttribute("role");
        String label = element.getAttribute("aria-label");
        if (role != null && !role.isEmpty()) printInfo("ARIA role", role);
        if (label != null && !label.isEmpty()) printInfo("ARIA label", label);
        extras.put("aria_role", role != null ? role : "");
        extras.put("aria_label", label != null ? label : "");

        return extras;
    }

    private static void printReport(String elType, InteractionProps props, List<LocatorInfo> locators,
                                    Map<String, Object> coords, String pageUrl, String linkTarget,
                                    Map<String, Object> extras, String highlightColour, String circleColour) {
        printBanner("📋 ÖZET RAPOR", MAGENTA);

        printInfo("Element Tipi", elType);
        printInfo("Sayfa URL", pageUrl);
        printInfo("Görünür", props.visible ? "✔" : "✘");
        printInfo("Etkin", props.enabled ? "✔" : "✘");
        printInfo("Tıklanabilir", props.clickable ? "✔" : "✘");
        printInfo("Yazılabilir", props.writable ? "✔" : "✘");

        if (coords != null && !coords.isEmpty()) {
            printInfo("Boyut", coords.get("width") + "×" + coords.get("height") + " px");
        }

        if (linkTarget != null && !linkTarget.isEmpty()) {
            printInfo("Hedef Link", linkTarget);
        } else {
            printInfo("Hedef Link", "—");
        }

        printInfo("Highlight Rengi", highlightColour);
        printInfo("Daire Rengi", circleColour);

        if (locators != null && !locators.isEmpty()) {
            LocatorInfo best = locators.get(0);
            String bestVal = best.value.length() > 60 ? best.value.substring(0, 60) : best.value;
            printInfo("En İyi Locator", best.by + " → " + bestVal);
        }

        if (extras.containsKey("has_shadow_dom") && (Boolean) extras.get("has_shadow_dom")) {
            printWarn("Shadow DOM tespit edildi!");
        }
        if (extras.containsKey("in_iframe") && (Boolean) extras.get("in_iframe")) {
            printWarn("Element iframe içinde!");
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    //  KONSOL ÇIKTI METODLARI
    // ══════════════════════════════════════════════════════════════════════

    private static void printBanner(String text, String colour) {
        String width = "══════════════════════════════════════════════════════════════════════";
        System.out.printf("%n%s%s%s%s%n", colour, BOLD, width, RESET);
        System.out.printf("%s%s  %s%s%n", colour, BOLD, text, RESET);
        System.out.printf("%s%s%s%s%n", colour, BOLD, width, RESET);
    }

    private static void printSection(String title) {
        System.out.printf("%n%s%s  ▶  %s%s%n", YELLOW, BOLD, title, RESET);
        System.out.printf("%s  %s%s%n", DIM, "─".repeat(60), RESET);
    }

    private static void printOk(String msg) {
        System.out.printf("  %s✔  %s%s%n", GREEN, msg, RESET);
    }

    private static void printFail(String msg) {
        System.out.printf("  %s✘  %s%s%n", RED, msg, RESET);
    }

    private static void printInfo(String label, Object value) {
        System.out.printf("  %s%-28s%s%s%s%s%n", CYAN, label + " ", RESET, WHITE, value, RESET);
    }

    private static void printWarn(String msg) {
        System.out.printf("  %s⚠  %s%s%n", YELLOW, msg, RESET);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  INNER CLASSES
    // ══════════════════════════════════════════════════════════════════════

    public static class InteractionProps {
        public boolean visible = false;
        public boolean enabled = false;
        public Boolean selected = null;
        public boolean clickable = false;
        public boolean writable = false;
        public String pointerEvents = "";
        public String opacity = "";
        public String zIndex = "";
    }

    public static class LocatorInfo {
        public int strength;
        public String by;
        public String value;
        public Boolean unique;

        public LocatorInfo(int strength, String by, String value, Boolean unique) {
            this.strength = strength;
            this.by = by;
            this.value = value;
            this.unique = unique;
        }
    }

    public static class ElementInfo {
        private final WebElement element;
        private final String type;
        private final InteractionProps props;
        private final List<LocatorInfo> locators;
        private final Map<String, Object> coordinates;
        private final String pageUrl;
        private final String linkTarget;
        private final Map<String, Object> extras;

        public ElementInfo(WebElement element, String type, InteractionProps props,
                           List<LocatorInfo> locators, Map<String, Object> coordinates,
                           String pageUrl, String linkTarget, Map<String, Object> extras) {
            this.element = element;
            this.type = type;
            this.props = props;
            this.locators = locators;
            this.coordinates = coordinates;
            this.pageUrl = pageUrl;
            this.linkTarget = linkTarget;
            this.extras = extras;
        }

        public WebElement getElement() { return element; }
        public String getType() { return type; }
        public boolean isVisible() { return props.visible; }
        public boolean isEnabled() { return props.enabled; }
        public boolean isClickable() { return props.clickable; }
        public boolean isWritable() { return props.writable; }
        public String getBestLocator() {
            return locators.isEmpty() ? null : locators.get(0).by + ": \"" + locators.get(0).value + "\"";
        }
        public List<LocatorInfo> getLocators() { return locators; }
        public Map<String, Object> getCoordinates() { return coordinates; }
        public String getPageUrl() { return pageUrl; }
        public String getLinkTarget() { return linkTarget; }
        public Map<String, Object> getExtras() { return extras; }
    }
}
