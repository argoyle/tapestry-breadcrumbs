package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class RequestMock implements Request {
    private final String path;

    public RequestMock(final String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Session getSession(final boolean create) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getContextPath() {
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
        throw new UnsupportedOperationException("Not yet implemented!");
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
    public String getServerName() {
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
    public int getServerPort() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}