package se.unbound.tapestry.breadcrumbs.mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.ServiceBindingOptions;
import org.apache.tapestry5.ioc.ServiceBuilder;

public class ServiceBinderMock implements ServiceBinder {
    private final Map<Class<?>, String> boundClasses = new HashMap<Class<?>, String>();

    @Override
    public <T> ServiceBindingOptions bind(final Class<T> implementationClass) {
        this.boundClasses.put(implementationClass, null);
        return new ServiceBindingOptionsMock<T>(this, implementationClass);
    }

    @Override
    public <T> ServiceBindingOptions bind(final Class<T> serviceInterface, final ServiceBuilder<T> builder) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public <T> ServiceBindingOptions bind(final Class<T> serviceInterface,
            final Class<? extends T> serviceImplementation) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public void assertBound(final Class<?> expectedBoundClass, final String expectedId) {
        assertTrue("class not bound", this.boundClasses.containsKey(expectedBoundClass));
        assertEquals("expected id", expectedId, this.boundClasses.get(expectedBoundClass));
    }

    public void bindId(final Class<?> implementationClass, final String id) {
        this.boundClasses.put(implementationClass, id);
    }
}
