package org.jcommons.lang.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/** Simply test if messages with named parameters can be easily used. */
public class NamedStringTest
{
  /** test with single parameter */
  @Test
  public void testMessage() {
    NamedString text = NamedString.message("Hello ${name}.").with("name", "John");
    assertEquals("Hello John.", text.toString());
    assertEquals("Hello Jill.", text.with("name", "Jill").toString());
  }

  /** test with missing parameter */
  @Test
  public void testMissingParameter() {
    NamedString text = NamedString.message("Hello ${name}.").with("name", "John");
    assertEquals("Hello John.", text.toString());
    assertEquals("Hello ${name}.", text.clear().toString());
  }

  /** test with missing text */
  @Test
  public void testMissingText() {
    assertNotNull(NamedString.message().toString());
    assertEquals("", NamedString.message().toString());
    assertNotNull(NamedString.message().with("name", "John").toString());
    assertEquals("", NamedString.message().with("name", "John").toString());
  }

  /** test with invalid placeholder */
  @Test
  public void testInvalidPlaceholder() {
    NamedString text = NamedString.message("Hello ${forename}.").with("name", "John");
    assertEquals("Hello ${forename}.", text.toString());
    assertEquals("Hello John.", text.text("Hello ${name}.").toString());
  }
}
