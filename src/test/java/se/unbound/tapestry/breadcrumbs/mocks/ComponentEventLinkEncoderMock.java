package se.unbound.tapestry.breadcrumbs.mocks;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;

public class ComponentEventLinkEncoderMock implements ComponentEventLinkEncoder {
    @Override
    public Link createPageRenderLink(final PageRenderRequestParameters parameters) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link createComponentEventLink(final ComponentEventRequestParameters parameters,
            final boolean forForm) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ComponentEventRequestParameters decodeComponentEventRequest(final Request request) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public PageRenderRequestParameters decodePageRenderRequest(final Request request) {
        final String[] parts = request.getPath().substring(1).split("/");
        if (request.getPath().length() == 1 || parts.length == 0) {
            return null;
        }
        if ("sv_SE".equals(parts[0])) {
            return new PageRenderRequestParameters(parts[1], new EventContextMock(), false);
        } else {
            return new PageRenderRequestParameters(parts[0], new EventContextMock(), false);
        }
    }
}
