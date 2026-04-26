package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.ExamplePage;

public class US24 {

    private static final Logger logger = LogManager.getLogger(US24.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage US24 = new ExamplePage(driver);


        @Given("kullanıcı sisteme kayıtlı bir Instructor hesabıyla giriş yapmıştır")
        public void kullanıcı_sisteme_kayıtlı_bir_ınstructor_hesabıyla_giriş_yapmıştır() {
        }

        @Given("kullanıcı Dashboard sayfasındadır")
        public void kullanıcı_dashboard_sayfasındadır() {
        }

        @Given("kullanıcı Dashboard kenar çubuğunu görüntüler")
        public void kullanıcı_dashboard_kenar_çubuğunu_görüntüler() {
        }

        @When("Dashboard sidebar'da Toplantılar linki görüntülenmelidir")
        public void dashboard_sidebar_da_toplantılar_linki_görüntülenmelidir() {
        }

        @Then("Toplantılar başlığı altında Rezervasyonlarım linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_rezervasyonlarım_linki_görünür_ve_aktif_olmalıdır() {
        }

        @Then("Toplantılar başlığı altında Taleplerim linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_taleplerim_linki_görünür_ve_aktif_olmalıdır() {
        }

        @Then("Toplantılar başlığı altında Ayarlar linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_ayarlar_linki_görünür_ve_aktif_olmalıdır() {
        }

        @Given("kullanıcı Toplantılar başlığı altında bulunan Rezervasyonlarım ikonuna tıklar")
        public void kullanıcı_toplantılar_başlığı_altında_bulunan_rezervasyonlarım_ikonuna_tıklar() {
        }

        @When("Rezervasyonlarım sayfası açılmalıdır")
        public void rezervasyonlarım_sayfası_açılmalıdır() {
        }

        @When("toplantı istatistik bilgileri sayfada görünür olmalıdır")
        public void toplantı_istatistik_bilgileri_sayfada_görünür_olmalıdır() {
        }

        @When("toplantılar listesi görüntülenir")
        public void toplantılar_listesi_görüntülenir() {
        }

        @When("toplantı listesi filtrelenir")
        public void toplantı_listesi_filtrelenir() {
        }

        @Then("Sonuçları göster butonu tıklanır")
        public void sonuçları_göster_butonu_tıklanır() {
        }

        @Then("filtrelenen toplantılar listede görüntülenebilir olmalıdır")
        public void filtrelenen_toplantılar_listede_görüntülenebilir_olmalıdır() {
        }

        @When("kullanıcı bir toplantı üzerinde {string} işlemini yapar")
        public void kullanıcı_bir_toplantı_üzerinde_işlemini_yapar(String string) {
        }

        @When("{string} olmalıdır")
        public void olmalıdır(String string) {
        }

        @When("kullanıcı Rezervasyonlarım sayfasına gider")
        public void kullanıcı_rezervasyonlarım_sayfasına_gider() {
        }

        @Then("Yalnızca açık toplantıları göster onay kutusu görünür ve seçilebilir olmalıdır")
        public void yalnızca_açık_toplantıları_göster_onay_kutusu_görünür_ve_seçilebilir_olmalıdır() {
        }

        @When("kullanıcı Yalnızca açık toplantıları göster onay kutusunu seçer")
        public void kullanıcı_yalnızca_açık_toplantıları_göster_onay_kutusunu_seçer() {
        }

        @Then("yalnızca açık durumundaki toplantılar listelenmeli")
        public void yalnızca_açık_durumundaki_toplantılar_listelenmeli() {
        }

        @When("kullanıcı Yalnızca açık toplantıları göster onay kutusunun seçimini kaldırır")
        public void kullanıcı_yalnızca_açık_toplantıları_göster_onay_kutusunun_seçimini_kaldırır() {
        }

        @Then("tüm toplantılar tekrar listelenmeli")
        public void tüm_toplantılar_tekrar_listelenmeli() {
        }

        @Then("filtre simgeleri görünür ve seçilebilir olmalıdır")
        public void filtre_simgeleri_görünür_ve_seçilebilir_olmalıdır() {
        }

        @When("kullanıcı istediği filtreleri seçer")
        public void kullanıcı_istediği_filtreleri_seçer() {
        }

        @Then("toplantı listesi seçilen kritere göre güncellenmeli")
        public void toplantı_listesi_seçilen_kritere_göre_güncellenmeli() {
        }

        @When("kullanıcı Taleplerim linkine tıklar")
        public void kullanıcı_taleplerim_linkine_tıklar() {
        }

        @Then("Taleplerim sayfası açılmalıdır")
        public void taleplerim_sayfası_açılmalıdır() {
        }

        @Given("kullanıcı Taleplerim sayfasındadır")
        public void kullanıcı_taleplerim_sayfasındadır() {
        }

        @Given("toplantı talep listesi görüntülenmektedir")
        public void toplantı_talep_listesi_görüntülenmektedir() {
        }

        @When("kullanıcı bir toplantı seçer")
        public void kullanıcı_bir_toplantı_seçer() {
        }

        @When("Add to Calendar butonuna tıklar")
        public void add_to_calendar_butonuna_tıklar() {
        }

        @Then("seçili toplantı kullanıcının takvimine başarıyla eklenmelidir")
        public void seçili_toplantı_kullanıcının_takvimine_başarıyla_eklenmelidir() {
        }

        @When("Contact instructor butonuna tıklar")
        public void contact_instructor_butonuna_tıklar() {
        }

        @Then("iletişim ekranı açılmalıdır")
        public void iletişim_ekranı_açılmalıdır() {
        }

        @Then("mesaj gönderilebilir olmalıdır")
        public void mesaj_gönderilebilir_olmalıdır() {
        }

        @When("Finish meeting butonuna tıklar")
        public void finish_meeting_butonuna_tıklar() {
        }

        @When("işlem yapılan toplantı listede finished olarak işaretlenmiş olmalıdır")
        public void işlem_yapılan_toplantı_listede_finished_olarak_işaretlenmiş_olmalıdır() {
        }

        @When("kullanıcı Taleplerim sayfasına gider")
        public void kullanıcı_taleplerim_sayfasına_gider() {
        }

        @Then("Yalnızca açık toplantıları göster onay kutusu görünür olmalıdır")
        public void yalnızca_açık_toplantıları_göster_onay_kutusu_görünür_olmalıdır() {
        }

        @Then("onay kutusu seçilebilir olmalıdır")
        public void onay_kutusu_seçilebilir_olmalıdır() {
        }

        @Then("yalnızca açık durumdaki talepler listelenmeli")
        public void yalnızca_açık_durumdaki_talepler_listelenmeli() {
        }

        @When("kullanıcı Ayarlar linkine tıklar")
        public void kullanıcı_ayarlar_linkine_tıklar() {
        }

        @Then("Ayarlar sayfası açılmalıdır")
        public void ayarlar_sayfası_açılmalıdır() {
        }

        @Then("gün ile ilgili zaman ekleme \\(add time) alanı görünür olmalıdır")
        public void gün_ile_ilgili_zaman_ekleme_add_time_alanı_görünür_olmalıdır() {
        }

        @When("kullanıcı gün bazlı zaman ekleme alanına geçerli bir değer girer")
        public void kullanıcı_gün_bazlı_zaman_ekleme_alanına_geçerli_bir_değer_girer() {
        }

        @When("Kaydet butonuna tıklar")
        public void kaydet_butonuna_tıklar() {
        }

        @Then("değişiklikler başarıyla kaydedilmelidir")
        public void değişiklikler_başarıyla_kaydedilmelidir() {
        }

        @Given("kullanıcı Ayarlar sayfasındadır")
        public void kullanıcı_ayarlar_sayfasındadır() {
        }

        @When("kullanıcı grup toplantısı için saatlik ücret seçim alanına gider")
        public void kullanıcı_grup_toplantısı_için_saatlik_ücret_seçim_alanına_gider() {
        }

        @Then("minimum katılımcı sayısı giriş alanı görünür olmalıdır")
        public void minimum_katılımcı_sayısı_giriş_alanı_görünür_olmalıdır() {
        }

        @Then("maksimum katılımcı sayısı giriş alanı görünür olmalıdır")
        public void maksimum_katılımcı_sayısı_giriş_alanı_görünür_olmalıdır() {
        }

        @When("kullanıcı minimum katılımcı sayısını seçer")
        public void kullanıcı_minimum_katılımcı_sayısını_seçer() {
        }

        @When("kullanıcı maksimum katılımcı sayısını seçer")
        public void kullanıcı_maksimum_katılımcı_sayısını_seçer() {
        }

        @Then("min-max katılımcı bilgileri başarıyla kaydedilmelidir")
        public void min_max_katılımcı_bilgileri_başarıyla_kaydedilmelidir() {
        }

        @When("kullanıcı minimum katılımcı sayısını girer")
        public void kullanıcı_minimum_katılımcı_sayısını_girer() {
        }

        @When("kullanıcı maksimum katılımcı sayısını girer")
        public void kullanıcı_maksimum_katılımcı_sayısını_girer() {
        }

        @When("kullanıcı Ayarlar sayfasına gider")
        public void kullanıcı_ayarlar_sayfasına_gider() {
        }

        @Then("Kaydet butonu sayfada görünür olmalıdır")
        public void kaydet_butonu_sayfada_görünür_olmalıdır() {
        }

        @Then("Kaydet butonu aktif ve tıklanabilir olmalıdır")
        public void kaydet_butonu_aktif_ve_tıklanabilir_olmalıdır() {
        }

        @When("kullanıcı herhangi bir ayar değişikliği yapar")
        public void kullanıcı_herhangi_bir_ayar_değişikliği_yapar() {
        }
    }


