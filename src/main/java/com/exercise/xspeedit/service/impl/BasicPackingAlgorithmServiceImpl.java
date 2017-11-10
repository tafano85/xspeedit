/**
 *
 */
package com.exercise.xspeedit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.model.Item;
import com.exercise.xspeedit.service.PackingAlgorithmService;
import com.exercise.xspeedit.util.Utils;

/**
 *
 * Basic packing algorithm service.
 *
 * @author Davide Ratti
 *
 */
public class BasicPackingAlgorithmServiceImpl implements PackingAlgorithmService {

	/** logger instance */
	private static final Logger log = LogManager.getLogger(AdvancedPackingAlgorithmServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Box> doPacking(List<Item> items, int maxBoxSize) {
		if (items == null || items.isEmpty()) {
			log.warn("Items array seems to be null or empty.");
			return new ArrayList<Box>();
		}

		List<Box> result = new ArrayList<Box>();
		Box currentBox = null;

		// simply iterate through items
		for (Item item : items) {
			// if current box is not enough, add create a new one
			if (currentBox == null || !Utils.canContain(item, currentBox, maxBoxSize)) {
				currentBox = new Box("id" + result.size() + 1);
				result.add(currentBox);
			}
			currentBox.addItem(item);
		}

		// convert from list to array
		return result;
	}

}
