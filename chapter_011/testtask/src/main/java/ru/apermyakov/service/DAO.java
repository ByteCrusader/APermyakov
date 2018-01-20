package ru.apermyakov.service;

import java.util.List;

public interface DAO<K> {

    K create(K insert);

    List<K> findAll();
}
