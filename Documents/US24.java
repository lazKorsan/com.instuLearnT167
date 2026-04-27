package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.*;
import utils.ReusableMethods;

import java.util.Random;

public class US24 {

    private static final Logger logger = LogManager.getLogger(US24.class);
    WebDriver driver = Hooks.getDriver();
    ExamplePage examplePage = new ExamplePage(driver);
    HomePage homePage = new HomePage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    InstructorDashboardPage instructorDashboardPage = new InstructorDashboardPage(driver);
    Actions actions = new Actions(driver);
    MarketingPage marketingPage = new MarketingPage(driver);
    MeetingsPage meetingsPage = new MeetingsPage(driver);
    Random random = new Random();


        @Given("kullanıcı sisteme kayıtlı bir Instructor hesabıyla giriş yapmıştır")
        public void kullanıcı_sisteme_kayıtlı_bir_ınstructor_hesabıyla_giriş_yapmıştır() {
            driver.get(ConfigReader.getProperty("url"));
            ReusableMethods.bekle(1);
            homePage.homePageLoginLink.click();
            examplePage.loginMethod("burak.yilmaz.teacher@instulearn.com","Learn.123!");
        }

        @Given("kullanıcı Dashboard sayfasındadır")
        public void kullanıcı_dashboard_sayfasındadır() {
            Assertions.assertTrue(instructorDashboardPage.dashboardPageTitle.isDisplayed());

            String expectedTitle="Dashboard";
            String actualTitle=instructorDashboardPage.dashboardPageTitle.getText();
            Assertions.assertEquals(expectedTitle,actualTitle);
            Assertions.assertTrue(instructorDashboardPage.dashboardPageTitle.isDisplayed());

        }



        // @US24_TC01
        @Given("kullanıcı Dashboard kenar çubuğunu görüntüler")
        public void kullanıcı_dashboard_kenar_çubuğunu_görüntüler() {
            Assertions.assertTrue(instructorDashboardPage.dashboardPageSidebar.isDisplayed());
        }

