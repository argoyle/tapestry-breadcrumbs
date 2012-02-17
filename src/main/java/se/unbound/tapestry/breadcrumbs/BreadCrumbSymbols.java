package se.unbound.tapestry.breadcrumbs;

/**
 * Symbols used by the crumb library.
 */
public class BreadCrumbSymbols {
    /**
     * Indicates if crumbs since the last visit to the current page should be discarded or not. Defaults to true.
     */
    public static final String DISCARD_DUPLICATES = "tapestry-breadcrumbs.discard-duplicates";
    /**
     * Indicates the maximum number of crumbs to store in the BreadCrumbList. Defaults to Integer.MAX_VALUE.
     */
    public static final String MAX_CRUMBS_TO_SAVE = "tapestry-breadcrumbs.max-crumbs-to-save";
}
