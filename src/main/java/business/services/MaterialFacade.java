package business.services;

import business.entities.materials.*;
import business.persistence.Database;
import business.mappers.MaterialMapper;

import java.util.List;

public class MaterialFacade {
    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        this.materialMapper = new MaterialMapper(database);
    }

    public List<Material> getAllMaterials() {
        return materialMapper.getAllMaterials();
    }

    public List<Wood> getAllWoods() {
        return materialMapper.getAllWoods();
    }

    public List<Fitting> getAllFittings() {
        return materialMapper.getAllFittings();
    }

    public List<Screw> getAllScrews() {
        return materialMapper.getAllScrews();
    }

    public List<RoofFlat> getAllRoofFlats() {
        return materialMapper.getAllRoofFlats();
    }

    public List<RoofRaised> getAllRoofRaiseds() {
        return materialMapper.getAllRoofRaiseds();
    }

    public List<Cladding> getAllCladdings() {
        return materialMapper.getAllCladdings();
    }

    public Material getSpecificMaterial(int materialId) {
        return materialMapper.getSpecificMaterial(materialId);
    }

}
