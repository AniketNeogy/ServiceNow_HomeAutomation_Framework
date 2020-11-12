package utils.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * OB: extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getReporter() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
	@SuppressWarnings("rawtypes")
	static Map extentTestMap = new HashMap();
	static ExtentReports extent = ExtentManager.getReporter();
	static Logger log = LogManager.getLogger(ExtentTestManager.class);

	public static synchronized ExtentTest getTest() {
		log.debug("Inside getTest() method for the thread :: "+Thread.currentThread().getId());
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	@SuppressWarnings("unchecked")
	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		log.debug("Inside startTest() method for the thread :: "+Thread.currentThread().getId());
		return test;
	}

	public static void changeBase64ImageLabelInExtent() throws IOException {
		/**The changeBase64ImageLabelInExtent() is the custom method created 
		 * to take base64screenshots and add them into the extent report HTML 
		 * [Note : Here the base64 images are directly added into the report HTML, 
		 * the default extent method only adds the references]*/
		log.debug("Inside changeBase64ImageLabelInExtent() method...");
		
		//Read the Extent Report HTML File created after Test execution
		File file = new File(System.getProperty("user.dir") + "\\ExtentReports\\ExtentReportResults.html");
		
		/*Use Jsoup Library to manipulate the ExtentReportResults.html
		 * DOM to have base64 screenshot as an image link 
		 */
		Document doc = Jsoup.parse(file,"UTF-8");
		Elements screenshotAnchorTags = doc.select("td.step-details a");
		for (Element  element : screenshotAnchorTags) {
			String base64String  = element.attr("href");
			Element imageNode = doc.createElement("img").attr("style", "display:block; width:100px;height:100px;").attr("id", "base64image").attr("src", base64String);
			Element newElement = doc.createElement("a").attr("href", base64String).attr("data-featherlight", "image").appendChild(imageNode);	
			element.replaceWith(newElement);
		}
		FileUtils.writeStringToFile(file, doc.outerHtml(), "UTF-8");
		log.debug("changeBase64ImageLabelInExtent() method completd successfully ! ");
	}
}