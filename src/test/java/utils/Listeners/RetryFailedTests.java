package utils.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer{
	private int counter=0;
	Logger log = LogManager.getLogger(RetryFailedTests.class);

	@SuppressWarnings("static-access")
	public boolean retry(ITestResult result) {
		log.info("Retrying Failed Test :: "+result.getMethod());
		if(result.getStatus()==result.FAILURE) {
			if(counter<2) {
				counter++;
				return true;
			}
		}
		return false;
	}

}
