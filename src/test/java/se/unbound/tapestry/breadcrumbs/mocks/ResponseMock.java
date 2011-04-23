package se.unbound.tapestry.breadcrumbs.mocks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.services.Response;

public class ResponseMock implements Response {
    @Override
    public PrintWriter getPrintWriter(final String contentType) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public OutputStream getOutputStream(final String contentType) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void sendRedirect(final String URL) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void sendRedirect(final Link link) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setStatus(final int sc) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void sendError(final int sc, final String message) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setContentLength(final int length) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setDateHeader(final String name, final long date) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setHeader(final String name, final String value) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setIntHeader(final String name, final int value) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String encodeURL(final String URL) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String encodeRedirectURL(final String URL) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public boolean isCommitted() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void disableCompression() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}