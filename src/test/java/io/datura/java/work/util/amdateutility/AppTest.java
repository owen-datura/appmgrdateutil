package io.datura.java.work.util.amdateutility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;

public class AppTest {
	@Test
	public void currentDateShouldBePopulated() {
		assertNotNull(AppMgrDateUtil.getCurrentAMDate());
	}

	@Test
	public void applicationManagerToGregorian() {
		// this date should correspond with 11/01/2013
		Long exampleAMDate = 41577l;
		Optional<LocalDate> converted = AppMgrDateUtil.fromAMDate(exampleAMDate);
		assertTrue(converted.isPresent());

		LocalDate ld = converted.get();
		Month m = ld.getMonth();
		assertEquals(Month.NOVEMBER, m);
		int d = ld.getDayOfMonth();
		assertEquals(1, d);
		int year = ld.getYear();
		assertEquals(2013, year);
	}

	@Test
	public void gregorianToAM() {
		// essentially just the opposite of the above test
		LocalDate tmp = LocalDate.of(2013, 11, 1);
		Optional<Number> ov = AppMgrDateUtil.toAMDate(tmp);
		assertTrue(ov.isPresent());
		
		long v = ov.get().longValue();
		assertEquals(41577l, v);
	}
	
	@Test
	public void isOutOfRangeInPast() {
		LocalDate old = LocalDate.of(1626, 1, 1);
		Optional<Number> ov = AppMgrDateUtil.toAMDate(old);
		assertFalse(ov.isPresent());
	}
	
	@Test
	public void isOutOfRangeInFuture() {
		LocalDate n = LocalDate.of(2173, 12, 24);
		Optional<Number> ov = AppMgrDateUtil.toAMDate(n);
		assertFalse(ov.isPresent());
	}
}
