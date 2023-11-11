package com.example.dogoout.domain.user;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.preference.Preference;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The `User` class represents a user entity in the application.
 * Users have various attributes and preferences related to their profiles.
 * <p>
 * This class includes methods to access and modify the user's attributes.
 */
public class UserImpl implements User, Serializable {

    private String firstname;
    private String surname;
    private String email;
    private String country;
    private LocalDate birthDate;
    private String gender;
    private String description;
    private String prompt;
    private ArrayList<URI> photosUser;
    private String promptAnswer;
    private Preference preferenceUser;
    private ArrayList<Dog> dogs;

    /**
     * Constructs a new empty `User`.
     */
    public UserImpl() {
    }

    ;

    public UserImpl(String firstname, String surname, String email, String country, LocalDate birthDate, String gender, String description, String prompt, ArrayList<URI> photosUser, String promptAnswer, Preference preferenceUser, ArrayList<Dog> dogs) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.country = country;
        this.birthDate = birthDate;
        this.gender = gender;
        this.description = description;
        this.prompt = prompt;
        this.photosUser = photosUser;
        this.promptAnswer = promptAnswer;
        this.preferenceUser = preferenceUser;
        this.dogs = dogs;
    }

    /**
     * Retrieves the user's first name.
     *
     * @return The user's first name.
     */
    @Override
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstname The new first name for the user.
     */
    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retrieves the user's surname.
     *
     * @return The user's surname.
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the user's surname.
     *
     * @param surname The new surname for the user.
     */
    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return The user's email address.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The new email address for the user.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's country of residence.
     *
     * @return The user's country of residence.
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * Sets the user's country of residence.
     *
     * @param country The new country of residence for the user.
     */
    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Retrieves the user's date of birth.
     *
     * @return The user's date of birth.
     */
    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the user's date of birth.
     *
     * @param birthDate The new date of birth for the user.
     */
    @Override
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Retrieves the user's gender.
     *
     * @return The user's gender.
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * Sets the user's gender.
     *
     * @param gender The new gender for the user.
     */
    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the user's description or bio.
     *
     * @return The user's description.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the user's description or bio.
     *
     * @param description The new description for the user.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the user's prompt.
     *
     * @return The user's prompt.
     */
    @Override
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the user's prompt.
     *
     * @param prompt The new  prompt for the user.
     */
    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Retrieves the user's answer to the security prompt.
     *
     * @return The user's answer to the security prompt.
     */
    @Override
    public String getPromptAnswer() {
        return promptAnswer;
    }

    /**
     * Sets the user's answer to the security prompt.
     *
     * @param promptAnswer The new answer to the security prompt for the user.
     */
    @Override
    public void setPromptAnswer(String promptAnswer) {
        this.promptAnswer = promptAnswer;
    }

    /**
     * Retrieves the user's preference.
     *
     * @return The user's preference.
     */
    @Override
    public Preference getPreference() {
        return preferenceUser;
    }

    /**
     * Sets the user's preference.
     *
     * @param preferenceUser The new preference for for the user.
     */
    @Override
    public void setPreferenceSex(Preference preferenceUser) {
        this.preferenceUser = preferenceUser;
    }

    /**
     * Retrieves the list of dogs associated with this user.
     *
     * @return An ArrayList of Dog objects representing the dogs owned or associated with this user.
     */
    @Override
    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    /**
     * Sets the list of dogs associated with this user.
     *
     * @param dogs An ArrayList of Dog objects to be associated with this user.
     */
    @Override
    public void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public ArrayList<URI> getPhotosUser() {
        return photosUser;
    }

    @Override
    public void setPhotosUser(ArrayList<URI> photosUser) {
        this.photosUser = photosUser;
    }
}
