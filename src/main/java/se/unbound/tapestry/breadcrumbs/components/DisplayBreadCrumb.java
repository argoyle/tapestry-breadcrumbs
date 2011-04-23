package se.unbound.tapestry.breadcrumbs.components;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentSource;

import se.unbound.tapestry.breadcrumbs.BreadCrumbInfo;

/**
 * Component for displaying the {@link BreadCrumbInfo} as a simple anchor, using the link as the HREF and the message
 * found by the titleKey as it's content.
 */
public class DisplayBreadCrumb {
    @Inject
    private ComponentSource componentSource;

    @Parameter(required = true, allowNull = false)
    private BreadCrumbInfo breadCrumb;

    /**
     * Retrives the title for the crumb by looking up the titleKey in the messages for the page.
     * 
     * @return The message for the specified titleKey.
     */
    public String getTitle() {
        final Component page = this.componentSource.getPage(this.breadCrumb.getPageName());

        return page.getComponentResources().getMessages().get(this.breadCrumb.getTitleKey());
    }

    /**
     * Retrieves the link from the {@link BreadCrumbInfo}.
     * 
     * @return the link from the {@link BreadCrumbInfo}.
     */
    public Link getLink() {
        return this.breadCrumb.getLink();
    }
}
