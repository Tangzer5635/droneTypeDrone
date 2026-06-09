package models.entities;

import models.exceptions.EntityException;
import models.references.TypeDrone;

import java.time.LocalDate;
import java.time.Period;

public class DroneTerrestre extends Drone{

    protected DroneTerrestre(String nom, TypeDrone type, int tempsAutonomieMax) throws EntityException {
        super(nom, type, tempsAutonomieMax);
    }

    @Override
    public int tempsRestantMiseEnService() {
        return 5 - Period.between(
                getDateMiseEnService(),
                LocalDate.now()
        ).getYears();
    }

    @Override
    public String displayable() {
        return """
                [Drone Terrestre] %s de type %s avec une autonomie maximale de %s h
                """.formatted(getNom(), getType(), getTempsAutonomieMax());
    }


    @Override
    public String toString() {
        return "[Drone Terrestre] %s %s vole actuellement depuis %sh et sa batterie est pleine ? %s"
                .formatted(getNom(), getType(), getTempsUtilisationActuel(), batteriePleine());    }

}
