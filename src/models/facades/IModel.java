package models.facades;

import models.entities.Drone;
import models.entities.DroneAerien;
import models.entities.DroneTerrestre;
import models.entities.Pilote;
import models.exceptions.EntityException;

import java.util.List;

public interface IModel {
    void addNewDroneAerien(DroneAerien droneAerien);
    void addNewDroneTerrestre(DroneTerrestre droneTerrestre);
    List<Drone> getAllDrones();

    List<Pilote> getAllPilotes();

    void addNewPilote(Pilote pilote);

    Pilote recupererUnPilote();

    void ajouterDroneAUnPilote(Pilote pilote, Drone drone) throws EntityException;

    void piloteUtiliseUnDrone(Pilote piloteChoisi,Drone droneChoisi,int heureUtilisation) throws EntityException;

}
