package com.example.dogoout.domain.preference;

public class PreferenceBuilder {
    private String sexPreference;
    private String dogOwnerPreference;
    private String dogBreedPreference;
    private int minAge;
    private int maxAge;

    public PreferenceBuilder() {
    }

    public PreferenceBuilder withSexPreference(String sexPreference) {
        this.sexPreference = sexPreference;
        return this;
    }

    public PreferenceBuilder withDogOwnerPreference(String dogOwnerPreference) {
        this.dogOwnerPreference = dogOwnerPreference;
        return this;
    }

    public PreferenceBuilder withDogBreedPreference(String dogBreedPreference) {
        this.dogBreedPreference = dogBreedPreference;
        return this;
    }

    public PreferenceBuilder withMinAge(int minAge) {
        this.minAge = minAge;
        return this;
    }

    public PreferenceBuilder withMaxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public Preference build() {
        return new Preference(sexPreference, dogOwnerPreference, dogBreedPreference, minAge, maxAge);
    }
}
