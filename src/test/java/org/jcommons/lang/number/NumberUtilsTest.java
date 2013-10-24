package org.jcommons.lang.number;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.jcommons.lang.number.NumberUtils.compare;
import static org.jcommons.lang.number.NumberUtils.isNotNull;
import static org.jcommons.lang.number.NumberUtils.isNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

/** check that number utilities work as expected */
public class NumberUtilsTest
{

  /** check if we can compare two numbers in a null safe manner */
  @Test
  public void testCompare()
  {
    // check for equal cases
    assertThat(compare(null, null), equalTo(0));
    assertThat(compare(0, 0), equalTo(0));
    assertThat(compare(1, 1), equalTo(0));

    // we compare values, not objects
    assertThat(compare(new BigDecimal(1), BigDecimal.ONE), equalTo(0));
    
    assertThat(compare(0, null), greaterThan(0));
    assertThat(compare(null, 0), lessThan(0));
    assertThat(compare(1, 0), greaterThan(0));
    assertThat(compare(1, -1), greaterThan(0));
  }

  /** check if two numbers are equal in a null safe manner */
  @Test
  public void testEquals()
  {
    assertTrue(NumberUtils.equals(null, null));
    assertTrue(NumberUtils.equals(0, 0));
    assertTrue(NumberUtils.equals(1, 1));

    // we check for value equality, not for object equality
    assertTrue(NumberUtils.equals(new BigDecimal(1), BigDecimal.ONE));
    // attention, a long is not a big decimal!
    assertFalse(NumberUtils.equals(new Long(1), BigDecimal.ONE));
    
    assertFalse(NumberUtils.equals(0, null));
    assertFalse(NumberUtils.equals(null, 0));
    assertFalse(NumberUtils.equals(1, 0));
    assertFalse(NumberUtils.equals(1, -1));
  }

  /** check if any number that is null or whose value is zero is regarded as a null */
  @Test
  public void testIsNull()
  {
    // positive cases
    assertTrue(isNull(null));
    assertTrue(isNull(0L));
    assertTrue(isNull(0.0f));
    assertTrue(isNull(-0.0f));
    assertTrue(isNull(new Long(0)));
    assertTrue(isNull(new Integer(0)));
    assertTrue(isNull(new Float(0.0)));
    assertTrue(isNull(new Double(0.0)));
    assertTrue(isNull(BigDecimal.ZERO));
    
    // negative cases
    assertFalse(isNull(2));
    assertFalse(isNull(1L));
    assertFalse(isNull(1.0f));
    assertFalse(isNull(-1.0f));
    assertFalse(isNull(new Long(1)));
    assertFalse(isNull(new Integer(1)));
    assertFalse(isNull(new Float(1.0)));
    assertFalse(isNull(new Double(1.0)));
    assertFalse(isNull(BigDecimal.ONE));

    // attention - isNull considers only integer parts!
    assertTrue(isNull(new Float(0.5f)));
    assertTrue(isNull(new Double(0.9f)));
  }

  /** check if any number that exists or whose value is not zero is regarded as not null */
  @Test
  public void testIsNotNull()
  {
    // positive cases
    assertFalse(isNotNull(null));
    assertFalse(isNotNull(0L));
    assertFalse(isNotNull(0.0f));
    assertFalse(isNotNull(-0.0f));
    assertFalse(isNotNull(new Long(0)));
    assertFalse(isNotNull(new Integer(0)));
    assertFalse(isNotNull(new Float(0.0)));
    assertFalse(isNotNull(new Double(0.0)));
    assertFalse(isNotNull(BigDecimal.ZERO));
    
    // negative cases
    assertTrue(isNotNull(2));
    assertTrue(isNotNull(1L));
    assertTrue(isNotNull(1.0f));
    assertTrue(isNotNull(-1.0f));
    assertTrue(isNotNull(new Long(1)));
    assertTrue(isNotNull(new Integer(1)));
    assertTrue(isNotNull(new Float(1.0)));
    assertTrue(isNotNull(new Double(1.0)));
    assertTrue(isNotNull(BigDecimal.ONE));

    // attention - isNotNull considers only integer parts!
    assertFalse(isNotNull(new Float(0.5f)));
    assertFalse(isNotNull(new Double(0.9f)));
  }
}
