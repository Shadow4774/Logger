package logger;

public class Logger {

	public static enum eLogLevel{
		DEBUG,
		INFO,
		ERROR,
		SYSTEM
	}
	
	public static enum eLogDestination{
		FILE,
		DATABASE,
		BOTH
	}
	
	private eLogLevel logLevel;
	private eLogDestination logDestination;
	private static final Logger istance = new Logger();
	
	private Logger() {
		logLevel = eLogLevel.INFO;
		logDestination = eLogDestination.FILE;
	}
	
	/**
	 * Sets the minimum level for the event to be logged
	 * @param logLevel Minimum event level for logging
	 */
	public static void setLogLevel(eLogLevel logLevel) {
		istance.logLevel = logLevel;
		istance.innerLog(eLogLevel.SYSTEM, "Log level set to " + logLevel.name());
	}

	/**
	 * Sets the log destination (File, DB or both)
	 * ONLY FILE FOR NOW
	 * @param logDestination Where the log should be written
	 */
	public static void setLogDestination(eLogDestination logDestination) {
		istance.logDestination = logDestination;
	}
	
	/**
	 * Sets the file path for FileLogger
	 * @param path Where the file should be written (MUST include filename)
	 */
	public static void setFilePath(String path) {
		FileLogger.get().setFilePath(path);
		istance.innerLog(eLogLevel.SYSTEM, "File log path set to " + path);
	}
	 
	private boolean innerLog(eLogLevel level, String message) {
		boolean result = false;
		if (level.compareTo(logLevel) >=0) {
			switch (logDestination) {
			case FILE:
				result = FileLogger.get().log(level, message);
				break;
				
			case DATABASE:
				//NOT IMPLEMENTED
				//result = DBLogger.get().log(level, message);
				break;
				
			case BOTH:
				result = FileLogger.get().log(level, message);
				//result = DBLogger.get().log(level, message) && result;
				break;
	
			default:
				break;
			}
		}
		return result;
	}
	
	@Deprecated
	public static boolean log(eLogLevel level, String message) {
		return istance.innerLog(level, message);
	}
	
	/**
	 * Logs a message with info level
	 * @param message Message to be logged
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean info(String message) {
		return istance.innerLog(eLogLevel.INFO, message);
	}
	
	/**
	 * Logs a message with error level
	 * @param message Message to be logged
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean error(String message) {
		return istance.innerLog(eLogLevel.ERROR, message);
	}
	
	/**
	 * Logs a message with debug level
	 * @param message Message to be logged
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean debug(String message) {
		return istance.innerLog(eLogLevel.DEBUG, message);
	}
	
	/**
	 * Logs a message with system level
	 * @param message Message to be logged
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean system(String message) {
		return istance.innerLog(eLogLevel.SYSTEM, message);
	}
}
