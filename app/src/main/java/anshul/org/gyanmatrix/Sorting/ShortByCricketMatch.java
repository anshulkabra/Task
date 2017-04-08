package anshul.org.gyanmatrix.Sorting;

import java.util.Comparator;

import anshul.org.gyanmatrix.data.model.RecordsEntity;


public class ShortByCricketMatch implements Comparator<RecordsEntity> {
    @Override
    public int compare(RecordsEntity obj1, RecordsEntity obj2) {
        Double i1 = Double.parseDouble(obj1.getMatches_played());
        Double i2 = Double.parseDouble(obj2.getMatches_played());

        int value1 = i2.compareTo(i1);
        return value1;
    }
}
