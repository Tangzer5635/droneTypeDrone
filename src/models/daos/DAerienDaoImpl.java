package models.daos;

import models.daos.generic.MemoryDao;
import models.entities.DroneAerien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAerienDaoImpl extends MemoryDao<DroneAerien> implements IDAerienDao {
    @Override
    public List<DroneAerien> findByName(String name){
        List<DroneAerien> drones = new ArrayList<>();
        for(DroneAerien droneA: readAll()){
            if (droneA.getNom().equals(name)) {
                drones.add(droneA);
            }
        }
        return Collections.unmodifiableList(drones);
    }
}
