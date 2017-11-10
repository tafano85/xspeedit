/**
 *
 */
package com.exercise.xspeedit.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.model.Item;
import com.exercise.xspeedit.service.PackingAlgorithmService;
import com.exercise.xspeedit.util.Utils;

/**
 *
 * Advanced packing algorithm service.
 *
 * @author Davide Ratti
 *
 */
public class AdvancedPackingAlgorithmServiceImpl implements PackingAlgorithmService {

	/** logger instance */
	private static final Logger log = LogManager.getLogger(AdvancedPackingAlgorithmServiceImpl.class);

	/**
	 * <p>
	 * 	{@inheritDoc}
	 * </p>
	 * <p>
	 * 	Bin Packing problem.
	 * </p>
	 */
	@Override
	public List<Box> doPacking(List<Item> items, int maxBoxSize) {
		if (items == null || items.isEmpty()) {
			log.warn("Items array seems to be null or empty.");
			return new ArrayList<Box>();
		}

		// sort the items array (desc)
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item one, Item two) {
				return Integer.compare(two.getSize(), one.getSize());
			}
		});

		List<Box> result = new ArrayList<Box>();
		Box suitableBox = null;
		// iterate over items
		for (Item item : items) {
			// search a suitable box
			for (Box box : result) {
				if (Utils.canContain(item, box, maxBoxSize)) {
					suitableBox = box;
					break;
				}
			}
			// if no suitable box found, create one
			if (suitableBox == null) {
				suitableBox = new Box("id" + result.size() + 1);
				result.add(suitableBox);
			}
			// add item to the box
			suitableBox.addItem(item);
			suitableBox = null;
		}

		return result;
	}

}
