package ru.apermyakov.JDBC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JavaBean class for modulate items to first xml file.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.11.2017
 */
@XmlType(propOrder = "field")
public class FieldEntry {

    /**
     * Field for field.
     */
    @XmlElement
    private String field;

    /**
     * Method for get field.
     *
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * Method for set field.
     *
     * @param field field
     */
    public void setField(int field) {
        this.field = field + " field";
    }

    /**
     * Override equals.
     *
     * @param o object
     * @return ><=
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldEntry that = (FieldEntry) o;

        return field != null ? field.equals(that.field) : that.field == null;
    }

    /**
     * Override hash code.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return field != null ? field.hashCode() : 0;
    }

    /**
     * Override to string.
     *
     * @return to string
     */
    @Override
    public String toString() {
        return "FieldEntry{" +
                "field='" + field + '\'' +
                '}';
    }
}
