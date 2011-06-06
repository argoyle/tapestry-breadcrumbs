package se.unbound.tapestry.breadcrumbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.junit.Test;

import se.unbound.tapestry.breadcrumbs.mocks.ApplicationStateManagerMock;
import se.unbound.tapestry.breadcrumbs.mocks.ComponentClassResolverMock;
import se.unbound.tapestry.breadcrumbs.mocks.ComponentEventLinkEncoderMock;
import se.unbound.tapestry.breadcrumbs.mocks.ComponentSourceMock;
import se.unbound.tapestry.breadcrumbs.mocks.GroupPageMock;
import se.unbound.tapestry.breadcrumbs.mocks.IndexPageMock;
import se.unbound.tapestry.breadcrumbs.mocks.IndexPageWithIgnoreMock;
import se.unbound.tapestry.breadcrumbs.mocks.LinkMock;
import se.unbound.tapestry.breadcrumbs.mocks.PageRenderLinkSourceMock;
import se.unbound.tapestry.breadcrumbs.mocks.RequestMock;
import se.unbound.tapestry.breadcrumbs.mocks.ResponseMock;
import se.unbound.tapestry.breadcrumbs.mocks.UserPageMock;

public class BreadCrumbDispatcherTest {
    private final ApplicationStateManagerMock applicationStateManager = new ApplicationStateManagerMock();
    private final ComponentClassResolverMock componentClassResolver = new ComponentClassResolverMock();
    private final ComponentSourceMock componentSource = new ComponentSourceMock();
    private final PageRenderLinkSourceMock pageRenderLinkSource = new PageRenderLinkSourceMock();
    private final ComponentEventLinkEncoder componentEventLinkEncoder = new ComponentEventLinkEncoderMock();
    private final BreadCrumbDispatcher dispatcher = new BreadCrumbDispatcher(this.applicationStateManager,
            this.componentSource, this.pageRenderLinkSource, this.componentEventLinkEncoder);

    @Test
    public void dispatcherReturnFalseForRootPage() throws Exception {
        this.addPage("/", null);
        final Request request = new RequestMock("/");
        final Response response = new ResponseMock();
        final boolean handled = this.dispatcher.dispatch(request, response);
        assertFalse("handled", handled);
    }

    @Test
    public void dispatcherReturnFalseForPageWithoutAnnotation() throws Exception {
        this.addPage("user", new UserPageMock());
        final Request request = new RequestMock("/user/1");
        final Response response = new ResponseMock();
        final boolean handled = this.dispatcher.dispatch(request, response);
        assertFalse("handled", handled);
    }

    @Test
    public void dispatcherAddsPageWithAnnotationToBreadCrumbList() throws Exception {
        final BreadCrumbList breadCrumbList = this.createBreadCrumbList();
        this.applicationStateManager.set(BreadCrumbList.class, breadCrumbList);
        this.addPage("group", new GroupPageMock());
        final Request request = new RequestMock("/group/1");
        final Response response = new ResponseMock();
        this.dispatcher.dispatch(request, response);
        assertEquals("list size", 1, breadCrumbList.size());
    }

    @Test
    public void dispatcherAddsLocalizedPageWithAnnotationToBreadCrumbList() throws Exception {
        final BreadCrumbList breadCrumbList = this.createBreadCrumbList();
        this.applicationStateManager.set(BreadCrumbList.class, breadCrumbList);
        this.addPage("group", new GroupPageMock());
        final Request request = new RequestMock("/sv_SE/group/1");
        final Response response = new ResponseMock();
        this.dispatcher.dispatch(request, response);
        assertEquals("list size", 1, breadCrumbList.size());
    }

    @Test
    public void dispatcherResetsBreadCrumbListIfResetAnnotationIsPresent() throws Exception {
        final BreadCrumbList breadCrumbList = this.createBreadCrumbList();
        breadCrumbList.add(new BreadCrumbInfo("edit.title", new LinkMock("/edit/2"), "Edit"));
        this.applicationStateManager.set(BreadCrumbList.class, breadCrumbList);
        this.addPage("index", new IndexPageMock());
        final Request request = new RequestMock("/index/1");
        final Response response = new ResponseMock();
        this.dispatcher.dispatch(request, response);
        assertEquals("list size", 1, breadCrumbList.size());
    }

    @Test
    public void dispatcherDoesNotResetBreadCrumbListIfResetAnnotationHasSpecifiedPreviousPageClassAsIgnored()
            throws Exception {
        final BreadCrumbList breadCrumbList = this.createBreadCrumbList();
        breadCrumbList.add(new BreadCrumbInfo("edit.title", new LinkMock("/edit/2"), "Edit"));
        this.applicationStateManager.set(BreadCrumbList.class, breadCrumbList);
        this.addPage("index", new IndexPageWithIgnoreMock());
        this.addPage("group", new GroupPageMock());
        final RequestMock request = new RequestMock("localhost", 80, "/app", "/index/1");
        request.addHeader("Referer", "http://localhost/app/group/1");
        final Response response = new ResponseMock();
        this.dispatcher.dispatch(request, response);
        assertEquals("list size", 2, breadCrumbList.size());
    }

    @Test
    public void dispatcherResetsBreadCrumbListIfRefererIsNoValidURI() throws Exception {
        final BreadCrumbList breadCrumbList = this.createBreadCrumbList();
        breadCrumbList.add(new BreadCrumbInfo("edit.title", new LinkMock("/edit/2"), "Edit"));
        this.applicationStateManager.set(BreadCrumbList.class, breadCrumbList);
        this.addPage("index", new IndexPageMock());
        final RequestMock request = new RequestMock("localhost", 80, "/app", "/index/1");
        request.addHeader("Referer", "http://localhost/app/");
        final Response response = new ResponseMock();
        this.dispatcher.dispatch(request, response);
        assertEquals("list size", 1, breadCrumbList.size());
    }

    private BreadCrumbList createBreadCrumbList() {
        return new BreadCrumbList(true, 5);
    }

    private void addPage(final String pageName, final Component page) {
        this.componentClassResolver.addPage(pageName, page);
        this.componentSource.addPage(pageName, page);
    }
}
