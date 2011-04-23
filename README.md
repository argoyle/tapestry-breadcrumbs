Description
===========
Tapestry-breadcrumbs is a bread crumb trail implementation for Tapestry 5.

It's implemented as an annotation to be placed at each page-class that should
be part of the trail, a Dispatcher which looks for the annotation and inserts
the page into the list of bread crumbs and two components to be used in your
templates where you want the crumb trail to be displayed.

Usage
=====
Add the annotation to your page-class:
@BreadCrumb(titleKey="index.title")
public class Index {

Contribute the dispatcher to the masterDispatcher in your apps module-class:
	public void contributeMasterDispatcher(final OrderedConfiguration<Dispatcher> configuration,
			@InjectService("BreadCrumbDispatcher") final Dispatcher breadCrumbDispatcher) {
					configuration.add("BreadCrumbDispatcher", breadCrumbDispatcher,
									"before:PageRender);
	}

Add the crumb trail to your template:
	<ul>
		<li t:type="crumb/breadcrumbtrail" t:value="breadCrumb"><a t:type="crumb/displaybreadcrumb" t:breadcrumb="breadCrumb" /></li>
	</ul>
