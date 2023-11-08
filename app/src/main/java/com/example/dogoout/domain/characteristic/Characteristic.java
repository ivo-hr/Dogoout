package com.example.dogoout.domain.characteristic;

public interface Characteristic {
    /**
     * Retrieves the name for the characteristic.
     *
     * @return The name for the characteristic.
     */
    String getId();

    /**
     * Sets the name for the characteristic.
     *
     * @param id The new name for the characteristic.
     */
    void setId(String id);

    /**
     * Retrieves the name of the characteristic.
     *
     * @return The name of the characteristic.
     */
    String getCharacteristic();

    /**
     * Sets the name of the characteristic.
     *
     * @param characteristic The new name for the characteristic.
     */
    void setCharacteristic(String characteristic);
}
