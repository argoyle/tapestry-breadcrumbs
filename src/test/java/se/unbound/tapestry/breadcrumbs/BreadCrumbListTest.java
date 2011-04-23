package se.unbound.tapestry.breadcrumbs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.mocks.LinkMock;

public class BreadCrumbListTest {
    private final BreadCrumbList list = new BreadCrumbList();

    @Test
    public void equalCrumbsAreOnlyAddedOnce() {
        assertEquals("size", 0, this.list.size());
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        assertEquals("size", 1, this.list.size());
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        assertEquals("size", 1, this.list.size());
    }

    @Test
    public void crumbsAddedBetweenEqualCrumbsArePurged() {
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page"));
        assertEquals("size", 2, this.list.size());
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        assertEquals("size", 1, this.list.size());
    }

    @Test
    public void iteratorReturnAnIteratorOfAddedCrumbs() {
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        this.list.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page"));

        int crumbs = 2;
        for (final BreadCrumbInfo crumb : this.list) {
            crumbs--;
        }

        assertEquals("iterations left", 0, crumbs);
    }
}
