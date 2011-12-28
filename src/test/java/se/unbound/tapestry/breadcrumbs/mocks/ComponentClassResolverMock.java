package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.transform.ControlledPackageType;

public class ComponentClassResolverMock implements ComponentClassResolver {
    private final Map<String, Component> pages = new HashMap<String, Component>();

    public void addPage(final String pageName, final Component page) {
        this.pages.put(pageName, page);
    }

    @Override
    public String resolvePageNameToClassName(final String pageName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public boolean isPageName(final String pageName) {
        return this.pages.containsKey(pageName);
    }

    @Override
    public List<String> getPageNames() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String resolvePageClassNameToPageName(final String pageClassName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String canonicalizePageName(final String pageName) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String resolveComponentTypeToClassName(final String componentType) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String resolveMixinTypeToClassName(final String mixinType) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Map<String, String> getFolderToPackageMapping() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Map<String, ControlledPackageType> getControlledPackageMapping() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public boolean isPage(String arg0) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}