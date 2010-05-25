package org.jcommons.lang.string;

import java.text.MessageFormat;

/**
 * String utilities that build upon {@link org.apache.commons.lang.StringUtils}.
 *
 * @author Thorsten Goeckeler
 *
 * @see org.apache.commons.lang.StringUtils
 */
public final class StringUtils
  extends org.apache.commons.lang.StringUtils
{
  /** default empty (not-null) string */
  public static final String EMPTY = "";

  /** hide sole constructor */
  private StringUtils() {
  }

  /**
   * Creates an ad hoc message using a MessageFormat from the given text without a parameter.
   *
   * This method only exist so you can use always the same method call, as it doesn't really make sense to use a
   * MessageFormat if you have no parameters.
   *
   * @param message the message text
   * @return the same message, never null
   */
  public static String message(final String message) {
    return defaultString(message);
  }

  /**
   * Creates an ad hoc message using a MessageFormat from the given text and parameters.
   *
   * Example: The message text contains the text "Hello {0}!". Then you can call this routine as
   * <code>message("Hello {0}!", String[] { "World" });</code> It will then return "Hello World!".
   *
   * This is only a convenience function for ad hoc use of the messages. If you need the same message formatted numerous
   * times, you are better of creating your own MessageFormat and to reuse it all the time.
   *
   * If the message contains single quotes, make sure to escape them: "''" will lead to "'" within the result. For
   * details refer to {@link MessageFormat}.
   *
   * @param message the message text
   * @param parameters the correct number of arguments for this message
   * @return the message with all the parameters set
   */
  public static String message(final String message, final Object... parameters) {
    String result = null;
    if (message != null) {
      MessageFormat format = new MessageFormat(message);
      result = format.format(parameters);
    }
    return result;
  }

  /**
   * Creates an ad hoc message using a MessageFormat from the given text and single parameter.
   *
   * Example: The message text contains the text "Hello {0}!". Then you can call this routine as
   * <code>message("Hello {0}!", "World");</code> It will then return "Hello World!".
   *
   * This is only a convenience function for ad hoc use of the messages. If you need the same message formatted numerous
   * times, you are better of creating your own MessageFormat and to reuse it all the time.
   *
   * @param message the message text
   * @param parameter the single parameter for this message
   * @return the message with all the parameters set
   */
  public static String message(final String message, final Object parameter) {
    Object[] args = { parameter };
    return message(message, args);
  }

  /**
   * Creates an ad hoc message using a MessageFormat from the given text and two parameters.
   *
   * Example: The message text contains the text "{0} {1}!". Then you can call this routine as
   * <code>message("{0} {1}!", "Hello", "World");</code> It will then return "Hello World!".
   *
   * This is only a convenience function for ad hoc use of the messages. If you need the same message formatted numerous
   * times, you are better of creating your own MessageFormat and to reuse it all the time.
   *
   * @param message the message text
   * @param parameter1 The first argument for this message
   * @param parameter2 The second argument for this message
   * @return the message with all the parameters set
   */
  public static String message(final String message, final Object parameter1, final Object parameter2) {
    Object[] args = { parameter1, parameter2 };
    return message(message, args);
  }
}
