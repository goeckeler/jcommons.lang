package org.jcommons.lang.clazz;

import org.jcommons.lang.string.StringUtils;

/**
 * String utilities that build upon {@link org.apache.commons.lang.ClassUtils}.
 *
 * @author Thorsten Goeckeler
 *
 * @see org.apache.commons.lang.ClassUtils
 */
public final class ClassUtils
  extends org.apache.commons.lang3.ClassUtils
{
  /** hide sole constructor */
  private ClassUtils() {
  }

  /**
   * Determine the package for the given class in path notation.
   *
   * For example <code>getPackagePath(java.lang.String.class)</code> yields <code>java/lang</code>.
   *
   * @param clazz the class for which to determine the package path
   * @return the path of that class, never <code>null</code>
   */
  public static String getPackagePath(final Class<?> clazz) {
    return getPackagePath(clazz, null);
  }

  /**
   * Determine the package for the given class in path notation after the given prefix.
   *
   * For example <code>getPackagePath(java.lang.String.class, "./test")</code> yields <code>./test/java/lang</code>.
   *
   * @param clazz the class for which to determine the package path
   * @param prefix the optional string to prepend to the found package path
   * @return the path of that class, never <code>null</code>
   */
  public static String getPackagePath(final Class<?> clazz, final String prefix) {
    String packagePath = StringUtils.defaultString(getPackageName(clazz)).replace('.', '/');

    if (StringUtils.isNotEmpty(prefix)) {
      packagePath = new StringBuilder(prefix).append(prefix.endsWith("/") ? "" : "/").append(packagePath).toString();
    }

    return packagePath;
  }
}
