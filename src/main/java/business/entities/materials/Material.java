package business.entities.materials;

public abstract class Material {
    private int materialId;
    private String name;
    private String dimension;
    private String description;
    private double price;
    private int unitSize;

    public Material(int materialId, String name, String dimension, String description, double price, int unitSize) {
        this.materialId = materialId;
        this.name = name;
        this.dimension = dimension;
        this.description = description;
        this.price = price;
        this.unitSize = unitSize;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }
}
