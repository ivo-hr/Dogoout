package com.example.dogoout.domain.photo;

import java.io.Serializable;

/**
 * The `Photo` class represents a photo associated with a user and a dog in the application.
 */
public class PhotoImpl implements Photo, Serializable {

    // Attributes
    private String id;
    private String name;
    private String link;
    private int orderOfAppearance;
    private String idUser;
    private String idDog;

    /**
     * Default constructor for creating a new Photo object.
     */
    public PhotoImpl() {}

    /**
     * Constructor for creating a new Photo object with specified attributes.
     *
     * @param id The unique identifier of the photo.
     * @param name The name of the photo.
     * @param link The link to the photo.
     * @param orderOfAppearance The order of appearance of the photo.
     * @param idUser The unique identifier of the user to whom the photo belongs.
     * @param idDog The unique identifier of the dog to which the photo is associated.
     */
    public PhotoImpl(String id, String name, String link, int orderOfAppearance, String idUser, String idDog) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.orderOfAppearance = orderOfAppearance;
        this.idUser = idUser;
        this.idDog = idDog;
    }

    /**
     * Get the unique identifier of the photo.
     *
     * @return The unique identifier of the photo.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Set the unique identifier of the photo.
     *
     * @param id The unique identifier to set.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the photo.
     *
     * @return The name of the photo.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the photo.
     *
     * @param name The name to set.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the link to the photo.
     *
     * @return The link to the photo.
     */
    @Override
    public String getLink() {
        return link;
    }

    /**
     * Set the link to the photo.
     *
     * @param link The link to set.
     */
    @Override
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get the order of appearance of the photo.
     *
     * @return The order of appearance of the photo.
     */
    @Override
    public int getOrderOfAppearance() {
        return orderOfAppearance;
    }

    /**
     * Set the order of appearance of the photo.
     *
     * @param orderOfAppearance The order of appearance to set.
     */
    @Override
    public void setOrderOfAppearance(int orderOfAppearance) {
        this.orderOfAppearance = orderOfAppearance;
    }

    /**
     * Get the unique identifier of the user to whom the photo belongs.
     *
     * @return The unique identifier of the user.
     */
    @Override
    public String getIdUser() {
        return idUser;
    }

    /**
     * Set the unique identifier of the user to whom the photo belongs.
     *
     * @param idUser The unique identifier of the user to set.
     */
    @Override
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Get the unique identifier of the dog to which the photo is associated.
     *
     * @return The unique identifier of the associated dog.
     */
    @Override
    public String getIdDog() {
        return idDog;
    }

    /**
     * Set the unique identifier of the dog to which the photo is associated.
     *
     * @param idDog The unique identifier of the dog to set.
     */
    @Override
    public void setIdDog(String idDog) {
        this.idDog = idDog;
    }
}
