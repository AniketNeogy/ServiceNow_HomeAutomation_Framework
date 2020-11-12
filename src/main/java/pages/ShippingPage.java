package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShippingPage extends BasePage {
	
	Logger log = LogManager.getLogger(ShippingPage.class);

    /**Constructor*/
    public ShippingPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing LoginPage...");
    }
    
    /**Web Elements*/
    By shippingIcon  = By.xpath("//span[@class='navigation_page' and text()='Shipping']");
    By acceptShippingMessageLabel = By.xpath("//div[@id='uniform-cgv']/following-sibling::label");
    By acceptShippingcheckbox = By.xpath("//input[@type='checkbox']");
    By errorNotification = By.xpath("//div[@class='fancybox-inner']/p");
    By closeNotification = By.xpath("//a[@title='Close']");
    By proceedToCheckoutButton = By.xpath("(//span[contains(text(),'Proceed to checkout')])[2]");
    
    
    /**Page Methods*/
    public PaymentsPage verifyShippingDetailsAndProceed() {
    	log.info("Verify shipping details and Proceed to the enxt step");
    	waitVisibility(shippingIcon);
    	Assert.assertEquals(readText(acceptShippingMessageLabel),"I agree to the terms of service and will adhere to them unconditionally.");
    	click(proceedToCheckoutButton);
    	Assert.assertEquals(readText(errorNotification),"You must agree to the terms of service before continuing.");
    	click(closeNotification);
    	forceClick(acceptShippingcheckbox);
    	click(proceedToCheckoutButton);
    	return new PaymentsPage(driver);
    }

}
