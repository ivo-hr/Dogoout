package com.example.dogoout.domain.dog;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.util.ArrayList;

public interface Dog {
    /**
     * Retrieves the unique identifier for the dog.
     *
     * @return The unique identifier for the dog.
     */
    String getId();

    /**
     * Sets the unique identifier for the dog.
     *
     * @param id The new unique identifier for the dog.
     */
    void setId(String id);

    /**
     * Retrieves the name of the dog.
     *
     * @return The name of the dog.
     */
    String getName();

    /**
     * Sets the name of the dog.
     *
     * @param name The new name for the dog.
     */
    void setName(String name);

    /**
     * Retrieves the breed of the dog.
     *
     * @return The breed of the dog.
     */
    String getBreed();

    /**
     * Sets the breed of the dog.
     *
     * @param breed The new breed for the dog.
     */
    void setBreed(String breed);

    /**
     * Retrieves the year of birth of the dog.
     *
     * @return The year of birth of the dog.
     */
    int getYearOfBirth();

    /**
     * Sets the year of birth of the dog.
     *
     * @param yearOfBirth The new year of birth for the dog.
     */
    void setYearOfBirth(int yearOfBirth);

    /**
     * Retrieves the unique identifier of the dog's prompt.
     *
     * @return The unique identifier of the prompt.
     */
    String getPromptId();

    /**
     * Sets the unique identifier of the dog's prompt.
     *
     * @param promptId The new prompt id for the dog.
     */
    void setPromptId(String promptId);

    /**
     * Retrieves the dog's answer to the prompt.
     *
     * @return The dog's answer to the prompt.
     */
    String getPromptAnswer();

    /**
     * Sets the dog's answer to the prompt.
     *
     * @param promptAnswer The new answer to the prompt for the dog.
     */
    void setPromptAnswer(String promptAnswer);

    /**
     * Retrieves the list of characteristics associated with the dog.
     *
     * @return An ArrayList of Characteristic objects representing the dog's characteristics.
     */
    ArrayList<Characteristic> getCharacteristics();

    /**
     * Sets the list of characteristics associated with the dog.
     *
     * @param characteristics An ArrayList of Characteristic objects to be associated with the dog.
     */
    void setCharacteristics(ArrayList<Characteristic> characteristics);
}
