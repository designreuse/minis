package net.minis.api.spring.data;

import java.io.Serializable;

/**
 * Base Data Transfer Object (DTO).<br>
 * 
 * An DTO need implement methods: equals(), hashCode(), toString() .
 * 
 * @author yen.
 */
public interface BaseModel extends Serializable {

    int hashCode();

    boolean equals(Object object);

    String toString();

}
