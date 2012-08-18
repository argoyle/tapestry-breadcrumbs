package se.unbound.tapestry.breadcrumbs.services;

import static org.junit.Assert.assertNotNull;

import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.LibraryMapping;
import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.BreadCrumbDispatcher;
import se.unbound.tapestry.breadcrumbs.mocks.ConfigurationMock;
import se.unbound.tapestry.breadcrumbs.mocks.LibraryMappingChecker;
import se.unbound.tapestry.breadcrumbs.mocks.OrderedConfigurationMock;
import se.unbound.tapestry.breadcrumbs.mocks.ServiceBinderMock;

public class BreadCrumbModuleTest {
    @Test
    public void instantiation() {
        final BreadCrumbModule breadCrumbModule = new BreadCrumbModule();
        assertNotNull(breadCrumbModule);
    }

    @Test
    public void testBind() {
        final ServiceBinderMock serviceBinder = new ServiceBinderMock();
        BreadCrumbModule.bind(serviceBinder);

        serviceBinder.assertBound(BreadCrumbDispatcher.class, "BreadCrumbDispatcher");
    }

    @Test
    public void dispatcherIsAddedBeforePageRender() {
        final OrderedConfigurationMock<Dispatcher> orderedConfiguration = new OrderedConfigurationMock<Dispatcher>();
        final BreadCrumbDispatcher dispatcher = new BreadCrumbDispatcher(null, null, null, null);
        new BreadCrumbModule();
        BreadCrumbModule.contributeMasterDispatcher(orderedConfiguration, dispatcher);
        orderedConfiguration.assertConfiguration("BreadCrumbDispatcher", dispatcher,
                "after:ComponentEvent", "before:PageRender");
    }

    @Test
    public void testContributeComponentClassResolver() {
        final ConfigurationMock<LibraryMapping> configuration = new ConfigurationMock<LibraryMapping>();
        BreadCrumbModule.contributeComponentClassResolver(configuration);
        configuration.assertConfiguration(new LibraryMappingChecker("crumb",
                "se.unbound.tapestry.breadcrumbs"));
    }
}
