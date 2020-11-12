package utils.Commons;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screencapture {
	static Logger log = LogManager.getLogger(Screencapture.class);
	public static String getScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try 
		{
			FileUtils.copyFile(src, destination);
			log.debug("Capture Failed Successful");
		} catch (IOException e) 
		{
			log.debug("Capture Failed "+e.getMessage());
		}

		return path;
	}
	
	public static String getScreenshotBase64(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		String srcFile=ts.getScreenshotAs(OutputType.BASE64);
		log.debug("Base64 screenshot taken successfully");
		return srcFile;
	}

}
