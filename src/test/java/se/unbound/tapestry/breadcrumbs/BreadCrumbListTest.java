package se.unbound.tapestry.breadcrumbs;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.mocks.LinkMock;

public class BreadCrumbListTest {
    private final BreadCrumbList discardingList = new BreadCrumbList(true, 5);
    private final BreadCrumbList nonDiscardingLimitedList = new BreadCrumbList(false, 2);

    @Test
    public void equalCrumbsAreOnlyAddedOnce() {
        assertEquals("size", 0, this.discardingList.size());
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        assertEquals("size", 1, this.discardingList.size());
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        assertEquals("size", 1, this.discardingList.size());
    }

    @Test
    public void crumbsAddedBetweenEqualCrumbsArePurged() {
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page1"));
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page2"));
        assertEquals("size", 2, this.discardingList.size());
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page1"));
        assertEquals("size", 1, this.discardingList.size());
    }

    @Test
    public void iteratorReturnAnIteratorOfAddedCrumbs() {
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page1"));
        this.discardingList.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page2"));

        int crumbs = 2;
        for (final BreadCrumbInfo crumb : this.discardingList) {
            crumbs--;
        }

        assertEquals("iterations left", 0, crumbs);
    }

    @Test
    public void nonDuplicteDiscardingListKeepsDuplicatesOfPages() {
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page"));

        assertEquals("size", 2, this.nonDiscardingLimitedList.size());
    }

    @Test
    public void limitedListKeepsOnlyNewestCrumbs() {
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page1"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page2"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/view"), "page3"));

        assertEquals("size", 2, this.nonDiscardingLimitedList.size());
        final Iterator<BreadCrumbInfo> iterator = this.nonDiscardingLimitedList.iterator();
        assertEquals("crumb 1", "page2", iterator.next().getPageName());
        assertEquals("crumb 2", "page3", iterator.next().getPageName());
    }

    @Test
    public void limitedListIgnoresCrumbsNotShownInTrail() {
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/index"), "page1"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page2"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/edit"), "page2"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("ignored"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("ignored"));
        this.nonDiscardingLimitedList.add(new BreadCrumbInfo("key", new LinkMock("/view"), "page3"));

        assertEquals("size", 2, this.nonDiscardingLimitedList.getCrumbsToDisplay().size());
        final Iterator<BreadCrumbInfo> iterator = this.nonDiscardingLimitedList.iterator();
        assertEquals("crumb 1", "page2", iterator.next().getPageName());
        assertEquals("crumb 2", "page3", iterator.next().getPageName());
    }

    @Test(expected = IllegalStateException.class)
    public void getLastCrumbThrowsIllegalStateExceptionIfCrumbListIsEmpty() {
        this.nonDiscardingLimitedList.getLastCrumb();
    }
}
