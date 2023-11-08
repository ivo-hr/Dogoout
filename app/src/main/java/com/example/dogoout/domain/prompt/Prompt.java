package com.example.dogoout.domain.prompt;

public interface Prompt {
    /**
     * Retrieves the unique identifier of the prompt.
     *
     * @return The unique identifier of the prompt.
     */
    String getId();

    /**
     * Sets the unique identifier of the prompt.
     *
     * @param id The new unique identifier for the prompt.
     */
    void setId(String id);

    /**
     * Retrieves the text of the prompt.
     *
     * @return The text of the prompt.
     */
    String getPrompt();

    /**
     * Sets the text of the prompt.
     *
     * @param prompt The new text for the prompt.
     */
    void setPrompt(String prompt);
}
