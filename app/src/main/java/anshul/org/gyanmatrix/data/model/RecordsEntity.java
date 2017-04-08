package anshul.org.gyanmatrix.data.model;

import java.io.Serializable;


public class RecordsEntity implements Serializable{


    private String id;
    private String name;
    private String image;
    private String total_score;
    private String description;
    private String matches_played;
    private String country;

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
}
