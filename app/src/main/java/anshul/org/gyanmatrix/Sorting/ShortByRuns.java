package anshul.org.gyanmatrix.Sorting;

import java.util.Comparator;

import anshul.org.gyanmatrix.data.model.RecordsEntity;


public class ShortByRuns implements Comparator<RecordsEntity> {

    @Override
    public int compare(RecordsEntity obj1, RecordsEntity obj2) {

        Integer i1 = Integer.parseInt(obj1.getTotal_score());
        Integer i2 = Integer.parseInt(obj2.getTotal_score());

        int value1 = i2.compareTo(i1);
        return value1;
    }
}

