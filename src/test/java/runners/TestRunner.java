package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepdefinitions",
		plugin = {
				"pretty",
				"html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		},
		monochrome = true, // orjinal konum 20nci satir




		tags = "@US29_TC01", // orjinal konum 25. satir scanarioOutline




		publish=true,  // orjinal konum 30. satir
        dryRun = false
)
public class TestRunner {
	private static final Logger logger = LogManager.getLogger(TestRunner.class);

	@BeforeClass
	public static void setup() {
		// Test çalışmaya başlamadan önce loglama yapılır
		logger.info("Cucumber Test Runner başlatılıyor...");

		// mvn allure:report
		// allure serve target/allure-results
	}
}
