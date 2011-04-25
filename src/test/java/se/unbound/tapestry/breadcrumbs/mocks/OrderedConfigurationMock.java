package se.unbound.tapestry.breadcrumbs.mocks;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.ioc.OrderedConfiguration;

public class OrderedConfigurationMock<T> implements OrderedConfiguration<T> {
    private final Map<String, T> configurations = new HashMap<String, T>();
    private final Map<String, Class<?>> instanceConfigurations = new HashMap<String, Class<?>>();
    private final Map<String, String[]> orderConstraints = new HashMap<String, String[]>();

    @Override
    public void add(final String id, final T object, final String... constraints) {
        this.configurations.put(id, object);
        this.orderConstraints.put(id, constraints);
    }

    @Override
    public void addInstance(final String id, final Class<? extends T> clazz, final String... constraints) {
        this.instanceConfigurations.put(id, clazz);
        this.orderConstraints.put(id, constraints);
    }

    @Override
    public void override(final String id, final T object, final String... constraints) {
    }

    @Override
    public void overrideInstance(final String id, final Class<? extends T> clazz, final String... constraints) {
    }

    public void assertConfiguration(final String expectedId, final T expectedObject,
            final String... expectedConstraints) {
        assertTrue("No configuration found for id: " + expectedId,
                this.configurations.containsKey(expectedId));
        assertEquals("Unexpected object configured for id: " + expectedId, expectedObject,
                this.configurations.get(expectedId));
        assertArrayEquals("Unexpected constraints for id: " + expectedId, expectedConstraints,
                this.orderConstraints.get(expectedId));
    }
}
