package com.example.dogoout.domain.photo;

public interface Photo {
    /**
     * Get the unique identifier of the photo.
     *
     * @return The unique identifier of the photo.
     */
    String getId();

    /**
     * Set the unique identifier of the photo.
     *
     * @param id The unique identifier to set.
     */
    void setId(String id);

    /**
     * Get the name of the photo.
     *
     * @return The name of the photo.
     */
    String getName();

    /**
     * Set the name of the photo.
     *
     * @param name The name to set.
     */
    void setName(String name);

    /**
     * Get the link to the photo.
     *
     * @return The link to the photo.
     */
    String getLink();

    /**
     * Set the link to the photo.
     *
     * @param link The link to set.
     */
    void setLink(String link);

    /**
     * Get the order of appearance of the photo.
     *
     * @return The order of appearance of the photo.
     */
    int getOrderOfAppearance();

    /**
     * Set the order of appearance of the photo.
     *
     * @param orderOfAppearance The order of appearance to set.
     */
    void setOrderOfAppearance(int orderOfAppearance);

    /**
     * Get the unique identifier of the user to whom the photo belongs.
     *
     * @return The unique identifier of the user.
     */
    String getIdUser();

    /**
     * Set the unique identifier of the user to whom the photo belongs.
     *
     * @param idUser The unique identifier of the user to set.
     */
    void setIdUser(String idUser);

    /**
     * Get the unique identifier of the dog to which the photo is associated.
     *
     * @return The unique identifier of the associated dog.
     */
    String getIdDog();

    /**
     * Set the unique identifier of the dog to which the photo is associated.
     *
     * @param idDog The unique identifier of the dog to set.
     */
    void setIdDog(String idDog);
}
