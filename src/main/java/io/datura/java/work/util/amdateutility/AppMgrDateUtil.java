package io.datura.java.work.util.amdateutility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

public final class AppMgrDateUtil {
	private static final LocalDate AM_EPOCH_DATE = LocalDate.of(1900, 1, 1);
	private static final LocalDate AM_EARLIEST = LocalDate.of(1626, 03, 19);
	private static final LocalDate AM_LATEST = LocalDate.of(2173, 10, 15);

	private static final long AM_NUM_DAYS_EARLIEST = -99999l;
	private static final long AM_NUM_DAYS_LATEST = 99999l;

	private static final String DATE_FORMAT = "MMddyyyy";
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.US);

	public static Number getCurrentAMDate() {
		return toAMDate(LocalDate.now()).get();
	}

	public static Optional<LocalDate> tryparse(CharSequence value) {
		try {
			return Optional.of(LocalDate.parse(value, fmt));
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}

	public static Optional<Number> toAMDate(LocalDate d) {
		return (!d.isBefore(AM_EARLIEST) && !d.isAfter(AM_LATEST))
				? Optional.of(ChronoUnit.DAYS.between(AM_EPOCH_DATE, d))
				: Optional.empty();
	}

	public static Optional<LocalDate> fromAMDate(Number amDate) {
		if (amDate == null)
			return Optional.empty();

		final long dayDelta = amDate.longValue();
		if (dayDelta < AM_NUM_DAYS_EARLIEST || dayDelta > AM_NUM_DAYS_LATEST)
			return Optional.empty();

		// the 'AM Date' sets 01/01/1900 as its Epoch (AME),
		// so calculating the Gregorian date from it
		// involves adding that many days to the AME
		return Optional.of(AM_EPOCH_DATE.plusDays(amDate.longValue()));
	}
	
	public static String formatDateForUI(LocalDate d ) {
		return fmt.format(d);
	}
}
