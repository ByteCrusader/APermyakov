package ru.apermyakov.mapping.ad;

import ru.apermyakov.mapping.car.Car;
import ru.apermyakov.mapping.user.User;

public class Ad {

    private int id;

    private String description;

    private Car car;

    private User user;

    private String photoPath;

    private boolean status;

    public Ad() {
    }

    public Ad(String description, boolean status, String photoPath) {
        this.description = description;
        this.status = status;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
