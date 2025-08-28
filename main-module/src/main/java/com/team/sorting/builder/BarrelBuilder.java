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

    public BarrelBuilder storedMaterial(Barrel.StoredMaterial storedMaterial) {
        barrel.setStoredMaterial(storedMaterial);
        return this;
    }

    public BarrelBuilder material(Barrel.Material material) {
        barrel.setMaterial(material);
        return this;
    }

    public Barrel build() {
        return barrel;
    }
}
