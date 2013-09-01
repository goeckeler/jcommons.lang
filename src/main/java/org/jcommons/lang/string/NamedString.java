package org.jcommons.lang.string;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

/**
 * String utilities to support named messages.
 *
 * A named text is a text where the parameters are not index based, e.g. {0}, {1}, and so on, but are named, e.g.
 * ${name}, ${city}.
 *
 * Usage:
 * <pre>
 *   NamedString.message("Hello ${name}.").with("name", "John").toString();
 * </pre>
 *
 * @author Thorsten Goeckeler
 *
 * @see org.apache.commons.lang.text.StrSubstitutor
 */
public class NamedString
{
  private String text;
  private final Map<String, Object> values = new HashMap<String, Object>();

  /** Creates an empty message. */
  public NamedString() {
    this(null);
  }

  /**
   * Creates the given message.
   *
   * @param text the text with placeholders to be used, can be null
   */
  public NamedString(final String text) {
    this.text = text;
  }

  /**
   * Creates an empty message.
   *
   * @return this to allow chaining
   */
  public static NamedString message() {
    return new NamedString();
  }

  /**
   * Creates a message with the given text.
   *
   * @param text the text with placeholders to be used, can be null
   * @return this to allow chaining
   */
  public static NamedString message(final String text) {
    return new NamedString(text);
  }

  /**
   * Define the text to be used for this message.
   *
   * @param text the text with placeholders to be used, can be null
   * @return this to allow chaining
   */
  public NamedString text(final String text) {
    this.text = text;
    return this;
  }

  /**
   * Define a value for a given placeholder name.
   *
   * @param name the name of a placeholder, e.g. "name" for "${name}"
   * @param value the value of this placeholder, e.g. "John" or 12345
   * @return this to allow chaining
   */
  public NamedString with(final String name, final Object value) {
    values.put(name, value);
    return this;
  }

  /**
   * Clear all placeholders.
   *
   * @return this to allow chaining
   */
  public NamedString clear() {
    values.clear();
    return this;
  }

  /** @return the current text with all placeholders resolved */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    if (StringUtils.isNotEmpty(text)) {
      string.append(StrSubstitutor.replace(text, values));
    }
    return string.toString();
  }
}
