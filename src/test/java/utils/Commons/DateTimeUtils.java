package utils.Commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeUtils {
	static Logger log = LogManager.getLogger(DateTimeUtils.class);

	/**Utility method for generating the current date and time*/
	public static String getCurrentSystemDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		log.debug("Current System Date :: "+date);
		return dateFormat.format(date);
	}

}
