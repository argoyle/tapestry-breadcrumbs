package se.unbound.tapestry.breadcrumbs.mocks;

import java.lang.annotation.Annotation;

import org.apache.tapestry5.ioc.ServiceBindingOptions;

public class ServiceBindingOptionsMock<T> implements ServiceBindingOptions {
    private final ServiceBinderMock serviceBinder;
    private final Class<?> implementationClass;

    public ServiceBindingOptionsMock(final ServiceBinderMock serviceBinder, final Class<T> implementationClass) {
        this.serviceBinder = serviceBinder;
        this.implementationClass = implementationClass;
    }

    @Override
    public ServiceBindingOptions withId(final String id) {
        this.serviceBinder.bindId(this.implementationClass, id);
        return this;
    }

    @Override
    public ServiceBindingOptions scope(final String scope) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ServiceBindingOptions eagerLoad() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ServiceBindingOptions preventDecoration() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ServiceBindingOptions preventReloading() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public <T extends Annotation> ServiceBindingOptions withMarker(final Class<T>... marker) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public ServiceBindingOptions withSimpleId() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

}
