/**
 *
 */
package com.exercise.xspeedit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * XspeedIt robot test.
 *
 * @author Davide Ratti
 *
 */
public class XspeedItRobotTest {

	private final XspeedItRobot robot;

	/**
	 * default constructor
	 */
	public XspeedItRobotTest() {
		robot = new XspeedItRobot();
	}

	/**
	 * Expected correctly.
	 */
	@Test
	public void test() {

		String[] expectedOutputs = new String[] { "9/72", "27/9" };
		String output = robot.execute("279");
		Assert.assertTrue(ArrayUtils.contains(expectedOutputs, output));
	}

	/**
	 * Expected empty result.
	 */
	@Test
	public void testEmpty() {
		String expectedOutput = StringUtils.EMPTY;
		String output = robot.execute(null);
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * Expected exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		String output = robot.execute("abc");
		Assert.assertEquals(null, output);
	}

}
