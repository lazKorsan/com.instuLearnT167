# 🖱️ HoverUtilities — Takım İçin Hover Yardımcı Kütüphanesi

Selenium Actions ile hover işlemleri yapmak için **yeniden kullanılabilir static metodlar** koleksiyonu.

---

## 📦 Kurulum

`HoverUtilities.java` dosyasını `src/main/java/utils/` veya `src/test/java/utils/` altına koy. Tek dosya, ek dependency yok.

**Gereksinim:** Selenium WebDriver (Actions sınıfı için)

---

## 🎯 Ne Zaman Kullanılır?

| Senaryo | Metod |
|---------|-------|
| Basit hover (tooltip, highlight efekti) | `hover()` |
| Hover sonrası bekleme (CSS animasyonu) | `hoverWithWait()` |
| Ekran dışındaki elemente hover | `scrollAndHover()` |
| Gizli buton ortaya çıkarma (e-ticaret) | `hoverChain()` |
| Hover sonra tıkla | `hoverAndClick()` |
| Ekran dışındaki butona scroll+hover+tıkla | `scrollAndHoverAndClick()` |
| Gizli butona hover+tıkla (Add to Cart) | `hoverChainAndClick()` |
| Çoklu menüde gezinme | `hoverMultiple()` |

---

## 📖 Metod Referansı ve Örnekler

### 1️⃣ `hover(driver, element)` — En Temel Hover

```java
// Basit kullanım
WebElement menuItem = driver.findElement(By.id("menu"));
HoverUtilities.hover(driver, menuItem);
```

**Ne yapar?** Mouse'u elementin üstüne getirir.

---

### 2️⃣ `hoverWithWait(driver, element, seconds)` — Hover + Bekleme

```java
// Hover yap, 2 saniye bekle (animasyon için)
HoverUtilities.hoverWithWait(driver, tooltipIcon, 2);
```

**Ne zaman?** Hover sonrası tooltip/animasyon render olması gerekiyorsa.

---

### 3️⃣ `scrollAndHover(driver, element)` — Scroll + Hover

```java
// Sayfanın altındaki bir footer linkine hover yap
WebElement footerLink = driver.findElement(By.xpath("//footer//a[text()='Contact']"));
HoverUtilities.scrollAndHover(driver, footerLink);
```

**Ne zaman?** Element ekran dışındaysa (aşağı/yukarı scroll gerekiyorsa).

> **Neden gerekli?** Selenium Actions, ekranda görünmeyen elementlere hover yapamaz. JavaScript ile önce scroll yaparız.

---

### 4️⃣ `hoverChain(driver, parent, child)` — ⭐ Gizli Buton Ortaya Çıkarma

```java
// E-ticaret ürün kartına hover yapınca "Add to Cart" ikonu ortaya çıkar
WebElement productCard = driver.findElement(By.id("product-123"));
WebElement addToCartIcon = driver.findElement(By.className("btn-add-to-cart"));

HoverUtilities.hoverChain(driver, productCard, addToCartIcon);
// Artık addToCartIcon görünür durumda, başka işlem yapabilirsin
```

**Ne zaman?** Parent'a hover yapınca ortaya çıkan gizli butonlara erişmek için.

> **⚠️ KRITIK:** Normal `hover(parent)` → `hover(child)` çalışmaz! Çünkü Selenium bu iki aksiyonu ayrı yapar, child görünmeden önce mouse hareket eder. Bu metod tek atomik aksiyonla yapar.

---

### 5️⃣ `hoverAndClick(driver, element)` — Hover + Tıkla

```java
// Dropdown menü itemine tıkla
WebElement subMenuItem = driver.findElement(By.xpath("//a[text()='Settings']"));
HoverUtilities.hoverAndClick(driver, subMenuItem);
```

**Ne zaman?** Bazı elementler sadece hover sonrası "tıklanabilir" state'e gelir.

---

### 6️⃣ `scrollAndHoverAndClick(driver, element)` — Üçlü Kombinasyon

```java
// Sayfanın altındaki Submit butonuna tıkla
HoverUtilities.scrollAndHoverAndClick(driver, submitButton);
```

**Ne zaman?** Ekran dışındaki butonlara tıklamanın en güvenli yolu.

---

### 7️⃣ `hoverChainAndClick(driver, parent, child)` — 🏆 Altın Metod

```java
// Ürün kartına hover → Ortaya çıkan "Add to Cart" ikonuna tıkla
// TEK SATIRDA GİZLİ BUTONA TIKLAMA!
HoverUtilities.hoverChainAndClick(driver, productCard, addToCartButton);
```

**Ne zaman?** Gizli butonlara tıklamak için. E-ticaret sitelerinde **SAYISIZ KEZ** işe yarar.

**Neden "altın metod"?** Çoğu test otomasyon mühendisi bu durumu doğru çözmekte zorlanır. Bu pattern'in varlığı bile başlı başına değerli.

---

### 8️⃣ `hoverMultiple(driver, elements...)` — Nested Menu Navigasyonu

```java
// 3 seviyeli menüde gezin: Products → Electronics → Laptops
WebElement productsMenu = driver.findElement(By.id("nav-products"));
WebElement electronicsSubMenu = driver.findElement(By.id("submenu-electronics"));
WebElement laptopsItem = driver.findElement(By.id("item-laptops"));

HoverUtilities.hoverMultiple(driver, productsMenu, electronicsSubMenu, laptopsItem);
```

