package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends BasePage {
	
	Logger log = LogManager.getLogger(AddressPage.class);

    /**Constructor*/
    public AddressPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing LoginPage...");
    }
    
    /**Variables**/
    String AddressReferenceName = null;

    /**Web Elements*/
    By addNewAddressButton = By.xpath("//span[contains(text(),'Add a new address')]");
    By firstNameTextBox  =  By.id("firstname");
    By lastNameTextBox = By.id("lastname");
    By addressTextBox = By.id("address1");
    By cityTextBox = By.id("city");
    By stateDropdown = By.name("id_state");
    By zipCodeTextBox = By.id("postcode");
    By countryDropDown = By.id("id_country");
    By homePhoneTextBox = By.id("phone");
    By mobilePhoneTextBox = By.id("phone_mobile");
    By addressRefNameTextBox = By.id("alias");
    By SaveButton  = By.xpath("//span[contains(text(),'Save')]");
    By deliveryAddressDropdown = By.id("id_address_delivery");
    By proceedToCheckoutButton = By.xpath("//button[@type='submit' and @name='processAddress']");
    

    /**Page Methods*/
    public AddressPage addNewAddress (String firstName, String LastName, String Address, String City, String State, String zipCode, String Country, String phoneNumber, String referenceName) {
    	click(addNewAddressButton);
    	enterText(firstNameTextBox, firstName);
    	enterText(lastNameTextBox, LastName);
    	enterText(addressTextBox, Address);
    	enterText(cityTextBox, City);
    	selectItemFromDropdown(stateDropdown, State);
    	enterText(zipCodeTextBox, zipCode);
    	selectItemFromDropdown(countryDropDown, Country);
    	enterText(homePhoneTextBox, phoneNumber);
    	enterText(mobilePhoneTextBox, phoneNumber);
    	AddressReferenceName = referenceName + getCurrentDateTime();
    	enterText(addressRefNameTextBox, AddressReferenceName);
    	click(SaveButton);
		return this;  
    }
    
    
    public ShippingPage chooseDeliveryAddressAndProceed () {
    	log.info("Choose delivery method and proceed to the next step");
    	selectItemFromDropdown(deliveryAddressDropdown, AddressReferenceName);
    	forceClick(proceedToCheckoutButton);
		return new ShippingPage(driver);  
    }

}
