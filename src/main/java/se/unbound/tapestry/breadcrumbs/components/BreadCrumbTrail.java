package se.unbound.tapestry.breadcrumbs.components;

import java.util.Iterator;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import se.unbound.tapestry.breadcrumbs.BreadCrumbInfo;
import se.unbound.tapestry.breadcrumbs.BreadCrumbList;

/**
 * BreadCrumbTrail component. Similar to Tapestrys Loop-component but always loops over the BreadCrumbList in
 * SessionState.
 */
public class BreadCrumbTrail {
    @Inject
    private ComponentResources componentResources;

    @SessionState
    @Property
    private BreadCrumbList breadCrumbList;

    /**
     * The current value, set before the component renders its body.
     */
    @Parameter(principal = true)
    private BreadCrumbInfo value;

    /**
     * The element to render. If not null, then the loop will render the indicated element around its body (on each pass
     * through the loop). The default is derived from the component template.
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String element;

    private Iterator<BreadCrumbInfo> iterator;

    String defaultElement() {
        return this.componentResources.getElementName();
    }

    @SetupRender
    boolean setupRender() {
        this.iterator = this.breadCrumbList.iterator();
        return this.iterator.hasNext();
    }

    @BeginRender
    void begin(final MarkupWriter writer) {
        this.value = this.iterator.next();
        if (this.element != null) {
            writer.element(this.element);
            this.componentResources.renderInformalParameters(writer);
        }
    }

    @AfterRender
    boolean after(final MarkupWriter writer) {
        if (this.element != null) {
            writer.end();
        }
        return !this.iterator.hasNext();
    }
}