**Ne zaman?** Birden fazla hover'ı ardışık yapmak gerekiyorsa.

---

## 🧪 Gerçek Test Örneği

Aşağıda gerçek bir e-ticaret satın alma akışı — `HoverUtilities` olmadan 20+ satır, varsa 3 satır:

### ❌ HoverUtilities Kullanmadan (Uzun ve Karmaşık)

```java
// Ürün kartına hover (gizli butonları ortaya çıkar)
Actions actions = new Actions(driver);
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productCard);
Thread.sleep(1000);
actions.moveToElement(productCard).perform();
Thread.sleep(1500);

// Add to Cart'a tıkla (hover'ı koruyarak)
actions.moveToElement(productCard)
       .pause(Duration.ofSeconds(1))
       .moveToElement(addToCartButton)
       .pause(Duration.ofMillis(500))
       .click()
       .perform();

// Description tab'a hover
js.executeScript("arguments[0].scrollIntoView({block: 'center'});", descriptionTab);
Thread.sleep(1000);
actions.moveToElement(descriptionTab).perform();
```

### ✅ HoverUtilities Kullanarak (Okunaklı ve Kısa)

```java
HoverUtilities.hover(driver, productCard);
HoverUtilities.hoverChainAndClick(driver, productCard, addToCartButton);
HoverUtilities.scrollAndHover(driver, descriptionTab);
```

---

## 🎯 Kullanım İpuçları

### ✅ YAP

- Test kodunda hover gerekiyorsa **önce bu util'e bak**. %90 ihtimalle senin senaryona uygun bir metod var.
- **Parametre isimlerine dikkat et:** `parentElement` önce, `childElement` sonra gelir.
- Hata alırsan logda **hangi metod çağrıldığını** belirt.

### ❌ YAPMA

- `HoverUtilities` instance'ı OLUŞTURMA (`new HoverUtilities()`). Static class — direkt kullan.
- `null` parametre geçme. Metod sana hemen `IllegalArgumentException` fırlatır.
- Bu util'i modify etme — yeni ihtiyaç varsa **yeni metod ekle**, mevcut olanları değiştirme (backward compatibility).

---

## 🧠 Design Decisions (Nedenleri)

### Neden `static` metodlar?
Util class'ı state tutmaz. Her çağrı bağımsızdır. Instance oluşturmak gereksiz memory ve karmaşıklık getirir.

### Neden private constructor?
Yanlışlıkla `new HoverUtilities()` yazılmasını engeller. Yanlış kullanımı **compile/runtime** seviyesinde yakalar.

### Neden `Duration.ofMillis` kullanılıyor?
Selenium 4'te önerilen yaklaşım. Hem hassasiyet hem okunabilirlik sağlar.

### Neden `Thread.sleep` var?
Actions zinciri içindeki `.pause()` dışında bazı scroll sonrası bekleme gerekli. `WebDriverWait` bu tür generic util'ler için overkill — basit sleep yeterli.

### Neden `validateParameters` private helper?
DRY prensibi: Parametre validasyonu tek yerden kontrol ediliyor. Değiştirmek istenirse sadece bir yeri değiştirir.

---

## 🐛 Sık Karşılaşılan Hatalar ve Çözümler

### ❗ `MoveTargetOutOfBoundsException`
**Sebep:** Element ekran dışında (viewport'a girmiyor).
**Çözüm:** `hover` yerine `scrollAndHover` kullan.

### ❗ `ElementNotInteractableException`
**Sebep:** Element görünür ama "click"e izin vermiyor (overlay, CSS pointer-events: none).
**Çözüm:** `hoverAndClick` veya `hoverChainAndClick` dene.

### ❗ Hover yapıyor ama gizli buton görünmüyor
**Sebep:** Mouse parent'tan uzaklaşıyor.
**Çözüm:** `hover()` + `click()` yerine **`hoverChainAndClick`** kullan.

### ❗ `NullPointerException`
**Sebep:** Element locator bulamadı, null dönüyor.
**Çözüm:** Element'in `@FindBy` ile doğru tanımlandığından emin ol. Bizim util null check yapıyor ve net mesaj veriyor.

---

## 📊 Performans Notları

| Metod | Ortalama Süre |
|-------|---------------|
| `hover` | ~100ms |
| `hoverWithWait(3)` | ~3.1sn |
| `scrollAndHover` | ~1.2sn (scroll animasyonu) |
| `hoverChain` | ~1.2sn |
| `hoverAndClick` | ~0.6sn |
| `scrollAndHoverAndClick` | ~1.8sn |
| `hoverChainAndClick` | ~1.8sn |
| `hoverMultiple` (3 element) | ~3.3sn |

> Not: Sayfa yüklenme süresi dahil değil. Saf util metod süreleri.

---

## 📚 İlgili Kaynaklar

- [Selenium Actions API Docs](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html)
- [W3C WebDriver Actions Spec](https://www.w3.org/TR/webdriver/#actions)

---

**Soru/öneri için:** QA Automation Team ile iletişime geç.
