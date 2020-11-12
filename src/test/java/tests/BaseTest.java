package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.ExtentReports.ExtentTestManager;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    Logger log = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver;
    }
    
    public BaseTest() {
		// TODO Auto-generated constructor stub
	}
    
    @BeforeTest
    @Parameters("browser")
    public void classLevelSetup(String browserName) {
    	log.info("Class Level Steup taking Place...");
    	if(browserName.equals("chrome")) {
    		WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            log.debug("Chrome Driver Initialization Successful");
    	}
    	else if (browserName.equals("firefox")) {
    		WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            log.debug("Firefox Driver Initialization Successful");
    	}
    	driver.manage().window().maximize();
    	driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        log.info("Class Level Steup Successful ! ");
    }

    @BeforeMethod
    public void methodLevelSetup(Method method) {
    	/*Taking the test method name & test description (it will fetch the test name and description from the test being executed) and passing
    	 them as parameters to the ExtentTestManager.startTest() method so that we can log the into the extent report.*/
    	log.info("Method Level Steup taking Place :: "+ method.getName());
    	driver.manage().deleteAllCookies();
    	String testMethodName = method.getName()+" :: "+((RemoteWebDriver) driver).getCapabilities().getBrowserName();
    	String descriptiveTestName = method.getAnnotation(Test.class).description();
    	ExtentTestManager.startTest(testMethodName,descriptiveTestName);
    	homePage = new HomePage(driver);
    	log.info("Method Level Steup Successful ! :: "+ method.getName());
    }

    @AfterTest
    public void classLevelTeardown() {
    	log.info("Class Level Teardown taking Place...");
        driver.quit();
        log.info("Class Level Teardown Successful ! ");
    }
    
    @AfterSuite
    public void suiteLevelTeardown() throws IOException {
    	log.info("Suite Level Teardown taking Place...");
    	/*Here we call the ExtentTestManager.changeBase64ImageLabelInExtent() method, which is the custom method created to take base64screenshots
    	 and add them into the extent report HTML*/
    	ExtentTestManager.changeBase64ImageLabelInExtent();
    	log.info("Suite Level Teardown Successful ! ");
    }
}
