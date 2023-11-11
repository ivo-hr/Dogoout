package com.example.dogoout.domain.dog;

import android.graphics.drawable.Drawable;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

/**
 * The `Dog` class represents an entity for a dog in the application.
 */
public class DogImpl implements Dog, Serializable {

    private String name;
    private String breed;
    private String prompt;
    private String promptAnswer;
    private ArrayList<String> characteristics;
    private ArrayList<URI> photosDog;

    /**
     * Constructs a new `Dog` with default values.
     */
    public DogImpl() {
    }

    /**
     * Constructs a new `Dog` with the given attributes.
     *
     * @param name            The name of the dog.
     * @param breed           The breed of the dog.
     * @param prompt          The dog's prompt.
     * @param promptAnswer    The dog's answer to the prompt.
     * @param characteristics The list of characteristics or traits associated with the dog.
     */
    public DogImpl(String name, String breed, String prompt, String promptAnswer, ArrayList<String> characteristics, ArrayList<URI> photosDog) {
        this.name = name;
        this.breed = breed;
        this.prompt = prompt;
        this.promptAnswer = promptAnswer;
        this.characteristics = characteristics;
        this.photosDog = photosDog;
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
     * Retrieves the dog's prompt.
     *
     * @return The prompt.
     */
    @Override
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the dog's prompt.
     *
     * @param prompt The new prompt for the dog.
     */
    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
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
    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    /**
     * Sets the list of characteristics associated with the dog.
     *
     * @param characteristics An ArrayList of Characteristic objects to be associated with the dog.
     */
    @Override
    public void setCharacteristics(ArrayList<String> characteristics) {
        this.characteristics = characteristics;
    }

    /**
     * Retrieves the list of photos associated with the dog.
     *
     * @return An ArrayList of Drawable objects representing the dog's photos.
     */
    @Override
    public ArrayList<URI> getPhotosDog() {
        return photosDog;
    }

    /**
     * Sets the list of photos associated with the dog.
     *
     * @param photosDog An ArrayList of Drawable objects to be associated with the dog.
     */
    @Override
    public void setPhotosDog(ArrayList<URI> photosDog) {
        this.photosDog = photosDog;
    }
}
