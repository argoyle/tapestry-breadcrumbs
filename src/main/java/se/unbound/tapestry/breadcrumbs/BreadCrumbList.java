package se.unbound.tapestry.breadcrumbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for holding the list of crumbs in SessionState.
 */
public class BreadCrumbList implements Iterable<BreadCrumbInfo> {
    private static final long serialVersionUID = 4046582733432334945L;
    private final List<BreadCrumbInfo> crumbs = new ArrayList<BreadCrumbInfo>();

    @Override
    public Iterator<BreadCrumbInfo> iterator() {
        return this.crumbs.iterator();
    }

    /**
     * Adds a new crumb to the list.
     * 
     * @param breadCrumbInfo The crumb to add.
     */
    public void add(final BreadCrumbInfo breadCrumbInfo) {
        final int index = this.crumbs.indexOf(breadCrumbInfo);
        // TODO: Make it configurable if duplicates should remove other
        // bread crumbs
        if (index != -1) {
            this.crumbs.subList(index + 1, this.crumbs.size()).clear();
        } else {
            this.crumbs.add(breadCrumbInfo);
        }
    }

    /**
     * Returns the number of crumbs in this list.
     * 
     * @return the number of crumbs in this list
     */
    public int size() {
        return this.crumbs.size();
    }
}
