package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
	Logger log = LogManager.getLogger(LoginPage.class);

    /**Constructor*/
    public LoginPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing LoginPage...");
    }

    /**Web Elements*/
    By userNameTextBox = By.id("email");
    By passwordTextBox = By.id("passwd");
    By submitButton = By.id("SubmitLogin");
    By errorMessageUsernameXpath = By.xpath("//div[@class='alert alert-danger']//li");

    /**Page Methods*/
    public AddressPage enterLoginCredentialsAndSignin(String username, String password) {
        /* Empty username and password -- check for notification popup */
        log.info("Verify invalid login Scenarios :: ");
        click(submitButton);
        waitVisibility(errorMessageUsernameXpath);
        Assert.assertEquals(readText(errorMessageUsernameXpath), "An email address required.");
        
        /* valid login */
        log.info("Verify valid login Scenario :: ");
        enterText(userNameTextBox, username);
        enterText(passwordTextBox, password);
        click(submitButton);
        return new AddressPage(driver);
    }

}
