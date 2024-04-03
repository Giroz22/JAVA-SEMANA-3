package model;

import java.util.List;

public interface CRUD {
    public List<Object> findAll();
    public Object findById(int id);
    public Object save(Object obj);
    public Object update(Object obj);
    public boolean delete(int id);
}
