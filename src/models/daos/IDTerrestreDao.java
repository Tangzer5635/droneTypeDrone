package models.daos;

import models.daos.generic.Dao;
import models.entities.DroneTerrestre;

import java.util.List;

public interface IDTerrestreDao extends Dao<DroneTerrestre> {
    List<DroneTerrestre> findByName(String name);

}
