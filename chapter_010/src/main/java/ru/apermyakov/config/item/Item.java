package ru.apermyakov.config.item;

/**
 * Class for modulate Item.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class Item {

    /**
     * Field for id.
     */
    private int id;

    /**
     * Field for desc.
     */
    private String description;

    /**
     * Field for created.
     */
    private String created;

    /**
     * Field for done.
     */
    private boolean done;

    /**
     * Get id.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get desc.
     *
     * @return desc.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set desc.
     *
     * @param description desc.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get created.
     *
     * @return created.
     */
    public String getCreated() {
        return created;
    }

    /**
     * Set created.
     *
     * @param created created.
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * Is done.
     *
     * @return is done.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set done.
     *
     * @param done done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
