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

        final String jsessionidRegexp = ";jsessionid=.*$";
        final String path = uri.replaceFirst(serverRegexp, "").replaceFirst(jsessionidRegexp, "");
        return new ReferrerRequest(path, request);
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Session getSession(final boolean create) {
        return this.wrappedRequest.getSession(create);
    }

    @Override
    public String getContextPath() {
        return this.wrappedRequest.getContextPath();
    }

    @Override
    public List<String> getParameterNames() {
        return this.wrappedRequest.getParameterNames();
    }

    @Override
    public String getParameter(final String name) {
        return this.wrappedRequest.getParameter(name);
    }

    @Override
    public String[] getParameters(final String name) {
        return this.wrappedRequest.getParameters(name);
    }

    @Override
    public Locale getLocale() {
        return this.wrappedRequest.getLocale();
    }

    @Override
    public List<String> getHeaderNames() {
        return this.wrappedRequest.getHeaderNames();
    }

    @Override
    public long getDateHeader(final String name) {
        return this.wrappedRequest.getDateHeader(name);
    }

    @Override
    public String getHeader(final String name) {
        return this.wrappedRequest.getHeader(name);
    }

    @Override
    public boolean isXHR() {
        return this.wrappedRequest.isXHR();
    }

    @Override
    public boolean isSecure() {
        return this.wrappedRequest.isSecure();
    }

    @Override
    public String getServerName() {
        return this.wrappedRequest.getServerName();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return this.wrappedRequest.isRequestedSessionIdValid();
    }

    @Override
    public Object getAttribute(final String name) {
        return this.wrappedRequest.getAttribute(name);
    }

    @Override
    public void setAttribute(final String name, final Object value) {
        this.wrappedRequest.setAttribute(name, value);
    }

    @Override
    public String getMethod() {
        return this.wrappedRequest.getMethod();
    }

    @Override
    public int getLocalPort() {
        return this.wrappedRequest.getLocalPort();
    }

    @Override
    public int getServerPort() {
        return this.wrappedRequest.getServerPort();
    }

    @Override
    public String getRemoteHost() {
        return this.wrappedRequest.getRemoteHost();
    }
}
