package model;

import entity.Flight;

import java.util.List;

public class FlightModel extends BaseModel{
    public FlightModel() {
        super(new Flight());
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public Object save(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
