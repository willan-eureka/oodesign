package com.design.model;

import java.io.Serializable;

/**
 * This abstract class allow define and force to its inherits class
 * to implement basic and important methods.
 * This a good practice implement is methods.
 */

public abstract class BaseModel implements Serializable {
    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
}
