package org.jcommons.lang.string;

import org.apache.commons.lang3.StringUtils;
import org.jcommons.lang.number.NumberUtils;

/**
 * String utilities that build upon {@link org.apache.commons.lang.text.StrBuilder}.
 *
 * @author Thorsten Goeckeler
 *
 * @see org.apache.commons.lang.text.StrBuilder
 */
public final class StringBuilderUtils
  extends org.apache.commons.lang3.text.StrBuilder
{
  private static final long serialVersionUID = -2371182240527419968L;

  /** hide sole constructor */
  private StringBuilderUtils() {
  }

  /**
   * Appends the given value and the optional post- and prefixes if the value is regarded not empty.
   *
   * The method names are rather long as to use static imports easily. Example:
   *
   * <pre>
   * StringBuilder text = new StringBuilder(&quot;Filter was :&quot;);
   * StringBuilderUtils.appendIfNotEmpty(text, &quot; Invoice #&quot;, invoiceNumber, null);
   * StringBuilderUtils.appendIfNotEmpty(text, &quot; [&quot;, invoiceNumberChecksum, &quot;]&quot;);
   * </pre>
   *
   * @param text the string builder to append the value to if the value is non-empty
   * @param prefix the optional label to append before the value, can be null
   * @param value the non-floating number to print only if it has a content
   * @param postfix the optional label to append after the value, can be null
   * @return the original <code>StringBuilder</code>, only null if no builder was given
   */
  public static StringBuilder appendIfNotEmpty(final StringBuilder text, final String prefix, final Number value,
                                               final String postfix)
  {
    assert text != null;
    if (text == null) return null;

    if (NumberUtils.isNotNull(value)) {
      text.append(StringUtils.defaultString(prefix));
      text.append(value);
      text.append(StringUtils.defaultString(postfix));
    }

    return text;
  }

  /**
   * Appends the given value and the optional post- and prefixes if the value is regarded not empty.
   *
   * The method names are rather long as to use static imports easily. Example:
   *
   * <pre>
   * StringBuilder text = new StringBuilder(&quot;Filter was :&quot;);
   * StringBuilderUtils.appendIfNotEmpty(text, &quot; Customer Name #&quot;, customerName, null);
   * StringBuilderUtils.appendIfNotEmpty(text, &quot; [&quot;, reference, &quot;]&quot;);
   * </pre>
   *
   * @param text the string builder to append the value to if the value is non-empty
   * @param prefix the optional label to append before the value, can be null
   * @param value the string to print only if it has a content
   * @param postfix the optional label to append after the value, can be null
   * @return the original <code>StringBuilder</code>, only null if no builder was given
   */
  public static StringBuilder appendIfNotEmpty(final StringBuilder text, final String prefix, final String value,
                                               final String postfix)
  {
    assert text != null;
    if (text == null) return null;

    if (StringUtils.isNotBlank(value)) {
      text.append(StringUtils.defaultString(prefix));
      text.append(value);
      text.append(StringUtils.defaultString(postfix));
    }

    return text;
  }
}
