# 📊 Allure Reports: Kurulum ve Kullanım Rehberi

Allure, test otomasyonu sonuçlarınızı zengin, interaktif ve anlaşılır web raporlarına dönüştüren esnek, çok dilli bir raporlama aracıdır. Bu rehber, Maven tabanlı projenize Allure entegrasyonunu adım adım açıklar.

---

## 🚀 1. Maven Bağımlılıklarını Ekleme (`pom.xml`)

Allure'un testlerinizden veri toplayabilmesi için `pom.xml` dosyanıza gerekli kütüphaneleri eklemelisiniz.

### 📦 Adaptör Seçimi
Kullandığınız test framework'üne uygun olan bağımlılığı seçin:

**JUnit 5 için:**
```xml
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-junit5</artifactId>
    <version>2.24.0</version>
</dependency>
```

**Cucumber 7 için:**
```xml
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-cucumber7-jvm</artifactId>
    <version>2.24.0</version>
</dependency>
```

> [!IMPORTANT]
> **AspectJ Weaver Yapılandırması:**
> Allure'un `@Step` gibi anotasyonları işleyebilmesi için `maven-surefire-plugin` içinde AspectJ Weaver tanımlanmalıdır.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar"
        </argLine>
    </configuration>
</plugin>
```

---

## 🛠️ 2. Allure Komut Satırı Aracını (CLI) Kurma

JSON verilerini görsel raporlara dönüştürmek için Allure CLI gereklidir.

| İşletim Sistemi | Komut |
| :--- | :--- |
| **Windows (Scoop)** | `scoop install allure` |
| **macOS (Homebrew)** | `brew install allure` |
| **Linux** | [GitHub Releases](https://github.com/allure-framework/allure2/releases) üzerinden manuel kurulum. |

> [!TIP]
> Kurulum sonrası terminalde `allure --version` komutuyla kurulumu doğrulayın.

---

## 📈 3. Rapor Oluşturma ve Görüntüleme

### 🏁 Testleri Çalıştırma
Öncelikle testleri koşturarak sonuç verilerini (`allure-results`) üretin:
```bash
mvn clean test
```

### 🖼️ Raporu Görüntüleme
Verileri anlık olarak bir web sunucusu üzerinden açmak için:
```bash
allure serve target/allure-results
```

### 📂 Kalıcı Rapor Üretme (CI/CD)
Paylaşılabilir statik bir HTML klasörü oluşturmak için:
```bash
allure generate target/allure-results -o target/allure-report --clean
```

---

## ✨ 4. Raporları Zenginleştirme (Anotasyonlar)

Kodunuzun içine ekleyeceğiniz bu anotasyonlar, raporun okunabilirliğini %100 artırır:

| Anotasyon | Açıklama |
| :--- | :--- |
| `@Epic` / `@Feature` | Testleri modüllere göre gruplar. |
| `@Story` | Kullanıcı hikayesini belirtir. |
| `@Description` | Testin amacını detaylı açıklar. |
| `@Step` | Rapor içerisinde adım adım izleme sağlar. |
| `@Attachment` | Hata anında ekran görüntüsü eklemek için kullanılır. |

**Örnek Kullanım:**
```java
@Test
@Feature("Giriş Paneli")
@Story("Geçerli bilgilerle login")
@Description("Kullanıcının sisteme sorunsuz giriş yaptığının doğrulanması")
public void testLogin() {
    // Test adımları...
}
```

---

## 💡 İpuçları
- `allure-results` klasörünü `.gitignore` dosyanıza eklemeyi unutmayın.
- Jenkins veya GitHub Actions kullanıyorsanız, Allure eklentilerini kurarak her build sonrası otomatik rapor üretebilirsiniz.

---
⭐ *Daha fazla bilgi için [Allure Documentation](https://docs.qameta.io/allure/) sayfasını ziyaret edebilirsiniz.*
