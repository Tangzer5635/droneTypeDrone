package models.daos;

import models.daos.generic.MemoryDao;
import models.entities.DroneTerrestre;
import models.entities.Pilote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PiloteDaoImpl extends MemoryDao<Pilote> implements IPiloteDao{
    @Override
    public List<Pilote> findByName(String name){
        List<Pilote> pilotes = new ArrayList<>();
        for(Pilote pilote: readAll()){
            if (pilote.getNom().equals(name)) {
                pilotes.add(pilote);
            }
        }
        return Collections.unmodifiableList(pilotes);
    }

    @Override
    public Iterable<Pilote> readAll() {
        return Collections.unmodifiableCollection(persist.values());
    }
}
