package com.example.dogoout.domain.dog;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.util.ArrayList;

/**
 * The `DogBuilder` class is used to construct instances of the `Dog` class with various attributes.
 */
public class DogBuilder {
    private String id;
    private String name;
    private String breed;
    private int yearOfBirth;
    private String promptId;
    private String promptAnswer;
    private ArrayList<Characteristic> characteristics;

    /**
     * Constructs a new `DogBuilder`.
     */
    public DogBuilder() {
    }

    /**
     * Set the unique identifier for the dog.
     *
     * @param id The new unique identifier for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Set the name of the dog.
     *
     * @param name The new name for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the breed of the dog.
     *
     * @param breed The new breed for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Set the year of birth of the dog.
     *
     * @param yearOfBirth The new year of birth for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    /**
     * Set the unique identifier of the dog's prompt.
     *
     * @param promptId The new prompt identifier for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withPromptId(String promptId) {
        this.promptId = promptId;
        return this;
    }

    /**
     * Set the dog's answer to the prompt.
     *
     * @param promptAnswer The new answer to the prompt for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withPromptAnswer(String promptAnswer) {
        this.promptAnswer = promptAnswer;
        return this;
    }

    /**
     * Set the list of characteristics or traits associated with the dog.
     *
     * @param characteristics An ArrayList of Characteristic objects to be associated with the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withCharacteristics(ArrayList<Characteristic> characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    /**
     * Build a `Dog` object with the specified attributes.
     *
     * @return The constructed `Dog` object.
     */
    public Dog build() {
        return new DogImpl(id, name, breed, yearOfBirth, promptId, promptAnswer, characteristics);
    }
}

