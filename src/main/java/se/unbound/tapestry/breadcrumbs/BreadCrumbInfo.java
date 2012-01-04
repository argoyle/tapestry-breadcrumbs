package se.unbound.tapestry.breadcrumbs;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.tapestry5.Link;

/**
 * Information on one specific crumb in the trail.
 */
public class BreadCrumbInfo implements Serializable {
    private static final long serialVersionUID = 4242583224611192888L;
    private final String titleKey;
    private final transient Link link;
    private final String pageName;

    /**
     * Constructs a new {@link BreadCrumbInfo}.
     * 
     * @param titleKey The titleKey is the key into the message catalog that the title of the crumb should use.
     * @param link The link that the crumb points to.
     * @param pageName The name of the page the crumb points to.
     */
    public BreadCrumbInfo(final String titleKey, final Link link, final String pageName) {
        this.titleKey = titleKey;
        this.link = link;
        this.pageName = pageName;
    }

    /**
     * Constructs a new {@link BreadCrumbInfo} for pages not to show in crumb trail.
     * 
     * @param pageName The name of the page the crumb points to.
     */
    public BreadCrumbInfo(final String pageName) {
        this.titleKey = null;
        this.link = null;
        this.pageName = pageName;
    }

    public String getTitleKey() {
        return this.titleKey;
    }

    public Link getLink() {
        return this.link;
    }

    public String getPageName() {
        return this.pageName;
    }

    /**
     * Should this bread crumb be shown in the crumb trail?
     * 
     * @return true if the crumb has a link, otherwise false.
     */
    public boolean showInCrumbTrail() {
        return this.link != null;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[] { "link", "titleKey" });
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[] { "link", "titleKey" });
    }
}
