package com.example.dogoout.domain.prompt;

import java.io.Serializable;

/**
 * The `Prompt` class represents a prompt entity in the application.
 * Prompts are used to provide questions for the user to respond.
 *
 * This class includes methods to access and modify the prompt's attributes.
 */
public class PromptImpl implements Prompt, Serializable {

    private String id;
    private String prompt;

    /**
     * Constructs a new empty `Prompt` object.
     */
    public PromptImpl() {};

    /**
     * Constructs a new `Prompt` object with the given identifier and prompt text.
     *
     * @param id     The unique identifier of the prompt.
     * @param prompt The text of the prompt.
     */
    public PromptImpl(String id, String prompt) {
        this.id = id;
        this.prompt = prompt;
    }

    /**
     * Retrieves the unique identifier of the prompt.
     *
     * @return The unique identifier of the prompt.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the prompt.
     *
     * @param id The new unique identifier for the prompt.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the text of the prompt.
     *
     * @return The text of the prompt.
     */
    @Override
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the text of the prompt.
     *
     * @param prompt The new text for the prompt.
     */
    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}