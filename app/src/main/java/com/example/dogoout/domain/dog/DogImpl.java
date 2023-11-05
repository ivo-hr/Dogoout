package com.example.dogoout.domain.dog;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The `Dog` class represents an entity for a dog in the application.
 */
public class DogImpl implements Dog, Serializable {

    private String id;
    private String name;
    private String breed;
    private int yearOfBirth;
    private String promptId;
    private String promptAnswer;
    private ArrayList<Characteristic> characteristics;

    /**
     * Constructs a new `Dog` with default values.
     */
    public DogImpl() {}

    /**
     * Constructs a new `Dog` with the given attributes.
     *
     * @param id The unique identifier for the dog.
     * @param name The name of the dog.
     * @param breed The breed of the dog.
     * @param yearOfBirth The year of birth of the dog.
     * @param promptId The unique identifier of the dog's prompt.
     * @param promptAnswer The dog's answer to the prompt.
     * @param characteristics The list of characteristics or traits associated with the dog.
     */
    public DogImpl(String id, String name, String breed, int yearOfBirth, String promptId, String promptAnswer, ArrayList<Characteristic> characteristics) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.yearOfBirth = yearOfBirth;
        this.promptId = promptId;
        this.promptAnswer = promptAnswer;
        this.characteristics = characteristics;
    }

    /**
     * Retrieves the unique identifier for the dog.
     *
     * @return The unique identifier for the dog.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the dog.
     *
     * @param id The new unique identifier for the dog.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the dog.
     *
     * @return The name of the dog.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the dog.
     *
     * @param name The new name for the dog.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the breed of the dog.
     *
     * @return The breed of the dog.
     */
    @Override
    public String getBreed() {
        return breed;
    }

    /**
     * Sets the breed of the dog.
     *
     * @param breed The new breed for the dog.
     */
    @Override
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Retrieves the year of birth of the dog.
     *
     * @return The year of birth of the dog.
     */
    @Override
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the year of birth of the dog.
     *
     * @param yearOfBirth The new year of birth for the dog.
     */
    @Override
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Retrieves the unique identifier of the dog's prompt.
     *
     * @return The unique identifier of the prompt.
     */
    @Override
    public String getPromptId() {
        return promptId;
    }

    /**
     * Sets the unique identifier of the dog's prompt.
     *
     * @param promptId The new prompt id for the dog.
     */
    @Override
    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    /**
     * Retrieves the dog's answer to the prompt.
     *
     * @return The dog's answer to the prompt.
     */
    @Override
    public String getPromptAnswer() {
        return promptAnswer;
    }

    /**
     * Sets the dog's answer to the prompt.
     *
     * @param promptAnswer The new answer to the prompt for the dog.
     */
    @Override
    public void setPromptAnswer(String promptAnswer) {
        this.promptAnswer = promptAnswer;
    }

    /**
     * Retrieves the list of characteristics associated with the dog.
     *
     * @return An ArrayList of Characteristic objects representing the dog's characteristics.
     */
    @Override
    public ArrayList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    /**
     * Sets the list of characteristics associated with the dog.
     *
     * @param characteristics An ArrayList of Characteristic objects to be associated with the dog.
     */
    @Override
    public void setCharacteristics(ArrayList<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
