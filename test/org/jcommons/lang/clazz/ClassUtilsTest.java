package org.jcommons.lang.clazz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Check that <code>ClassUtils</code> extended methods work as expected.
 *
 * @author Thorsten Goeckeler
 */
public class ClassUtilsTest
{
  /** test the package paths routines */
  @Test
  public void testPackagePath() {
    assertEquals("java/lang", ClassUtils.getPackagePath(java.lang.String.class));
    assertEquals("./test/java/lang", ClassUtils.getPackagePath(java.lang.String.class, "./test"));
    assertEquals("./test/java/lang", ClassUtils.getPackagePath(java.lang.String.class, "./test/"));
    assertEquals("", ClassUtils.getPackagePath(null));
    assertEquals("./test/", ClassUtils.getPackagePath(null, "./test/"));
  }
}
