package se.unbound.tapestry.breadcrumbs.mocks;

import static org.junit.Assert.assertEquals;

import org.apache.tapestry5.services.LibraryMapping;

import se.unbound.tapestry.breadcrumbs.mocks.ConfigurationMock.ConfigurationChecker;

/**
 * Implementation of the ConfigurationChecker interface for checking library mappings.
 */
public class LibraryMappingChecker implements ConfigurationChecker<LibraryMapping> {
    private final String prefix;
    private final String rootPackage;

    /**
     * Creates a new LibraryMappingChecker.
     * 
     * @param prefix The expected prefix of the library mapping.
     * @param rootPackage The root package of the library mapping.
     */
    public LibraryMappingChecker(final String prefix, final String rootPackage) {
        this.prefix = prefix;
        this.rootPackage = rootPackage;
    }

    @Override
    public void check(final LibraryMapping config) {
        assertEquals("Unexpected prefix", this.prefix, config.getPathPrefix());
        assertEquals("Unexpected root package", this.rootPackage, config.getRootPackage());
    }
}
