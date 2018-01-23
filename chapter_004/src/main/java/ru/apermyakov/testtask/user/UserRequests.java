package ru.apermyakov.testtask.user;

import ru.apermyakov.testtask.requests.Request;

public interface UserRequests {

    void setPequests(Request request);

    Request getRequests();
}