        @When("Dashboard sidebar'da Toplantılar linki görüntülenmelidir")
        public void dashboard_sidebar_da_toplantılar_linki_görüntülenmelidir() {
            actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
            ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMeetingsLink);
            Assertions.assertTrue(instructorDashboardPage.sidebarMeetingsLink.isDisplayed());
        }

        @Then("Toplantılar başlığı altında Rezervasyonlarım linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_rezervasyonlarım_linki_görünür_ve_aktif_olmalıdır() {
            instructorDashboardPage.sidebarMeetingsLink.click();
            Assertions.assertTrue(instructorDashboardPage.myReservationsByMeetings.isDisplayed());
            Assertions.assertTrue(instructorDashboardPage.myReservationsByMeetings.isEnabled());
        }

        @Then("Toplantılar başlığı altında Taleplerim linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_taleplerim_linki_görünür_ve_aktif_olmalıdır() {
            Assertions.assertTrue(instructorDashboardPage.requestByMeetings.isDisplayed());
            Assertions.assertTrue(instructorDashboardPage.requestByMeetings.isEnabled());
        }

        @Then("Toplantılar başlığı altında Ayarlar linki görünür ve aktif olmalıdır")
        public void toplantılar_başlığı_altında_ayarlar_linki_görünür_ve_aktif_olmalıdır() {
            Assertions.assertTrue(instructorDashboardPage.settingsByMeetings.isDisplayed());
            Assertions.assertTrue(instructorDashboardPage.settingsByMeetings.isEnabled());
        }





        // @US24_TC02
        @Given("kullanıcı Toplantılar başlığı altında bulunan Rezervasyonlarım ikonuna tıklar")
        public void kullanıcı_toplantılar_başlığı_altında_bulunan_rezervasyonlarım_ikonuna_tıklar() {
            instructorDashboardPage.myReservationsByMeetings.click();
        }

        @When("Rezervasyonlarım sayfası açılmalıdır")
        public void rezervasyonlarım_sayfası_açılmalıdır() {
            String expectedİçerik = "reservation";
            String actualUrlİçerik = driver.getCurrentUrl();
            Assertions.assertTrue(actualUrlİçerik.contains(expectedİçerik));
        }

        @When("toplantı istatistik bilgileri sayfada görünür olmalıdır")
        public void toplantı_istatistik_bilgileri_sayfada_görünür_olmalıdır() {
            String expectedTitle= "Meeting statistics";
            String actualTitle=meetingsPage.MeetingStatisticsTitleByMyReservations.getText();
            Assertions.assertEquals(expectedTitle,actualTitle);

            Assertions.assertTrue(meetingsPage.openMeetingByStatistics.isDisplayed());
            Assertions.assertTrue(meetingsPage.totalMeetingByStatistics.isDisplayed());
            Assertions.assertTrue(meetingsPage.totalHoursByStatistics.isDisplayed());
        }

        @When("toplantılar listesi görüntülenir")
        public void toplantılar_listesi_görüntülenir() {
            String expectedTitle = "Meetings List";
            String actualTitle = meetingsPage.meetingListTitleByMyReservations.getText();
            Assertions.assertEquals(expectedTitle,actualTitle);

            ReusableMethods.scrollToElement(driver,meetingsPage.meetingListTableByMyReservations);
            ReusableMethods.bekle(1);
            Assertions.assertTrue(meetingsPage.meetingListTableByMyReservations.isDisplayed());
        }

        Select selectDay=new Select(meetingsPage.meetingListDayDropdown);
        Select selectİd=new Select(meetingsPage.instructorIdDropdown);
        @When("toplantı listesinde {string} işlemi yapılır ve sonuçları göster butonu tıklanır")
        public void toplantıListesindeIşlemiYapılırVeSonuçlarıGösterButonuTıklanır(String filtre) {
            switch (filtre) {
                case "day":
                    selectDay.selectByVisibleText("Wednesday");
                    meetingsPage.showResultsButton.click();
                    ReusableMethods.scrollToElement(driver,meetingsPage.meetingListTableByMyReservations);
                    break;
                case "id":
                    selectİd.selectByVisibleText("Asher Morgan");
                    meetingsPage.showResultsButton.click();
                    ReusableMethods.scrollToElement(driver,meetingsPage.meetingListTableByMyReservations);
                    break;
            }
        }

         @Then("filtrelenen toplantılar {string} olarak görüntülenebilir olmalıdır")
         public void filtrelenenToplantılarOlarakGörüntülenebilirOlmalıdır(String filtrelenmiş_liste) {
            switch (filtrelenmiş_liste){
                case "day":
                    ReusableMethods.scrollToElement(driver,meetingsPage.meetingListTableByMyReservations);
                    Assertions.assertFalse(
                            meetingsPage.meetingListDayList.isEmpty(),
                            "Filtrelenmiş gün listesi boş!");

                    for (int i = 0; i < meetingsPage.meetingListDayList.size(); i++) {
                        String expectedDay="Wed";
                        String actualDay=meetingsPage.meetingListDayList.get(i).getText();
                        Assertions.assertEquals(expectedDay,actualDay);
                    }
                    break;
                case "id":
                    ReusableMethods.scrollToElement(driver,meetingsPage.meetingListTableByMyReservations);
                    Assertions.assertFalse(
                            meetingsPage.meetingListInstructorList.isEmpty(),
                            "Filtrelenmiş eğitmen listesi boş!");

                    for (int i = 0; i < meetingsPage.meetingListInstructorList.size(); i++) {
                        String expectedId="Asher Morgan";
                        String actualId=meetingsPage.meetingListInstructorList.get(i).getText();
                        Assertions.assertEquals(expectedId,actualId);
                    }
                    break;
            }
        }

        @When("kullanıcı bir toplantı üzerinde {string} işlemini yapar")
        public void kullanıcı_bir_toplantı_üzerinde_işlemini_yapar(String işlem) {
            switch (işlem){
                case "görüntüle":

                    break;

                case "düzenle":

                    break;

                case "sil":

                    break;
            }
        }

        @When("{string} olmalıdır")
        public void olmalıdır(String beklenen_sonuc) {
            switch (beklenen_sonuc) {
                case "görüntüle":

                    break;

                case "düzenle":

                    break;

                case "sil":

                    break;
            }
        }





        //@US24_TC04
        @When("kullanıcı Rezervasyonlarım sayfasına gider")
        public void kullanıcı_rezervasyonlarım_sayfasına_gider() {
            actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
            ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMeetingsLink);
            instructorDashboardPage.myReservationsByMeetings.click();
        }

        @Then("Yalnızca açık toplantıları göster onay kutusu görünür ve seçilebilir olmalıdır")
        public void yalnızca_açık_toplantıları_göster_onay_kutusu_görünür_ve_seçilebilir_olmalıdır() {
            Assertions.assertTrue(meetingsPage.showOnlyOpenMeetingSwitch.isDisplayed());
            Assertions.assertTrue(meetingsPage.showOnlyOpenMeetingSwitch.isEnabled());
        }

        @When("kullanıcı Yalnızca açık toplantıları göster onay kutusunu seçer")
        public void kullanıcı_yalnızca_açık_toplantıları_göster_onay_kutusunu_seçer() {
            meetingsPage.showOnlyOpenMeetingSwitch.click();
        }

        @Then("yalnızca açık durumundaki toplantılar listelenmeli")
        public void yalnızca_açık_durumundaki_toplantılar_listelenmeli() {

        }

        @When("kullanıcı Yalnızca açık toplantıları göster onay kutusunun seçimini kaldırır")
        public void kullanıcı_yalnızca_açık_toplantıları_göster_onay_kutusunun_seçimini_kaldırır() {
            meetingsPage.showOnlyOpenMeetingSwitch.click();
        }

        @Then("tüm toplantılar tekrar listelenmeli")
        public void tüm_toplantılar_tekrar_listelenmeli() {

        }




        // @US24_TC05
        @When("kullanıcı Taleplerim linkine tıklar")
        public void kullanıcı_taleplerim_linkine_tıklar() {
            actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
            ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMeetingsLink);
            instructorDashboardPage.requestByMeetings.click();

        }

        @Then("Taleplerim sayfası açılmalıdır")
        public void taleplerim_sayfası_açılmalıdır() {
            Assertions.assertTrue(driver.getTitle().contains("requests"));
        }



        // @US24_TC06
        @Given("kullanıcı Taleplerim sayfasındadır")
        public void kullanıcı_taleplerim_sayfasındadır() {
            actions.moveToElement(instructorDashboardPage.dashboardPageSidebar).perform();
            ReusableMethods.scrollToElement(driver,instructorDashboardPage.sidebarMeetingsLink);
            instructorDashboardPage.requestByMeetings.click();
        }

        @Given("toplantı talep listesi görüntülenmektedir")
        public void toplantı_talep_listesi_görüntülenmektedir() {
            Assertions.assertTrue(meetingsPage.meetingRequestsTable.isDisplayed());
        }

        int randomint=random.nextInt(meetingsPage.requestsTableListCRUD.size());
        @When("kullanıcı bir toplantı seçer")
        public void kullanıcı_bir_toplantı_seçer() {
            meetingsPage.requestsTableListCRUD.get(randomint).click();
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


