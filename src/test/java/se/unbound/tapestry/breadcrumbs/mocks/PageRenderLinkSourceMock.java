package se.unbound.tapestry.breadcrumbs.mocks;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.services.PageRenderLinkSource;

public class PageRenderLinkSourceMock implements PageRenderLinkSource {
    @Override
    public Link createPageRenderLink(final String pageName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link createPageRenderLinkWithContext(final String pageName, final Object... context) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link createPageRenderLinkWithContext(final String pageName, final EventContext eventContext) {
        return new LinkMock("/" + pageName);
    }

    @Override
    public Link createPageRenderLink(final Class pageClass) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link createPageRenderLinkWithContext(final Class pageClass, final Object... context) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link createPageRenderLinkWithContext(final Class pageClass, final EventContext eventContext) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}