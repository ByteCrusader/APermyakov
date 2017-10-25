package ru.apermyakov.test;

/**
 * Class for initial bank map of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class User {

    /**
     * Field for name of user.
     */
    private String name;

    /**
     * Field for passport of user.
     */
    private Passport passport;

    /**
     * Design user.
     *
     * @param name user name
     * @param passport user passport
     */
    public User(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Override equals.
     *
     * @param o object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    /**
     * Override hashcode.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
