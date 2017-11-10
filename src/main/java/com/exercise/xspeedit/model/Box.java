package com.exercise.xspeedit.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Model representing a box.
 *
 * @author Davide Ratti
 *
 */
public class Box {

	private String id;
	private List<Item> items;
	private int currentSize;

	/**
	 * @param id
	 */
	public Box(String id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Box [id=" + id + ", items=" + items + "]";
	}

	/**
	 * @param item the item to add
	 */
	public void addItem(Item item) {
		if (item != null) {
			if (items == null) {
				items = new ArrayList<Item>();
			}
			items.add(item);
			currentSize += item.getSize();
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the currentSize
	 */
	public int getCurrentSize() {
		return currentSize;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

}
