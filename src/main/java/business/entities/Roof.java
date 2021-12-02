package business.entities;

import business.entities.materials.Material;

public class Roof {
    private int roofId;
    private Material roofMaterial;
    private int slope;

    public Roof(Material roofMaterial, int slope) {
        this.roofMaterial = roofMaterial;
        this.slope = slope;
    }

    public Roof(int roofId, Material roofMaterial, int slope) {
        this.roofId = roofId;
        this.roofMaterial = roofMaterial;
        this.slope = slope;
    }

    public int getRoofId() {
        return roofId;
    }

    public void setRoofId(int roofId) {
        this.roofId = roofId;
    }

    public Material getRoofMaterial() {
        return roofMaterial;
    }

    public void setRoofMaterial(Material roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    public int getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }
}
