package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.services.ApplicationStateManager;

public class ApplicationStateManagerMock implements ApplicationStateManager {
    private final Map<Class<?>, Object> sso = new HashMap<Class<?>, Object>();

    @Override
    public <T> T get(final Class<T> ssoClass) {
        return (T) this.sso.get(ssoClass);
    }

    @Override
    public <T> T getIfExists(final Class<T> ssoClass) {
        return (T) this.sso.get(ssoClass);
    }

    @Override
    public <T> boolean exists(final Class<T> ssoClass) {
        return this.sso.containsKey(ssoClass);
    }

    @Override
    public <T> void set(final Class<T> ssoClass, final T SSO) {
        this.sso.put(ssoClass, SSO);
    }
}