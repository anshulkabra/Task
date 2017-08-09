package anshul.org.gyanmatrix.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class ItemsEntity implements Parcelable {

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

    protected ItemsEntity(Parcel in) {
        quote_max = in.readString();
        quote_available = in.readString();
        if (in.readByte() == 0x01) {
            records = new ArrayList<RecordsEntity>();
            in.readList(records, RecordsEntity.class.getClassLoader());
        } else {
            records = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quote_max);
        dest.writeString(quote_available);
        if (records == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(records);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItemsEntity> CREATOR = new Parcelable.Creator<ItemsEntity>() {
        @Override
        public ItemsEntity createFromParcel(Parcel in) {
            return new ItemsEntity(in);
        }

        @Override
        public ItemsEntity[] newArray(int size) {
            return new ItemsEntity[size];
        }
    };
}