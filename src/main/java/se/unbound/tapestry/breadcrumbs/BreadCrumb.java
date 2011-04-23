package se.unbound.tapestry.breadcrumbs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to place on page-classes that should be part of the crumb-trail.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BreadCrumb {
    /**
     * The titleKey is the key into the message catalog that the title of the crumb should use.
     * 
     * @return the titleKey.
     */
    String titleKey();
}
