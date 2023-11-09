package com.example.dogoout.domain.dog;

import android.graphics.drawable.Drawable;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.util.ArrayList;

/**
 * The `DogBuilder` class is used to construct instances of the `Dog` class with various attributes.
 */
public class DogBuilder {
    private String name;
    private String breed;
    private String prompt;
    private String promptAnswer;
    private ArrayList<String> characteristics;
    private ArrayList<Drawable> photosDog;

    /**
     * Constructs a new `DogBuilder`.
     */
    public DogBuilder() {
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
     * Set the dog's prompt.
     *
     * @param prompt The new prompt for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withPromptId(String prompt) {
        this.prompt = prompt;
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
     * @param characteristics The new list of characteristics for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withCharacteristics(ArrayList<String> characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    /**
     * Set the list of photos associated with the dog.
     *
     * @param photosDog The new list of photos for the dog.
     * @return The DogBuilder instance for method chaining.
     */
    public DogBuilder withPhotosDog(ArrayList<Drawable> photosDog) {
        this.photosDog = photosDog;
        return this;
    }


    /**
     * Build a `Dog` object with the specified attributes.
     *
     * @return The constructed `Dog` object.
     */
    public Dog build() {
        return new DogImpl(name, breed, prompt, promptAnswer, characteristics, photosDog);
    }
}

