package com.team.sorting.model;

/**
 * Represents a barrel entity with volume, stored material, and construction material.
 * 
 * This class provides a model for storing and managing barrel data with
 * appropriate getters and setters for all properties.
 */
public class Barrel {

    /**
     * Enumeration of materials that can be stored in barrels.
     */
    public enum StoredMaterial {
        WATER, OIL, WINE, BEER, HONEY
    }

    /**
     * Enumeration of materials used to construct barrels.
     */
    public enum Material {
        WOOD, METAL, PLASTIC
    }

    /**
     * The volume of the barrel in liters.
     */
    private int volume;
    
    /**
     * The material stored inside the barrel.
     */
    private StoredMaterial storedMaterial;
    
    /**
     * The material used to construct the barrel.
     */
    private Material material;

    /**
     * Gets the volume of the barrel.
     *
     * @return The volume of the barrel in liters.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Gets the material stored inside the barrel.
     *
     * @return The stored material in the barrel.
     */
    public StoredMaterial getStoredMaterial() {
        return storedMaterial;
    }

    /**
     * Gets the material used to construct the barrel.
     *
     * @return The construction material of the barrel.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the volume of the barrel.
     *
     * @param volume The volume to set for the barrel in liters.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Sets the material stored inside the barrel.
     *
     * @param storedMaterial The material to store in the barrel.
     */
    public void setStoredMaterial(StoredMaterial storedMaterial) {
        this.storedMaterial = storedMaterial;
    }

    /**
     * Sets the material used to construct the barrel.
     *
     * @param material The construction material to set for the barrel.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Returns a string representation of the barrel in Russian.
     * The format includes volume, stored material, and construction material.
     *
     * @return A string representation of the barrel.
     */
    @Override
    public String toString() {
        return "Бочка: " +
                "объём " + volume + "л, " +
                (storedMaterial != null ? "содержит " + storedMaterial + ", " : "") +
                (material != null ? "материал изготовителя " + material : "");
    }
}
