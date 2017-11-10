/**
 *
 */
package com.exercise.xspeedit.configuration;

/**
 *
 * @author Davide Ratti
 *
 */
public final class ConfigurationPropertyRegistry {

	public static final String BOX_MAX_SIZE = "xspeedit.box.max.size";
	public static final String PACKING_ALGORITHM = "xspeedit.packing.algorithm";

	/**
	 * default constructor (private)
	 */
	private ConfigurationPropertyRegistry() {
		throw new IllegalStateException("Cannot instantiate an utility class");
	}
}
