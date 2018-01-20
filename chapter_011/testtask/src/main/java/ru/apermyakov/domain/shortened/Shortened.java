package ru.apermyakov.domain.shortened;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@Entity(name = "shortened")
public class Shortened {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @Column(name = "longurl")
    private String longUrl;

    @Column(name = "shorturl")
    private String shortUrl;

    @JsonIgnore
    private int uses;

    public Shortened() {
    }

    public Shortened(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @JsonGetter
    public String getShortUrl() {
        return shortUrl;
    }

    @JsonSetter
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
}
