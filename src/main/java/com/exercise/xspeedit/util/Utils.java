/**
 *
 */
package com.exercise.xspeedit.util;

import java.util.ArrayList;
import java.util.List;

import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.model.Item;

/**
 *
 * Generic utility methods.
 *
 * @author Davide Ratti
 *
 */
public class Utils {

	/**
	 * default constructor (private)
	 */
	private Utils() {
		throw new IllegalStateException("Cannot instantiate an utility class");
	}

	/**
	 * Convert from item-sizes-<code>String</code> (ie. &quot;163841689525773&quot;) into {@link Item} array.
	 * @param itemsString the item-sizes-<code>String</code>
	 * @return the conversion result as {@link Item} array
	 */
	public static List<Item> convertToItems(String itemsString) {
		if (itemsString != null) {
			if (!Utils.validateItemString(itemsString)) {
				throw new IllegalArgumentException("Wrong input : \"" + itemsString + "\"");
			}
			List<Item> result = new ArrayList<Item>();
			for (int i = 0; i < itemsString.length(); i++) {
				String size = String.valueOf(itemsString.charAt(i));
				result.add(new Item("id" + i, Integer.parseInt(size)));
			}
			return result;
		}
		return new ArrayList<Item>();
	}

	/**
	 * Test if in the box there is enough space for the item.
	 *
	 * @param item the item to add
	 * @param box the current box
	 * @param maxBoxSize the max box size
	 * @return true if the box can contain the item, otherwise false
	 */
	public static boolean canContain(Item item, Box box, int maxBoxSize) {
		return box.getCurrentSize() + item.getSize() <= maxBoxSize;
	}

	/**
	 * Validate the input itemString.
	 *
	 * @param itemsString the item string to validate
	 * @return true if itemsString is empty or completely numeric
	 */
	public static boolean validateItemString(String itemsString) {
		for (char c : itemsString.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Convert from {@link Box} array to boxes-divided-item-sizes-<code>String</code>.
	 * @param boxes the {@link Box} array to convert
	 * @return the conversion result as boxes-divided-item-sizes-<code>String</code>
	 */
	public static String convertToString(List<Box> boxes) {
		if (boxes != null) {
			StringBuilder sb = new StringBuilder();
			for (Box box : boxes) {
				if (sb.length() > 0) {
					sb.append("/");
				}
				for (Item item : box.getItems()) {
					sb.append(item.getSize());
				}
			}
			return sb.toString();
		}
		return null;
	}

}
