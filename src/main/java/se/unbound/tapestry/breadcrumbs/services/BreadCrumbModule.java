package se.unbound.tapestry.breadcrumbs.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;

import se.unbound.tapestry.breadcrumbs.BreadCrumbDispatcher;

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
     * Contributes a new {@link LibraryMapping} to the component class resolver.
     * 
     * @param configuration The {@link LibraryMapping} configuration to add the new mapping to.
     */
    public static void contributeComponentClassResolver(final Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("crumb", "se.unbound.tapestry.breadcrumbs"));
    }
}
