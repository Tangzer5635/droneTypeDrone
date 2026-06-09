package models.daos;

import models.daos.generic.MemoryDao;
import models.entities.DroneTerrestre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTerrestreDaoImpl extends MemoryDao<DroneTerrestre> implements IDTerrestreDao {
    @Override
    public List<DroneTerrestre> findByName(String name){
        List<DroneTerrestre> drones = new ArrayList<>();
        for(DroneTerrestre droneT: readAll()){
            if (droneT.getNom().equals(name)) {
                drones.add(droneT);
            }
        }
        return Collections.unmodifiableList(drones);
    }
}
