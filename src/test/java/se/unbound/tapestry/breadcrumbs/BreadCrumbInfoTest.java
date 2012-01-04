package se.unbound.tapestry.breadcrumbs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.mocks.LinkMock;

public class BreadCrumbInfoTest {
    private final BreadCrumbInfo crumb = new BreadCrumbInfo("key", new LinkMock("/index"), "index");

    @Test
    public void titleKey() {
        assertEquals("title key", "key", this.crumb.getTitleKey());
    }

    @Test
    public void link() {
        assertEquals("link", "/index", this.crumb.getLink().toString());
    }

    @Test
    public void pageName() {
        assertEquals("page name", "index", this.crumb.getPageName());
    }

    @Test
    public void hashCodeIsCorrect() {
        assertEquals("hashCode", 100346695, this.crumb.hashCode());
    }
}
