package se.unbound.tapestry.breadcrumbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Class for holding the list of crumbs in SessionState.
 */
public class BreadCrumbList implements Iterable<BreadCrumbInfo> {
    private static final long serialVersionUID = 4046582733432334945L;
    private final List<BreadCrumbInfo> crumbs = new ArrayList<BreadCrumbInfo>();
    private final Boolean discardDuplicates;
    private final Integer maxCrumbsToSave;

    public BreadCrumbList(@Symbol(BreadCrumbSymbols.DISCARD_DUPLICATES) final Boolean discardDuplicates,
            @Symbol(BreadCrumbSymbols.MAX_CRUMBS_TO_SAVE) final Integer maxCrumbsToSave) {
        this.discardDuplicates = discardDuplicates;
        this.maxCrumbsToSave = maxCrumbsToSave;
    }

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
}
