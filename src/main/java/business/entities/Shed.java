package business.entities;

import business.entities.materials.Material;

public class Shed {
    private int shedId;
    private Material cladding;
    private String placement;
    private int width;
    private int length;

    public Shed(Material cladding, String placement, int width, int length) {
        this.cladding = cladding;
        this.placement = placement;
        this.width = width;
        this.length = length;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public Material getCladding() {
        return cladding;
    }

    public void setCladding(Material cladding) {
        this.cladding = cladding;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
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
}
