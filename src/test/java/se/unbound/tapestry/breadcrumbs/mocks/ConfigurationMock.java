package se.unbound.tapestry.breadcrumbs.mocks;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.Configuration;

/**
 * Mock implementation of the Configuration interface for unit testing.
 * 
 * @param <T> Type of the configuration.
 */
public class ConfigurationMock<T> implements Configuration<T> {
    private final List<T> configuration = new ArrayList<T>();

    @Override
    public void add(final T object) {
        this.configuration.add(object);
    }

    @Override
    public void addInstance(final Class<? extends T> clazz) {
    }

    /**
     * Verifier the configuration using the provided configuration checker.
     * 
     * @param configChecker The configuration checker to use to verify the configuration.
     */
    public void assertConfiguration(final ConfigurationChecker<T> configChecker) {
        for (final T config : this.configuration) {
            configChecker.check(config);
        }
    }

    /**
     * Used to verify configurations.
     * 
     * @param <T> Type of the configuration to check.
     */
    public interface ConfigurationChecker<T> {
        /**
         * Verifies the provided configuration.
         * 
         * @param config The configuration to check.
         */
        void check(T config);
    }
}
