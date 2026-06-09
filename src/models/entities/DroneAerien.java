package models.entities;

import models.exceptions.EntityException;
import models.references.TypeDrone;

import java.time.LocalDate;
import java.time.Period;

public class DroneAerien extends Drone{
    private int altitudeMax;

    public int getAltitudeMax() {
        return altitudeMax;
    }

    public void setAltitudeMax(int altitudeMax) throws EntityException {
        if (altitudeMax < 1 || altitudeMax > 120) throw new EntityException("Altitude Max doit etre entre 1 et 120 mètre(s)");
        this.altitudeMax = altitudeMax;
    }

    protected DroneAerien(String nom, TypeDrone type, int tempsAutonomieMax, int altitudeMax) throws EntityException {
        super(nom, type, tempsAutonomieMax);
        this.altitudeMax = altitudeMax;
    }

    @Override
    public String displayable() {
        return """
                [Drone Aérien] %s de type %s avec une autonomie maximale de %s h
                """.formatted(getNom(), getType(), getTempsAutonomieMax());
    }

    @Override
    public int tempsRestantMiseEnService() {
        return 2 - Period.between(
                getDateMiseEnService(),
                LocalDate.now()
        ).getYears();
    }

    @Override
    public String toString() {
        return "[Drone Aérien] %s %s vole actuellement depuis %sh et sa batterie est pleine ? %s"
                .formatted(getNom(), getType(), getTempsUtilisationActuel(), batteriePleine());
    }
}
