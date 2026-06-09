package views.facades;

import models.entities.AbstractEntity;
import models.entities.Drone;
import models.entities.Pilote;
import models.references.GenreDrone;
import views.exceptions.ViewException;

import java.util.List;
import java.util.function.Function;

public interface IView {

    void afficherMessage(String message);
    void afficherMenuPrincipal(List<String> menu, String titre);
    void afficherMenuEncadreAvecRetour(List<String> menu, String titre);
    void afficherMenuSimpleAvecRetour(List<String> choix, String nomElement);

    int saisirChoixMenuAvecSortie(int tailleMenu);
    <T extends AbstractEntity> void afficherListe(List<T> maListe, Function<T, String> affichable) throws ViewException;
    <T extends AbstractEntity> T choisirDansListe(List<T> maListe, Function<T, String> affichable) throws ViewException;
    <T extends Enum<T>> T choisirEnum(Class<T> monEnum, Function<T, String> affichable) throws ViewException;

    String entrerNom();

    int choisirAutonomieMax();
    int saisirAltitudeMax();

    void afficherTousLesDrones(List<Drone> drones);

    void afficherTousLesPilotes(List<Pilote> pilotes);

    String saisirNid();

    int saisirChoixListe(int tailleListe);

    int saisirTempsPilotage(int tempsMax);
}
