package models.daos;

import models.daos.generic.Dao;
import models.entities.DroneAerien;
import models.entities.DroneTerrestre;

import java.util.List;

public interface IDAerienDao extends Dao<DroneAerien> {
    List<DroneAerien> findByName(String name);

}
