package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentSource;

public class ComponentSourceMock implements ComponentSource {
    private final Map<String, Component> pages = new HashMap<String, Component>();

    public void addPage(final String pageName, final Component page) {
        this.pages.put(pageName, page);
    }

    @Override
    public Component getComponent(final String completeId) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Component getPage(final String pageName) {
        return this.pages.get(pageName);
    }

    @Override
    public Component getPage(final Class pageClass) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Component getActivePage() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}