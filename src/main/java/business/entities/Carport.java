package business.entities;

import business.entities.materials.Material;

public class Carport {
    private int carportId;
    private int width;
    private int length;
    private int height;
    private Shed shed;
    private Roof roof;

    public Carport(int width, int length, Shed shed, Roof roof) {
        this.width = width;
        this.length = length;
        this.shed = shed;
        this.roof = roof;
    }

    public Carport(int width, int length, int height, Shed shed, Roof roof) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.shed = shed;
        this.roof = roof;
    }

    public Carport(int carportId, int width, int length, int height, Shed shed, Roof roof) {
        this.carportId = carportId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.shed = shed;
        this.roof = roof;
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

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }
}
