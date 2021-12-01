package business.persistence;

import business.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    private Database database;
    List<Material> allMaterials;

    public MaterialMapper(Database database) {
        this.database = database;
        allMaterials = getAllMaterials();
    }

    public List<Material> getAllMaterials() {
        List<Material> allMats = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM materials";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    String name = rs.getString("name");
                    String dimension = rs.getString("dimension");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int unitSize = rs.getInt("unit_size");
                    int materialType = rs.getInt("material_type_id");

                    Material material = createMaterial(materialId, name, dimension, description, price, unitSize, materialType);

                    allMats.add(material);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMats;
    }

    public List<Wood> getAllWoods() {
        List<Wood> allWoods = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof Wood) {
                allWoods.add((Wood) material);
            }
        }
        return allWoods;
    }

    public List<Fitting> getAllFittings() {
        List<Fitting> allFittings = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof Fitting) {
                allFittings.add((Fitting) material);
            }
        }
        return allFittings;
    }

    public List<Screw> getAllScrews() {
        List<Screw> allScrews = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof Screw) {
                allScrews.add((Screw) material);
            }
        }
        return allScrews;
    }

    public List<RoofFlat> getAllRoofFlats() {
        List<RoofFlat> allRoofFlats = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof RoofFlat) {
                allRoofFlats.add((RoofFlat) material);
            }
        }
        return allRoofFlats;
    }

    public List<RoofRaised> getAllRoofRaiseds() {
        List<RoofRaised> allRoofRaiseds = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof RoofRaised) {
                allRoofRaiseds.add((RoofRaised) material);
            }
        }
        return allRoofRaiseds;
    }

    public List<Cladding> getAllCladdings() {
        List<Cladding> allCladdings = new ArrayList<>();

        for (Material material : allMaterials) {
            if (material instanceof Cladding) {
                allCladdings.add((Cladding) material);
            }
        }
        return allCladdings;
    }

    public Material getSpecificMaterial(int materialId) {
        for (Material material : allMaterials) {
            if (material.getMaterialId() == materialId) {
                return material;
            }
        }
        return null;
    }


    private Material createMaterial(int materialId, String name, String dimension, String description, double price, int unitSize, int materialType) {
        Material material = null;
        switch (materialType) {
            case 1: material = new Wood(materialId, name, dimension, description, price, unitSize); break;
            case 2: material = new Fitting(materialId, name, dimension, description, price, unitSize); break;
            case 3: material = new Screw(materialId, name, dimension, description, price, unitSize); break;
            case 4: material = new RoofFlat(materialId, name, dimension, description, price, unitSize); break;
            case 5: material = new RoofRaised(materialId, name, dimension, description, price, unitSize); break;
            case 6: material = new Cladding(materialId, name, dimension, description, price, unitSize); break;
        }
        return material;
    }
}
