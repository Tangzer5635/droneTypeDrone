package models.entities;

import models.exceptions.EntityException;
import models.references.Grade;

import java.util.Collections;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class Pilote extends AbstractEntity {
    private String nom;
    private Grade grade;
    private String nid;

    private Set<Drone> drones = new HashSet<>();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws EntityException {
        if (nom == null || nom.isBlank()) {
            throw new EntityException("Nom invalide");
        }
        this.nom = nom;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) throws EntityException {
        if (nid.length() != 10) throw new EntityException("Le nid doit faire 10 caractères !");
        this.nid = nid;
    }

    public Set<Drone> getDrones() {
        return Collections.unmodifiableSet(drones);
    }

    public void addDrone(Drone drone) throws EntityException {

        if (drones.contains(drone)) {
            throw new EntityException(
                    "Le pilote possède déjà ce drone."
            );
        }

        if (drone instanceof DroneTerrestre
                && grade.ordinal() < Grade.ADJUDANT_CHEF.ordinal()) {

            throw new EntityException(
                    "Un pilote de grade inférieur à Adjudant-Chef ne peut pas posséder de drone terrestre."
            );
        }

        drones.add(drone);
    }

    public int getTempsVolTotal() {
        return drones.stream()
                .mapToInt(Drone::getTempsUtilisationTotal)
                .sum();
    }

    protected Pilote(String nom, Grade grade, String nid) throws EntityException {
        setNom(nom);
        setGrade(grade);
        setNid(nid);
    }

    @Override
    public String displayable() {
        return """
            [Pilote] %s %s, ayant volé au total %s heure(s)
            """.formatted(
                getGrade(),
                getNom(),
                getTempsVolTotal()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pilote pilote = (Pilote) o;
        return Objects.equals(nid, pilote.nid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nid);
    }


}
