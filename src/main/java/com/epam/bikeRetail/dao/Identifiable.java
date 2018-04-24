package com.epam.bikeRetail.dao;

import java.io.Serializable;

/**
 *
 * @author Stepan Menchytsky
 * Interface that identify objects by Long value.
 */
public interface Identifiable extends Serializable {

    /**
     * Return identification of object
     */
    int getId();

    /**
     * Set identification of object
     */
    void setId(int key);

}
