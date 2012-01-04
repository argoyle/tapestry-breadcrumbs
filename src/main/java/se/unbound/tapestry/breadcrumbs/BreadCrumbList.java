package se.unbound.tapestry.breadcrumbs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Class for holding the list of crumbs in SessionState.
 */
public class BreadCrumbList implements Iterable<BreadCrumbInfo>, Serializable {
    private static final long serialVersionUID = 4046582733432334945L;
    private final List<BreadCrumbInfo> crumbs = new ArrayList<BreadCrumbInfo>();
    private final Boolean discardDuplicates;
    private final Integer maxCrumbsToSave;

    /**
     * Constructs a new BreadCrumbList.
     * 
     * @param discardDuplicates If duplicate crumbs should be discarded.
     * @param maxCrumbsToSave The maximum number of crumbs to save.
     */
    public BreadCrumbList(@Symbol(BreadCrumbSymbols.DISCARD_DUPLICATES)
    final Boolean discardDuplicates,
            @Symbol(BreadCrumbSymbols.MAX_CRUMBS_TO_SAVE)
            final Integer maxCrumbsToSave) {
        this.discardDuplicates = discardDuplicates;
        this.maxCrumbsToSave = maxCrumbsToSave;
    }

    @Override
    public Iterator<BreadCrumbInfo> iterator() {
        return this.getCrumbsToDisplay().iterator();
    }

    /**
     * Retrieves the crumbs to display as a list.
     * 
     * @return a list of crumbs to display or an empty list if no crumbs to display exists.
     */
    public List<BreadCrumbInfo> getCrumbsToDisplay() {
        final List<BreadCrumbInfo> crumbsToDisplay = new ArrayList<BreadCrumbInfo>();
        for (final BreadCrumbInfo crumb : this.crumbs) {
            if (crumb.showInCrumbTrail()) {
                crumbsToDisplay.add(crumb);
            }
        }
        return crumbsToDisplay;
    }

    /**
     * Adds a new crumb to the list.
     * 
     * @param breadCrumbInfo The crumb to add.
     */
    public void add(final BreadCrumbInfo breadCrumbInfo) {
        final int index = this.crumbs.indexOf(breadCrumbInfo);
        if (this.discardDuplicates && index != -1) {
            this.crumbs.subList(index + 1, this.crumbs.size()).clear();
        } else {
            if (this.crumbs.size() == this.maxCrumbsToSave) {
                this.crumbs.remove(0);
            }
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

    /**
     * Resets the list of crumbs.
     */
    public void reset() {
        this.crumbs.clear();
    }

    /**
     * Retrieve the last crumb (the last visited page).
     * 
     * @return The last crumb in the list or null if the list of crumbs is empty.
     */
    public BreadCrumbInfo getLastCrumb() {
        if (this.crumbs.isEmpty()) {
            throw new IllegalStateException("No crumbs to fetch.");
        }
        return this.crumbs.get(this.crumbs.size() - 1);
    }
}
