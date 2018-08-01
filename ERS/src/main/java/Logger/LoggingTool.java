package Logger;

import org.apache.log4j.Logger;

public class LoggingTool {
	
	final static Logger log = Logger.getLogger(LoggingTool.class);
	
	public static void logInfo(String info) {
		log.info(info);
	}
	
	public static void logError(String err) {
		log.error(err);
	}
	
	public static void logWarn(String warn) {
		log.warn(warn);
	}
	
	public static void logDebug(String s) {
		log.debug(s);
	}

}
