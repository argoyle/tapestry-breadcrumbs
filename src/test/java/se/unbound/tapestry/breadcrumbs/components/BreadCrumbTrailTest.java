package se.unbound.tapestry.breadcrumbs.components;

import static org.junit.Assert.assertTrue;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.test.PageTester;
import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.BreadCrumbInfo;
import se.unbound.tapestry.breadcrumbs.BreadCrumbList;

public class BreadCrumbTrailTest extends PageTester {
    public BreadCrumbTrailTest() {
        super("se.unbound.tapestry.breadcrumbs", "BreadCrumb");
    }

    @Test
    public void renderCrumbList() {
        final PageRenderLinkSource linkSource = this.getService(PageRenderLinkSource.class);
        final BreadCrumbList breadCrumbList = new BreadCrumbList(true, 5);
        breadCrumbList.add(new BreadCrumbInfo("msg1", linkSource.createPageRenderLink("page1"), "page1"));
        breadCrumbList.add(new BreadCrumbInfo("msg2", linkSource.createPageRenderLink("page2"), "page2"));
        this.getService(ApplicationStateManager.class).set(BreadCrumbList.class, breadCrumbList);

        final Document page = this.renderPage("page2");
        final String pageContent = page.toString();
        assertTrue("page contains Message1", pageContent.contains("Message1"));
        assertTrue("page contains Message2", pageContent.contains("Message2"));
    }
    
    @Test
    public void renderCrumbListTitle() {
        final PageRenderLinkSource linkSource = this.getService(PageRenderLinkSource.class);
        final BreadCrumbList breadCrumbList = new BreadCrumbList(true, 5);
        BreadCrumbInfo info = new BreadCrumbInfo("msg1", linkSource.createPageRenderLink("page1"), "page1");
        info.setTitle("PageTitle");
        breadCrumbList.add(info);
        breadCrumbList.add(new BreadCrumbInfo("msg2", linkSource.createPageRenderLink("page2"), "page2"));
        this.getService(ApplicationStateManager.class).set(BreadCrumbList.class, breadCrumbList);

        final Document page = this.renderPage("page2");
        final String pageContent = page.toString();
        assertTrue("page contains PageTitle", pageContent.contains("PageTitle"));
        assertTrue("page contains Message2", pageContent.contains("Message2"));
    }

    @Test
    public void renderEmptyCrumbList() {
        final Document page = this.renderPage("page2");
        final String pageContent = page.toString();
        assertTrue("page contains empty <ul>", pageContent.contains("<ul></ul>"));
    }
}
