package se.unbound.tapestry.breadcrumbs.mocks;

import org.apache.tapestry5.EventContext;

public class EventContextMock implements EventContext {
    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public <T> T get(final Class<T> desiredType, final int index) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String[] toStrings() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
