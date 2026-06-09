package models.daos;

import models.daos.generic.Dao;
import models.entities.Pilote;

import java.util.List;

public interface IPiloteDao extends Dao<Pilote> {
    List<Pilote> findByName(String name);
}
