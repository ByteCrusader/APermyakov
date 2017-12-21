package ru.apermyakov.maprealize;

import java.util.Calendar;

/**
 * Class for check equals and hashCode.
 *
 * @author apermyakov
 * @version 1.0
 * @since 08.11.2017
 */
public class User {

    /**
     * Field for user name.
     */
    private String name;

    /**
     * Field for number of user's children.
     */
    private int children;

    /**
     * Field for user's birthday.
     */
    private Calendar birthday;


    /**
	 * Design user.
	 *
     * @param name user name
     * @param children user's children number
     * @param birthday user's birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
	 * Method for override equals by name, birthday and children.
	 *
     * @param o equals object
     * @return equal or not
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

        if (children != user.children) {
			return false;
		}
        if (name != null ? !name.equals(user.name) : user.name != null) {
			return false;
		}
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }

    /**
	 * Method for override hashCode by name, birthday and children.
	 *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
