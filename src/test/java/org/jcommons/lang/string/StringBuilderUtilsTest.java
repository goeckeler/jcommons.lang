package org.jcommons.lang.string;

import static org.hamcrest.Matchers.equalTo;
import static org.jcommons.lang.string.StringBuilderUtils.appendIfNotEmpty;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

/** check if extensions to StringBuilder actually work as expected */
public class StringBuilderUtilsTest
{
  private StringBuilder text = new StringBuilder("Criteria:");

  /** append if number is not null */
  @Test
  public void shouldAppendNumber()
  {
    appendIfNotEmpty(text, " Invoice #", 123L, null);
    appendIfNotEmpty(text, " [", 4L, "]");

    assertThat(text.toString(), equalTo("Criteria: Invoice #123 [4]"));
  }

  /** do not append if number is null */
  @Test
  public void shouldNotAppendNumber()
  {
    appendIfNotEmpty(text, " Invoice #", (Number) null, null);
    appendIfNotEmpty(text, " [", 0L, "]");

    assertThat(text.toString(), equalTo("Criteria:"));
  }

  /** append if string is not null */
  @Test
  public void shouldAppendString()
  {
    appendIfNotEmpty(text, " Invoice #", "2012-03-13", null);
    appendIfNotEmpty(text, " [", "tba", "]");

    assertThat(text.toString(), equalTo("Criteria: Invoice #2012-03-13 [tba]"));
  }

  /** do not append if string is null */
  @Test
  public void shouldNotAppendString()
  {
    appendIfNotEmpty(text, " Invoice #", (String) null, null);
    appendIfNotEmpty(text, " [", "", "]");

    assertThat(text.toString(), equalTo("Criteria:"));
  }
  
  /** do not append if string builder is null - attention, with enabled assertions this will fail */
  @Test
  @Ignore
  public void shouldNotThrowException()
  {
    assertNull(appendIfNotEmpty(null, " Invoice #", "2012-03-13", null));
    assertNull(appendIfNotEmpty(null, " Invoice #", 123L, null));
  }
}
