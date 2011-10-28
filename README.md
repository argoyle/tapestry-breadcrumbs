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
      <version>1.6</version>
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

