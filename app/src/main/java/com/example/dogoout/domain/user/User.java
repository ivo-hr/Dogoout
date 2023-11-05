package com.example.dogoout.domain.user;

import java.time.LocalDate;

public interface User {
    /**
     * Retrieves the user's first name.
     *
     * @return The user's first name.
     */
    String getFirstname();

    /**
     * Sets the user's first name.
     *
     * @param firstname The new first name for the user.
     */
    void setFirstname(String firstname);

    /**
     * Retrieves the user's surname.
     *
     * @return The user's surname.
     */
    String getSurname();

    /**
     * Sets the user's surname.
     *
     * @param surname The new surname for the user.
     */
    void setSurname(String surname);

    /**
     * Retrieves the user's email address.
     *
     * @return The user's email address.
     */
    String getEmail();

    /**
     * Sets the user's email address.
     *
     * @param email The new email address for the user.
     */
    void setEmail(String email);

    /**
     * Retrieves the user's country of residence.
     *
     * @return The user's country of residence.
     */
    String getCountry();

    /**
     * Sets the user's country of residence.
     *
     * @param country The new country of residence for the user.
     */
    void setCountry(String country);

    /**
     * Retrieves the user's date of birth.
     *
     * @return The user's date of birth.
     */
    LocalDate getBirthDate();

    /**
     * Sets the user's date of birth.
     *
     * @param birthDate The new date of birth for the user.
     */
    void setBirthDate(LocalDate birthDate);

    /**
     * Retrieves the user's password for authentication.
     *
     * @return The user's password.
     */
    String getPassword();

    /**
     * Sets the user's password for authentication.
     *
     * @param password The new password for the user.
     */
    void setPassword(String password);

    /**
     * Retrieves the user's gender.
     *
     * @return The user's gender.
     */
    String getGender();

    /**
     * Sets the user's gender.
     *
     * @param gender The new gender for the user.
     */
    void setGender(String gender);

    /**
     * Retrieves the user's description or bio.
     *
     * @return The user's description.
     */
    String getDescription();

    /**
     * Sets the user's description or bio.
     *
     * @param description The new description for the user.
     */
    void setDescription(String description);

    /**
     * Retrieves the unique identifier of the user's security prompt.
     *
     * @return The unique identifier of the security prompt.
     */
    int getPromptId();

    /**
     * Sets the unique identifier of the user's security prompt.
     *
     * @param promptId The new security prompt identifier for the user.
     */
    void setPromptId(int promptId);

    /**
     * Retrieves the user's answer to the security prompt.
     *
     * @return The user's answer to the security prompt.
     */
    String getPromptAnswer();

    /**
     * Sets the user's answer to the security prompt.
     *
     * @param promptAnswer The new answer to the security prompt for the user.
     */
    void setPromptAnswer(String promptAnswer);

    /**
     * Retrieves the user's preference for dog ownership.
     *
     * @return The user's preference for dog ownership.
     */
    int getPreferenceDogOwner();

    /**
     * Sets the user's preference for dog ownership.
     *
     * @param preferenceDogOwner The new preference for dog ownership for the user.
     */
    void setPreferenceDogOwner(int preferenceDogOwner);

    /**
     * Retrieves the user's preference for the opposite sex.
     *
     * @return The user's preference for the opposite sex.
     */
    int getPreferenceSex();

    /**
     * Sets the user's preference for the opposite sex.
     *
     * @param preferenceSex The new preference for the opposite sex for the user.
     */
    void setPreferenceSex(int preferenceSex);
}
