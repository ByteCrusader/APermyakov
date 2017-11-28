package ru.apermyakov.JDBC;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean class for modulate items to first xml.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.11.2017
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "entries")
@XmlType(propOrder = "entry")
public class Entries {

    /**
     * Field for entry.
     */
    @XmlElement
    private List<FieldEntry> entry = new ArrayList<>();

    /**
     * Method for get entry.
     *
     * @return entry
     */
    public List<FieldEntry> getEntry() {
        return entry;
    }

    /**
     * Method for set entry.
     *
     * @param entry entry
     */
    public void setEntry(List<FieldEntry> entry) {
        this.entry = entry;
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

        Entries entries = (Entries) o;

        return entry != null ? entry.equals(entries.entry) : entries.entry == null;
    }

    /**
     * Override hash code.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return entry != null ? entry.hashCode() : 0;
    }

    /**
     * Override to string.
     *
     * @return to string
     */
    @Override
    public String toString() {
        return "Entries{" +
                "entry=" + entry +
                '}';
    }
}
