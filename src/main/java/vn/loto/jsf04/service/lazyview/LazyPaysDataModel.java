package vn.loto.jsf04.service.lazyview;

import jakarta.faces.context.FacesContext;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;
import vn.loto.jsf04.metier.Pays;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LazyPaysDataModel extends LazyDataModel<Pays> {
    private static final long serialVersionUID = 1L;

    private List<Pays> datasource;

    public LazyPaysDataModel(List<Pays> datasource){
        this.datasource = datasource;
    }
    @Override
    public Pays getRowData(String rowKey) {
        for (Pays pays : datasource) {
            if (pays.getId() == Integer.parseInt(rowKey)) {
                return pays;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Pays pays) {
        return String.valueOf(pays.getId());
    }


    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int) datasource.stream()
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
    }

    @Override
    public List<Pays> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<Pays> pays = datasource.stream()
                .skip(offset)
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .limit(pageSize)
                .collect(Collectors.toList());

        if(!sortBy.isEmpty()) {
            List<Comparator<Pays>> comparators = sortBy.values().stream()
                    .map(o -> new LazyPaysSorter(o.getField(), o.getOrder()))
                    .collect(Collectors.toList());

            for(Comparator<Pays> cp : comparators)
                pays.sort(cp);
        }
        return pays;
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                // Replace ShowcaseUtil.getPropertyValueViaReflection with reflection directly
                Object columnValue = o.getClass().getMethod("get" + capitalize(filter.getField())).invoke(o);
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            } catch (ReflectiveOperationException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }

    // Helper method to capitalize the first letter of a string
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
