package ru.apermyakov.gc.user;

/**
 * Class for modulate user.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class CollectorUser implements User {

    /**
     * Field for age.
     */
    private int age;

    /**
     * Field for name.
     */
    private String name;

    /**
     * Field for sex.
     */
    private String sex;

    /**
     * Constructor.
     *
     * @param name name.
     * @param sex sex.
     */
    public CollectorUser(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    /**
     * Method for ser age.
     *
     * @param age age.
     */
    @Override
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method for get age.
     *
     * @return age.
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * Method for get name.
     *
     * @return name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method for get user sex.
     *
     * @return sex.
     */
    @Override
    public String getSex() {
        return this.sex;
    }

    /**
     * Override finalize to catch gc work.
     *
     * @throws Throwable exception.
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.format("Finalize %s%s", this.getName(), System.lineSeparator());
    }
}
