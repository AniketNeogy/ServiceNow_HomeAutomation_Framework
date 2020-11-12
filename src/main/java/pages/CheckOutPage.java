package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckOutPage extends BasePage {
	
	Logger log = LogManager.getLogger(CheckOutPage.class);
	String finalQuantitySelected = null;
	String finalSizeSelected = null;
	String finalPrice = null;
	String itemName = null;
	String color = null;
	
	 /**Constructors*/
	 public CheckOutPage(WebDriver driver) {
	        super(driver);
	        log.debug("Initializing Product Info Page...");
	    }

	public CheckOutPage(WebDriver driver, String finalQuantity, String finalSize, String price, String itemNameAddedToCard, String finalColor) {
		super(driver);
		this.finalQuantitySelected = finalQuantity;
		this.finalSizeSelected = finalSize;
		this.finalPrice = price;
		this.itemName = itemNameAddedToCard;
		this.color = finalColor;
	}
	
	
	/**Web Elements*/
    By productSuccesfullyAddedLabel = By.xpath("//div[contains(@class,'layer_cart_product')]/h2");
    By productItemNameLabel = By.xpath("//span[@id='layer_cart_product_title']");
    By productSizeAndColorLabel = By.xpath("//span[@id='layer_cart_product_attributes']");
    By productQuantityLabel = By.xpath("//span[@id='layer_cart_product_quantity']");
    By proceedToCheckoutButtonReview = By.xpath("//span[contains(text(),'Proceed to checkout')]/parent::a");
    By proceedToCheckoutButton = By.xpath("(//span[contains(text(),'Proceed to checkout')]/parent::a)[2]");
    
    By summaryProductNameLabel = By.xpath("(//p[@class='product-name'])[2]/a");
    By summaryProductColorAndSizeLabel = By.xpath("(//small/a)[2]");
    By summaryProductAvailibility = By.xpath("//td[@class='cart_avail']/span");
    By summaryProductQuantityLabel = By.xpath("//td[@class='cart_quantity text-center']/input[1]");
    
    
    
    
    /**Page Methods*/
    public CheckOutPage reviewCartAndProceedToCheckout() {
    	log.info("Review the item added to the card and then proceed to checkout");
    	Assert.assertEquals(readText(productSuccesfullyAddedLabel).trim(), "Product successfully added to your shopping cart");
    	Assert.assertEquals(readText(productItemNameLabel).trim(), itemName);
    	Assert.assertEquals(readText(productSizeAndColorLabel).trim(), color + ", " +finalSizeSelected);
    	Assert.assertEquals(readText(productQuantityLabel).trim(), finalQuantitySelected);
    	click(proceedToCheckoutButtonReview);
        return this;
    }
    
    public LoginPage verifyOrderSummaryAndProceed() {
    	log.info("On the Checkout page - Verify the Order Summary to check if the order is as per the requirement");
    	Assert.assertEquals(readText(summaryProductNameLabel).trim(), itemName);
    	Assert.assertEquals(readText(summaryProductColorAndSizeLabel).trim(), "Color : "+color+", Size : "+finalSizeSelected);
    	Assert.assertEquals(readText(summaryProductAvailibility).trim(), "In stock");
    	Assert.assertEquals(getAttribiuteValue(summaryProductQuantityLabel,"value").trim(), finalQuantitySelected);
    	click(proceedToCheckoutButton);
        return new LoginPage(driver);
    }
	 

}
