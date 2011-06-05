package se.unbound.tapestry.breadcrumbs;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class ReferrerRequest implements Request {

    private final String path;
    private final Request wrappedRequest;

    public ReferrerRequest(final String path, final Request request) {
        this.path = path;
        this.wrappedRequest = request;
    }

    public static ReferrerRequest fromUri(final String uri, final Request request) {
        if (uri == null) {
            return null;
        }

        final String serverRegexp = "^.*://" + request.getServerName() + "(:" + request.getServerPort()
                + ")?" + request.getContextPath();
        if (!uri.matches(serverRegexp + ".*$")) {
            return null;
        }

        final String path = uri.replaceFirst(serverRegexp, "");
        return new ReferrerRequest(path, request);
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public Session getSession(final boolean create) {
        return this.wrappedRequest.getSession(create);
    }

    public String getContextPath() {
        return this.wrappedRequest.getContextPath();
    }

    public List<String> getParameterNames() {
        return this.wrappedRequest.getParameterNames();
    }

    public String getParameter(final String name) {
        return this.wrappedRequest.getParameter(name);
    }

    public String[] getParameters(final String name) {
        return this.wrappedRequest.getParameters(name);
    }

    public Locale getLocale() {
        return this.wrappedRequest.getLocale();
    }

    public List<String> getHeaderNames() {
        return this.wrappedRequest.getHeaderNames();
    }

    public long getDateHeader(final String name) {
        return this.wrappedRequest.getDateHeader(name);
    }

    public String getHeader(final String name) {
        return this.wrappedRequest.getHeader(name);
    }

    public boolean isXHR() {
        return this.wrappedRequest.isXHR();
    }

    public boolean isSecure() {
        return this.wrappedRequest.isSecure();
    }

    public String getServerName() {
        return this.wrappedRequest.getServerName();
    }

    public boolean isRequestedSessionIdValid() {
        return this.wrappedRequest.isRequestedSessionIdValid();
    }

    public Object getAttribute(final String name) {
        return this.wrappedRequest.getAttribute(name);
    }

    public void setAttribute(final String name, final Object value) {
        this.wrappedRequest.setAttribute(name, value);
    }

    public String getMethod() {
        return this.wrappedRequest.getMethod();
    }

    public int getLocalPort() {
        return this.wrappedRequest.getLocalPort();
    }

    public int getServerPort() {
        return this.wrappedRequest.getServerPort();
    }
}
