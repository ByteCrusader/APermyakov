package ru.apermyakov;

/**
 * Class for initial user.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class User implements Comparable<User> {

    /**
     * Initial user id.
     */
    private int id;

    /**
     * Initial user name.
     */
    private String name;

    /**
     * Initial user city.
     */
    private String city;

    /**
     * Initial user city.
     */
    private String age;

    /**
     * Design User.
     *
     * @param id user id
     * @param name user name
     * @param city user city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Design User.
     *
     * @param age user age
     * @param name user name
     */
    public User(String name, String age) {
        this.age = age;
        this.name = name;
    }

    /**
     * Method for return user id.
     *
     * @return user id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method for override compare to.
     *
     * @param o compare object
     * @return int
     */
    @Override
    public int compareTo(User o) {
        int compareAge = this.age.compareTo(o.age);
        int compareName = this.name.compareTo(o.name);
        return compareAge != 0 ? compareAge : compareName;
    }

    /**
     * Method for override to string.
     *
     * @return needed string
     */
    @Override
    public String toString() {
        return "User{"
                + ", name='" + name + '\''
                + ", age='" + age + '\''
                + '}';
    }
}
