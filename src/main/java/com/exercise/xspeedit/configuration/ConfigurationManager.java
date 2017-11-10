/**
 *
 */
package com.exercise.xspeedit.configuration;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.io.ClasspathLocationStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * File-based configuration utility.
 *
 * @author Davide Ratti
 *
 */
public class ConfigurationManager {

	/** logger instance */
	private static final Logger log = LogManager.getLogger(ConfigurationManager.class);

	/* locks */
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final WriteLock w = rwl.writeLock();
	private static final ReadLock r = rwl.readLock();

	/* @formatter:off */
	private static final String[] propFiles = new String[] {
		"config.properties"
	};
	/* @formatter:on */

	/**
	 * default constructor (private)
	 */
	private ConfigurationManager() {
		throw new IllegalStateException("Cannot instantiate an utility class");
	}

	/**
	 * Wrapped application properties
	 */
	protected static Configuration config = null;

	/**
	 * @return the application configuration
	 */
	public static Configuration conf() {
		return retrieveConfig();
	}

	/**
	 * @return the configuration
	 */
	protected static Configuration retrieveConfig() {
		r.lock();
		try {
			if (config == null) {
				r.unlock();
				w.lock();
				try {
					if (config == null) {
						long startL = System.currentTimeMillis();

						config = new CompositeConfiguration();
						((CompositeConfiguration) config).addConfiguration(new SystemConfiguration());
						for (String propFile : propFiles) {
							ClassLoader.getSystemClassLoader().getResourceAsStream(propFile);
							PropertiesBuilderParameters pbp = new Parameters().properties().setFileName(propFile)
									.setLocationStrategy(new ClasspathLocationStrategy());
							FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
									PropertiesConfiguration.class).configure(pbp);
							((CompositeConfiguration) config).addConfiguration(builder.getConfiguration());
						}

						long stopL = System.currentTimeMillis();
						log.debug("Configuration loaded in {} ms", stopL - startL);
					}
				} catch (Exception e) {
					throw new IllegalStateException(e.getMessage(), e);
				} finally {
					r.lock();
					w.unlock();
				}
			}
		} finally {
			r.unlock();
		}
		return config;
	}

}
