/**
 *
 */
package com.exercise.xspeedit.model;

/**
*
* Model representing a box item.
*
* @author Davide Ratti
*
*/
public class Item {

	private String id;
	private Integer size;

	/**
	 * @param id
	 * @param size
	 */
	public Item(String id, Integer size) {
		this.id = id;
		this.size = size;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", size=" + size + "]";
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
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

}
