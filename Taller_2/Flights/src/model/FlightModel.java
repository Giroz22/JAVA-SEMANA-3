package model;

import entity.Flight;

import java.util.List;

public class FlightModel extends BaseModel{
    public FlightModel() {
        super(Flight.class);
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
