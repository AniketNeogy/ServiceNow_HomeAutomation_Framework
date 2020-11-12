package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderSummaryPage extends BasePage {

	Logger log = LogManager.getLogger(OrderSummaryPage.class);

	/**Constructor*/
	public OrderSummaryPage(WebDriver driver) {
		super(driver);
		log.debug("Initializing LoginPage...");
	}

	/**Web Elements*/
	By paymentViaBankWireLabel = By.xpath(" //p[@class='cheque-indent']/strong");
	By paymentViaBankChequeLabel = By.xpath("//h3[@class='page-subheading']");



	/**Page Methods*/
	public OrderSummaryPage verifyOrderSummaryBankWire() {
		log.info("Verify order summary details when the mode of payment was Bank Wire");
		Assert.assertEquals(readText(paymentViaBankWireLabel).trim(),"Your order on My Store is complete.");
		return this;
	}

	public OrderSummaryPage verifyOrderSummaryBankCheque() {
		log.info("Verify order summary details when the mode of payment was Bank Cheque");
		Assert.assertEquals(readText(paymentViaBankChequeLabel).trim(),"YOUR CHECK MUST INCLUDE:");
		return this;
	}

}
