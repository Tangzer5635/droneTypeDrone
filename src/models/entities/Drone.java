package models.entities;

import models.exceptions.EntityException;
import models.references.TypeDrone;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Drone extends AbstractEntity {
    private String nom;
    private TypeDrone type;
    private int tempsUtilisationTotal;
    private int tempsUtilisationActuel;
    private int tempsAutonomieMax;
    private LocalDate dateMiseEnService;
    private int limiliteMiseEnService;



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws EntityException {
        if (nom == null || nom.isBlank()) {
            throw new EntityException("Le nom ne peut pas être vide");
        }
        this.nom = nom;
    }

    public TypeDrone getType() {
        return type;
    }

    public void setType(TypeDrone type) {
        this.type = type;
    }

    public int getTempsUtilisationTotal() {
        return tempsUtilisationTotal;
    }

    public void setTempsUtilisationTotal(int tempsUtilisationTotal) {
        this.tempsUtilisationTotal = tempsUtilisationTotal;
    }

    public int getTempsUtilisationActuel() {
        return tempsUtilisationActuel;
    }

    public void setTempsUtilisationActuel(int tempsUtilisationActuel) throws EntityException {
        if (tempsUtilisationActuel > getTempsAutonomieMax()) {
            throw new EntityException("Le temps d'utilisation actuel ne peut pas être supérieur à l'autonomie max du drone");
        }
        this.tempsUtilisationActuel = tempsUtilisationActuel;
    }

    public int getTempsAutonomieMax() {
        return tempsAutonomieMax;
    }

    public void setTempsAutonomieMax(int tempsAutonomieMax) {
        this.tempsAutonomieMax = tempsAutonomieMax;
    }

    public LocalDate getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(LocalDate dateMiseEnService) throws EntityException {
        if (dateMiseEnService == null) {
            throw new EntityException("La date de mise en service ne peut pas être nulle");
        }
        if (dateMiseEnService.isAfter(LocalDate.now())) {
            throw new EntityException("La date de mise en service ne peut pas être après aujourd'hui");
        }
        this.dateMiseEnService = dateMiseEnService;
    }

    public int getLimiliteMiseEnService() {
        return limiliteMiseEnService;
    }

    public void setLimiliteMiseEnService(int limiliteMiseEnService) {
        this.limiliteMiseEnService = limiliteMiseEnService;
    }

    public abstract int tempsRestantMiseEnService();

    public boolean batterieVide() {
        return getTempsUtilisationActuel() >= getTempsAutonomieMax();
    }

    public boolean batteriePleine() {
        return !batterieVide();
    }

    public void rechargerBatteries() throws EntityException {
        setTempsUtilisationActuel(0);
    }

    public Drone(String nom, TypeDrone type, int tempsAutonomieMax) throws EntityException {
        setNom(nom);
        setType(type);
        setTempsAutonomieMax(tempsAutonomieMax);
        setDateMiseEnService(LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drone drone = (Drone) o;
        return Objects.equals(nom, drone.nom) && type == drone.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nom, type);
    }

}
