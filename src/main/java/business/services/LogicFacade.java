package business.services;

import business.persistence.Database;
import business.persistence.StandardLengthMapper;

import java.util.List;

public class LogicFacade {
    StandardLengthMapper standardLengthMapper;

    public LogicFacade(Database database) {
        this.standardLengthMapper = new StandardLengthMapper(database);
    }

    public List<Integer> getStandardLengths() {
        return standardLengthMapper.getStandardLengths();
    }

    public List<Integer> getCarportLengths(){
        return standardLengthMapper.getCarportLengths();
    }
}
