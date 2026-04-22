
Feature: CreateBundele

  Scenario Outline: Kullanici teacher olarak kayit işlemlerini yapar
    * Instructor "<mail>" mail adresi ve "<password>" sifresi kayit olur
    Examples:
      | mail                             | password         |
      | ahmet.instructor2@InstuLearn.com | InsruLearn.2026! |


  @preConditionRegisteration2
  Scenario Outline: Instructor Login Adimlari easy way
     * Instructor "<mail>" adresi ve "<password>" ile sisteme giris yapar
      Examples:
        | mail                             | password         |
        | ahmet.instructor2@InstuLearn.com | InsruLearn.2026! |

  @preConditionRegisteration3
  Scenario: Kullanici kurst kayit islemleri sayfasina gider
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici dashBoard menuden newButtonuna tiklar
    * Kullanici newWebinar sayfasinda oldugunu dogrular
    * Kullanici basicInformation sayfasi bilgilerini doldurur


    @preConditionStep3
  Scenario: step2 adimlari
    * Instructor "ahmet.instructor2@InstuLearn.com" adresi ve "InsruLearn.2026!" ile sisteme giris yapar
    * Kullanici extraInformation sayfasi bilgilerini doldurur
