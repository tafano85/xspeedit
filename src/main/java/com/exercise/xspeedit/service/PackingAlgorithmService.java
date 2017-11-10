/**
 *
 */
package com.exercise.xspeedit.service;

import java.util.List;

import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.model.Item;

/**
 *
 * Packing algorithm service interface.
 *
 * @author Davide Ratti
 *
 */
public interface PackingAlgorithmService {

	/**
	 * Apply a packing algorithm.
	 *
	 * @param items the items to pack
	 * @param maxBoxSize the box max size
	 * @return the boxes fulfilled
	 */
	List<Box> doPacking(List<Item> items, int maxBoxSize);

}
