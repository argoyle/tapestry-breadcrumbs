package se.unbound.tapestry.breadcrumbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.tapestry5.services.Request;
import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.mocks.RequestMock;

public class ReferrerRequestTest {

    @Test
    public void fromUriReturnNullForNullUri() {
        assertNull("result", ReferrerRequest.fromUri(null, null));
    }

    @Test
    public void fromUriReturnNullIfPassedInUriDiffersInServerOrContext() {
        final Request request = this.createRequest("localhost", 80, "/app", "/edit");
        assertNull("result", ReferrerRequest.fromUri("http://localhost:8080/index", request));
    }

    @Test
    public void fromUriReturnValidObjectIfServerAndContextMatches() {
        final Request request = this.createRequest("localhost", 80, "/app", "/edit");
        final ReferrerRequest referrerRequest = ReferrerRequest
                .fromUri("http://localhost/app/index", request);
        assertNotNull("result", referrerRequest);
        assertEquals("path", "/index", referrerRequest.getPath());
    }

    @Test
    public void fromUriReturnValidObjectIfServerAndContextMatchesAndPortIsSpecified() {
        final Request request = this.createRequest("localhost", 80, "/app", "/edit");
        final ReferrerRequest referrerRequest = ReferrerRequest.fromUri("http://localhost:80/app/index",
                request);
        assertNotNull("result", referrerRequest);
        assertEquals("path", "/index", referrerRequest.getPath());
    }

    private Request createRequest(final String server, final int port, final String context,
            final String path) {
        return new RequestMock(server, port, context, path);
    }
}
