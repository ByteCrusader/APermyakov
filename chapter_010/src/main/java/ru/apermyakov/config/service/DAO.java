package ru.apermyakov.config.service;

import java.util.List;

public interface DAO<T> {

    void create(T item);

    List<T> findAll();
}
