package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    Logger log = LogManager.getLogger(BasePage.class);

    /**Constructor*/
    public BasePage(WebDriver driver) {
    	log.debug("Initializing BasePage...");
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**Click Method*/
    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }
    
    /**Force Click Method*/
    public void forceClick(By elementLocation) {
        waitElementEnabled(elementLocation);
        scrollElementIntoView(elementLocation);
        WebElement element = driver.findElement(elementLocation);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**Write Text*/
    public void enterText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }

    /**Read Text*/
    public String readText(By elementLocation) {
        waitVisibility(elementLocation);
        return driver.findElement(elementLocation).getText();
    }
    
    /**Read Text*/
    public String getAttribiuteValue(By elementLocation, String attribute) {
        return driver.findElement(elementLocation).getAttribute(attribute);
    }
    
    /**Select From Dropdown**/
    public void selectItemFromDropdown(By elementLocation, String visibleText) {
        Select sel = new Select(driver.findElement(elementLocation));
        sel.selectByVisibleText(visibleText);
    }
    
    /**Check if the all WebElements in the list contains the text**/
    public void webElementListContainsText(By elementListLocation, String text) {
    	waitVisibility(elementListLocation);
    	for (WebElement itemName : driver.findElements(elementListLocation)) {
    		Assert.assertTrue(itemName.getText().toLowerCase().contains(text),itemName.getText()+" does not contain the text :: "+text);
		}
    }
    
    /**Perform mouse over action**/
    public void mouseOver(By elementLocation){
    	waitVisibility(elementLocation);
    	Actions action = new Actions(driver);
    	action.moveToElement(driver.findElement(elementLocation));
    }
    
    /**Scroll element into view**/
    public void scrollElementIntoView(By elementLocation){
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	WebElement element = driver.findElement(elementLocation);
    	js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public String getCurrentDateTime() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		log.debug("Current System Date :: "+date);
		return dateFormat.format(date);
    }

    /**Wait*/
    public void waitVisibility(By elementLocator){
    	try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    	}catch (Exception e) {
    		log.info("The element :: " + elementLocator + " is not visible, try again after checking if the locator is correct or by increasing the wait time !");
    		log.debug(e.getMessage());
		}
    }
    
    public void waitElementEnabled(By elementLocator){
    	try {
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    	}catch (Exception e) {
    		log.info("The element :: " + elementLocator + " is not visible, try again after checking if the locator is correct or by increasing the wait time !");
    		log.debug(e.getMessage());
		}
    }
}