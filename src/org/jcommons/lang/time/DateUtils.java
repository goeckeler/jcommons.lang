package org.jcommons.lang.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date utilities that build upon {@link org.apache.commons.lang.times.DateUtils}.
 *
 * @author Thorsten Goeckeler
 */
public final class DateUtils
  extends org.apache.commons.lang.time.DateUtils
{
  /** day formats to be tested */
  private static final List<String> DAY_FORMATS = new ArrayList<String>();
  /** day and time formats to be tested */
  private static final List<String> TIME_FORMATS = new ArrayList<String>();

  static {
    DAY_FORMATS.add("dd-MMM-yyyy");
    DAY_FORMATS.add("dd-MMM-yy");
    DAY_FORMATS.add("y-M-d");
    DAY_FORMATS.add("y-MMM-d");
    DAY_FORMATS.add("d-MMM-y");
    DAY_FORMATS.add("d.M.y");
    DAY_FORMATS.add("MMM d, y");
    DAY_FORMATS.add("MMM d,y");
    DAY_FORMATS.add("d/M/y");
    DAY_FORMATS.add("d-M-y");
    DAY_FORMATS.add("d. MMMM y");

    TIME_FORMATS.add("dd-MMM-yyyy HH:mm:ss");
    TIME_FORMATS.add("dd-MMM-yyyy HH:mm");
    TIME_FORMATS.add("dd-MMM-yy HH:mm:ss");
    TIME_FORMATS.add("dd-MMM-yy HH:mm");
    TIME_FORMATS.add("y-M-d HH:mm:ss");
    TIME_FORMATS.add("y-M-d HH:mm");
    TIME_FORMATS.add("y-MMM-d HH:mm:ss");
    TIME_FORMATS.add("y-MMM-d HH:mm");
    TIME_FORMATS.add("d-MMM-y HH:mm:ss");
    TIME_FORMATS.add("d-MMM-y HH:mm");
    TIME_FORMATS.add("d.M.y HH:mm:ss");
    TIME_FORMATS.add("d.M.y HH:mm");
    TIME_FORMATS.add("MMM d, y HH:mm:ss");
    TIME_FORMATS.add("MMM d, y HH:mm");
    TIME_FORMATS.add("MMM d,y HH:mm:ss");
    TIME_FORMATS.add("MMM d,y HH:mm");
    TIME_FORMATS.add("d/M/y HH:mm:ss");
    TIME_FORMATS.add("d/M/y HH:mm");
    TIME_FORMATS.add("d-M-y HH:mm:ss");
    TIME_FORMATS.add("d-M-y HH:mm");
    TIME_FORMATS.add("d. MMMM y HH:mm:ss");
    TIME_FORMATS.add("d. MMMM y HH:mm");
  }

  private static final Map<Locale, SimpleDateFormat[]> DAY_PATTERNS = new HashMap<Locale, SimpleDateFormat[]>();
  private static final Map<Locale, SimpleDateFormat[]> TIME_PATTERNS = new HashMap<Locale, SimpleDateFormat[]>();

  /** hide sole constructor */
  private DateUtils() {
  }

  /**
   * Tries to interpret a given string as a day
   *
   * This routines assumes that all date parts of a day are present, time components are ignored. It then tries to match
   * the given string according to the default format, and then tries various international formats with a preference
   * for European date orders.
   *
   * The tested formats are presented below:
   *
   * <code>
   *   y-M-d  y-MMM-d  d-MMM-y  d.M.y  MMM d, y  MMM d,y  d/M/y  d-M-y
   * </code>
   *
   * These formats will be first tested against the default locale, and then against an English locale (Great Britain,
   * not USA, because of the formats).
   *
   * @param dateString a formatted date string
   * @return null if the date couldn't be parsed, otherwise the date
   */
  public static Date toDay(final String dateString) {
    if (dateString == null || dateString.trim().length() < 6) return null;

    // locales to be tried
    Locale[] locales = { Locale.getDefault(), Locale.UK, Locale.GERMANY };

    // attempt to convert the given date
    for (Locale locale : locales) {
      if (!DAY_PATTERNS.containsKey(locale)) {
        // create appropriate date patterns/formatters for the given locale
        List<SimpleDateFormat> patterns = new ArrayList<SimpleDateFormat>();
        for (String pattern : DAY_FORMATS) {
          patterns.add(new SimpleDateFormat(pattern, locale));
        }

        DAY_PATTERNS.put(locale, patterns.toArray(new SimpleDateFormat[0]));
      }

      for (SimpleDateFormat pattern : DAY_PATTERNS.get(locale)) {
        // try to convert the date string
        try {
          Date date = toCurrentCentury(pattern.parse(dateString));
          if (date != null) return date;
        } catch (ParseException pex) {
          // well, didn't work, so why complain
        }
      }
    }
    return null;
  }

  /**
   * Tries to interpret a given string as a timestamp
   *
   * This routines assumes that all date parts of a day are present, including time components. It then tries to match
   * the given string according to the default format, and then tries various international formats with a preference
   * for European date orders.
   *
   * The tested formats are presented below, missing the time format HH:mm:[ss]:
   *
   * <code>
   *   y-M-d  y-MMM-d  d-MMM-y  d.M.y  MMM d, y  MMM d,y  d/M/y  d-M-y
   * </code>
   *
   * These formats will be first tested against the default locale, and then against an English locale (Great Britain,
   * not USA, because of the formats).
   *
   * @param dateString a formatted date string
   * @return null if the date couldn't be parsed, otherwise the date with time details
   */
  public static Date toTime(final String dateString) {
    if (dateString == null || dateString.trim().length() < 6) return null;

    // locales to be tried
    Locale[] locales = { Locale.getDefault(), Locale.UK, Locale.GERMANY };

    // attempt to convert the given date
    for (Locale locale : locales) {
      if (!TIME_PATTERNS.containsKey(locale)) {
        // create appropriate date patterns/formatters for the given locale
        List<SimpleDateFormat> patterns = new ArrayList<SimpleDateFormat>();
        for (String pattern : TIME_FORMATS) {
          patterns.add(new SimpleDateFormat(pattern, locale));
        }

        TIME_PATTERNS.put(locale, patterns.toArray(new SimpleDateFormat[0]));
      }

      for (SimpleDateFormat pattern : TIME_PATTERNS.get(locale)) {
        // try to convert the date string
        try {
          Date date = toCurrentCentury(pattern.parse(dateString));
          if (date != null) return date;
        } catch (ParseException pex) {
          // well, didn't work, so why complain
        }
      }
    }
    return null;
  }

  /**
   * Moves the date into this century using the sliding window technique if the century is amiss.
   *
   * The date will remain unmodified if a century was given, it will only be moved if the century seems to be amiss.
   * Okay, all dates in the first century of the first millenium will be modified, but we regard that as no longer
   * applicable nowadays.
   *
   * @param date the date to move into this century if the century was not given
   * @return <code>null</code> if the date was null, otherwise the date with century
   */
  public static Date toCurrentCentury(final Date date) {
    Date currentDate = date;

    if (currentDate != null) {
      // apply sliding window technique around current year
      Calendar today = Calendar.getInstance();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      if (year < 100) {
        // no century specified, window it
        int currentYear = today.get(Calendar.YEAR) % 100;
        int century = today.get(Calendar.YEAR) - currentYear;
        year += century - ((year - currentYear > 25) ? 100 : 0);
        calendar.set(Calendar.YEAR, year);
        currentDate = calendar.getTime();
      }
    }

    return currentDate;
  }
}
