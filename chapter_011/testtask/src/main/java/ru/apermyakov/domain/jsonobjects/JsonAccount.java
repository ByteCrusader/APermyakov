package ru.apermyakov.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class JsonAccount {

    private boolean success;

    private String description;

    private String password;

    @JsonGetter
    public boolean getSuccess() {
        return success;
    }

    @JsonSetter
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }
}
