Description
===========
Tapestry-breadcrumbs is a bread crumb trail implementation for Tapestry 5.

It's implemented as an annotation to be placed at each page-class that should
be part of the trail, a Dispatcher which looks for the annotation and inserts
the page into the list of bread crumbs and two components to be used in your
templates where you want the crumb trail to be displayed.

Usage
=====
Add a dependency to your POM:

    <dependency>
      <groupId>se.unbound</groupId>
      <artifactId>tapestry-breadcrumbs</artifactId>
      <version>1.9</version>
    </dependency>

Add the annotation to your page-classes:

    @BreadCrumb(titleKey="index.title")
    public class Index {

The titleKey is the key into the message catalog that the title of the crumb should use.

Add the crumb trail to your template:

    <ul>
      <li t:type="crumb/breadcrumbtrail" t:value="breadCrumb">
        <a t:type="crumb/displaybreadcrumb" t:breadcrumb="breadCrumb" />
      </li>
    </ul>

You also need a property for holding the bread crumb when rendering the trail. This should be in your page or layout class 
depending on where you place the trail:

    @Property
    private BreadCrumbInfo breadCrumb;

It's also possible to have a page reset the crumb trail by placing a @BreadCrumbReset-annotation on the page. It's also possible 
to ignore resetting the crumb trail if the previous page was specified in the ignorePages-attribute (eg. 
@BreadCrumbReset(ignorePages = {Edit.class, View.class}) will ignore resetting the trail if coming from Edit or View).

From version 1.9 it's possible to change the title of a breadcrumb from the page. Just add

    @SessionState
    private BreadCrumbList breadCrumbList;

And then in one of the render-methods

    public void beginRender() {
      breadCrumbList.getLastCrumb().setTitle("<new title>");


Configuration
=============
You can configure the maximum number of crumbs to save (default is Integer.MAX_VALUE) as well as if duplicate crumbs should 
be discarded or not (default is to discard duplicates).

Configuration is done using the standard Tapestry-way by contributing application defaults:

    public static void contributeApplicationDefaults(final MappedConfiguration<String, String> configuration) {
        configuration.add(BreadCrumbSymbols.DISCARD_DUPLICATES, "false");
        configuration.add(BreadCrumbSymbols.MAX_CRUMBS_TO_SAVE, "10");
    }
