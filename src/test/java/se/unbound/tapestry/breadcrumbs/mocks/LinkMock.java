package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.List;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.internal.services.LinkSecurity;

public class LinkMock implements Link {
    private final String linkString;

    public LinkMock(final String linkString) {
        this.linkString = linkString;
    }

    @Override
    public List<String> getParameterNames() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getParameterValue(final String name) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void addParameter(final String parameterName, final String value) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link addParameterValue(final String parameterName, final Object value) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void removeParameter(final String parameterName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getBasePath() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Link copyWithBasePath(final String basePath) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String toURI() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String toRedirectURI() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String getAnchor() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setAnchor(final String anchor) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String toAbsoluteURI() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String toAbsoluteURI(final boolean secure) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String toString() {
        return this.linkString;
    }

    @Override
    public LinkSecurity getSecurity() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void setSecurity(LinkSecurity arg0) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}