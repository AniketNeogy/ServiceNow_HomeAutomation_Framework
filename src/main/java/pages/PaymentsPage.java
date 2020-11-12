package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentsPage extends BasePage {

	Logger log = LogManager.getLogger(PaymentsPage.class);

    /**Constructor*/
    public PaymentsPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing LoginPage...");
    }
    
    /**Web Elements*/
    By paymentViaBankWireButton = By.xpath("//p[@class='payment_module']/a[@class='bankwire']");
    By paymentViaChequeButton = By.xpath("//p[@class='payment_module']/a[@class='cheque']");
    By bankWirePaymentLabel = By.xpath("//h3[@class='page-subheading']");
    By bankChequePaymentLabel = By.xpath("//h3[@class='page-subheading']");
    By confirmTransferButton = By.xpath("//span[contains(text(),'I confirm my order')]");
    
    
    /**Page Methods*/
    public OrderSummaryPage completePaymentViaBankWire() {
    	log.info("Complete payment using payment mode as  Bank Wire");
    	click(paymentViaBankWireButton);
    	Assert.assertEquals(readText(bankWirePaymentLabel).trim(),"BANK-WIRE PAYMENT.");
    	click(confirmTransferButton);
    	return new OrderSummaryPage(driver);
    }
    
    public OrderSummaryPage completePaymentViaCheque() {
    	log.info("Complete payment using payment mode as  Bank Cheque");
    	click(paymentViaChequeButton);
    	Assert.assertEquals(readText(bankChequePaymentLabel).trim(),"CHECK PAYMENT");
    	click(confirmTransferButton);
    	return new OrderSummaryPage(driver);
    }
    
    
}
