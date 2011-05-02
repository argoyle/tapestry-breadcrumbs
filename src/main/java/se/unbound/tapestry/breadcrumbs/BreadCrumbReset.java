package se.unbound.tapestry.breadcrumbs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to place on page-classes that should reset the crumb-trail. Will be processed before the regular
 * BreadCrumb-annotation.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BreadCrumbReset {
}
