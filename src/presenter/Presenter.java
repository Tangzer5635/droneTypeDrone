package presenter;

import models.entities.*;
import models.exceptions.EntityException;
import models.exceptions.FactoryException;
import models.facades.IModel;
import models.references.GenreDrone;
import models.references.Grade;
import models.references.TypeDrone;
import views.exceptions.ViewException;
import views.facades.IView;
import views.utils.AffichageConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    private IModel model;
    private IView view;

    public Presenter(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    private final int CHOIX_SORTIE = 0;

    private final String TITRE_MENU_PRINCIPAL = "Menu Principal";
    private final List<String> MENU_PRINCIPAL = new ArrayList<>(Arrays.asList(
            "Saisir un drone",
            "Afficher les drones",
            "Saisir un pilote",
            "Afficher les pilotes",
            "Afficher les drones d'un pilotes",
            "Ajouter un drone pour un pilote",
            "Utiliser un drone",
            "Recharger tous les drones d'un pilotes"
    ));
    private final int TAILLE_MENU_PRINCIPAL = MENU_PRINCIPAL.size();

    public void start(){

        int choix;
        do {
            view.afficherMenuPrincipal(MENU_PRINCIPAL, TITRE_MENU_PRINCIPAL);
            choix = view.saisirChoixMenuAvecSortie(TAILLE_MENU_PRINCIPAL);
            gestionMenu(choix);
        } while (choix != CHOIX_SORTIE);
    }

    private void gestionMenu(int choix){
        switch (choix) {
            case 1 -> saisirDrone();
            case 2 -> afficherDrones();
            case 3 -> saisirPilote();
            case 4 -> afficherPilotes();
            case 5 -> afficherLesDronesDuPilote();
            case 6 -> ajouterDroneAUnPilote();
            case 7 -> utiliserUnDrone();
            case 8 -> rechargerTousLesDronesDuPilote();
        }
    }

    //CHOIX 1

    private void saisirDrone(){
        String nom = view.entrerNom();
        TypeDrone typeDrone;
        try {
            typeDrone = view.choisirEnum(TypeDrone.class, TypeDrone::name);
        } catch (ViewException e) {
            view.afficherMessage("Erreur lors du choix du type de drone");
            return;
        }
        int tempsAutonomie = view.choisirAutonomieMax();
        try {
            GenreDrone genreDrone = view.choisirEnum(GenreDrone.class, GenreDrone::name);
            switch (genreDrone) {
                case AERIEN -> {
                    int altitudeMax = view.saisirAltitudeMax();
                    DroneAerien newDrone = FactoryEntities.createDroneAerien(
                            nom,
                            typeDrone,
                            tempsAutonomie,
                            altitudeMax
                    );
                    model.addNewDroneAerien(newDrone);
                }
                case TERRESTRE -> {
                    DroneTerrestre newDrone = FactoryEntities.createDroneTerrestre(
                            nom,
                            typeDrone,
                            tempsAutonomie
                    );
                    model.addNewDroneTerrestre(newDrone);
                }
            }
            view.afficherMessage("Drone bien créé !");
        } catch (ViewException | FactoryException e) {
            view.afficherMessage("Erreur lors de la création du drone");
        }
    }

    //CHOIX 2

    private void afficherDrones(){
        List<Drone> drones = model.getAllDrones();
        view.afficherTousLesDrones(drones);
    }

    //CHOIX 3

    private void saisirPilote() {
        String nom = view.entrerNom();
        Grade grade;
        try {
            grade = view.choisirEnum(Grade.class, Grade::name);
        } catch (ViewException e) {
            view.afficherMessage("Erreur lors du choix du grade");
            return;
        }
        String nid = view.saisirNid();
        try {
            Pilote pilote = FactoryEntities.createPilote(nom, grade, nid);
            model.addNewPilote(pilote);
            view.afficherMessage(pilote.getNom() + " a bien été ajouté !");
        } catch (FactoryException e) {
            view.afficherMessage(e.getMessage());
        }
    }

    //CHOIX 4

    private void afficherPilotes(){
        List<Pilote> pilotes = model.getAllPilotes();
        view.afficherTousLesPilotes(pilotes);
    }

    //CHOIX 5

    private void afficherLesDronesDuPilote() {
        try {
            Pilote piloteChoisi = view.choisirDansListe(
                    model.getAllPilotes(),
                    Pilote::displayable
            );
            piloteChoisi.getDrones().forEach(drone -> view.afficherMessage(drone.toString()));
        } catch (ViewException e) {
            view.afficherMessage(e.getMessage());
        }
    }

    //CHOIX 6

    private void ajouterDroneAUnPilote(){
        try {
            Pilote piloteChoisi = view.choisirDansListe(
                    model.getAllPilotes(),
                    Pilote::displayable
            );
            Drone droneChoisi = view.choisirDansListe(
                    model.getAllDrones(),
                    Drone::displayable
            );

            model.ajouterDroneAUnPilote(piloteChoisi, droneChoisi);
            view.afficherMessage(
                    "Drone " + droneChoisi.getNom()
                            + " a bien été ajouté au pilote " + piloteChoisi.getNom() + " !"
            );
        } catch (ViewException | EntityException e) {
            view.afficherMessage(e.getMessage());
        }
    }

    //CHOIX 7

    private void utiliserUnDrone(){
        try {
            Pilote piloteChoisi = view.choisirDansListe(
                    model.getAllPilotes(),
                    Pilote::displayable
            );

            List<Drone> dronesDuPilote = new ArrayList<>(piloteChoisi.getDrones());
            Drone droneChoisi = view.choisirDansListe(dronesDuPilote, Drone::displayable);

            int heureUtilisation = view.saisirTempsPilotage(droneChoisi.getTempsAutonomieMax());
            model.piloteUtiliseUnDrone(piloteChoisi, droneChoisi, heureUtilisation);

            view.afficherMessage(
                    "Le drone " + droneChoisi.getNom()
                            + " du pilote " + piloteChoisi.getNom()
                            + " vole depuis " + heureUtilisation + " heures !"
            );
        } catch (ViewException | EntityException e) {
            view.afficherMessage(e.getMessage());
        }
    }

    //CHOIX 8

    private void rechargerTousLesDronesDuPilote() {
        try {
            Pilote piloteChoisi = view.choisirDansListe(
                    model.getAllPilotes(),
                    Pilote::displayable
            );
            piloteChoisi.getDrones().forEach(drone -> view.afficherMessage(drone.toString()));
            for (Drone drone : piloteChoisi.getDrones()) {
                try {
                    drone.rechargerBatteries();
                } catch (EntityException e) {
                    view.afficherMessage(e.getMessage());
                }
                view.afficherMessage(drone.getNom() + " rechargé !");
            }
        }
        catch (ViewException e) {
            view.afficherMessage(e.getMessage());
        }
    }


}
