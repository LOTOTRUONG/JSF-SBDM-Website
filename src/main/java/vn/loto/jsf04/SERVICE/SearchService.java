package vn.loto.jsf04.SERVICE;

import vn.loto.jsf04.METIER.Marque;

import java.util.ArrayList;
import java.util.List;

public class SearchService<T> {
    public List<T> search(List<T> dataList, String query) {
        List<T> filteredList = new ArrayList<>();
        for (T data : dataList) {
            String name = extractName(data);
            if (name != null && name.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(data);
            }
        }
        return filteredList;
    }

    private String extractName(T data) {
        if (data instanceof Marque) {
            return ((Marque) data).getLibelle();
        } else {
            return null;
        }
    }

}
