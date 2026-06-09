package models.facades;

import models.daos.*;
import models.entities.*;
import models.exceptions.EntityException;
import models.exceptions.FactoryException;
import models.references.Grade;
import models.references.TypeDrone;
import views.utils.AffichageConsole;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements IModel{

    IDAerienDao aerienDao = DaoFactory.getAerienDao();
    IDTerrestreDao terrienDao = DaoFactory.getTerrestreDao();
    IPiloteDao piloteDao = DaoFactory.getPiloteDao();


    public ModelImpl(){
        init();
    }

    private void init(){
        try {
            DroneAerien dA1 = FactoryEntities.createDroneAerien("DJI Combat V1", TypeDrone.COMBAT, 12, 50);
            DroneTerrestre dT1 = FactoryEntities.createDroneTerrestre("COSMO Soutien V4", TypeDrone.SOUTIEN, 10);

            addNewDroneAerien(dA1);
            addNewDroneTerrestre(dT1);

            Pilote p1 = FactoryEntities.createPilote("Le Buhé", Grade.SERGENT, "1234567890");

            addNewPilote(p1);
            p1.addDrone(dA1);
        }catch (FactoryException | EntityException e){
            AffichageConsole.afficherMessageAvecSautLigne(("[Erreur init] " + e.getMessage()).replaceAll("'", ""));
        }

    }


    @Override
    public void addNewDroneAerien(DroneAerien droneAerien) {
        aerienDao.create(droneAerien);
    }

    @Override
    public void addNewDroneTerrestre(DroneTerrestre droneTerrestre) {
        terrienDao.create(droneTerrestre);
    }

    @Override
    public List<Drone> getAllDrones() {
        List<Drone> drones = new ArrayList<>();
        for (DroneTerrestre d : terrienDao.readAll()) {
            drones.add(d);
        }
        for (DroneAerien d : aerienDao.readAll()) {
            drones.add(d);
        }
        return drones;
    }

    @Override
    public List<Pilote> getAllPilotes() {
        List<Pilote> pilotes = new ArrayList<>();
        for (Pilote p : piloteDao.readAll()) {
            pilotes.add(p);
        }
        return pilotes;
    }

    @Override
    public void addNewPilote(Pilote pilote) {
        piloteDao.create(pilote);
    }

    @Override
    public Pilote recupererUnPilote() {
        return piloteDao.readAll().iterator().next();
    }

    @Override
    public void ajouterDroneAUnPilote(Pilote pilote, Drone drone) throws EntityException {
        pilote.addDrone(drone);
    }

    @Override
    public void piloteUtiliseUnDrone(Pilote piloteChoisi, Drone drone,int heureUtilisation) throws EntityException {
        if (drone.getTempsUtilisationActuel() + heureUtilisation
                > drone.getTempsAutonomieMax()) {
            throw new EntityException(
                    "Batterie insuffisante pour cette utilisation."
            );
        }

        drone.setTempsUtilisationActuel(drone.getTempsUtilisationActuel() + heureUtilisation);
        drone.setTempsUtilisationTotal(drone.getTempsUtilisationTotal() + heureUtilisation);

        piloteDao.update(piloteChoisi);
    }
}
