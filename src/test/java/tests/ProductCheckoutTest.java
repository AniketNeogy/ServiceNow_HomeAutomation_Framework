package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import utils.Commons.DateTimeUtils;

public class ProductCheckoutTest extends BaseTest {
	Logger log = LogManager.getLogger(ProductCheckoutTest.class);
	public ProductCheckoutTest() {
		// TODO Auto-generated constructor stub
	}
	
    /*Here Data Set1 Corresponds to selecting the cheapest item with product name containing "printed". For this Item we will choose the below configuration :: 
     * Quantity = 3 
     * Size =  M
     * Color  = Green
     * Shipping Address : Name = Aniket Neogy, Address1 = 52 Downtown, City = Denver, State = Colorado, 
     * 					  ZIP = 11111, Country = United States", Home_Phone = 8981833643, Mobile_Phone = 8981833643
    */
    @Test(description = "Search `Printed` on the serachbar and then place order for cheapest item")
    public void searchPrintedAndPlaceOrderForCheapestItemDataSet1() {
        homePage
        .gotoMyStoreHomePage()
        .performGlobalSearch("printed")
        .verfiySearchResultsContainText("printed")
        .applySortingFilter("Price: Lowest first")
        .verifyItemsSortedLowestFirst()
        .selectProductAndNavigateToProductDetailsPage()
        .selectProductDetails(3, "M", "Green")
        .addProductToCard()
        .reviewCartAndProceedToCheckout()
        .verifyOrderSummaryAndProceed()
        .enterLoginCredentialsAndSignin("zarmanshah@malomiesed.com","Testing123")
        .addNewAddress("Aniket", "Neogy", "52 Downtown", "Denver", "Colorado", "11111", "United States", "8981833643", "AniketAddress")
        .chooseDeliveryAddressAndProceed()
        .verifyShippingDetailsAndProceed()
        .completePaymentViaBankWire()
        .verifyOrderSummaryBankWire();
    }
    
    /*Here Data Set2 Corresponds to selecting the cheapest item with product name containing "printed". For this Item we will choose the below configuration :: 
     * Quantity = 4
     * Size =  S
     * Color  = Yellow
     * Shipping Address : Name = Aniket Neogy, Address1 = 32 Downtown, City = Denver, State = Colorado, 
     * 					  ZIP = 55555, Country = United States", Home_Phone = 8981833643, Mobile_Phone = 8981833643
    */
    @Test(description = "Search `Printed` on the serachbar and then place order for cheapest item")
    public void searchPrintedAndPlaceOrderForCheapestItemDataSet2() {
        homePage
        .gotoMyStoreHomePage()
        .performGlobalSearch("printed")
        .verfiySearchResultsContainText("printed")
        .applySortingFilter("Price: Lowest first")
        .verifyItemsSortedLowestFirst()
        .selectProductAndNavigateToProductDetailsPage()
        .selectProductDetails(4, "S", "Yellow")
        .addProductToCard()
        .reviewCartAndProceedToCheckout()
        .verifyOrderSummaryAndProceed()
        .enterLoginCredentialsAndSignin("zarmanshah@malomiesed.com","Testing123")
        .addNewAddress("Aniket", "Neogy", "32 Downtown", "Denver", "Colorado", "55555", "United States", "8981833643", "AnikAddress")
        .chooseDeliveryAddressAndProceed()
        .verifyShippingDetailsAndProceed()
        .completePaymentViaCheque()
        .verifyOrderSummaryBankCheque();
    }
    
}