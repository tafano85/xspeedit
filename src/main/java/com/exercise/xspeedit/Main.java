/**
 *
 */
package com.exercise.xspeedit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exercise.xspeedit.util.Constants;
import com.exercise.xspeedit.util.Utils;

/**
 *
 * XspeedIt launcher.
 *
 * @author Davide Ratti
 *
 */
public class Main {

	/** logger instance */
	private static final Logger log = LogManager.getLogger(Main.class);

	private static String itemsString;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		banner();
		input(args);

		// call robot
		new XspeedItRobot().execute(itemsString);
	}

	/**
	 * Handle input data.
	 *
	 * @param args input arguments
	 */
	private static void input(String[] args) {
		// items data (size) : external input
		itemsString = Constants.ITEMS_STRING_EXAMPLE;
		if (args != null && args.length > 0) {
			if (Utils.validateItemString(args[0])) {
				itemsString = args[0];
			} else {
				log.error("Wrong input : \"{}\"", args[0]);
				throw new IllegalArgumentException("Wrong input : \"" + args[0] + "\"");
			}
		} else {
			log.warn("No input, example data will be used : \"{}\"", Constants.ITEMS_STRING_EXAMPLE);
		}
	}

	/**
	 * Print an application title / banner.
	 */
	private static void banner() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append("\t __   __                        _ _____ _   \n");
		sb.append("\t \\ \\ / /                       | |_   _| |  \n");
		sb.append("\t  \\ V / ___ _ __   ___  ___  __| | | | | |_ \n");
		sb.append("\t  /   \\/ __| '_ \\ / _ \\/ _ \\/ _` | | | | __|\n");
		sb.append("\t / /^\\ \\__ \\ |_) |  __/  __/ (_| |_| |_| |_ \n");
		sb.append("\t \\/   \\/___/ .__/ \\___|\\___|\\__,_|\\___/ \\__|\n");
		sb.append("\t           | |                              \n");
		sb.append("\t           |_|                              \n");
		log.info(sb.toString());
	}

}
