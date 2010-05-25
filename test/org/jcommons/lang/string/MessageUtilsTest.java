package org.jcommons.lang.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/** Simply test if messages with named parameters can be easily used. */
public class MessageUtilsTest
{
  /** test with single parameter */
  @Test
  public void testMessage() {
    MessageUtils text = MessageUtils.message("Hello ${name}.").with("name", "John");
    assertEquals("Hello John.", text.toString());
    assertEquals("Hello Jill.", text.with("name", "Jill").toString());
  }

  /** test with missing parameter */
  @Test
  public void testMissingParameter() {
    MessageUtils text = MessageUtils.message("Hello ${name}.").with("name", "John");
    assertEquals("Hello John.", text.toString());
    assertEquals("Hello ${name}.", text.clear().toString());
  }

  /** test with missing text */
  @Test
  public void testMissingText() {
    assertNotNull(MessageUtils.message().toString());
    assertEquals("", MessageUtils.message().toString());
    assertNotNull(MessageUtils.message().with("name", "John").toString());
    assertEquals("", MessageUtils.message().with("name", "John").toString());
  }

  /** test with invalid placeholder */
  @Test
  public void testInvalidPlaceholder() {
    MessageUtils text = MessageUtils.message("Hello ${forename}.").with("name", "John");
    assertEquals("Hello ${forename}.", text.toString());
    assertEquals("Hello John.", text.text("Hello ${name}.").toString());
  }
}
