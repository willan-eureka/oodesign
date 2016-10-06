package com.design.model;

import java.util.Date;

/**
 * The class represent a entity and contains all methods
 * that a stored entity must have.
 */

public interface Entity {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    Date getDate();
    void setDate(Date date);
}
