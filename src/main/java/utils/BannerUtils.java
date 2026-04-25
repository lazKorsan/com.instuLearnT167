package utils;

import config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BannerUtils {



    //<--===========================================-->
    public static void displayDynamicBanner(WebDriver driver, String bannerType,
                                            String mainTitle, String testName,
                                            String testDescription, int duration) {
        displayConsoleBanner(bannerType, mainTitle, testName, testDescription);
        displayBrowserBanner(driver, bannerType, mainTitle, testName, testDescription, duration);
    }

    /**
     * 🎯 Hızlı kullanım için overload method
     */
    public static void displayAdminTestBanner(WebDriver driver, String testName, String testDescription) {
        displayDynamicBanner(driver, "start", "ADMIN TESTİ BAŞLIYOR", testName, testDescription, 5000);
    }

    /**
     * 🎯 Test ilerleyişi için banner
     */
    public static void displayProgressBanner(WebDriver driver, String progressTitle, String testName, String stepDescription) {
        displayDynamicBanner(driver, "progress", progressTitle, testName, stepDescription, 3000);
    }

    /**
     * 🎯 Başarı banner'ı
     */
    public static void displaySuccessBanner(WebDriver driver, String successTitle, String testName) {
        displayDynamicBanner(driver, "success", successTitle, testName, "Test başarıyla tamamlandı", 4000);
    }

    /**
     * 🎯 Konsol banner'ı
     */
    private static void displayConsoleBanner(String bannerType, String mainTitle, String testName, String testDescription) {
        String icon = getBannerIcon(bannerType);
        String colorCode = getBannerColorCode(bannerType);

        String banner =
                "\n" + colorCode +
                        "==================================================\n" +
                        "               " + icon + " " + mainTitle + " \n" +
                        "==================================================\n" +
                        "⏰ Zaman: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + "\n" +
                        "🎯 Test: " + testName + "\n" +
                        "📝 Durum: " + testDescription + "\n" +
                        "🌐 URL: " + ConfigReader.getProperty("url") + "\n" +
                        "==================================================\n" +
                        "\u001B[0m"; // Reset color

        System.out.println(banner);
    }

    /**
     * 🎯 Tarayıcı banner'ı
     */
    private static void displayBrowserBanner(WebDriver driver, String bannerType,
                                             String mainTitle, String testName,
                                             String testDescription, int duration) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String[] colors = getBannerColors(bannerType);
            String icon = getBannerIcon(bannerType);

            String script =
                    "// Önceki banner'ı temizle\n" +
                            "var oldBanner = document.getElementById('dynamicTestBanner');\n" +
                            "if (oldBanner) { oldBanner.remove(); }\n" +
                            "\n" +
                            "// Yeni banner oluştur\n" +
                            "var banner = document.createElement('div');\n" +
                            "banner.id = 'dynamicTestBanner';\n" +
                            "banner.style.position = 'fixed';\n" +
                            "banner.style.left = '50%';\n" +
                            "banner.style.top = '50%';\n" +
                            "banner.style.transform = 'translate(-50%, -50%)';\n" +
                            "banner.style.background = 'linear-gradient(135deg, ' + arguments[0] + ')';\n" +
                            "banner.style.color = 'white';\n" +
                            "banner.style.padding = '20px';\n" +
                            "banner.style.borderRadius = '15px';\n" +
                            "banner.style.zIndex = '9999';\n" +
                            "banner.style.boxShadow = '0 10px 30px rgba(0,0,0,0.3)';\n" +
                            "banner.style.fontFamily = 'Arial, sans-serif';\n" +
                            "banner.style.textAlign = 'center';\n" +
                            "banner.style.minWidth = '350px';\n" +
                            "banner.style.border = '3px solid ' + arguments[1];\n" +
                            "banner.style.animation = 'fadeIn 0.5s ease-in';\n" +
                            "\n" +
                            "// İçerik\n" +
                            "banner.innerHTML = '' +\n" +
                            "    '<div style=\"font-size: 28px; margin-bottom: 10px;\">' + arguments[2] + '</div>' +\n" +
                            "    '<h3 style=\"margin: 0 0 15px 0; font-size: 20px; font-weight: bold;\">' + arguments[3] + '</h3>' +\n" +
                            "    '<div style=\"font-size: 14px; margin-bottom: 8px; background: rgba(255,255,255,0.1); padding: 5px; border-radius: 5px;\"><strong>Test:</strong> ' + arguments[4] + '</div>' +\n" +
                            "    '<div style=\"font-size: 14px; margin-bottom: 8px;\"><strong>Açıklama:</strong> ' + arguments[5] + '</div>' +\n" +
                            "    '<div style=\"font-size: 11px; opacity: 0.8; margin-top: 15px; border-top: 1px solid rgba(255,255,255,0.2); padding-top: 10px;\">' + new Date().toLocaleString() + '</div>' +\n" +
                            "    '<div style=\"font-size: 10px; opacity: 0.6; margin-top: 5px;\">' + arguments[6] + ' ms sonra kapanacak...</div>';\n" +
                            "\n" +
                            "// CSS animation ekle\n" +
                            "var style = document.createElement('style');\n" +
                            "style.innerHTML = '@keyframes fadeIn { from { opacity: 0; transform: translate(-50%, -60%); } to { opacity: 1; transform: translate(-50%, -50%); } }';\n" +
                            "document.head.appendChild(style);\n" +
                            "\n" +
                            "// Sayfaya ekle\n" +
                            "document.body.appendChild(banner);\n" +
                            "\n" +
                            "// Belirtilen süre sonra kaldır\n" +
                            "setTimeout(function() {\n" +
                            "    banner.style.animation = 'fadeOut 0.5s ease-out';\n" +
                            "    setTimeout(function() { \n" +
                            "        if (banner.parentNode) { \n" +
                            "            banner.parentNode.removeChild(banner); \n" +
                            "        }\n" +
                            "    }, 500);\n" +
                            "}, arguments[6]);\n" +
                            "\n" +
                            "// Fade out animation için CSS\n" +
                            "var fadeOutStyle = document.createElement('style');\n" +
                            "fadeOutStyle.innerHTML = '@keyframes fadeOut { from { opacity: 1; transform: translate(-50%, -50%); } to { opacity: 0; transform: translate(-50%, -60%); } }';\n" +
                            "document.head.appendChild(fadeOutStyle);";

            js.executeScript(script, colors[0], colors[1], icon, mainTitle, testName, testDescription, duration);

        } catch (Exception e) {
            System.out.println("⚠️ Browser banner gösterilemedi: " + e.getMessage());
        }
    }

    /**
     * 🎯 Banner tipine göre icon belirle
     */
    private static String getBannerIcon(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return "🚀";
            case "progress": return "⏳";
            case "success": return "✅";
            case "warning": return "⚠️";
            case "error": return "❌";
            default: return "🎯";
        }
    }

    /**
     * 🎯 Banner tipine göre renk belirle
     */
    private static String[] getBannerColors(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return new String[]{"#667eea 0%, #764ba2 100%", "#ff6b6b"};
            case "progress": return new String[]{"#f093fb 0%, #f5576c 100%", "#ffd93d"};
            case "success": return new String[]{"#4facfe 0%, #00f2fe 100%", "#00b894"};
            case "warning": return new String[]{"#ff9a9e 0%, #fad0c4 100%", "#fdcb6e"};
            case "error": return new String[]{"#ff5858 0%, #f09819 100%", "#d63031"};
            default: return new String[]{"#667eea 0%, #764ba2 100%", "#ff6b6b"};
        }
    }

    /**
     * 🎯 Konsol renk kodu
     */
    private static String getBannerColorCode(String bannerType) {
        switch (bannerType.toLowerCase()) {
            case "start": return "\u001B[35m"; // Purple
            case "progress": return "\u001B[33m"; // Yellow
            case "success": return "\u001B[32m"; // Green
            case "warning": return "\u001B[33m"; // Yellow
            case "error": return "\u001B[31m"; // Red
            default: return "\u001B[36m"; // Cyan
        }
    }

    // // BANNER BANNER1
    //        ReusableMethods.bekle(2);
    //        BannerUtils.displayProgressBanner(
    //                driver,
    //                "Test Başladı",
    //                "Test Koşuluyor",
    //                "Kullanici LoginPage Sayfasina gider "
    //        ); ReusableMethods.bekle(6);
    //
    //        // // ==================================================
    //        //        //               ⏳ Test Başladı
    //        //        //==================================================
    //        //        //⏰ Zaman: 26.04.2026 00:16:23
    //        //        //🎯 Test: Test Koşuluyor
    //        //        //📝 Durum: Kullanici LoginPage Sayfasina gider
    //        //        //🌐 URL: https://qa.instulearn.com
    //        //        //==================================================


}