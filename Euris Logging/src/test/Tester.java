package test;

import logger.Logger;
import logger.Logger.eLogLevel;

public class Tester {

	public static void main(String[] args) {
		Logger.setLogLevel(eLogLevel.INFO);
//		Logger.log(eLogLevel.INFO, "Info");
//		Logger.log(eLogLevel.ERROR, "Error");
//		Logger.log(eLogLevel.DEBUG, "Debug");
		Logger.info("Info");
		Logger.error("Error");
		Logger.debug("Debug");
		
		System.out.println("End");

	}

}
