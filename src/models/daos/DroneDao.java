package models.daos;

import models.daos.generic.Dao;
import models.entities.Drone;

import java.util.List;

public interface DroneDao extends Dao<Drone> {
    List<Drone> findByName(String name);
}
