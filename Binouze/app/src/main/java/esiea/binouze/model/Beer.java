package esiea.binouze.model;

import java.util.Date;

public class Beer {

    private Integer id;
    private String name;

    private String description;
    private String buveur;

    private String note;
    private String note_moyenne;
    private String number_of_notes;

    private String image;
    private String thumb;

    private Integer country_id;
    private String country;

    private Integer category_id;
    private String category;

    private Date created_at;
    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuveur() {
        return buveur;
    }

    public void setBuveur(String buveur) {
        this.buveur = buveur;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote_moyenne() {
        return note_moyenne;
    }

    public void setNote_moyenne(String note_moyenne) {
        this.note_moyenne = note_moyenne;
    }

    public String getNumber_of_notes() {
        return number_of_notes;
    }

    public void setNumber_of_notes(String number_of_notes) {
        this.number_of_notes = number_of_notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


}
