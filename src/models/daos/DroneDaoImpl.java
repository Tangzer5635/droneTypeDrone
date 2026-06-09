package models.daos;

import models.daos.generic.MemoryDao;
import models.entities.Drone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DroneDaoImpl extends MemoryDao<Drone> implements DroneDao {
    @Override
    public List<Drone> findByName(String name){
        List<Drone> drones = new ArrayList<>();
        for(Drone drone : readAll()){
            if (drone.getNom().equals(name)) {
                drones.add(drone);
            }
        }
        return Collections.unmodifiableList(drones);
    }


}
