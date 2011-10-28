package se.unbound.tapestry.breadcrumbs;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

/**
 * Dispatcher which checks pages for the {@link BreadCrumb} annotation and adds a new crumb to the
 * {@link BreadCrumbList}.
 */
public class BreadCrumbDispatcher implements Dispatcher {
    private final ApplicationStateManager applicationStateManager;
    private final ComponentSource componentSource;
    private final PageRenderLinkSource pageRenderLinkSource;
    private final ComponentEventLinkEncoder componentEventLinkEncoder;

    /**
     * Constructs a new {@link BreadCrumbDispatcher}.
     * 
     * @param applicationStateManager The {@link ApplicationStateManager} to fetch the {@link BreadCrumbList} from.
     * @param componentSource The {@link ComponentSource} to use to retrieve page instances from.
     * @param pageRenderLinkSource The {@link PageRenderLinkSource} to use to render the {@link Link} for the crumb.
     * @param componentEventLinkEncoder The {@link ComponentEventLinkEncoder} to use to decode page render requests.
     */
    public BreadCrumbDispatcher(final ApplicationStateManager applicationStateManager,
            final ComponentSource componentSource, final PageRenderLinkSource pageRenderLinkSource,
            final ComponentEventLinkEncoder componentEventLinkEncoder) {
        this.applicationStateManager = applicationStateManager;
        this.componentSource = componentSource;
        this.pageRenderLinkSource = pageRenderLinkSource;
        this.componentEventLinkEncoder = componentEventLinkEncoder;
    }

    @Override
    public boolean dispatch(final Request tapestryRequest, final Response tapestryResponse)
            throws IOException {
        final PageRenderRequestParameters requestParameters = this.componentEventLinkEncoder
                .decodePageRenderRequest(tapestryRequest);

        if (requestParameters == null) {
            return false;
        }

        final Component previousPage;
        final String referrer = tapestryRequest.getHeader("Referer");
        final ReferrerRequest referrerRequest = ReferrerRequest.fromUri(referrer, tapestryRequest);
        if (referrerRequest != null && !referrer.contains(";")) {
            final PageRenderRequestParameters referrerParameters = this.componentEventLinkEncoder
                    .decodePageRenderRequest(referrerRequest);
            if (referrerParameters != null) {
                previousPage = this.componentSource.getPage(referrerParameters.getLogicalPageName());
            } else {
                previousPage = null;
            }
        } else {
            previousPage = null;
        }

        /* Is the requested page tagged with BreadCrumb- or BreadCrumbReset-annotation? */
        final Component page = this.componentSource.getPage(requestParameters.getLogicalPageName());

        final BreadCrumbList breadCrumbList = this.applicationStateManager.get(BreadCrumbList.class);
        final BreadCrumbReset reset = this.findAnnotation(page.getClass(), BreadCrumbReset.class);
        if (reset != null) {
            if (previousPage == null
                    || !this.previousPageIsIgnored(previousPage.getClass(), reset.ignorePages())) {
                breadCrumbList.reset();
            }
        }

        final BreadCrumb annotation = this.findAnnotation(page.getClass(), BreadCrumb.class);
        if (annotation != null) {
            final String titleKey = annotation.titleKey();

            final EventContext context = requestParameters.getActivationContext();

            final Link link = this.pageRenderLinkSource.createPageRenderLinkWithContext(
                    requestParameters.getLogicalPageName(), context);
            final BreadCrumbInfo breadCrumbInfo = new BreadCrumbInfo(titleKey, link,
                    requestParameters.getLogicalPageName());

            breadCrumbList.add(breadCrumbInfo);
        }

        return false;
    }

    private boolean previousPageIsIgnored(final Class<?> pageClass, final Class<?>[] ignorePages) {
        return Arrays.asList(ignorePages).contains(pageClass);
    }

    private <T extends Annotation> T findAnnotation(final Class<?> clazz, final Class<T> annotation) {
        T result = clazz.getAnnotation(annotation);

        if (result == null && clazz.getSuperclass() != null) {
            result = this.findAnnotation(clazz.getSuperclass(), annotation);
        }

        return result;
    }
}
