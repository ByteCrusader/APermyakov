package ru.apermyakov.servlets;

import java.util.List;

/**
 * JavaBean for data base user.
 */
public class TransferObject {

    /**
     * Field for user's id.
     */
    private int id;

    /**
     * Field for user's name.
     */
    private String name;

    /**
     * Field for user's email.
     */
    private String email;

    /**
     * Field for user's login.
     */
    private String login;

    /**
     * Field for user's password.
     */
    private String password;

    /**
     * Field for user's role.
     */
    private String role;

    /**
     * Field for user's country.
     */
    private String country;

    /**
     * Field for user's city.
     */
    private String city;

    /**
     * Field for user's create date.
     */
    private String createDate;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private List<String> musicType;

    /**
     * Method for get user's id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Method for set user's id.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for get user's name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Method for set user's name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for get user's email.
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method for set user's email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method for get user's login.
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Method for set user's login.
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Method for get user's password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for set user's password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for get user's role.
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * Method for set user's role.
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method for get user's country.
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method for set user's country.
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Method for get user's city.
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Method for set user's city.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Method for get user's create date.
     *
     * @return
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Method for set user's create date.
     *
     * @param createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return
     */
    public List<String> getMusicType() {
        return musicType;
    }

    /**
     * @param musicType
     */
    public void setMusicType(List<String> musicType) {
        this.musicType = musicType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferObject user = (TransferObject) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (createDate != null ? !createDate.equals(user.createDate) : user.createDate != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        return musicType != null ? musicType.equals(user.musicType) : user.musicType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (musicType != null ? musicType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransferObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", createDate='" + createDate + '\'' +
                ", address='" + address + '\'' +
                ", musicType=" + musicType +
                '}';
    }
}
