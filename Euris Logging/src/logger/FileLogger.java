package logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logger.Logger.eLogLevel;

/**
 * @author Marco
 * Logger class to write on files
 */
public class FileLogger {

	private static FileLogger istance;				//Singleton!!
	private static Object locker = new Object();
	
	private String filePath;
	
	/**
	 * Default, empty constructor
	 * Remember to use setFilePath!
	 */
	private FileLogger() {
		//Default path
		filePath = "D:\\Euris\\Files\\Logger\\Log.txt";
	}
	
	/**
	 * Creates, if needed, the singleton istance and returns it
	 * @return Singleton istance
	 */
	protected static FileLogger get() {
		if (istance == null)
			synchronized (locker) {
				if (istance == null)
					istance = new FileLogger();
			}
		return istance;
	}
	
	/**
	 * Sets the path where the logger will write
	 * @param filePath Local path where the log will be written. 
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Creates the log line and sends it to FileManager to be written
	 * @param level Message log level (debug, info, error, system)
	 * @param message Message to be logged
	 * @return True if all went well. False if exceptions occurred (from FileManager class)
	 */
	protected boolean log(eLogLevel level, String message) {
		String line = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		line += df.format(new Date());
		line += " [" + level.name() + "] ";
		line += message;
		
		return utils.FileManager.appendToFile(filePath, line);
	}
}
