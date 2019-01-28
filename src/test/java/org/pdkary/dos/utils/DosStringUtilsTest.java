package org.pdkary.dos.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DosStringUtilsTest {
	public static String TEST_HOUR_ONE = "0030";
	public static String TEST_NOON = "1200";
	public static String TEST_HOUR_48 = "2400";
	@Test
	public void test24HourToDosTime() {
		int val1 = DosStringUtils.toDosTime(TEST_HOUR_ONE);
		int valNoon = DosStringUtils.toDosTime(TEST_NOON);
		int val48 = DosStringUtils.toDosTime(TEST_HOUR_48);
		assertTrue(1 == val1);
		assertTrue(24 == valNoon);
		assertTrue(48 == val48);
	}
	

}
