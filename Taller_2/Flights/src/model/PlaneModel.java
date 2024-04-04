package model;

import entity.Plane;

public class PlaneModel extends BaseModel
{
    public PlaneModel() {
        super(Plane.class);
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
