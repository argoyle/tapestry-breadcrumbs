package se.unbound.tapestry.breadcrumbs;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

/**
 * Dispatcher which checks pages for the {@link BreadCrumb} annotation and adds a new crumb to the
 * {@link BreadCrumbList}.
 */
public class BreadCrumbDispatcher implements Dispatcher {
    private final ApplicationStateManager applicationStateManager;
    private final ComponentClassResolver componentClassResolver;
    private final ComponentSource componentSource;
    private final String supportedLocales;
    private final PageRenderLinkSource pageRenderLinkSource;

    /**
     * Constructs a new {@link BreadCrumbDispatcher}.
     * 
     * @param applicationStateManager The {@link ApplicationStateManager} to fetch the {@link BreadCrumbList} from.
     * @param componentClassResolver The {@link ComponentClassResolver} to use to validate page-names.
     * @param componentSource The {@link ComponentSource} to use to retrieve page instances from.
     * @param pageRenderLinkSource The {@link PageRenderLinkSource} to use to render the {@link Link} for the crumb.
     * @param supportedLocales The supported locales of the application. Used to strip away the localization-part of the
     *            path.
     */
    public BreadCrumbDispatcher(final ApplicationStateManager applicationStateManager,
            final ComponentClassResolver componentClassResolver,
            final ComponentSource componentSource, final PageRenderLinkSource pageRenderLinkSource,
            @Symbol(SymbolConstants.SUPPORTED_LOCALES) final String supportedLocales) {
        this.applicationStateManager = applicationStateManager;
        this.componentClassResolver = componentClassResolver;
        this.componentSource = componentSource;
        this.pageRenderLinkSource = pageRenderLinkSource;
        this.supportedLocales = supportedLocales;
    }

    @Override
    public boolean dispatch(final Request tapestryRequest, final Response tapestryResponse)
            throws IOException {
        /*
         * We need to get the Tapestry page requested by the user. So we parse the path extracted from the request
         */
        final String path = this.getUnlocalizedPagePath(tapestryRequest.getPath());
        int nextslashx = path.length();
        String pageName;

        while (true) {
            pageName = path.substring(1, nextslashx);
            if (!pageName.endsWith("/") && this.componentClassResolver.isPageName(pageName)) {
                break;
            }
            nextslashx = path.lastIndexOf('/', nextslashx - 1);
            if (nextslashx <= 1) {
                return false;
            }
        }

        /* Is the requested page tagged with BreadCrumb-annotation? */
        final Component page = this.componentSource.getPage(pageName);
        final BreadCrumb annotation = this.findAnnotation(page.getClass());

        if (annotation != null) {
            final BreadCrumbList breadCrumbList = this.applicationStateManager.get(BreadCrumbList.class);

            final String titleKey = annotation.titleKey();

            final Object[] context = this.getContext(path, pageName);

            final Link link = this.pageRenderLinkSource.createPageRenderLinkWithContext(pageName, context);
            final BreadCrumbInfo breadCrumbInfo = new BreadCrumbInfo(titleKey, link, pageName);

            breadCrumbList.add(breadCrumbInfo);
        }

        return false;
    }

    private BreadCrumb findAnnotation(final Class<?> clazz) {
        BreadCrumb result = clazz.getAnnotation(BreadCrumb.class);

        if (result == null && clazz.getSuperclass() != null) {
            result = this.findAnnotation(clazz.getSuperclass());
        }

        return result;
    }

    private String getUnlocalizedPagePath(final String requestPath) {
        for (final String locale : this.supportedLocales.split(",")) {
            if (requestPath.startsWith("/" + locale)) {
                return requestPath.substring(locale.length() + 1);
            }
        }
        return requestPath;
    }

    private Object[] getContext(final String path, final String pageName) {
        Object[] context = new Object[0];

        if (path.length() > pageName.length() + 2) {
            final String contextString = path.substring(pageName.length() + 2);
            context = contextString.split("/");
        }
        return context;
    }
}
