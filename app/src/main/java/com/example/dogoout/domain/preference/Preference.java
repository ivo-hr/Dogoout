package com.example.dogoout.domain.preference;

import java.io.Serializable;
public class Preference implements Serializable{

    private String sexPreference;
    private String dogOwnerPreference;
    private String dogBreedPreference;
    private int minAge;
    private int maxAge;

    public Preference() {}

    public Preference(String sexPreference, String dogOwnerPreference, String dogBreedPreference, int minAge, int maxAge) {
        this.sexPreference = sexPreference;
        this.dogOwnerPreference = dogOwnerPreference;
        this.dogBreedPreference = dogBreedPreference;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getSexPreference() {
        return sexPreference;
    }

    public void setSexPreference(String sexPreference) {
        this.sexPreference = sexPreference;
    }

    public String getDogOwnerPreference() {
        return dogOwnerPreference;
    }

    public void setDogOwnerPreference(String dogOwnerPreference) {
        this.dogOwnerPreference = dogOwnerPreference;
    }

    public String getDogBreedPreference() {
        return dogBreedPreference;
    }

    public void setDogBreedPreference(String dogBreedPreference) {
        this.dogBreedPreference = dogBreedPreference;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
