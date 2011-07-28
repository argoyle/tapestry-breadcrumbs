package se.unbound.tapestry.breadcrumbs.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.LibraryMapping;

import se.unbound.tapestry.breadcrumbs.BreadCrumbDispatcher;
import se.unbound.tapestry.breadcrumbs.BreadCrumbSymbols;

/**
 * Module class for the bread crumb module.
 */
public class BreadCrumbModule {
    /**
     * Called by Tapestry when the module is loaded to bind implementations to interfaces.
     * 
     * @param binder The {@link ServiceBinder} to use in the binding process.
     */
    public static void bind(final ServiceBinder binder) {
        binder.bind(BreadCrumbDispatcher.class).withId("BreadCrumbDispatcher");
    }

    /**
     * Contributes the {@link BreadCrumbDispatcher} to the master dispatcher configuration.
     * 
     * @param configuration The dispatcher configuration to add the {@link BreadCrumbDispatcher} to.
     * @param breadCrumbDispatcher The {@link BreadCrumbDispatcher} to add.
     */
    public static void contributeMasterDispatcher(final OrderedConfiguration<Dispatcher> configuration,
            @InjectService("BreadCrumbDispatcher")
            final Dispatcher breadCrumbDispatcher) {
        configuration.add("BreadCrumbDispatcher", breadCrumbDispatcher,
                "after:ComponentEvent,before:PageRender");
    }

    /**
     * Contributes factory defaults that may be overridden.
     * 
     * @param configuration The factory defaults configuration to add default values to.
     */
    public static void contributeFactoryDefaults(final MappedConfiguration<String, String> configuration) {
        configuration.add(BreadCrumbSymbols.DISCARD_DUPLICATES, "true");
        configuration.add(BreadCrumbSymbols.MAX_CRUMBS_TO_SAVE, String.valueOf(Integer.MAX_VALUE));
    }

    /**
     * Contributes a new {@link LibraryMapping} to the component class resolver.
     * 
     * @param configuration The {@link LibraryMapping} configuration to add the new mapping to.
     */
    public static void contributeComponentClassResolver(final Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("crumb", "se.unbound.tapestry.breadcrumbs"));
    }
}
