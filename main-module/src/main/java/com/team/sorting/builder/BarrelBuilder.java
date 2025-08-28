package com.team.sorting.builder;

import com.team.sorting.model.Barrel;

public class BarrelBuilder {

    private final Barrel barrel;

    public BarrelBuilder(Barrel barrel) {
        this.barrel = barrel;
    }

    public BarrelBuilder volume(int volume) {
        barrel.setVolume(volume);
        return this;
    }

    public BarrelBuilder storedWater() {
        barrel.setStoredMaterial(Barrel.StoredMaterial.WATER);
        return this;
    }

    public BarrelBuilder storedOil() {
        barrel.setStoredMaterial(Barrel.StoredMaterial.OIL);
        return this;
    }

    public BarrelBuilder storedWine() {
        barrel.setStoredMaterial(Barrel.StoredMaterial.WINE);
        return this;
    }

    public BarrelBuilder storedBeer() {
        barrel.setStoredMaterial(Barrel.StoredMaterial.BEER);
        return this;
    }

    public BarrelBuilder storedHoney() {
        barrel.setStoredMaterial(Barrel.StoredMaterial.HONEY);
        return this;
    }

    public BarrelBuilder materialWood() {
        barrel.setMaterial(Barrel.Material.WOOD);
        return this;
    }

    public BarrelBuilder materialMetal() {
        barrel.setMaterial(Barrel.Material.METAL);
        return this;
    }

    public BarrelBuilder materialPlastic() {
        barrel.setMaterial(Barrel.Material.PLASTIC);
        return this;
    }

    public Barrel build() {
        return barrel;
    }
}
