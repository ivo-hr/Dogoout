package com.example.dogoout.domain.user;

import android.graphics.drawable.Drawable;

import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.preference.Preference;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The `UserBuilder` class is used to construct instances of the `UserImpl` class with various attributes.
 */
public class UserBuilder {
    private String id;
    private String firstname;
    private String surname;
    private String email;
    private String country;
    private LocalDate birthDate;
    private String password;
    private String gender;
    private String description;
    private int promptId;
    private ArrayList<Drawable> photosUser;
    private String promptAnswer;
    private Preference userPreference;
    private ArrayList<Dog> dogs;

    /**
     * Constructs a new `UserBuilder`.
     */
    public UserBuilder() {
    }

    /**
     * Set the user's id.
     *
     * @param id The new id for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Set the user's first name.
     *
     * @param firstname The new first name for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Set the user's surname.
     *
     * @param surname The new surname for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    /**
     * Set the user's email address.
     *
     * @param email The new email address for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Set the user's country of residence.
     *
     * @param country The new country of residence for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * Set the user's date of birth.
     *
     * @param birthDate The new date of birth for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Set the user's password for authentication.
     *
     * @param password The new password for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Set the user's gender.
     *
     * @param gender The new gender for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Set the user's description or bio.
     *
     * @param description The new description for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Set the unique identifier of the user's security prompt.
     *
     * @param promptId The new security prompt identifier for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withPromptId(int promptId) {
        this.promptId = promptId;
        return this;
    }

    /**
     * Set the user's answer to the security prompt.
     *
     * @param promptAnswer The new answer to the security prompt for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withPromptAnswer(String promptAnswer) {
        this.promptAnswer = promptAnswer;
        return this;
    }

    /**
     * Set the user's photos.
     *
     * @param photosUser The photos for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withPromptAnswer(ArrayList<Drawable> photosUser) {
        this.photosUser = photosUser;
        return this;
    }

    /**
     * Set the user's preference.
     *
     * @param preference The new preference for the user.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withPreference(Preference preference) {
        this.userPreference = preference;
        return this;
    }

    /**
     * Set the user's list of dogs.
     *
     * @param dogs The list of dogs.
     * @return The UserBuilder instance for method chaining.
     */
    public UserBuilder withDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
        return this;
    }

    /**
     * Build a `UserImpl` object with the specified attributes.
     *
     * @return The constructed `UserImpl` object.
     */
    public UserImpl build() {
        return new UserImpl(id, firstname, surname, email, country, birthDate, password, gender, description, promptId, photosUser, promptAnswer, userPreference , dogs);
    }
}
