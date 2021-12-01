package business.entities;

import business.entities.materials.Material;

public class Carport {
    private int carportId;
    private int width;
    private int length;
    private int height;
    private Shed shed;
    private Material roof;
    private int roofSlope;

    public Carport(int width, int length, Shed shed, Material roof, int roofSlope) {
        this.width = width;
        this.length = length;
        this.shed = shed;
        this.roof = roof;
        this.roofSlope = roofSlope;
    }

    public int getCarportId() {
        return carportId;
    }

    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    public Material getRoof() {
        return roof;
    }

    public void setRoof(Material roof) {
        this.roof = roof;
    }

    public int getRoofSlope() {
        return roofSlope;
    }

    public void setRoofSlope(int roofSlope) {
        this.roofSlope = roofSlope;
    }
}
