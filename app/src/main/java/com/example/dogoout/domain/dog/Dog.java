package com.example.dogoout.domain.dog;

import android.graphics.drawable.Drawable;

import com.example.dogoout.domain.characteristic.Characteristic;

import java.net.URI;
import java.util.ArrayList;

public interface Dog {

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
     * Retrieves the dog's prompt.
     *
     * @return The prompt of the dog.
     */
    String getPrompt();

    /**
     * Sets the dog's prompt.
     *
     * @param prompt The new prompt for the dog.
     */
    void setPrompt(String prompt);

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
    ArrayList<String> getCharacteristics();

    /**
     * Sets the list of characteristics associated with the dog.
     *
     * @param characteristics An ArrayList of Characteristic objects to be associated with the dog.
     */
    void setCharacteristics(ArrayList<String> characteristics);

    /**
     * Retrieves the list of photos associated with the dog.
     *
     * @return An ArrayList of Photo objects representing the dog's photos.
     */
    ArrayList<URI> getPhotosDog();

    /**
     * Sets the list of photos associated with the dog.
     *
     * @param photos An ArrayList of Photo objects to be associated with the dog.
     */
    void setPhotosDog(ArrayList<URI> photos);
}
