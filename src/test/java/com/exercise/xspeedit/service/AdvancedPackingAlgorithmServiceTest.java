package com.exercise.xspeedit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.exercise.xspeedit.model.Box;
import com.exercise.xspeedit.service.impl.AdvancedPackingAlgorithmServiceImpl;
import com.exercise.xspeedit.util.Utils;

/**
 *
 * Advanced algorithm test.
 *
 * @author Davide Ratti
 *
 */
public class AdvancedPackingAlgorithmServiceTest {

	private final PackingAlgorithmService service;

	/**
	 * default constructor
	 */
	public AdvancedPackingAlgorithmServiceTest() {
		service = new AdvancedPackingAlgorithmServiceImpl();
	}

	/**
	 * Expected correctly.
	 */
	@Test
	public void test() {
		String input = "279";
		String expectedOutput = "9/72";
		List<Box> output = service.doPacking(Utils.convertToItems(input), 10);
		Assert.assertEquals(expectedOutput, Utils.convertToString(output));
	}

	/**
	 * Expected empty result.
	 */
	@Test
	public void testEmpty() {
		String expectedOutput = StringUtils.EMPTY;
		List<Box> output = service.doPacking(null, 10);
		Assert.assertEquals(expectedOutput, Utils.convertToString(output));
	}

}
