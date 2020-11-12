package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage {
	Logger log = LogManager.getLogger(HomePage.class);

    /**Constructor*/
    public HomePage(WebDriver driver) {
        super(driver);
        log.debug("Initializing HomePage...");
    }

    /**Variables*/
    String baseURL = "http://automationpractice.com";

    /**Web Elements*/
    By homePageLogo = By.xpath("//img[@src='http://automationpractice.com/img/logo.jpg']");
    By searchInputTextBox = By.id("search_query_top");
    By searchIcon = By.name("submit_search");
    By sortFilterDropDown = By.id("selectProductSort");
    By firstDisplayedItemImage = By.xpath("//ul[@class='product_list grid row']/li[1]//div/a/img");
    By itemNameLabelList = By.xpath("//ul[@class='product_list grid row']/li//h5/a");
    By itemPriceLabelList = By.xpath("//ul[@class='product_list grid row']//span[@itemprop='price']");
    By firstItemMoreDetailsButton = By.xpath("//ul[@class='product_list grid row']/li[1]//div//span[text()='More']");

    
    /**Page Methods*/
    public HomePage gotoMyStoreHomePage() {
    	log.info("Navigate to My Store page");
        driver.get(baseURL);
        waitVisibility(homePageLogo); 
        return this;
    }

    public HomePage performGlobalSearch(String productName) {
    	log.info("Perform global serach with the text :: " + productName);
    	enterText(searchInputTextBox,productName);
    	click(searchIcon);    	
        return this;
    }
    
    public HomePage verfiySearchResultsContainText(String productNameString) {
    	log.info("Verify global serach results to check whether the item is displayed");
    	webElementListContainsText(itemNameLabelList,productNameString);
    	return this;
    }
    
    public HomePage applySortingFilter(String filterName) {
    	log.info("Apply filter with value :: " + filterName);
    	selectItemFromDropdown(sortFilterDropDown,filterName);
        return this;
    }
    
    public HomePage verifyItemsSortedLowestFirst() {
    	log.info("Verify that the items are sorted in lowest first order");
    	double minPrice = Double.parseDouble(driver.findElements(itemPriceLabelList).get(1).getText().trim().replace("$", ""));
    	for (WebElement itemPrice : driver.findElements(itemPriceLabelList)) {
    		if(itemPrice.getText().length()>1 && minPrice > Double.parseDouble(itemPrice.getText().trim().replace("$", "")))
    			Assert.assertEquals("Unsorted List", "Sorted List","Sorting not applied correctly");
		}
		return this;
    }

    public ProductInfoPage selectProductAndNavigateToProductDetailsPage() {
    	log.info("Select the product and procced to the product details page");
    	click(firstDisplayedItemImage);
        return new ProductInfoPage(driver);
    }
    
}