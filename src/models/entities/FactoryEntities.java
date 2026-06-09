package models.entities;

import models.exceptions.EntityException;
import models.exceptions.FactoryException;
import models.references.Grade;
import models.references.TypeDrone;

public final class FactoryEntities {

    private FactoryEntities(){}

    public static DroneAerien createDroneAerien(String nom, TypeDrone typeDrone, int tempsAutonomieMax, int altitudeMax) throws FactoryException {
        try {
            return new DroneAerien(nom, typeDrone, tempsAutonomieMax, altitudeMax);
        }
        catch (EntityException e){
            throw new FactoryException("[Erreur création Drone Aérien] " + e.getMessage());
        }
    }

    public static DroneTerrestre createDroneTerrestre(String nom, TypeDrone typeDrone, int tempsAutnomieMax) throws FactoryException {
        try {
            return new DroneTerrestre(nom, typeDrone, tempsAutnomieMax);
        }
        catch (EntityException e){
            throw new FactoryException("[Erreur création Drone Terrestre] " + e.getMessage());
        }
    }

    public static Pilote createPilote(String nom, Grade grade, String nid) throws FactoryException {
        try {
            return new Pilote(nom, grade, nid);
        }
        catch (EntityException e){
            throw new FactoryException("[Erreur création Pilote] " + e.getMessage());
        }
    }
}
