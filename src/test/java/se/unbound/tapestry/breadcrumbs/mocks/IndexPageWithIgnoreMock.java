package se.unbound.tapestry.breadcrumbs.mocks;

import se.unbound.tapestry.breadcrumbs.BreadCrumb;
import se.unbound.tapestry.breadcrumbs.BreadCrumbReset;

@BreadCrumbReset(ignorePages = { GroupPageMock.class })
@BreadCrumb(titleKey = "index.title")
public class IndexPageWithIgnoreMock extends PageMock {
}