package vn.loto.jsf04.service.lazyview;

import org.primefaces.model.SortOrder;
import vn.loto.jsf04.metier.Pays;

import java.util.Comparator;

public class LazyPaysSorter implements Comparator<Pays> {
    private String sortField;
    private SortOrder order;

    public LazyPaysSorter(String sortField, SortOrder order) {
        this.sortField = sortField;
        this.order = order;
    }

    @Override
    public int compare(Pays p1, Pays p2) {
        try {
            Object val1 = p1.getComparatorByValue(sortField);
            Object val2 = p2.getComparatorByValue(sortField);

            int value = ((Comparable) val1).compareTo(val2);

            return SortOrder.ASCENDING.equals(order) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
