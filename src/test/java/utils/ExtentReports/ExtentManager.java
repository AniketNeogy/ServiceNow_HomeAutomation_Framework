package utils.ExtentReports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**OB: ExtentReports extent instance created here. 
That instance can be reachable by getReporter() method.
*/
public class ExtentManager {
	private static ExtentHtmlReporter htmlReporter;;
	private static ExtentReports extent;
	static Logger log = LogManager.getLogger(ExtentManager.class);

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			//Set HTML reporting file location
			String workingDir = System.getProperty("user.dir");
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				htmlReporter = new ExtentHtmlReporter(workingDir + "\\ExtentReports\\ExtentReportResults.html");
				log.debug("New instance of Extent HTML Reporter is created for Windows");
			}
			else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				htmlReporter = new ExtentHtmlReporter(workingDir + "/ExtentReports/ExtentReportResults.html");
				log.debug("New instance of Extent HTML Reporter is created for Mac");
			}
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			
			//Set System Info
			extent.setSystemInfo("Host Name", "DemoTestWebsite");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("User Name", "Aniket Neogy");
			
			htmlReporter.config().setDocumentTitle("AventStack Extent Report"); 
			
			// Name of the report
			htmlReporter.config().setReportName("Selenium Demo Test Report"); 
			
			// Dark Theme
			htmlReporter.config().setTheme(Theme.DARK);	
		}
		else {
			log.debug("The singleton instance of Extent HTML Reporter is returned");
		}
		return extent;
	}
}
