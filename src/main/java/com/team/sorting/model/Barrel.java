package com.team.sorting.model;

public class Barrel {

    public enum StoredMaterial {
        WATER, OIL, WINE, BEER, HONEY
    }

    public enum Material {
        WOOD, METAL, PLASTIC
    }

    private int volume; // объём в литрах
    private StoredMaterial storedMaterial;
    private Material material;

    public int getVolume() {
        return volume;
    }

    public StoredMaterial getStoredMaterial() {
        return storedMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setStoredMaterial(StoredMaterial storedMaterial) {
        this.storedMaterial = storedMaterial;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Бочка: " +
                "объём " + volume + "л, " +
                (storedMaterial != null ? "содержит " + storedMaterial + ", " : "") +
                (material != null ? "материал изготовителя " + material : "");
    }
}
