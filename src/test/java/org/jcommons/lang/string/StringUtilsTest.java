package org.jcommons.lang.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/** Check that string utilities do their job. */
public class StringUtilsTest
{
  /** test message formats */
  @Test
  public void testMessage() {
    String format = "Hello {0}!";

    assertNotNull(StringUtils.message(null));
    assertEquals(format, StringUtils.message(format));
    assertEquals("Hello John!", StringUtils.message(format, "John"));
    assertEquals("Hello John!", StringUtils.message("{0} {1}!", "Hello", "John"));
    assertEquals("Hello John!", StringUtils.message("{0} {1}{2}", "Hello", "John", "!"));
  }
}
