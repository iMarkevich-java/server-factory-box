package com.markevich.factorybox.dao.daointerface;

import java.util.List;

public interface Dao<T> {

    List<T> loadAll();

    T loadById(String id);

    void save(T t);

    void update(T t);

    void delete(String id);
}
