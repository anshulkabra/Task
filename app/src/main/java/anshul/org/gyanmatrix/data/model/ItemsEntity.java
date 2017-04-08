package anshul.org.gyanmatrix.data.model;

import java.io.Serializable;
import java.util.List;


public class ItemsEntity implements Serializable{

    private String quote_max;
    private String quote_available;
    private List<RecordsEntity> records;

    public String getQuote_max() {
        return quote_max;
    }

    public void setQuote_max(String quote_max) {
        this.quote_max = quote_max;
    }

    public String getQuote_available() {
        return quote_available;
    }

    public void setQuote_available(String quote_available) {
        this.quote_available = quote_available;
    }

    public List<RecordsEntity> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsEntity> records) {
        this.records = records;
    }


}
