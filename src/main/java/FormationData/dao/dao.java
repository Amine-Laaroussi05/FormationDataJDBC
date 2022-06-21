package FormationData.dao;

import java.util.List;

public interface dao<T,PK> {
    List<T> findAll();

    T find(PK id);

    void create(T obj);

    void update(T obj);

    void delete(PK id);
}
