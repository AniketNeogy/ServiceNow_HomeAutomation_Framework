package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductInfoPage extends BasePage{
	
	Logger log = LogManager.getLogger(ProductInfoPage.class);
	String finalQuantitySelected = null;
	String finalSizeSelected = null;
	String finalPrice = null;
	String finalColor = null;
	
    /**Constructor*/
    public ProductInfoPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing Product Info Page...");
    }

    /**Web Elements*/
    By productQuantityTextBox = By.id("quantity_wanted");
    By productQuantityIncrementButton = By.className("icon-plus");
    By productQuantityDecrementButton = By.className("icon-minus");
    By selectSizeDropdown = By.xpath("//select[@class='form-control attribute_select no-print']");
    By selectedSizeLabel = By.xpath("//select[@class='form-control attribute_select no-print']/preceding-sibling::span");
    By productColorGreen = By.xpath("//ul[@id='color_to_pick_list']//a[@name='Green']");
    By productColorYellow = By.xpath("//ul[@id='color_to_pick_list']//a[@name='Yellow']");
    By itemName = By.xpath("//h1[text()='Printed Chiffon Dress']");
    By addToCartButton = By.xpath("//span[text()='Add to cart']");
    By priceLabel = By.xpath("//span[@itemprop='price']");

    /**Page Methods*/
    public ProductInfoPage selectProductDetails(int Quantity, String size, String color) {
    	log.info("Select product details for which we want to place the order");
    	enterText(productQuantityTextBox, String.valueOf(Quantity));
    	click(productQuantityIncrementButton);
    	click(productQuantityIncrementButton);
    	click(productQuantityDecrementButton);
    	scrollElementIntoView(addToCartButton);
    	selectItemFromDropdown(selectSizeDropdown, size);
    	Assert.assertEquals(readText(selectedSizeLabel).trim(), size);
    	if(color.equals("Green"))
    		click(productColorGreen);
    	else if (color.contentEquals("Yellow"))
    		click(productColorYellow);
    	finalQuantitySelected = String.valueOf(Quantity+1);
    	finalSizeSelected = readText(selectedSizeLabel).trim();
    	finalPrice = readText(priceLabel).trim().replace("$", "");
    	finalColor = color;
    			
        return this;
    }

    public CheckOutPage addProductToCard() {
    	log.info("Add the product to cart once all the details are selected");
    	click(addToCartButton);
    	String itemNameAddedToCard = readText(itemName);
        return new CheckOutPage(driver,finalQuantitySelected,finalSizeSelected,finalPrice,itemNameAddedToCard, finalColor);
    }

}
