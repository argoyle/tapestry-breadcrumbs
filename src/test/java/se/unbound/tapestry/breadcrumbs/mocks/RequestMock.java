package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class RequestMock implements Request {
    private final String path;
    private final String server;
    private final int port;
    private final String context;

    private final Map<String, String> headers = new HashMap<String, String>();

    public RequestMock(final String path) {
        this(null, 80, null, path);
    }

    public RequestMock(final String server, final int port, final String context, final String path) {
        this.server = server;
        this.port = port;
        this.context = context;
        this.path = path;
    }

    public void addHeader(final String name, final String value) {
        this.headers.put(name, value);
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getContextPath() {
        return this.context;
    }

    @Override
    public String getServerName() {
        return this.server;
    }

    @Override
    public int getServerPort() {
        return this.port;
    }

    @Override
    public Session getSession(final boolean create) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<String> getParameterNames() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getParameter(final String name) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String[] getParameters(final String name) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Locale getLocale() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public List<String> getHeaderNames() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public long getDateHeader(final String name) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getHeader(final String name) {
        return this.headers.get(name);
    }

    @Override
    public boolean isXHR() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public boolean isSecure() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Object getAttribute(final String name) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setAttribute(final String name, final Object value) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getMethod() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public int getLocalPort() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getRemoteHost() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}