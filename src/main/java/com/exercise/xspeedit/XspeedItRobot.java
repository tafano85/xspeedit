/**
 *
 */
package com.exercise.xspeedit;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exercise.xspeedit.configuration.ConfigurationManager;
import com.exercise.xspeedit.configuration.ConfigurationPropertyRegistry;
import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.model.Item;
import com.exercise.xspeedit.service.PackingAlgorithmService;
import com.exercise.xspeedit.service.impl.AdvancedPackingAlgorithmServiceImpl;
import com.exercise.xspeedit.service.impl.BasicPackingAlgorithmServiceImpl;
import com.exercise.xspeedit.util.Constants;
import com.exercise.xspeedit.util.Utils;

/**
 *
 * XspeedIt application robot (core class).
 *
 * @author Davide Ratti
 *
 */
public class XspeedItRobot {

	/** logger instance */
	private static final Logger log = LogManager.getLogger(XspeedItRobot.class);

	private final String algorithm;
	private final int boxSize;

	/**
	 * default constructor
	 */
	public XspeedItRobot() {
		// maximum box size : from configuration
		boxSize = ConfigurationManager.conf().getInt(ConfigurationPropertyRegistry.BOX_MAX_SIZE, Constants.DEFAULT_BOX_MAX_SIZE);
		log.debug("Configured maximum box size: {}", boxSize);

		// maximum box size : from configuration
		algorithm = ConfigurationManager.conf().getString(ConfigurationPropertyRegistry.PACKING_ALGORITHM, Constants.BASIC_ALGORITHM);
		log.debug("Configured packing algorithm: {}", algorithm);
	}

	/**
	 * Execute the application.
	 *
	 * @param itemsString the items input
	 * @return the execution result
	 */
	public String execute(String itemsString) {
		log.info("{} : {}", StringUtils.rightPad("items", 18), itemsString);

		// handle items
		List<Item> items = Utils.convertToItems(itemsString);
		if (log.isDebugEnabled() && items != null) {
			log.debug("{} items to pack :", items.size());
			for (Item item : items) {
				log.debug("\t" + item.toString());
			}
		}

		// robot execution
		PackingAlgorithmService packingRobotService = selectAlgorithmService(algorithm);
		long startL = System.currentTimeMillis();
		List<Box> boxes = packingRobotService.doPacking(items, boxSize);
		long stopL = System.currentTimeMillis();
		log.debug("Items packed in {} millis.", stopL - startL);

		// handle boxes
		if (log.isDebugEnabled()) {
			log.debug("{} boxes used by {} robot :", boxes.size(), algorithm);
			for (Box box : boxes) {
				log.debug("\t" + box.toString());
			}
		}
		String result = Utils.convertToString(boxes);

		log.info("{} : {} => {} boxes in {} millis", StringUtils.rightPad(algorithm + " robot", 18), result, boxes.size(), stopL - startL);
		return result;
	}

	/**
	 * Retrieve the packing algorithm service.
	 *
	 * @param algorithm the required algorithm
	 * @return the algorithm instance as {@link PackingAlgorithmService}
	 */
	private static PackingAlgorithmService selectAlgorithmService(String service) {
		PackingAlgorithmService result;
		switch (service) {
			case Constants.ADVANCED_ALGORITHM:
				result = new AdvancedPackingAlgorithmServiceImpl();
				break;
			case Constants.BASIC_ALGORITHM:
				result = new BasicPackingAlgorithmServiceImpl();
				break;
			default:
				throw new UnsupportedOperationException("Cannot initialize algorithm : '" + service + "'");
		}
		return result;
	}

}
