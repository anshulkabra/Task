package anshul.org.gyanmatrix.Sorting;

import java.util.Comparator;

import anshul.org.gyanmatrix.data.model.RecordsEntity;


public class ShortByFetchOrder implements Comparator<RecordsEntity> {
    @Override
    public int compare(RecordsEntity obj1, RecordsEntity obj2) {
        Double i1 = Double.parseDouble(obj1.getId());
        Double i2 = Double.parseDouble(obj2.getId());

        int value1 = i1.compareTo(i2);
        return value1;
    }
}
