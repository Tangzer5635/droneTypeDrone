package views.facades;

import models.entities.AbstractEntity;
import models.entities.Drone;
import models.entities.Pilote;
import org.w3c.dom.ls.LSOutput;
import views.exceptions.ViewException;
import views.utils.AffichageConsole;
import views.utils.LectureConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ViewConsoleImpl implements IView{

    @Override
    public void afficherMessage(String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }

    @Override
    public void afficherMenuPrincipal(List<String> menu, String titre) {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(menu, titre, "Quitter");
    }

    @Override
    public void afficherMenuEncadreAvecRetour(List<String> menu, String titre) {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(menu, titre, "Retour");
    }

    @Override
    public void afficherMenuSimpleAvecRetour(List<String> choix, String nomElements) {
        AffichageConsole.afficherMessageAvecSautLigne("Liste des " + nomElements + " : ");
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(choix, "Retour");
    }

    @Override
    public int saisirChoixMenuAvecSortie(int tailleMenu) {
        return LectureConsole.lectureChoixInt(0, tailleMenu);
    }

    @Override
    public <T extends AbstractEntity> void afficherListe(List<T> maListe, Function<T, String> affichable) throws ViewException {

        if (maListe.isEmpty()) {
            throw new ViewException("La liste est vide");
        } else {

            //affichage de l'entête
            AffichageConsole.afficherMessageAvecSautLigne("Liste des " + maListe.getFirst().getClass().getSimpleName() + "s : ");

            //affichage de chaque élément de la liste
            for (T element : maListe) {
                AffichageConsole.afficherMessageAvecSautLigne(affichable.apply(element));
            }
        }
    }

    @Override
    public <T extends AbstractEntity> T choisirDansListe(List<T> maListe, Function<T, String> affichable) throws ViewException {

        if (maListe.isEmpty()) {
            throw new ViewException("La liste est vide");
        } else {

            //création de la liste affichable
            List<String> listeAffichable = new ArrayList<>();
            for (T element : maListe) {
                listeAffichable.add(affichable.apply(element));
            }

            //affichage de la liste avec entête
            AffichageConsole.afficherMessageAvecSautLigne("Liste des " + maListe.getFirst().getClass().getSimpleName() + "s : ");
            AffichageConsole.afficherMenuSimple(listeAffichable);

            //choix dans la liste
            int choix = LectureConsole.lectureChoixInt(1, maListe.size());
            return maListe.get(choix - 1);
        }
    }

    @Override
    public <T extends Enum<T>> T choisirEnum(Class<T> monEnum, Function<T, String> affichable) throws ViewException {

        if (monEnum.getEnumConstants().length == 0) {
            throw new ViewException("Aucun élément dans l'Enum");
        } else {

            //création de la liste affichable
            List<String> listeAffichable = new ArrayList<>();
            for (T element : monEnum.getEnumConstants()) {
                listeAffichable.add(affichable.apply(element));
            }

            //affichage de la liste avec entête
            AffichageConsole.afficherMessageAvecSautLigne("Liste des " + monEnum.getSimpleName() + "s : ");
            AffichageConsole.afficherMenuSimple(listeAffichable);

            //choix dans l'Enum
            int choix = LectureConsole.lectureChoixInt(1, monEnum.getEnumConstants().length);
            return monEnum.getEnumConstants()[choix-1];
        }
    }

    @Override
    public String entrerNom() {
        return LectureConsole.lectureChaineCaracteres("Saisir un nom : ");
    }

    @Override
    public int choisirAutonomieMax() {
        AffichageConsole.afficherMessageSansSautLigne("Temps d'autonomie Max en heure (1-20h) :");
        return LectureConsole.lectureChoixInt(1,20);
    }

    @Override
    public int saisirAltitudeMax() {
        AffichageConsole.afficherMessageSansSautLigne("Altitude Max en mètre (1-120m) :");
        return LectureConsole.lectureChoixInt(1, 120);
    }

    @Override
    public void afficherTousLesDrones(List<Drone> drones){
        AffichageConsole.afficherMessageAvecSautLigne("Nombre de drones : " + drones.size());

        int index = 1;
        for (Drone drone : drones) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%d - %s", index, drone.displayable()));
            index++;
        }
    }

    @Override
    public void afficherTousLesPilotes(List<Pilote> pilotes){
        AffichageConsole.afficherMessageAvecSautLigne("Nombre de pilotes : " + pilotes.size());

        int index = 1;
        for (Pilote pilote : pilotes) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%d - %s", index, pilote.displayable()));
            index++;
        }
    }

    @Override
    public String saisirNid() {
        return LectureConsole.lectureChaineCaracteres("Saisir le nid (10 caractère max) : ");
    }

    @Override
    public int saisirChoixListe(int tailleListe) {
        return LectureConsole.lectureChoixInt(1, tailleListe);
    }

    @Override
    public int saisirTempsPilotage(int tempsMax) {
        AffichageConsole.afficherMessageSansSautLigne("Temps de pilotage en heure (1-"+tempsMax+"h) : ");
        return LectureConsole.lectureChoixInt(1, tempsMax);
    }
}
