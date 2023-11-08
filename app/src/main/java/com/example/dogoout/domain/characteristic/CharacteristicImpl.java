package com.example.dogoout.domain.characteristic;

import java.io.Serializable;

/**
 * The `Characteristic` class represents a specific characteristic or trait.
 */
public class CharacteristicImpl implements Characteristic, Serializable {

    private String id;
    private String characteristic;

    /**
     * Constructs a new `Characteristic` with default values.
     */
    public CharacteristicImpl() {}

    /**
     * Constructs a new `Characteristic` with the given attributes.
     *
     * @param id The unique identifier for the characteristic.
     * @param characteristic The name of the characteristic.
     */
    public CharacteristicImpl(String id, String characteristic) {
        this.id = id;
        this.characteristic = characteristic;
    }

    /**
     * Retrieves the name for the characteristic.
     *
     * @return The name for the characteristic.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the name for the characteristic.
     *
     * @param id The new name for the characteristic.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the characteristic.
     *
     * @return The name of the characteristic.
     */
    @Override
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * Sets the name of the characteristic.
     *
     * @param characteristic The new name for the characteristic.
     */
    @Override
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}
