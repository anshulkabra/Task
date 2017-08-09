package anshul.org.gyanmatrix.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RecordsEntity implements Parcelable {

    private String id;
    private String name;
    private String image;
    private String total_score;
    private String description;
    private String matches_played;
    private String country;

    public RecordsEntity(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(String matches_played) {
        this.matches_played = matches_played;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    protected RecordsEntity(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        total_score = in.readString();
        description = in.readString();
        matches_played = in.readString();
        country = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(total_score);
        dest.writeString(description);
        dest.writeString(matches_played);
        dest.writeString(country);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecordsEntity> CREATOR = new Parcelable.Creator<RecordsEntity>() {
        @Override
        public RecordsEntity createFromParcel(Parcel in) {
            return new RecordsEntity(in);
        }

        @Override
        public RecordsEntity[] newArray(int size) {
            return new RecordsEntity[size];
        }
    };
}