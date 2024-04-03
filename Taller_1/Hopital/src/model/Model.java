package model;

import java.util.List;

public interface Model {
    public List<Object> findAll();
    public Object findById(int id);
    public Object save(Object object);
    public Object update(Object object);
    public boolean delete(int id);
}
